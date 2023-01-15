package com.coderdream.demo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.coderdream.demo.entity.ObjectMeta;
import com.coderdream.demo.pojo.StStbprpB;
import com.coderdream.demo.dao.StStbprpBMapper;
import com.coderdream.demo.service.StStbprpBService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.demo.utils.ExcelUtil;
import com.coderdream.demo.utils.PinyinUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MoBai·杰
 * @since 2023-01-08
 */
@Service
@Slf4j
public class StStbprpBServiceImpl extends ServiceImpl<StStbprpBMapper, StStbprpB> implements StStbprpBService {

    @Resource
    private StStbprpBMapper stStbprpBMapper;

    /**
     * 导出数据excel
     */
    @Override
    public void exportAllTypeExcel(HttpServletResponse response, XSSFWorkbook workbook) {
//        //类型
//        XSSFSheet typeSheet = workbook.getSheetAt(1);
//        //填充数据
//        ExcelUtil.setCellValueByModel(typeSheet, objectTypeMetaMapper.getTypeSheetValue());

        //对象实列
        XSSFSheet objectSheet = workbook.getSheet("对象实例");

        List<Map<String, Object>> objectList = stStbprpBMapper.getObjectSheetValue();

        List<Map<String, Object>> objectSheetValueList = new ArrayList<>();
        List<ObjectMeta> objectMetaList = transferValueToObject(objectList);
        String objectType;
        String objectName;
        String remark;
        Map<String, Object> tempMap;
        for (ObjectMeta objectMeta : objectMetaList) {
//            objectType = (String) map.get("类型");
//            objectName = (String) map.get("对象名称");
//            remark = (String) map.get("备注");
            objectType = objectMeta.getObjectType();
            objectName = objectMeta.getObjectName();
            remark = objectMeta.getRemark();
            tempMap = new LinkedHashMap<>();
            tempMap.put("类型", objectType);
            tempMap.put("对象名称", objectName);
            tempMap.put("备注", remark);
            String objectTypeLabel = PinyinUtils.getPinyin(objectName, "_");
            if (StrUtil.isNotEmpty(objectTypeLabel) && objectTypeLabel.length() > 50) {
                objectTypeLabel = objectTypeLabel.substring(0, 50);
            }
            objectTypeLabel = objectTypeLabel.replace("(", "");
            objectTypeLabel = objectTypeLabel.replace(")", "");
            objectTypeLabel = objectTypeLabel.replace("（", "");
            objectTypeLabel = objectTypeLabel.replace("）", "");
            objectTypeLabel = objectTypeLabel.replace("__", "_");
            int index = objectTypeLabel.lastIndexOf("_");
            if (objectTypeLabel.length() > 0 && index == objectTypeLabel.length() - 1) {
                objectTypeLabel = objectTypeLabel.substring(0, index);
            }
            tempMap.put("英文标识", objectTypeLabel);
            objectSheetValueList.add(tempMap);
        }

        ExcelUtil.setCellValueByModel(objectSheet, objectSheetValueList);
//        //属性类型
//        XSSFSheet attrTypeSheet = workbook.getSheetAt(3);
//        ExcelUtil.setCellValueByModel(attrTypeSheet, objectTypeMetaMapper.getAttrTypeSheetValue());
//        //属性
//        XSSFSheet attrSheet = workbook.getSheetAt(4);
//        ExcelUtil.setCellValueByModel(attrSheet, objectTypeMetaMapper.getAttrSheetValue());
//        //属性条目
//        XSSFSheet attrItemSheet = workbook.getSheetAt(5);
//        List<Map<String, Object>> list = objectTypeMetaMapper.getAttrItemSheetValue();
//
//        for (int i = 0; i < list.size(); i++) {
//            Map<String, Object> objectMap = list.get(i);
//            String dataType = objectMap.get("数据类型").toString();
//            DataTypeEnum dataTypeEnum = DataTypeEnum.init(dataType);
//            if (dataTypeEnum == null) {
//                log.error("数据类型不正确：" + dataType);
//            } else {
//                objectMap.put("数据类型", dataTypeEnum.getName());
//            }
//        }
//        ExcelUtil.setCellValueByModel(attrItemSheet, list);

        try {
            OutputStream os = getOutputStream(response);
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 相同类型名称相同，则设置成英文括号加中文，如杨家湾(一)、杨家湾(二)、
     *
     * @param objectList
     * @return
     */
    private static List<ObjectMeta> transferValueToObject(List<Map<String, Object>> objectList) {
        List<ObjectMeta> objectMetaList = new ArrayList<>();
        String stcd;
        String objectType;
        String objectName;
        String remark;
        ObjectMeta objectMeta;
        Map<String, Integer> objectNameCountMap = new LinkedHashMap<>();
        Integer count = 0;
        Map<String, ObjectMeta> objectMetaMap = new LinkedHashMap<>();// key 为 stcd
        Set<String> sameNameSet = new LinkedHashSet<>(); // objectType + objectName
        for (Map<String, Object> map : objectList) {
            stcd = (String) map.get("STCD");
            objectType = (String) map.get("类型");
            objectName = (String) map.get("对象名称");
            remark = (String) map.get("备注");

            objectMeta = new ObjectMeta();
            objectMeta.setStcd(stcd);
            objectMeta.setObjectType(objectType);
            objectMeta.setObjectName(objectName);
            objectMeta.setRemark(remark);

            count = objectNameCountMap.get(objectType + objectName);
            if (count == null) {
                count = 1;
                objectMeta.setObjectNameIndex(count);
                objectNameCountMap.put(objectType + objectName, count);
                objectMetaList.add(objectMeta);
                continue;
            }
            if (count != null && count > 0) {
                count++;
                objectMeta.setObjectNameIndex(count);
                objectNameCountMap.put(objectType + objectName, count);
                sameNameSet.add(objectType + objectName);
                objectMetaList.add(objectMeta);
            }
        }

        for (ObjectMeta objectMetaTemp : objectMetaList) {
            if (sameNameSet.contains(objectMetaTemp.getObjectType() + objectMetaTemp.getObjectName())) {
                switch (objectMetaTemp.getObjectNameIndex()) {
                    case 1:
                        objectMetaTemp.setObjectName(objectMetaTemp.getObjectName() + "(一)");
                        break;
                    case 2:
                        objectMetaTemp.setObjectName(objectMetaTemp.getObjectName() + "(二)");
                        break;
                    case 3:
                        objectMetaTemp.setObjectName(objectMetaTemp.getObjectName() + "(三)");
                        break;
                    case 4:
                        objectMetaTemp.setObjectName(objectMetaTemp.getObjectName() + "(四)");
                        break;
                    case 5:
                        objectMetaTemp.setObjectName(objectMetaTemp.getObjectName() + "(五)");
                        break;
                    case 6:
                        objectMetaTemp.setObjectName(objectMetaTemp.getObjectName() + "(六)");
                        break;
                    case 7:
                        objectMetaTemp.setObjectName(objectMetaTemp.getObjectName() + "(七)");
                        break;
                    case 8:
                        objectMetaTemp.setObjectName(objectMetaTemp.getObjectName() + "(八)");
                        break;
                    case 9:
                        objectMetaTemp.setObjectName(objectMetaTemp.getObjectName() + "(九)");
                        break;
                    default:

                        objectMetaTemp.setObjectName(objectMetaTemp.getObjectName() + "(十)");
                        break;
                }
            }
        }

        return objectMetaList;
    }

    private static OutputStream getOutputStream(HttpServletResponse response) throws Exception {
        try {
            String fileName = URLEncoder.encode("list", "UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出excel表格失败!", e);
        }
    }
}
