package com.coderdream.easyexcelpractise.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.easyexcelpractise.Result;
import com.coderdream.easyexcelpractise.client.*;
import com.coderdream.easyexcelpractise.data.os.BasicAttrData;
import com.coderdream.easyexcelpractise.data.os.DataAttrData;
import com.coderdream.easyexcelpractise.data.os.ObjectInstanceData;
import com.keepsoft.microservice.dto.*;
import com.keepsoft.microservice.dto.os.AttrTypeDto;
import com.keepsoft.microservice.dto.os.ObjectInstanceDto;
import com.keepsoft.microservice.dto.os.ObjectMetaReqDto;
import com.keepsoft.microservice.dto.os.ObjectMetaRespDto;
import com.coderdream.easyexcelpractise.entity.*;
import com.coderdream.easyexcelpractise.enums.SheetNameEnum;
import com.coderdream.easyexcelpractise.listener.os.BasicAttrDataListener;
import com.coderdream.easyexcelpractise.listener.os.DataAttrDataListener;
import com.coderdream.easyexcelpractise.listener.os.ObjectInstanceDataListener;
import com.coderdream.easyexcelpractise.mapper.AttrInfoMapper;
import com.coderdream.easyexcelpractise.mapper.IdMapMapper;
import com.coderdream.easyexcelpractise.service.MetaImportService;
import com.coderdream.easyexcelpractise.utils.CodeUtils;
import com.coderdream.easyexcelpractise.utils.FileUtils;
import com.coderdream.easyexcelpractise.utils.PinyinUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

@Service
@Slf4j
public class MetaImportServiceImpl implements MetaImportService {

    @Resource
    private AttrInfoMapper attrInfoMapper;

    @Resource
    private IdMapMapper idMapMapper;

    @Resource
    private ObjectTypeMetaServiceClient objectTypeServiceClient;

    @Resource
    private ObjectMetaServiceClient objectServiceClient;

    @Resource
    private AttrTypeMetaServiceClient attrTypeServiceClient;

    @Resource
    private AttrMetaServiceClient attrServiceClient;

    @Resource
    private AttrItemMetaServiceClient attrItemServiceClient;

    @Resource
    private ObjectBStringServiceClient objectBStringServiceClient;

    @Resource
    private IpMapServiceClient ipMapServiceClient;

    @Resource
    private ObjectAttrItemIndexServiceClient objectAttrItemIndexServiceClient;

    private static final String OBJECT_CODE = "0000";

    /**
     * 查询属性元数据列表
     *
     * @param fileName
     * @param sheetName
     * @param objectTypeCode
     * @return 操作结果
     */
    @Override
    public void processFile(String fileName, String sheetName, String objectTypeCode) {
        List<Object> result;
        if (sheetName.equals("基础属性")) {
            result = EasyExcel.read(fileName, BasicAttrData.class,
                    new BasicAttrDataListener()).sheet(sheetName).doReadSync();

            processAttrTypeAndAttr(objectTypeCode, "B", result);
        } else if (sheetName.equals("数据属性")) {
            result = EasyExcel.read(fileName, DataAttrData.class,
                    new DataAttrDataListener()).sheet(sheetName).doReadSync(); //

            processAttrTypeAndAttr(objectTypeCode, "D", result);
        } else if (sheetName.equals("对象实例")) {
            List<ObjectInstanceData> resultObjectInstance = EasyExcel.read(fileName, ObjectInstanceData.class,
                    new ObjectInstanceDataListener()).sheet(sheetName).doReadSync(); // 对象实例
            Boolean addAttrItemFlag = true; // 不添加属性条目 true false
            processObjectInstance("B", objectTypeCode, resultObjectInstance, addAttrItemFlag);
        }

        String shortFileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.lastIndexOf("."));
        System.out.println(shortFileName);
    }

    /**
     * 处理属性类型和属性
     *
     * @param objectTypeCode
     * @param structureTypeCode
     * @param result
     */
    private void processAttrTypeAndAttr(String objectTypeCode, String structureTypeCode, List<Object> result) {
        String attrTypeCode = "";
        AttrTypeMetaReqDto attrTypeMetaReqDto;
        List<AttrTypeDto> list = transferToAttrTypeDtoList(result);
        List<BasicAttrDto> attrDtoList;//属性列表
        Integer commonFlag = 0;
        // 新增属性类型
        for (AttrTypeDto attrTypeDto : list) {
            String attrTypeFullCode = "";
            String objectTypeFullCode = objectTypeCode + " 0000 0 00 00 00"; // 04 0000 0 00 00 00

            String attrTypeName = attrTypeDto.getName();
            if (attrTypeName == null || attrTypeName.trim().equals("")) {
                System.out.println("##### 属性类型名称不能为空");
                break;
            }
            // 1. 根据属性类型名称获取属性类型编码
            if (attrTypeDto.getCommonFlag() != null && attrTypeDto.getCommonFlag().trim().equals("公共")) {
                commonFlag = 1;
            }

            attrTypeMetaReqDto = new AttrTypeMetaReqDto();
            // 如果是公共属性
//                if(commonFlag == 1) {
            // 查询公共属性是否存在 3-提交时需要校验重复性：
            //   3.1-公共属性在该属性类型下，简码不可重复；例：D 01 01 只能有一个。
            //   3.2-非公共属性 的全码不可重复。例：01 0000 D 01 01 00 只能有一个。
            AttrTypeMetaReqDto attrTypeMetaReqDtoQueryForSelfCommon = new AttrTypeMetaReqDto();
            attrTypeMetaReqDtoQueryForSelfCommon.setStructureTypeCode(structureTypeCode);
            attrTypeMetaReqDtoQueryForSelfCommon.setObjectTypeFullCode(objectTypeFullCode); // 属性类型编码 查看自己类型下的
            attrTypeMetaReqDtoQueryForSelfCommon.setCommonFlag(commonFlag);
            attrTypeMetaReqDtoQueryForSelfCommon.setAttrTypeName(attrTypeName); //
            AttrTypeMetaRespDto selfCommonAttrTypeMetaRespDto = existAttrType(attrTypeMetaReqDtoQueryForSelfCommon); // 根据【对象类型全码+结构类型+属性类型名称】判断属性类型是否存在
            // 如果自己的公共属性类型不存在，则新增
            if (selfCommonAttrTypeMetaRespDto == null) {
                // 相同结构类型
                AttrTypeMetaReqDto attrTypeMetaReqDtoQueryForCommon = new AttrTypeMetaReqDto();
                attrTypeMetaReqDtoQueryForCommon.setStructureTypeCode(structureTypeCode);
                attrTypeMetaReqDtoQueryForCommon.setCommonFlag(commonFlag);
                attrTypeMetaReqDtoQueryForCommon.setAttrTypeName(attrTypeName);
                // 如果是公共属性，继续找其他结构类型下的相同属性名称的属性代码
                if (commonFlag == 1) {
                    AttrTypeMetaRespDto commonAttrTypeMetaRespDto = existAttrType(attrTypeMetaReqDtoQueryForCommon); // 根据【对象类型全码+结构类型+属性类型名称】判断属性类型是否存在
                    // 不存在公共属性
                    if (commonAttrTypeMetaRespDto == null) {
                        // 根据 属性类型全码、属性名称 生成新的属性编码
                        AttrTypeMetaReqDto attrTypeMetaReqDtoQueryForNewCode = new AttrTypeMetaReqDto();
                        attrTypeMetaReqDtoQueryForNewCode.setStructureTypeCode(structureTypeCode);
                        attrTypeMetaReqDtoQueryForNewCode.setObjectTypeFullCode(objectTypeFullCode);
                        attrTypeMetaReqDtoQueryForNewCode.setCommonFlag(commonFlag);
                        attrTypeCode = genAttrTypeCode(attrTypeMetaReqDtoQueryForNewCode); // 生成新的属性类型编码
                        // 获取新的属性类型编码失败
                        if (StrUtil.isBlank(attrTypeCode)) {
                            System.out.println("##### 获取新的属性类型编码失败");
                            break;
                        }
                    }
                    // 存在，则直接使用已有的属性编码
                    else {
                        attrTypeCode = commonAttrTypeMetaRespDto.getAttrTypeCode(); // 生成新的属性类型编码
                    }
                }
                //
                else {
                    // 根据 属性类型全码、属性名称 生成新的属性编码编码 私有公共属性
                    AttrTypeMetaReqDto attrTypeMetaReqDtoQueryForNewCode = new AttrTypeMetaReqDto();
                    attrTypeMetaReqDtoQueryForNewCode.setStructureTypeCode(structureTypeCode);
                    attrTypeMetaReqDtoQueryForNewCode.setObjectTypeFullCode(objectTypeFullCode);
                    if (StrUtil.isEmpty(structureTypeCode) || StrUtil.isEmpty(objectTypeFullCode)) {
                        System.out.println("##### 查询条件不能有空值");
                    }
                    attrTypeMetaReqDtoQueryForNewCode.setCommonFlag(commonFlag);
                    attrTypeCode = genAttrTypeCode(attrTypeMetaReqDtoQueryForNewCode); // 生成新的属性类型编码
                    // 获取新的属性类型编码失败
                    if (StrUtil.isBlank(attrTypeCode)) {
                        System.out.println("##### 获取新的属性编码失败");
                        break;
                    }
                }

                attrTypeMetaReqDto.setAttrTypeName(attrTypeName); // 属性类型名称
                String attrTypeLabel = attrTypeDto.getLabel();
                if (StrUtil.isEmpty(attrTypeLabel)) {
                    attrTypeLabel = getLabel(attrTypeName);
                    attrTypeMetaReqDto.setAttrTypeLabel(attrTypeLabel); // 属性编码
                }
                attrTypeMetaReqDto.setAttrTypeCode(attrTypeCode); // 属性编码
                attrTypeFullCode = objectTypeCode + " " + OBJECT_CODE + " " + structureTypeCode + " " + attrTypeCode + " 00 00";
                attrTypeMetaReqDto.setAttrTypeFullCode(attrTypeFullCode); // 属性编码


                attrTypeMetaReqDto.setCommonFlag(commonFlag); // 公共标识
                Date operateTime = new Date();
                attrTypeMetaReqDto.setUpdateTime(operateTime); // 修改时间
                attrTypeMetaReqDto.setCreateTime(operateTime); // 创建时间
                attrTypeMetaReqDto.setRemark(attrTypeDto.getRemark()); // 属性描述
                attrTypeMetaReqDto.setAttrTypeFullCode(attrTypeFullCode);
                attrTypeMetaReqDto.setStructureTypeCode(structureTypeCode);
                attrTypeMetaReqDto.setObjectTypeFullCode(objectTypeFullCode);

                if (StrUtil.isEmpty(attrTypeMetaReqDto.getAttrTypeCode())) {
                    System.out.println("##### 属性类型编码不能为空，不能新增属性！");
                } else {
                    // 新增属性类型
                    Result resultAddAttrTypeMeta = attrTypeServiceClient.add(attrTypeMetaReqDto);
                    System.out.println(resultAddAttrTypeMeta);
                }
            }
            // 否则跳过新增
            else {
                attrTypeFullCode = selfCommonAttrTypeMetaRespDto.getAttrTypeFullCode(); // 设置属性类型编码

                if (StrUtil.isEmpty(attrTypeFullCode)) {
                    System.out.println("##### 属性类型全码不能为空！");
                    break;
                }
                String[] codeArray = attrTypeFullCode.split(" ");
                attrTypeCode = codeArray[3]; // 设置属性类型编码
                System.out.println("##### 属性已存在，不需要新增！");
            }

            // 继续处理【属性】列表
            attrDtoList = attrTypeDto.getAttrDtoList();
            processAttrList(attrDtoList, objectTypeCode, structureTypeCode, attrTypeCode, commonFlag, attrTypeFullCode, objectTypeFullCode);
        }
    }

    private void processAttrList(List<BasicAttrDto> attrDtoList, String objectTypeCode, String structureTypeCode,
                                 String attrTypeCode, Integer commonFlag, String attrTypeFullCode, String objectTypeFullCode) {
        // 如果是公共属性，则查询是否已建立其他对象类型+相同结构类型的属性类型
        // 检查属性类型全码是否为空
        if (StrUtil.isBlank(attrTypeFullCode)) {
            System.out.println("##### 属性类型全码为空，不能新增属性");
            return;
        }

        AttrMetaReqDto attrMetaReqDto;
        String attrCode = ""; // 属性编码
        String attrFullCode = ""; // 属性全码
        // 如果列表不为空
        if (!CollectionUtils.isEmpty(attrDtoList)) {
            for (BasicAttrDto attrDto : attrDtoList) {
                String attrName = attrDto.getName();
                if (attrName == null || attrName.trim().equals("")) {
                    System.out.println("##### 属性名称不能为空");
                    break;
                }
                attrMetaReqDto = new AttrMetaReqDto();
                // 如果是公共属性
//                if(commonFlag == 1) {
                // 查询公共属性是否存在 3-提交时需要校验重复性：
                //   3.1-公共属性在该属性类型下，简码不可重复；例：D 01 01 只能有一个。
                //   3.2-非公共属性 的全码不可重复。例：01 0000 D 01 01 00 只能有一个。
                AttrMetaReqDto attrMetaReqDtoQueryForSelfCommon = new AttrMetaReqDto();
                attrMetaReqDtoQueryForSelfCommon.setStructureTypeCode(structureTypeCode);
                attrMetaReqDtoQueryForSelfCommon.setObjectTypeFullCode(objectTypeFullCode); // 对象类型编码 查看自己类型下的
                attrMetaReqDtoQueryForSelfCommon.setAttrTypeFullCode(attrTypeFullCode); // 属性类型全码 查看自己类型下的
                attrMetaReqDtoQueryForSelfCommon.setCommonFlag(commonFlag);
                attrMetaReqDtoQueryForSelfCommon.setAttrName(attrName); // TODO
                AttrMetaRespDto selfCommonAttrMetaRespDto = existAttr(attrMetaReqDtoQueryForSelfCommon); // 根据【对象类型全码+结构类型+属性类型名称】判断属性类型是否存在
                // 如果自己的公共属性不存在，则新增
                if (selfCommonAttrMetaRespDto == null) {
                    // 相同结构类型
                    AttrMetaReqDto attrMetaReqDtoQueryForCommon = new AttrMetaReqDto();
                    attrMetaReqDtoQueryForCommon.setStructureTypeCode(structureTypeCode);
                    attrMetaReqDtoQueryForCommon.setCommonFlag(commonFlag);
                    attrMetaReqDtoQueryForCommon.setAttrName(attrName);
                    // 如果是公共属性，继续找其他结构类型下的相同属性名称的属性代码
                    if (commonFlag == 1) {
                        AttrMetaRespDto commonAttrMetaRespDto = existAttr(attrMetaReqDtoQueryForCommon); // 根据【对象类型全码+结构类型+属性类型名称】判断属性类型是否存在
                        // 不存在公共属性
                        if (commonAttrMetaRespDto == null) {
                            // 根据 属性类型全码、属性名称 生成新的属性编码
                            AttrMetaReqDto attrMetaReqDtoQueryForNewCode = new AttrMetaReqDto();
                            attrMetaReqDtoQueryForNewCode.setStructureTypeCode(structureTypeCode);
                            attrMetaReqDtoQueryForNewCode.setAttrTypeCode(attrTypeCode);
                            attrMetaReqDtoQueryForNewCode.setCommonFlag(commonFlag);
                            attrCode = genAttrCode(attrMetaReqDtoQueryForNewCode); // 生成新的属性类型编码
                            // 获取新的属性类型编码失败
                            if (StrUtil.isBlank(attrCode)) {
                                System.out.println("##### 获取新的属性编码失败");
                                break;
                            }
                        }
                        // 存在，则直接使用已有的属性编码
                        else {
                            attrCode = commonAttrMetaRespDto.getAttrCode(); // 生成新的属性类型编码
                        }
                    } else {
                        // 根据 属性类型全码、属性名称 生成新的属性编码
                        AttrMetaReqDto attrMetaReqDtoQueryForNewCode = new AttrMetaReqDto();
                        attrMetaReqDtoQueryForNewCode.setStructureTypeCode(structureTypeCode);
                        attrMetaReqDtoQueryForNewCode.setObjectTypeCode(objectTypeCode);
                        attrMetaReqDtoQueryForNewCode.setAttrTypeCode(attrTypeCode);
                        if (StrUtil.isEmpty(structureTypeCode) || StrUtil.isEmpty(objectTypeCode) || StrUtil.isEmpty(attrTypeCode)) {
                            System.out.println("##### 查询条件不能有空值");
                        }
                        attrMetaReqDtoQueryForNewCode.setCommonFlag(commonFlag);
                        attrCode = genAttrCode(attrMetaReqDtoQueryForNewCode); // 生成新的属性类型编码
                        // 获取新的属性类型编码失败
                        if (StrUtil.isBlank(attrCode)) {
                            System.out.println("##### 获取新的属性编码失败");
                            break;
                        }
                    }

                    attrMetaReqDto.setAttrName(attrName); // 属性类型名称
                    String attrLabel = attrDto.getLabel();
                    if (StrUtil.isEmpty(attrLabel)) {
                        attrLabel = getLabel(attrName);
                        attrMetaReqDto.setAttrLabel(attrLabel); // 属性编码
                    }
                    attrMetaReqDto.setAttrCode(attrCode); // 属性编码
                    attrFullCode = objectTypeCode + " " + OBJECT_CODE + " " + structureTypeCode + " " + attrTypeCode + " " + attrCode + " 00";
                    attrMetaReqDto.setAttrFullCode(attrFullCode); // 属性编码
                    // TODO 数据类型
                    String dataType = attrDto.getDataType();
                    if (StrUtil.isEmpty(dataType)) {
                        dataType = "string";
                    } else {
                        switch (dataType) {
                            case "string":
                            case "point":
                            case "double":
                            case "datetime":
                                dataType = "string";
                                break;
                            case "table":
                            case "curve":
                                dataType = "curve";
                                break;
                            default:
                                dataType = "curve";
                                break;
                        }
                    }
                    attrMetaReqDto.setDataType(dataType);

                    attrMetaReqDto.setCommonFlag(commonFlag); // 公共标识
                    Date operateTime = new Date();
                    attrMetaReqDto.setUpdateTime(operateTime); // 修改时间
                    attrMetaReqDto.setCreateTime(operateTime); // 创建时间
                    attrMetaReqDto.setRemark(attrDto.getRemark()); // 属性描述
                    attrMetaReqDto.setAttrTypeFullCode(attrTypeFullCode);

                    if (StrUtil.isEmpty(attrMetaReqDto.getAttrCode())) {
                        System.out.println("##### 属性编码不能为空，不能新增属性！");
                    } else {
                        // 新增属性类型  同一类型-类型结构-属性类型下属性名称不可重复！
                        Result resultAddAttrMeta = attrServiceClient.add(attrMetaReqDto);
                        System.out.println(resultAddAttrMeta);
                    }
                }
                // 否则跳过新增
                else {
                    System.out.println("##### 属性已存在，不需要新增！");
                }
//                }
//                // 处理私有类型
//                else {
//                    System.out.println("##### 属性已存在，不需要新增！");
//                }
            }
        }
    }

    @Nullable
    private String genAttrCodeString(Integer commonFlag, String attrTypeFullCode) {
        String attrCode;
        // 根据 属性类型全码、属性名称 生成新的属性编码
        AttrMetaReqDto attrMetaReqDtoQueryForNewCode = new AttrMetaReqDto();
        attrMetaReqDtoQueryForNewCode.setAttrTypeFullCode(attrTypeFullCode);
//        attrMetaReqDtoQueryForNewCode.setAttrName(attrName);
        attrMetaReqDtoQueryForNewCode.setCommonFlag(commonFlag);
        attrCode = genAttrCode(attrMetaReqDtoQueryForNewCode); // 生成新的属性类型编码
        // 获取新的属性类型编码失败
        if (StrUtil.isBlank(attrCode)) {
            System.out.println("##### 获取新的属性编码失败");
            return null;
        }
        return attrCode;
    }

    /**
     * @param objectTypeCode
     * @param result
     * @param addAttrItemFlag 是否需要录入基础属性的属性条目
     */
    private void processObjectInstance(String structureTypeCode, String objectTypeCode, List<ObjectInstanceData> result, Boolean addAttrItemFlag) {
        List<ObjectInstanceDto> list = transferToObjectInstanceDtoList(result, objectTypeCode);
        List<ObjectMetaEntity> objectMetaEntities = new ArrayList<>();
        ObjectMetaEntity objectMetaEntity;
        ObjectMetaReqDto objectMetaReqDto;
        String objectCode = "";
        String objectFullCode = "";
        List<ObjectBStringEntity> objectBStringEntities = new ArrayList<>();
        List<ObjectAttrItemIndexEntity> entities = new ArrayList<>();
        List<AttrItemMetaEntity> attrItemMetaEntities = new ArrayList<>();
        // 新增对象
        for (ObjectInstanceDto objectInstanceDto : list) {

            String objectName = objectInstanceDto.getObjectName();
            if (objectName == null || objectName.trim().equals("")) {
                System.out.println("##### 对象名称不能为空");
                break;
            } else {
                objectName = objectName.trim();
            }
            // 1. 根据属性类型名称获取属性类型编码
            ObjectMetaReqDto objectMetaReqDtoQuery = new ObjectMetaReqDto();
            objectMetaReqDtoQuery.setObjectName(objectInstanceDto.getObjectName());
            String objectTypeFullCode = objectTypeCode + " 0000 0 00 00 00"; // 04 0000 0 00 00 00
            objectMetaReqDtoQuery.setObjectTypeFullCode(objectTypeFullCode);// 设置对象类型全码
            ObjectMetaRespDto objectMetaRespDto = existObjectInstance(objectMetaReqDtoQuery); // 根据【对象类型全码】判断对象实例是否存在
            // 如果不存在，则新增
            if (objectMetaRespDto == null) {
                objectMetaReqDto = new ObjectMetaReqDto();
                objectMetaReqDto.setObjectTypeFullCode(objectTypeFullCode);// 设置对象类型全码
                objectMetaReqDto.setObjectName(objectName); // 设置对象名称
                objectCode = genObjectCode(objectMetaReqDtoQuery); // 自动生成对象编码
                // 如果没有创建过这个公共属性类型，则获取新的属性类型编码
                if (StrUtil.isEmpty(objectCode)) {
                    System.out.println("##### 自动生成新的属性类型编码失败，不能新增！");
                    break;
                } else {
                    objectMetaReqDto.setObjectCode(objectCode);// 设置对象编码
                    objectFullCode = objectTypeCode + " " + objectCode + " 0 00 00 00";
                    objectMetaReqDto.setObjectFullCode(objectFullCode);// 设置对象全码
                }

                String objectLabel = objectInstanceDto.getObjectLabel();
                if (StrUtil.isEmpty(objectLabel)) {
                    objectMetaReqDto.setObjectLabel(getLabel(objectName)); // 对象标识
                }

                Date operateTime = new Date();
                objectMetaReqDto.setUpdateTime(operateTime); // 修改时间
                objectMetaReqDto.setCreateTime(operateTime); // 创建时间
                objectMetaReqDto.setRemark(objectInstanceDto.getRemark()); // 对象描述

                if (StrUtil.isNotEmpty(objectInstanceDto.getSpaceLevel())) {

                    objectMetaReqDto.setSpaceLevel(objectInstanceDto.getSpaceLevel()); // 对象的空间维度等级
                } else {
                    System.out.println("####对象的空间维度等级为空，");
                    objectMetaReqDto.setSpaceLevel("L1"); // 对象的空间维度等级
                }

                // 新增对象实例
                Result resultAddObjectMeta = objectServiceClient.add(objectMetaReqDto);
                System.out.println(resultAddObjectMeta);
            }
            // 否则跳过新增
            else {
                // 更新实例
                System.out.println("##### 对象实例已存在，不需要新增！");
                objectMetaEntity = new ObjectMetaEntity();
                BeanUtils.copyProperties(objectMetaRespDto, objectMetaEntity);
                objectMetaEntity.setSpaceLevel(objectInstanceDto.getSpaceLevel());
                //BeanUtils.copyProperties(objectInstanceDto, objectMetaEntity);
                objectMetaEntities.add(objectMetaEntity);

                objectCode = objectMetaRespDto.getObjectCode();
                objectFullCode = objectMetaRespDto.getObjectFullCode();
            }
            // 是否自动添加属性条目
            if (addAttrItemFlag) {
                attrItemMetaEntities.addAll(autoInsertAttrItem(structureTypeCode, objectTypeCode, objectCode, objectFullCode, objectTypeFullCode));
            }
            // 是否处理【经度】、【维度】和【空间维度】三个值
            // 查找属性条目 【对象全码】+【名称】+【属性全码】
            // 基础属性、对象类型、标识信息、属性名称（）
            List<String> names = new ArrayList<>();
            names.add("名称");
            names.add("经度");
            names.add("纬度");
            names.add("空间维度");
            String attrTypeName = "标识信息";
            AttrMetaReqDto attrMetaReqDto = new AttrMetaReqDto();
            attrMetaReqDto.setAttrNames(names);
            attrMetaReqDto.setAttrTypeName(attrTypeName);
            attrMetaReqDto.setObjectTypeFullCode(objectTypeFullCode);
            attrMetaReqDto.setStructureTypeCode(structureTypeCode);

            Result<List<AttrMetaRespDto>> attrResult = objectTypeServiceClient.listAllAttrs(attrMetaReqDto);

            System.out.println(attrResult.getResult());
            if (result != null && !CollectionUtils.isEmpty(attrResult.getResult())) {
                List<AttrMetaRespDto> attrMetaRespDtoList = attrResult.getResult();
                String attrFullCodeName = ""; // 名称
                String attrFullCodeLongitude = ""; // 经度
                String attrFullCodeLatitude = "";// 纬度
                String attrFullCodeSpaceLevel = "";// 空间维度
                for (AttrMetaRespDto attrMetaRespDto : attrMetaRespDtoList) {
                    if ("名称".equals(attrMetaRespDto.getAttrName())) {
                        attrFullCodeName = attrMetaRespDto.getAttrFullCode();
                        continue;
                    }
                    if ("经度".equals(attrMetaRespDto.getAttrName())) {
                        attrFullCodeLongitude = attrMetaRespDto.getAttrFullCode();
                        continue;
                    }
                    if ("纬度".equals(attrMetaRespDto.getAttrName())) {
                        attrFullCodeLatitude = attrMetaRespDto.getAttrFullCode();
                        continue;
                    }
                    if ("空间维度".equals(attrMetaRespDto.getAttrName())) {
                        attrFullCodeSpaceLevel = attrMetaRespDto.getAttrFullCode();
                        continue;
                    }
                }

                String dataType = "string";
                //名称
                String nameValue = objectInstanceDto.getObjectName();
                if (StrUtil.isNotEmpty(nameValue) && StrUtil.isNotEmpty(attrFullCodeName)) {
                    ObjectBStringEntity objectBStringEntity = new ObjectBStringEntity();
                    objectBStringEntity.setAttrItemFullCode(genAttrItemFullCode(objectCode, attrFullCodeName));
                    objectBStringEntity.setValue(nameValue);
                    objectBStringEntities.add(objectBStringEntity);

                    ObjectAttrItemIndexEntity objectAttrItemIndexEntity = new ObjectAttrItemIndexEntity();
                    objectAttrItemIndexEntity.setAttrItemFullCode(genAttrItemFullCode(objectCode, attrFullCodeName));
                    objectAttrItemIndexEntity.setObjectFullCode(objectFullCode);
                    objectAttrItemIndexEntity.setDataType(dataType);
                    entities.add(objectAttrItemIndexEntity);
                }
                //经度
                String longitudeValue = objectInstanceDto.getLongitude();
                if (StrUtil.isNotEmpty(longitudeValue) && StrUtil.isNotEmpty(attrFullCodeLongitude)) {
                    ObjectBStringEntity objectBStringEntity = new ObjectBStringEntity();
                    objectBStringEntity.setAttrItemFullCode(genAttrItemFullCode(objectCode, attrFullCodeLongitude));
                    objectBStringEntity.setValue(longitudeValue);
                    objectBStringEntities.add(objectBStringEntity);

                    ObjectAttrItemIndexEntity objectAttrItemIndexEntity = new ObjectAttrItemIndexEntity();
                    objectAttrItemIndexEntity.setAttrItemFullCode(genAttrItemFullCode(objectCode, attrFullCodeLongitude));
                    objectAttrItemIndexEntity.setObjectFullCode(objectFullCode);
                    objectAttrItemIndexEntity.setDataType(dataType);
                    entities.add(objectAttrItemIndexEntity);
                }

                //纬度
                String latitudeValue = objectInstanceDto.getLatitude();
                if (StrUtil.isNotEmpty(latitudeValue) && StrUtil.isNotEmpty(attrFullCodeLatitude)) {
                    ObjectBStringEntity objectBStringEntity = new ObjectBStringEntity();
                    objectBStringEntity.setAttrItemFullCode(genAttrItemFullCode(objectCode, attrFullCodeLatitude));
                    objectBStringEntity.setValue(latitudeValue);
                    objectBStringEntities.add(objectBStringEntity);

                    ObjectAttrItemIndexEntity objectAttrItemIndexEntity = new ObjectAttrItemIndexEntity();
                    objectAttrItemIndexEntity.setAttrItemFullCode(genAttrItemFullCode(objectCode, attrFullCodeLatitude));
                    objectAttrItemIndexEntity.setObjectFullCode(objectFullCode);
                    objectAttrItemIndexEntity.setDataType(dataType);
                    entities.add(objectAttrItemIndexEntity);
                }

                //空间维度
                String spaceLevelValue = objectInstanceDto.getSpaceLevel();
                if (StrUtil.isNotEmpty(spaceLevelValue) && StrUtil.isNotEmpty(attrFullCodeSpaceLevel)) {
                    ObjectBStringEntity objectBStringEntity = new ObjectBStringEntity();
                    objectBStringEntity.setAttrItemFullCode(genAttrItemFullCode(objectCode, attrFullCodeSpaceLevel));
                    objectBStringEntity.setValue(spaceLevelValue);
                    objectBStringEntities.add(objectBStringEntity);

                    ObjectAttrItemIndexEntity objectAttrItemIndexEntity = new ObjectAttrItemIndexEntity();
                    objectAttrItemIndexEntity.setAttrItemFullCode(genAttrItemFullCode(objectCode, attrFullCodeSpaceLevel));
                    objectAttrItemIndexEntity.setObjectFullCode(objectFullCode);
                    objectAttrItemIndexEntity.setDataType(dataType);
                    entities.add(objectAttrItemIndexEntity);
                }
            }
        }

        if(!CollectionUtils.isEmpty(objectMetaEntities)) {
            Result<Integer> objectInsertOrUpdateBatch = objectServiceClient.insertOrUpdateBatch(objectMetaEntities);
            System.out.println(objectInsertOrUpdateBatch);
        }

        Set<String> objectFullCodeSet = new HashSet<>();
        for (AttrItemMetaEntity entity : attrItemMetaEntities) {
            objectFullCodeSet.add(entity.getObjectFullCode());
        }


        // 属性条目列表
        Result<Integer> resultAddAttrItemMeta = attrItemServiceClient.insertOrUpdateBatch(attrItemMetaEntities);
        System.out.println(resultAddAttrItemMeta);

        if (!CollectionUtils.isEmpty(entities) && !CollectionUtils.isEmpty(objectBStringEntities)) {
            // 增加 object_attr_item_index
            Result<Integer> insertObjectAttrItemIndexSize = objectAttrItemIndexServiceClient.insertOrUpdateBatch(entities);
            System.out.println("#### 属性条目值插入成功：" + insertObjectAttrItemIndexSize.getMsg() + insertObjectAttrItemIndexSize.getResult());

            Result<Integer> insertSize = objectBStringServiceClient.insertBatch(objectBStringEntities);
            System.out.println("#### 属性条目值插入成功：" + insertSize.getMsg());
        }

    }

    private String genAttrItemFullCode(String objectCode, String attrFullCode) {
        String[] codeArray = attrFullCode.split(" ");
        return codeArray[0] + " " + objectCode + " " + codeArray[2] + " " + codeArray[3] + " " + codeArray[4] + " " + codeArray[5];
    }

    /**
     * 自动新增基础属性的属性条目 00
     *
     * @param structureTypeCode
     * @param objectTypeCode
     * @param objectCode
     * @param objectFullCode
     * @param objectTypeFullCode
     */
    private List<AttrItemMetaEntity> autoInsertAttrItem(String structureTypeCode, String objectTypeCode, String objectCode, String objectFullCode, String objectTypeFullCode) {
        List<AttrItemMetaEntity> entities = new ArrayList<>(); // 批量插入属性条目
        AttrMetaReqDto attrMetaReqDtoQuery = new AttrMetaReqDto();
        attrMetaReqDtoQuery.setObjectTypeFullCode(objectTypeFullCode);
        attrMetaReqDtoQuery.setStructureTypeCode(structureTypeCode); // 现阶段先只增加基础属性
        Result<List<AttrMetaRespDto>> allAttrListResult = objectTypeServiceClient.listAllAttrs(attrMetaReqDtoQuery);

        String attrItemCode = "";
        String attrItemFullCode = "";
        String[] codeArray = null;
        if (!CollectionUtils.isEmpty(allAttrListResult.getResult()) && allAttrListResult.getResult().size() > 0) {
            List<AttrMetaRespDto> allAttrList = allAttrListResult.getResult();
            String attrTypeCode = "";// 属性类型编码
            String attrCode = "";// 属性编码

            AttrItemMetaEntity entity;
            // 遍历属性列表
            for (AttrMetaRespDto attrMetaRespDto : allAttrList) {
                String attrFullCode = attrMetaRespDto.getAttrFullCode();
                if (StrUtil.isEmpty(attrFullCode)) {
                    System.out.println("#### 属性全码为空，不能新增！");
                }
                codeArray = attrFullCode.split(" ");
                if (codeArray == null || codeArray.length != 6) {
                    System.out.println("#### 属性全码格式不正确，应该为6组编码，格式为【MM MMMM M MM MM MM】，不能新增！");
                } else {
                    attrTypeCode = codeArray[3];
                    attrCode = codeArray[4];
                }
                // TODO 如果属于特征曲线，直接跳过
                if ("04".equals(attrTypeCode)) {
                    continue;
                }
                // 构造属性条目
                AttrItemMetaReqDto attrItemMetaReqDto = new AttrItemMetaReqDto();
                attrItemMetaReqDto.setAttrItemName(attrMetaRespDto.getAttrName());

                // 查找属性条目是否存在 TODO 同一对象类型-对象-结构类型-属性类型-属性-名称下属性条目不可重复！
                AttrItemMetaReqDto attrItemMetaReqDtoQuery = new AttrItemMetaReqDto();
                attrItemMetaReqDtoQuery.setObjectTypeFullCode(objectTypeFullCode);     // 对象类型
                if (StrUtil.isEmpty(objectTypeFullCode)) {
                    System.out.println("##### 对象类型为空，不能新增！");
                }
                attrItemMetaReqDtoQuery.setObjectFullCode(objectFullCode); // 对象
                if (StrUtil.isEmpty(objectTypeFullCode)) {
                    System.out.println("##### 对象类型为空，不能新增！");
                }
//                    attrItemMetaReqDtoQuery.setStructureTypeCode(structureTypeCode); // 结构类型
//                    if(StrUtil.isEmpty(structureTypeCode)) {
//                        System.out.println("##### 结构类型为空，不能新增！");
//                    }
//                    attrItemMetaReqDtoQuery.setAttrTypeFullCode(attrTypeFullCode); // 属性类型全码
//                    if(StrUtil.isEmpty(objectTypeFullCode)) {
//                        System.out.println("##### 对象类型为空，不能新增！");
//                    }
                attrItemMetaReqDtoQuery.setAttrFullCode(attrMetaRespDto.getAttrFullCode()); // 属性全码
                if (StrUtil.isEmpty(attrMetaRespDto.getAttrFullCode())) {
                    System.out.println("##### 属性全码为空，不能新增！");
                }
                attrItemMetaReqDtoQuery.setAttrItemName(attrMetaRespDto.getAttrName()); // 属性条目名称（同属性名称）
                if (StrUtil.isEmpty(attrMetaRespDto.getAttrName())) {
                    System.out.println("##### 属性名称为空，不能新增！");
                }

                attrItemMetaReqDto.setAttrItemName(attrMetaRespDto.getAttrName()); // 属性条目名称
                attrItemMetaReqDto.setAttrItemLabel(attrMetaRespDto.getAttrLabel()); // 属性条目标识

                attrItemCode = "00"; // 默认的属性条目编码为00
                // 构造属性条目全码，默认的
                attrItemFullCode = objectTypeCode + " " + objectCode + " " + structureTypeCode + " " + attrTypeCode + " " + attrCode + " " + attrItemCode;
                attrItemMetaReqDto.setAttrItemCode(attrItemCode); // 属性条目编码
                attrItemMetaReqDto.setAttrItemFullCode(attrItemFullCode); // 属性条目全码
                attrItemMetaReqDto.setObjectFullCode(objectFullCode); // 对象全码
                attrItemMetaReqDto.setAttrFullCode(attrMetaRespDto.getAttrFullCode()); // 属性全码

                Date date = new Date();
                attrItemMetaReqDto.setUpdateTime(date);
                attrItemMetaReqDto.setCreateTime(date);
                attrItemMetaReqDto.setHashRateLevel("collection");// collection 属性的算力级别，分为采集：collection、计算：calc、指标：key、决策：decision；
                attrItemMetaReqDto.setDataSource("info_system"); // 数据来源: info_system:系统来源；model:模型来源；literature: 文献来源；web:网络来源

                entity = new AttrItemMetaEntity();
                // 不查旧值，每次都刷新为新值
                BeanUtils.copyProperties(attrItemMetaReqDto, entity);
                entities.add(entity);

                // 新增属性条目
            }

        }

        return entities;
    }

    private void autoInsertAttrItemValue(String structureTypeCode, String objectTypeCode, String objectCode, String objectFullCode, String objectTypeFullCode) {
        AttrMetaReqDto attrMetaReqDtoQuery = new AttrMetaReqDto();
        attrMetaReqDtoQuery.setObjectTypeFullCode(objectTypeFullCode);
        attrMetaReqDtoQuery.setStructureTypeCode(structureTypeCode); // 现阶段先只增加基础属性
        Result<List<AttrMetaRespDto>> allAttrListResult = objectTypeServiceClient.listAllAttrs(attrMetaReqDtoQuery);

        String attrItemCode = "";
        String attrItemFullCode = "";
        String[] codeArray = null;
        if (!CollectionUtils.isEmpty(allAttrListResult.getResult()) && allAttrListResult.getResult().size() > 0) {
            List<AttrMetaRespDto> allAttrList = allAttrListResult.getResult();
            String attrTypeCode = "";// 属性类型编码
            String attrCode = "";// 属性编码
            // 遍历属性列表
            for (AttrMetaRespDto attrMetaRespDto : allAttrList) {
                String attrFullCode = attrMetaRespDto.getAttrFullCode();
                if (StrUtil.isEmpty(attrFullCode)) {
                    System.out.println("#### 属性全码为空，不能新增！");
                }
                codeArray = attrFullCode.split(" ");
                if (codeArray == null || codeArray.length != 6) {
                    System.out.println("#### 属性全码格式不正确，应该为6组编码，格式为【MM MMMM M MM MM MM】，不能新增！");
                } else {
                    attrTypeCode = codeArray[3];
                    attrCode = codeArray[4];
                }

                // 构造属性条目
                AttrItemMetaReqDto attrItemMetaReqDto = new AttrItemMetaReqDto();
                attrItemMetaReqDto.setAttrItemName(attrMetaRespDto.getAttrName());

                // 查找属性条目是否存在 TODO 同一对象类型-对象-结构类型-属性类型-属性-名称下属性条目不可重复！
                AttrItemMetaReqDto attrItemMetaReqDtoQuery = new AttrItemMetaReqDto();
                attrItemMetaReqDtoQuery.setObjectTypeFullCode(objectTypeFullCode);     // 对象类型
                if (StrUtil.isEmpty(objectTypeFullCode)) {
                    System.out.println("##### 对象类型为空，不能新增！");
                }
                attrItemMetaReqDtoQuery.setObjectFullCode(objectFullCode); // 对象
                if (StrUtil.isEmpty(objectTypeFullCode)) {
                    System.out.println("##### 对象类型为空，不能新增！");
                }

                attrItemMetaReqDtoQuery.setAttrFullCode(attrMetaRespDto.getAttrFullCode()); // 属性全码
                if (StrUtil.isEmpty(attrMetaRespDto.getAttrFullCode())) {
                    System.out.println("##### 属性全码为空，不能新增！");
                }
                attrItemMetaReqDtoQuery.setAttrItemName(attrMetaRespDto.getAttrName()); // 属性条目名称（同属性名称）
                if (StrUtil.isEmpty(attrMetaRespDto.getAttrName())) {
                    System.out.println("##### 属性名称为空，不能新增！");
                }

                AttrItemMetaRespDto existAttrItemFlag = existAttrItem(attrItemMetaReqDtoQuery); // 根据【对象类型全码+结构类型+属性类型名称】判断属性类型是否存在
                // 如果属性条目不存在，则新增
                if (existAttrItemFlag == null) {
                    attrItemMetaReqDto.setAttrItemName(attrMetaRespDto.getAttrName()); // 属性条目名称
                    attrItemMetaReqDto.setAttrItemLabel(attrMetaRespDto.getAttrLabel()); // 属性条目标识

                    // attrItemCode = genAttrItemCode(attrItemMetaReqDtoQuery); // 生成新的属性类型编码
                    attrItemCode = "00"; // 默认的属性条目编码为00
                    // 构造属性条目全码，默认的
                    attrItemFullCode = objectTypeCode + " " + objectCode + " " + structureTypeCode + " " + attrTypeCode + " " + attrCode + " " + attrItemCode;
                    attrItemMetaReqDto.setAttrItemCode(attrItemCode); // 属性条目编码
                    attrItemMetaReqDto.setAttrItemFullCode(attrItemFullCode); // 属性条目全码
                    attrItemMetaReqDto.setObjectFullCode(objectFullCode); // 对象全码
                    attrItemMetaReqDto.setAttrFullCode(attrMetaRespDto.getAttrFullCode()); // 属性全码

                    Date date = new Date();
                    attrItemMetaReqDto.setUpdateTime(date);
                    attrItemMetaReqDto.setCreateTime(date);
                    attrItemMetaReqDto.setHashRateLevel("collection");// collection 属性的算力级别，分为采集：collection、计算：calc、指标：key、决策：decision；
                    attrItemMetaReqDto.setDataSource("info_system"); // 数据来源: info_system:系统来源；model:模型来源；literature: 文献来源；web:网络来源

                    // 新增属性条目
                    Result resultAddAttrItemMeta = attrItemServiceClient.add(attrItemMetaReqDto);
                    System.out.println(resultAddAttrItemMeta);
                }// 否则跳过新增
                else {
                    System.out.println("##### 属性条目已存在，不需要新增！");
                }
            }
        }
    }

    @Override
    public void processFileForFolder(String path, List<String> sheetNameList) {
        List<String> names = FileUtils.fileTraversalNotRecursion(path);
        System.out.println(names.size());
        for (String fileName : names) {
            System.out.println(fileName);
            for (String sheetName : sheetNameList) {
                SheetNameEnum sheetNameEnum = SheetNameEnum.init(sheetName); // 基础属性、数据属性、对象实例
                String shortFileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.lastIndexOf("."));
                String objectTypeName = shortFileName.substring(shortFileName.lastIndexOf("_") + 1);
                objectTypeName = objectTypeName.trim();
//                String structureTypeCode = sheetNameEnum.getLabel();
                String structureTypeName = sheetNameEnum.getName();
                System.out.println(objectTypeName);
                ObjectTypeMetaReqDto objectTypeMetaReqDtoQuery = new ObjectTypeMetaReqDto();
                objectTypeMetaReqDtoQuery.setObjectTypeName(objectTypeName);

                String objectTypeCode = getObjectTypeCode(objectTypeMetaReqDtoQuery); // 获取对象类型编码
                if (StrUtil.isEmpty(objectTypeCode)) {
                    // 新增对象类型
                    ObjectTypeMetaReqDto objectTypeMetaReqDto = new ObjectTypeMetaReqDto();
                    objectTypeMetaReqDto.setObjectTypeName(objectTypeName);
                    objectTypeCode = shortFileName.substring(0, 2); // 从文件名取，不自动生成
                    objectTypeMetaReqDto.setObjectTypeCode(objectTypeCode);
                    objectTypeMetaReqDto.setObjectTypeFullCode(objectTypeCode + " 0000 0 00 00 00"); // 对象类型全码

                    String objectTypeLabel = getLabel(objectTypeName);
                    objectTypeMetaReqDto.setObjectTypeLabel(objectTypeLabel); // 对象类型标识


                    Date operateTime = new Date();
                    objectTypeMetaReqDto.setUpdateTime(operateTime); // 修改时间
                    objectTypeMetaReqDto.setCreateTime(operateTime); // 创建时间

                    // 新增对象类型实例
                    Result resultAddObjectTypeMeta = objectTypeServiceClient.add(objectTypeMetaReqDto);
                    System.out.println(resultAddObjectTypeMeta);
                }

                processFile(fileName, structureTypeName, objectTypeCode);
            }
        }
    }

    @NotNull
    private static String getLabel(String objectTypeName) {
        String objectTypeLabel = PinyinUtils.getPinyin(objectTypeName, "_");
        objectTypeLabel = objectTypeLabel.replaceAll("[^_a-zA-Z]", ""); // 去掉非大小写字母和下划线的字符
        if (objectTypeLabel.length() > 20) {
            objectTypeLabel = objectTypeLabel.substring(0, 20);
        }
        String tempStr = CodeUtils.getFourLetterRandom();
        if (objectTypeLabel.length() > 3) {
            objectTypeLabel = objectTypeLabel.substring(0, objectTypeLabel.length() - 4) + tempStr;
        }

        return objectTypeLabel;
    }

    private String getObjectTypeCode(ObjectTypeMetaReqDto objectTypeMetaReqDto) {
        String objectTypeCode = "";
        Result<Page<ObjectTypeMetaRespDto>> result = objectTypeServiceClient.list(objectTypeMetaReqDto);
        System.out.println(result.getResult().getTotal());
        if (result != null && result.getResult() != null && result.getResult().getTotal() > 0) {
            ObjectTypeMetaRespDto objectTypeMetaRespDto = result.getResult().getRecords().get(0);
            objectTypeCode = objectTypeMetaRespDto.getObjectTypeCode(); // 类型编码
        }
        return objectTypeCode;
    }


    private List<AttrTypeDto> transferToAttrTypeDtoList(List<Object> result) {
        List<AttrTypeDto> list = new ArrayList<>();

        Map<String, List<BasicAttrDto>> attrDtoListMap = new LinkedHashMap<>();
        Map<String, AttrTypeDto> attrTypeDtoMap = new LinkedHashMap<>();
//        AttrTypeDto attrInfoEntity;
        if (!CollectionUtils.isEmpty(result)) {
            AttrTypeDto attrTypeDto; // 属性类型
            AttrTypeDto attrTypeDtoTemp; // 临时属性类型
            List<BasicAttrDto> attrDtoList;
            BasicAttrDto attrDto; // 属性值
            String typeFlag = "attrType";
            String attrTypeName = ""; // 属性类型名称，用于属性类型挂载属性列表
            String attrCategory; // 属性or类型
            String name; // 名称字段
            BasicAttrData basicAttrInfoData;
            DataAttrData baseAttrInfoData;
            BasicAttrData attrInfoData = new BasicAttrData();
            for (Object obj : result) {
                if (obj instanceof BasicAttrData) {
                    basicAttrInfoData = (BasicAttrData) obj;
                    BeanUtils.copyProperties(basicAttrInfoData, attrInfoData);
                } else if (obj instanceof DataAttrData) {
                    baseAttrInfoData = (DataAttrData) obj;
                    BeanUtils.copyProperties(baseAttrInfoData, attrInfoData);
                }

                System.out.println(attrInfoData);
                attrCategory = attrInfoData.getAttrCategory();
                name = attrInfoData.getName();
                // 如果名称字段不为空，说明这行记录有效
                if (name != null && !name.trim().equals("")) {
                    // 如果是属性类型
                    if (typeFlag.equals("attrType")) {

                    }
                    // 字段的值为【属性类型】
                    if (attrCategory != null && !attrCategory.trim().equals("") && attrCategory.trim().equals("属性类型")) {
                        // 如果是属性类型
                        //if (typeFlag.equals("attrType")) {
                        attrTypeDto = new AttrTypeDto();
                        attrTypeDto.setAttrCategory("属性类型"); // 属性or类型
                        attrTypeDto.setName(name); // 名称
                        attrTypeDto.setCode(attrInfoData.getCode()); // 编码
                        attrTypeDto.setLabel(attrInfoData.getLabel()); // 标识
                        attrTypeDto.setDataType(attrInfoData.getDataType()); // 类型
                        attrTypeDto.setCommonFlag(attrInfoData.getCommonFlag()); // 是否公共
                        attrTypeDto.setRemark(attrInfoData.getRemark()); // 描述 描述信息暂时忽略
                        attrTypeDto.setAttrDtoList(new ArrayList<>()); // 属性列表
                        attrTypeName = name; // 设置属性类型名称，用于属性类型挂载属性列表
                        attrTypeDtoMap.put(attrTypeName, attrTypeDto); // 设置Map
                        //  }
                    }
                    // 非【属性类型】则为【属性】
                    else {
                        // 如果是属性
                        //if (typeFlag.equals("attrType")) {
                        attrDto = new BasicAttrDto();
                        attrDto.setAttrCategory("属性值"); // 属性or类型
                        attrDto.setName(name); // 名称
                        attrDto.setCode(attrInfoData.getCode()); // 编码
                        attrDto.setLabel(attrInfoData.getLabel()); // 标识
                        attrDto.setDataType(attrInfoData.getDataType()); // 类型
                        attrDto.setCommonFlag(attrInfoData.getCommonFlag()); // 是否公共

                        String remark = attrInfoData.getRemark();
                        if (StrUtil.isNotEmpty(remark) && -1 != remark.lastIndexOf("作为条目")) {
                            continue;
                        }

                        attrDto.setRemark(attrInfoData.getRemark()); // 描述

                        attrTypeDtoTemp = attrTypeDtoMap.get(attrTypeName);
                        if (attrTypeDtoTemp != null) {
                            attrDtoList = attrTypeDtoTemp.getAttrDtoList();
                            if (CollectionUtils.isEmpty(attrDtoList)) {
                                attrDtoList = new ArrayList<>();
                            }
                            attrDtoList.add(attrDto); // 把属性加入属性列表中
                            attrTypeDtoTemp.setAttrDtoList(attrDtoList); // 设置属性类型的属性列表的值
                            attrTypeDtoMap.put(attrTypeName, attrTypeDtoTemp); // 更新Map
                        }
                        // }
                    }
                }
                // 下一条记录又是属性类型
                else {
                    // 跳过
                    typeFlag = "attrType";
                }
                //
                list = new ArrayList<>(attrTypeDtoMap.values());
            }
        }

        return list;
    }

    private List<ObjectInstanceDto> transferToObjectInstanceDtoList(List<ObjectInstanceData> result, String objectTypeCode) {
        if (StrUtil.isEmpty(objectTypeCode)) {
            System.out.println("##### 对象类型编码不能为空！");
            return null;
        }
        List<ObjectInstanceDto> list = new ArrayList<>();
        String objectTypeFullCode = objectTypeCode + " 0000 0 00 00 00";
        if (!CollectionUtils.isEmpty(result)) {
            ObjectInstanceDto objectInstanceDto; // 对象实例
            String name; // 名称字段
            for (ObjectInstanceData objectInstanceData : result) {
                name = objectInstanceData.getName();
                // 如果名称字段不为空，说明这行记录有效
                if (name != null && !name.trim().equals("")) {
                    // 字段的值为【对象实例】
                    // 如果是对象实例
                    //if (typeFlag.equals("attrType")) {
                    objectInstanceDto = new ObjectInstanceDto();
                    BeanUtils.copyProperties(objectInstanceData, objectInstanceDto);
                    objectInstanceDto.setObjectName(name); // 名称
                    objectInstanceDto.setObjectCode(objectInstanceData.getCode()); // 编码
                    objectInstanceDto.setObjectLabel(objectInstanceData.getLabel()); // 标识
                    String objectFullCode = ""; // 需要构造 TODO
                    objectInstanceDto.setObjectFullCode(objectFullCode); // 类型
                    objectInstanceDto.setObjectTypeCode(objectTypeCode);
                    objectInstanceDto.setObjectTypeFullCode(objectTypeFullCode);
                    Date date = new Date();
                    objectInstanceDto.setUpdateTime(date); // 修改时间
                    objectInstanceDto.setCreateTime(date); // 创建时间
                    objectInstanceDto.setRemark(objectInstanceData.getRemark()); // 描述 描述信息暂时忽略
                    objectInstanceDto.setSpaceLevel(objectInstanceData.getSpaceLevel()); // 对象的空间维度等级
                    list.add(objectInstanceDto);
                }
                // 下一条记录又是对象实例
                else {
                    // 跳过
                }
            }
        }

        return list;
    }


    private String getAttrTypeCode(AttrTypeMetaReqDto attrTypeMetaReqDto) {
        String attrTypeCode = "";
        Result<AttrTypeMetaRespDto> result = attrTypeServiceClient.selectOne(attrTypeMetaReqDto);
        System.out.println(result.getResult());
        if (result != null && result.getResult() != null) {
            AttrTypeMetaRespDto attrTypeMetaRespDto = result.getResult();
            attrTypeCode = attrTypeMetaRespDto.getAttrTypeCode(); // 属性类型编码
        }
        return attrTypeCode;
    }

    private String getAttrCode(AttrMetaReqDto attrMetaReqDto) {
        String attrCode = "";
        Result<AttrMetaRespDto> result = attrServiceClient.selectOne(attrMetaReqDto);
        System.out.println(result.getResult());
        if (result != null && result.getResult() != null) {
            AttrMetaRespDto attrMetaRespDto = result.getResult();
            attrCode = attrMetaRespDto.getAttrCode(); // 属性编码
        }
        return attrCode;
    }

    /**
     * 生成最新属性类型编码
     *
     * @param attrTypeMetaReqDto
     * @return
     */
    private String genAttrTypeCode(AttrTypeMetaReqDto attrTypeMetaReqDto) {
        String attrTypeCode = "";
        AttrMetaReqDto attrMetaReqDto = new AttrMetaReqDto();
        attrMetaReqDto.setStructureTypeCode(attrTypeMetaReqDto.getStructureTypeCode()); //属性类型所属结构类型
        attrMetaReqDto.setObjectTypeFullCode(attrTypeMetaReqDto.getObjectTypeFullCode()); // 对象类型全码
        attrMetaReqDto.setCommonFlag(attrTypeMetaReqDto.getCommonFlag()); // 公共标识
        Result<String> result = attrTypeServiceClient.genCode(attrMetaReqDto);
        System.out.println(result.getResult());
        if (result.getMsg() != null && !result.getMsg().trim().equals("")) {
            attrTypeCode = result.getMsg(); // 属性类型编码
        }
        return attrTypeCode;
    }

    /**
     * 生成最新属性编码
     *
     * @param attrMetaReqDto
     * @return
     */
    private String genAttrCode(AttrMetaReqDto attrMetaReqDto) {
        String attrCode = "";
        Result<String> result = attrServiceClient.genCode(attrMetaReqDto);
        System.out.println(result.getResult());
        if (StrUtil.isNotEmpty(result.getMsg())) {
            attrCode = result.getMsg(); // 属性编码
        }
        return attrCode;
    }

    /**
     * 结构类型+对象类型全码+属性类型名称
     *
     * @param attrTypeMetaReqDto
     * @return
     */
    private AttrTypeMetaRespDto existAttrType(AttrTypeMetaReqDto attrTypeMetaReqDto) {
        AttrTypeMetaRespDto attrTypeMetaRespDto = null;
        Result<AttrTypeMetaRespDto> result = attrTypeServiceClient.selectOne(attrTypeMetaReqDto);
        System.out.println(result.getResult());
        if (result != null && result.getResult() != null) {
            attrTypeMetaRespDto = result.getResult();
        }
        return attrTypeMetaRespDto;
    }

    /**
     * 结构类型+对象类型全码+属性类型名称
     *
     * @param attrMetaReqDto
     * @return
     */
    private AttrMetaRespDto existAttr(AttrMetaReqDto attrMetaReqDto) {
        AttrMetaRespDto attrMetaRespDto = null;
        Result<AttrMetaRespDto> result = attrServiceClient.selectOne(attrMetaReqDto);
        System.out.println(result.getResult());
        if (result != null && result.getResult() != null) {
            attrMetaRespDto = result.getResult();
        }
        return attrMetaRespDto;
    }

    /**
     * 同一对象类型-对象-结构类型-属性类型-属性-名称下属性条目不可重复！
     *
     * @param attrItemMetaReqDto
     * @return
     */
    private AttrItemMetaRespDto existAttrItem(AttrItemMetaReqDto attrItemMetaReqDto) {
        AttrItemMetaRespDto attrItemMetaRespDto = null;
        Result<AttrItemMetaRespDto> result = attrItemServiceClient.selectOne(attrItemMetaReqDto);
        System.out.println(result);
        if (result != null && result.getResult() != null) {
            attrItemMetaRespDto = result.getResult();
        }
        return attrItemMetaRespDto;
    }

    /**
     * 生成最新属性条目编码
     *
     * @param attrItemMetaReqDto
     * @return
     */
    private String genAttrItemCode(AttrItemMetaReqDto attrItemMetaReqDto) {
        String attrCode = "";
        AttrMetaReqDto attrMetaReqDto = new AttrMetaReqDto();
        attrMetaReqDto.setObjectFullCode(attrItemMetaReqDto.getObjectFullCode()); // 对象全码
        attrMetaReqDto.setAttrFullCode(attrItemMetaReqDto.getAttrFullCode()); // 属性全码
        Result<String> result = attrItemServiceClient.genCode(attrMetaReqDto);
        System.out.println(result.getResult());
        if (StrUtil.isNotEmpty(result.getMsg())) {
            attrCode = result.getMsg(); // 属性编码
        }
        return attrCode;
    }

    /**
     * 结构类型+对象类型全码+属性类型名称
     *
     * @param objectMetaReqDto
     * @return
     */
    private ObjectMetaRespDto existObjectInstance(ObjectMetaReqDto objectMetaReqDto) {
        ObjectMetaRespDto objectMetaRespDto = null;
        Result<ObjectMetaRespDto> result = objectServiceClient.selectOne(objectMetaReqDto);
        System.out.println(result.getResult());
        if (result != null && result.getResult() != null) {
            objectMetaRespDto = result.getResult();
        }
        return objectMetaRespDto;
    }

    /**
     * 生成最新属性编码
     *
     * @param objectMetaReqDto
     * @return
     */
    private String genObjectCode(ObjectMetaReqDto objectMetaReqDto) {
        String attrCode = "";
        AttrMetaReqDto attrMetaReqDto = new AttrMetaReqDto();
        attrMetaReqDto.setObjectTypeFullCode(objectMetaReqDto.getObjectTypeFullCode()); // 对象类型全码
        Result<String> result = objectServiceClient.genCode(attrMetaReqDto);
        System.out.println(result.getResult());
        if (StrUtil.isNotEmpty(result.getMsg())) {
            attrCode = result.getMsg(); // 属性编码
        }
        return attrCode;
    }

}
