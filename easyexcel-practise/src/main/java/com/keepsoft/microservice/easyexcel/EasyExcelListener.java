package com.keepsoft.microservice.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class EasyExcelListener<T> extends AnalysisEventListener<T> {

    //成功结果集
    private List<T> successList = new ArrayList<>();

    //失败结果集
    private List<ExcelCheckErrDto<T>> errList = new ArrayList<>();

    //处理逻辑service
    private ExcelCheckManager<T> excelCheckManager;

    private List<T> list = new ArrayList<>();

    //excel对象的反射类
    private Class<T> clazz;

    public EasyExcelListener(ExcelCheckManager<T> excelCheckManager) {
        this.excelCheckManager = excelCheckManager;
    }

    public EasyExcelListener(ExcelCheckManager<T> excelCheckManager, Class<T> clazz) {
        this.excelCheckManager = excelCheckManager;
        this.clazz = clazz;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        String errMsg;
        try {

            log.info("解析到一条数据:{}", JSON.toJSONString(t));
            // System.out.println("解析到一条数据: " + JSON.toJSONString(t));
            try {
                //根据excel数据实体中的javax.validation + 正则表达式来校验excel数据
                errMsg = EasyExcelValidHelper.validateEntity(t);
            } catch (NoSuchFieldException e) {
                errMsg = "解析数据出错";
                e.printStackTrace();
            }
            if (!StringUtils.isEmpty(errMsg)) {
                ExcelCheckErrDto excelCheckErrDto = new ExcelCheckErrDto(t, errMsg);
                errList.add(excelCheckErrDto);
            } else {
                list.add(t);
            }
            //每1000条处理一次
            if (list.size() > 1000) {
                //校验
                ExcelCheckResult result = excelCheckManager.checkImportExcel(list);
                successList.addAll(result.getSuccessDtos());
                errList.addAll(result.getErrDtos());
                list.clear();
            }
        } catch (ExcelAnalysisException e) {
            e.printStackTrace();
            if (e.getCause() instanceof ExcelDataConvertException) {
                ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) e.getCause();
                String cellMsg = "";
                CellData cellData = excelDataConvertException.getCellData();
                //这里有一个cell data type的枚举值,用来判断CellData的数据类型
                CellDataTypeEnum type = cellData.getType();
                if (type.equals(CellDataTypeEnum.NUMBER)) {
                    cellMsg = cellData.getNumberValue().toString();
                } else if (type.equals(CellDataTypeEnum.STRING)) {
                    cellMsg = cellData.getStringValue();
                } else if (type.equals(CellDataTypeEnum.BOOLEAN)) {
                    cellMsg = cellData.getBooleanValue().toString();
                }
                String errorMsg = String.format("excel表格:第%s行,第%s列,数据值为:%s,该数据值不符合要求,请检验后重新导入!<span style=\"color:red\">请检查其他的记录是否有同类型的错误!</span>", excelDataConvertException.getRowIndex() + 1, excelDataConvertException.getColumnIndex(), cellMsg);
                log.error(errorMsg);
            }
        }
    }


    //所有数据解析完成了 都会来调用
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        ExcelCheckResult result = excelCheckManager.checkImportExcel(list);

        successList.addAll(result.getSuccessDtos());
        errList.addAll(result.getErrDtos());
        list.clear();
    }

    /**
     * @param headMap 传入excel的头部（第一行数据）数据的index,name
     * @param context
     * @return void
     * @throws
     * @description: 校验excel头部格式，必须完全匹配
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        if (clazz != null) {
            try {
                Map<Integer, String> indexNameMap = getIndexNameMap(clazz);
                Set<Integer> keySet = indexNameMap.keySet();
                for (Integer key : keySet) {
                    if (StringUtils.isEmpty(headMap.get(key))) {
                        throw new ExcelAnalysisException("解析excel出错，请传入正确格式的excel");
                    }
                    if (!headMap.get(key).equals(indexNameMap.get(key))) {
                        throw new ExcelAnalysisException("解析excel出错，请传入正确格式的excel");
                    }
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param clazz
     * @return java.util.Map<java.lang.Integer, java.lang.String>
     * @throws
     * @description: 获取注解里ExcelProperty的value，用作校验excel
     */
    @SuppressWarnings("rawtypes")
    public Map<Integer, String> getIndexNameMap(Class clazz) throws NoSuchFieldException {
        Map<Integer, String> result = new HashMap<>();
        Field field;
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            field = clazz.getDeclaredField(fields[i].getName());
            field.setAccessible(true);
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            if (excelProperty != null) {
                int index = excelProperty.index();
                String[] values = excelProperty.value();
                StringBuilder value = new StringBuilder();
                for (String v : values) {
                    value.append(v);
                }
                result.put(index, value.toString());
            }
        }
        return result;
    }

}
