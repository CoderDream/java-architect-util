package com.coderdream.freeapps.util.excel;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ZipUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CdExcelUtils {

    /**
     * 利用传入的数据，生成对应的xls文件
     *
     * @param data       需要导出的数据
     * @param sheetName  sheet的名字
     * @param titleValue excel主题（例如第一行显示：“2018-10月销售汇总”）
     * @param headArr    标题
     *
     * @return 生成的03excel工作簿
     */
    public static <T> HSSFWorkbook exportDataTo03(List<T> data, String sheetName, String titleValue, String[] headArr)
        throws IOException, IllegalArgumentException, IllegalAccessException {
        // 新建excel
        HSSFWorkbook wb = new HSSFWorkbook();

        // 新建sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 1.1创建合并单元格对象
        CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, headArr.length - 1);
        sheet.addMergedRegion(callRangeAddress);

        // 新建第一行表头
        HSSFRow titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(50);
        HSSFFont titleFont = wb.createFont();
        titleFont.setBold(true);
        titleFont.setFontName("微软雅黑");
        titleFont.setFontHeightInPoints((short) 35);
        // 样式
        HSSFCellStyle titleStyle = wb.createCellStyle();
        // 剧中
        titleStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        titleStyle.setFont(titleFont);

        HSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue(titleValue + "xxx");

        HSSFCellStyle hearStyle = wb.createCellStyle();
        hearStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        HSSFFont headFont = wb.createFont();
        headFont.setBold(true);
        headFont.setFontName("微软雅黑");
        headFont.setFontHeightInPoints((short) 50);

        HSSFRow headRow = sheet.createRow(1);
        headRow.setHeightInPoints(30);
        for (int i = 0; i < headArr.length; i++) {
            HSSFCell headCell = headRow.createCell(i);
            headCell.setCellStyle(hearStyle);
            headCell.setCellValue(headArr[i]);
            // TODO 这里要根据对应的列名设置对应的宽度，更美观一点。我这里偷懒直接所有的设置一样的
            sheet.setColumnWidth(i, 256 * 30);
        }

        HSSFCellStyle valueStyle = wb.createCellStyle();
        valueStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        HSSFFont valueFont = wb.createFont();
        valueFont.setFontName("微软雅黑");
        valueFont.setFontHeightInPoints((short) 15);
        valueStyle.setFont(valueFont);

        // 根据反射获取私有字段，与列名匹配，对应的话就插入对应数据
        for (int valueIndex = 0; valueIndex < data.size(); valueIndex++) {
            HSSFRow valueRow = sheet.createRow(valueIndex + 2);
            valueRow.setHeightInPoints(30);
            Class c = data.get(valueIndex).getClass();
            Field[] fields = c.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                NoExport amazonFiled = fields[i].getAnnotation(NoExport.class);
                if (amazonFiled != null && amazonFiled.value() == false) {
                    continue;
                }
                fields[i].setAccessible(true);
                String fieldName = fields[i].getName();
                for (int j = 0; j < headArr.length; j++) {
                    String headName = headArr[j];
                    if (fieldName.trim().equalsIgnoreCase(headName.trim())) {
                        HSSFCell valueCell = valueRow.createCell(j);
                        valueCell.setCellStyle(valueStyle);
                        valueCell.setCellValue(fields[i].get(data.get(valueIndex)) + "");
                        break;
                    }
                }
            }
        }
        return wb;
    }

    /**
     * 把传入的工作簿文件转成输入流
     *
     * @param workbooks 工作簿文件
     * @return 文件流list
     */
    public static List<ByteArrayOutputStream> workBook2Stream(Workbook... workbooks) throws IOException {
        List<ByteArrayOutputStream> list = new LinkedList<ByteArrayOutputStream>();
        for (Workbook obj : workbooks) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            obj.write(os);
            list.add(os);
            obj.close();
        }
        return list;
    }

    /**
     * 压缩文件
     *
     * @param workBook  生成的03工作簿
     * @param xworkWork 生成的07工作簿
     * @param fileNames 被压缩的文件名称
     *
     * @return File 生成的压缩包文件
     *
     * @throws IOException
     *
     */
    public static File zipFile(HSSFWorkbook workBook, XSSFWorkbook xworkWork, String[] fileNames) throws Exception {
        // 写入输入流
        List<ByteArrayOutputStream> workList = workBook2Stream(workBook, xworkWork);
        InputStream[] is = new InputStream[workList.size()];
        int step = 0;
        for (ByteArrayOutputStream bs : workList) {
            byte[] content = bs.toByteArray();
            ByteArrayInputStream bt = new ByteArrayInputStream(content);
            is[step] = bt;
            step++;
        }
        // 获取项目路径
        String rootPath = CdExcelUtils.class.getClassLoader().getResource("").getPath();
        // 创建临时文件tempFile
        File zipFile = new File(rootPath + "/tempFile.zip");
        if (!zipFile.exists()) {
            zipFile.createNewFile();
        }

        zipFile = ZipUtil.zip(zipFile, fileNames, is);

        // 关闭流
        for (ByteArrayOutputStream steam : workList) {
            IoUtil.close(steam);
        }

        for (InputStream steam : is) {
            IoUtil.close(steam);
        }
        return zipFile;
    }

}
