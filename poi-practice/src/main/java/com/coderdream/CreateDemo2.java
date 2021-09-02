package com.coderdream;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/9/2 16:58
 * @description：
 * @modified By：CoderDream
 * @version: $
 */


import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateDemo2 {
    void create() {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("表1");
        CellRangeAddress region = new CellRangeAddress(1, 4, 2, 5);
        XSSFRow row = sheet.createRow(1);
        row.createCell(2).setCellValue("第二至五行3-6列合并");
        sheet.addMergedRegion(region);

        XSSFCellStyle cellStyle = workbook.createCellStyle();

        cellStyle.setBorderBottom(BorderStyle.THIN); // 下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);// 左边框
        cellStyle.setBorderTop(BorderStyle.THIN);// 上边框
        cellStyle.setBorderRight(BorderStyle.THIN);// 右边框
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中

        XSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");// 设置字体名称
        font.setFontHeightInPoints((short) 10);// 设置字号
        font.setColor(IndexedColors.BLACK.index);// 设置字体颜色

        cellStyle.setFont(font);

        CellUtil.getCell(row, 2).setCellStyle(cellStyle);

        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, region, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, region, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, region, sheet);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, region, sheet);

        RegionUtil.setBottomBorderColor(IndexedColors.BLUE.index, region, sheet);
        RegionUtil.setLeftBorderColor(IndexedColors.BLUE.index, region, sheet);
        RegionUtil.setRightBorderColor(IndexedColors.BLUE.index, region, sheet);
        RegionUtil.setTopBorderColor(12, region, sheet);

        workbook.cloneSheet(0, "表2");

        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream("D:\\整机检测报告.xlsx");
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("写出成功！");
    }
}


