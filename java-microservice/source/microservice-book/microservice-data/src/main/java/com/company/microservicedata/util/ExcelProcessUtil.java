package com.company.microservicedata.util;

import com.company.microservicedata.vo.UserDetailsVO;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * Excel文件处理工具类
 * @author xindaqi
 * @since 2020-12-13
 */

@Component
public class ExcelProcessUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelProcessUtil.class);


    public void mergeTableTest() throws IOException {
        /**
         * 新建Excel
         * 新建Sheet
         * 新建表头
         * 内容格式:水平居中，垂直居中
         */
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("报表");



        HSSFRow headRow = sheet.createRow(0);
        List<String> titleList = Stream.of("姓名", "性别").collect(Collectors.toList());
        for(int i = 0; i < titleList.size(); i++) {
            headRow.createCell(i).setCellValue(titleList.get(i));
        }


        HttpHeaders header = null;
        header = new HttpHeaders();
        String fileName = "信息表.xls";
        header.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        /**
         * 起始行
         * 结束行
         * 起始列
         * 结束列
         */
        CellRangeAddress region = new CellRangeAddress(0, 2, 0, 0);
        sheet.addMergedRegion(region);
        HSSFCellStyle style = workbook.createCellStyle();

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        File file = new File("/Users/xindaqi/xinPrj/java/webStudy/microservice-book/" + fileName);
//        String filePath = "/Users/xindaqi/xinPrj/java/webStudy/microservice-book/" + fileName;
        FileOutputStream fos = new FileOutputStream(file);
        fos.flush();
        workbook.write(fos);
        if(workbook != null) {
            fos.close();
        }


    }

    public void mergeTestWithXSSF() throws IOException {
        /**
         * 新建Excel
         * 新建Sheet
         * 新建表头
         * 内容格式:水平居中，垂直居中
         */
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("报表");

        /**
         * 合并单元格：
         * 4个参数
         * 起始行、结束行、起始列、结束列
         */
        CellRangeAddress region1 = new CellRangeAddress(0, 0, 3, 4);
        CellRangeAddress region2 = new CellRangeAddress(0, 0, 5, 12);
        CellRangeAddress region3 = new CellRangeAddress(0, 0, 13, 16);
        CellRangeAddress region4 = new CellRangeAddress(0, 0, 17, 21);

        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);
        sheet.addMergedRegion(region3);
        sheet.addMergedRegion(region4);


        XSSFRow headRow = sheet.createRow(0);
        List<String> titleList = Stream.of("1", "2", "3", "4", "5", "6", "7").collect(Collectors.toList());
        for(int i = 0; i < titleList.size(); i++) {
            headRow.createCell(i).setCellValue(titleList.get(i));
        }
        XSSFRow rowTwo = sheet.createRow(1);


        HttpHeaders header = new HttpHeaders();
        String fileName = "信息表.xlsx";
        header.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        XSSFCellStyle style = workbook.createCellStyle();
        //居中
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //border
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);

        File file = new File("/Users/xindaqi/xinPrj/java/webStudy/microservice-book/" + fileName);
        /**
         * 合并单元格：
         * 4个参数
         * 起始行、结束行、起始列、结束列
         */
       CellRangeAddress region = new CellRangeAddress(0, 2, 0, 0);
       sheet.addMergedRegion(region);
        // File file = new File("G:\\AAA-工作\\兼职项目\\书籍出版\\JavaMicroService\\microservice-book\\" + fileName);
//        File file = new File("F:\\maven3.6.1\\"  + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.flush();
        workbook.write(fos);
        if(workbook != null) {
            fos.close();
        }

    }

    public void rawWrite(Class<?> clazz, String savePath, String fileName) {

        /**
         * 新建Excel文件和Sheet
         */
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("原生POI生成Excel");
        List<String> titleList = Stream.of("序号", "姓名", "性别", "地址").collect(Collectors.toList());
        XSSFRow headRow = sheet.createRow(0);
        for(int i = 0; i < titleList.size(); i++) {
            headRow.createCell(i).setCellValue(titleList.get(i));
        }

        List<UserDetailsVO> userDetailsVOList = new ArrayList<>();
        UserDetailsVO u1 = new UserDetailsVO();
//        u1.setId(1);
        u1.setAddress("黑龙江");
        u1.setSex("male");
        u1.setUsername("xiaoxiao");
        userDetailsVOList.add(u1);
        UserDetailsVO u2 = new UserDetailsVO();
//        u2.setId(2);
        u2.setAddress("辽宁");
        u2.setSex("male");
        u2.setUsername("xiaohei");
        userDetailsVOList.add(u2);
        UserDetailsVO u3 = new UserDetailsVO();
//        u3.setId(3);
        u3.setAddress("广东");
        u3.setSex("male");
        u3.setUsername("xiaohua");
        userDetailsVOList.add(u3);
        logger.info("user list: {}", userDetailsVOList);
        Field[] fields = clazz.getDeclaredFields();
        FileOutputStream fos = null;
        try {
            for(int i = 0; i < userDetailsVOList.size(); i++) {
                XSSFRow row = sheet.createRow(i+1);
                for(int j = 0; j < fields.length; j++) {
                    PropertyDescriptor pd = new PropertyDescriptor(fields[j].getName(), userDetailsVOList.get(i).getClass());
                    Method method = pd.getReadMethod();
                    String invoke = (null == method.invoke(userDetailsVOList.get(i))) ? "" : method.invoke(userDetailsVOList.get(i)).toString();
                    row.createCell(j).setCellValue(invoke);
                }
            }
            File file = new File(savePath + fileName);
            fos = new FileOutputStream(file);
            workbook.write(fos);
            logger.info("下载-完成写入");

        } catch(Exception e) {
            logger.error("下载异常：{}", e);
            logger.info("下载异常：{}", e);
        } finally {
            try {
                if(fos != null) {
                    fos.close();
                }
                logger.info("流关闭");
            } catch (Exception e) {
                logger.info("流关闭失败:{}", e);
            }
            try {
                workbook.close();
                logger.info("Workbook关闭");
            } catch (Exception e) {
                logger.info("Workbook关闭失败：{}", e);
            }
        }
    }



}