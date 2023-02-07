package com.coderdream.demo.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

public class EasyExcelUtil {

    public static final Logger logger = LoggerFactory.getLogger(EasyExcelUtil.class);

    /**
     * @param cellMap  LinkedMap<String, String>  key List存储对象成员变量,value excel头部
     * @param dataList List excel 内容体
     * @throws Exception
     */
    public static void dataExport(LinkedMap<String, String> cellMap, List dataList, HttpServletResponse response) throws Exception {
        try {
            // 获取excel翻译文件内的Map数据
            List<List<String>> cellList = new ArrayList<>();
            // excel头部数据
            cellList.add(handleHeaderData(cellMap));
            // excel 内容数据
            handleContent(cellList, cellMap, dataList);
            download(cellList, response);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param cellMap
     * @param dataList
     * @param fileName
     * @throws Exception
     */
    public static void dataExport(LinkedMap<String, String> cellMap, List dataList, String fileName, HttpServletResponse response)
            throws Exception {

        try {
            // 获取excel翻译文件内的Map数据
            List<List<String>> cellList = new ArrayList<>();
            // excel头部数据
            cellList.add(handleHeaderData(cellMap));
            // excel 内容数据
            handleContent(cellList, cellMap, dataList);
            download(cellList, fileName, response);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * excel头部数据
     *
     * @param cellMap
     * @return
     */
    private static List<String> handleHeaderData(LinkedMap<String, String> cellMap) {
        List<String> resultList = new ArrayList<>();
        for (String key : cellMap.keySet()) {
            resultList.add(cellMap.get(key));
        }
        return resultList;
    }

    /**
     * excel 内容数据封装
     *
     * @param cellList
     * @param cellMap
     * @param dataList
     * @return
     */
    private static List<List<String>> handleContent(List<List<String>> cellList, LinkedMap<String, String> cellMap, List<?> dataList) {
        if (CollectionUtils.isEmpty(dataList)) {
            return cellList;
        }
        cellList.addAll(
                dataList.stream().map(dto -> {
                    List<String> resultList = new ArrayList<>();
                    for (String key : cellMap.keySet()) {
                        Class clazz = dto.getClass();
                        // 无该方法,赋空值
                        try {
                            Method method = clazz.getDeclaredMethod("get" + key.substring(0, 1).toUpperCase() + key.substring(1));
                            resultList.add(method.invoke(dto).toString());
                        } catch (Exception e) {
                            resultList.add("");
                        }
                    }
                    return resultList;
                }).collect(Collectors.toList()));

        return cellList;
    }

    // 下载随机文件名
    private static void download(List<List<String>> cellData, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 随机生成文件名
        String fileName = UUID.randomUUID().toString();
        response.setHeader("Content-Disposition", "attachment;filename*= UTF-8''" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
        //表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 头部背景颜色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        //内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        EasyExcel.write(response.getOutputStream()).sheet("sheet").registerWriteHandler(horizontalCellStyleStrategy).doWrite(cellData);
    }

    // 下载设置文件名
    private static void download(List<List<String>> cellData, String fileName, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 随机生成文件名
        if (StringUtils.isEmpty(fileName)) {
            fileName = UUID.randomUUID().toString();
        }
        response.setHeader("Content-Disposition", "attachment;filename*= UTF-8''" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));

        //表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 头部背景颜色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        //内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        EasyExcel.write(response.getOutputStream()).sheet("sheet").registerWriteHandler(horizontalCellStyleStrategy).doWrite(cellData);
    }

    /**
     * 获取excel导出模板文件
     *
     * @param excelTemplatePath resources目录下路径
     * @return
     */
    public static InputStream getResourcesFileByPath(String excelTemplatePath) {
        //  ClassPathResource resource = new ClassPathResource(excelTemplatePath);
        InputStream inputStream = null;
        try {


            //  inputStream =  resource.getInputStream();
            if (inputStream == null) {
                inputStream = new FileInputStream(new File(excelTemplatePath));
            }

            System.out.println(inputStream);
        } catch (Exception e) {
            logger.error("error {}", e);
        }
        return inputStream;
    }

    /**
     * @param templatePath 导出模板路径,必须在resource路径下
     * @param mapData      map
     * @param listData     list
     * @param response
     * @throws Exception
     */
    public static void dataExportByTemplate(String templatePath, Map<String, String> mapData, List listData, HttpServletResponse response) throws Exception {

        InputStream templateInputStream = getResourcesFileByPath(templatePath);
        if (templateInputStream == null) {
            throw new Exception("excel模板不存在:" + templatePath);
        }
        try {
            // 下载,数据填充
            httpDownloadByTemplate(mapData, listData, templateInputStream, null, response);
        } catch (Exception e) {
            throw e;
        } finally {
            templateInputStream.close();
        }

    }

    /**
     * @param templatePath 导出模板路径,必须在resource路径下
     * @param mapData      map
     * @param listData     list
     * @param fileName     导出文件名
     * @param response
     * @throws Exception
     */
    public static void dataExportByTemplate(String templatePath, Map<String, String> mapData, List listData,
                                            String fileName, HttpServletResponse response) throws Exception {

        InputStream templateInputStream = getResourcesFileByPath(templatePath);
        if (templateInputStream == null) {
            throw new Exception("excel模板不存在:" + templatePath);
        }
        // 下载,数据填充
        httpDownloadByTemplate(mapData, listData, templateInputStream, fileName, response);
    }


    /**
     * @param mapData     其他填充数据
     * @param listData    列表数据
     * @param inputStream 模板文件路径
     * @param fileName    文件名称
     * @param response    输出六
     * @throws Exception
     */
    public static void httpDownloadByTemplate(Map<String, String> mapData, List listData, InputStream inputStream,
                                              String fileName, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 随机生成文件名
        if (StringUtils.isEmpty(fileName)) {
            fileName = UUID.randomUUID().toString();
        }
        response.setHeader("Content-Disposition", "attachment;filename*= UTF-8''" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
        //读取excel
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(inputStream).build();
        // 第一个sheet
        WriteSheet writeSheet = EasyExcel.writerSheet(0).build();

        if (Objects.nonNull(mapData)) {
            excelWriter.fill(mapData, writeSheet);
        }
        if (CollectionUtils.isNotEmpty(listData)) {
            excelWriter.fill(listData, writeSheet);
        }
        excelWriter.finish();
    }

//    public void exportExcelSettlement(String settSerialNo, HttpServletResponse response)throws IOException {
//        //获取模板路径
//        InputStream template = new PathMatchingResourcePatternResolver().getResource("templates/excel/settlement.xlsx").getInputStream();
//        if (template.available() == 0) {
//            logger.error("【导出对xx】获取xx模板失败");
////            throw EXPORT_TEMPLATES_EXCEPTION_SETT;
//        }
//        //获取数据
//        SettlementDetailVO vo = querySettlementDetail(settSerialNo);
//        List<SettlementDetailGoodsVO> goodsVOS = querySettlementDetailGoods(settSerialNo);
//        //判断数据是否存在
//        if (ObjectUtils.isEmpty(vo)) {
//            logger.error("【导出xx】xx信息不存在");
//            throw SETT_NOT_EXIST_EXCEPTION;
//        }
//        if (CollectionUtils.isEmpty(goodsVOS)) {
//            logger.error("【导出xx】xx信息不存在");
//            throw SETT_NOT_EXIST_EXCEPTION;
//        }
//        response.setContentType("application/json;charset=utf-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(vo.getBuyersName() + "-" + "对账单" + ".xlsx", "UTF-8"));
//        //ExcelWriter该对象用于通过POI将值写入Excel
//        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(template).build();
//        //构建excel的sheet
//        WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        //控制集合数据垂直填充;ps.集合中的数据需要以FillWrapper()包裹起来才能被解析
//        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.VERTICAL).build();
//        //表格goods的数据填充
//        excelWriter.fill(new FillWrapper("goods", goodsVOS), fillConfig, writeSheet);
//        //其他数据填充
//        excelWriter.fill(convertObject(vo), writeSheet);
//        excelWriter.finish();
//    }
//
//    //其余数据的转换
//    private SettlementDetailVO convertObject(SettlementDetailVO info) {
//        SettlementDetailVO vo = new SettlementDetailVO();
//        vo.setSettAmount(info.getSettAmount());
//        vo.setSettName(info.getSettName());
//        return vo;
//    }
}
