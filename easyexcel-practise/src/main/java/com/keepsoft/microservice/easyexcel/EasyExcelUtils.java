package com.keepsoft.microservice.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

public class EasyExcelUtils {
    private static final Logger logger = LoggerFactory.getLogger(EasyExcelUtils.class);

    private EasyExcelUtils() {
    }

    @SuppressWarnings("rawtypes")
    public static void webWriteExcel(HttpServletResponse response, List objects, Class clazz, String fileName) throws IOException {
        String sheetName = fileName;
        webWriteExcel(response, objects, clazz, fileName, sheetName);
    }

    @SuppressWarnings("rawtypes")
    public static void webWriteExcel(HttpServletResponse response, List objects, Class clazz, String fileName, String sheetName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为白
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            EasyExcelFactory.write(outputStream, clazz).registerWriteHandler(horizontalCellStyleStrategy).sheet(sheetName).doWrite(objects);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
        }
    }

    public static void localWriteExcel(String uploadDir, List objects, Class clazz, String fileName, String sheetName) throws IOException {

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为白
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        //建立文件对象
        String totalName = uploadDir + fileName + ".xlsx";
        File file = new File(totalName);
        OutputStream outputStream = new FileOutputStream(file);
//        ExcelWriter excelWriter = null;
        try {
//            EasyExcelFactory.write(outputStream, clazz).registerWriteHandler(horizontalCellStyleStrategy).sheet(sheetName).doWrite(objects);

            // 单独使用 EasyExcel 分别构造 ExcelWriter 和 WriteSheet
            // 构造 ExcelWriter
//            excelWriter = EasyExcel.write(file).registerWriteHandler(horizontalCellStyleStrategy).build();
//            // 构造 WriteSheet
//            WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();
//            excelWriter.write(objects, writeSheet);

            EasyExcel.write(totalName, clazz).sheet(sheetName).doWrite(objects);
//                    excelWriter.write(outputStream, writeSheet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            outputStream.close();
            // 千万别忘记finish 会帮忙关闭流
//            if (excelWriter != null) {
//                excelWriter.finish();
//            }
        }
    }

    public static void localWriteExcel2(String uploadDir, List objects, Class clazz, String fileName, String sheetName) throws IOException {

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为白
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        //建立文件对象
        String totalName = uploadDir + fileName + ".xlsx";
        File file = new File(totalName);
        OutputStream outputStream = new FileOutputStream(file);
//        ExcelWriter excelWriter = null;
        try {
//            EasyExcelFactory.write(outputStream, clazz).registerWriteHandler(horizontalCellStyleStrategy).sheet(sheetName).doWrite(objects);

            // 单独使用 EasyExcel 分别构造 ExcelWriter 和 WriteSheet
            // 构造 ExcelWriter
//            excelWriter = EasyExcel.write(file).registerWriteHandler(horizontalCellStyleStrategy).build();
//            // 构造 WriteSheet
//            WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();
//            excelWriter.write(objects, writeSheet);

            EasyExcel.write(totalName, clazz).sheet(sheetName).doWrite(objects);
//                    excelWriter.write(outputStream, writeSheet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            outputStream.close();
            // 千万别忘记finish 会帮忙关闭流
//            if (excelWriter != null) {
//                excelWriter.finish();
//            }
        }
    }

    public static void downloadExcel(HttpServletResponse response, String path) {
        try {
            // path是指想要下载的文件的路径
            File file = new File(path);
            logger.info(file.getPath());
            // 获取文件名
            String filename = file.getName();
            // 获取文件后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            logger.info("文件后缀名：" + ext);

            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            response.reset();
            // 设置response的Header
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
