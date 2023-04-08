package com.coderdream.freeapps.util.ppt.excelutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private XSSFWorkbook excel;

    public ExcelUtil(String filePath) {
        this.readExcel(filePath);
    }

    public XSSFWorkbook getExcel() {
        return this.excel;
    }

    // 读取 excel
    private XSSFWorkbook readExcel(String filePath) {
        try {
            this.excel = new XSSFWorkbook(new FileInputStream(new File(filePath)));
            return this.excel;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 写入 excel
     * @param exportPath
     */
    public void writeExcel(String exportPath) {
        try {
            File file = new File(exportPath);
            if (file.exists()) {
                file.delete();
            }
            this.excel.write(new FileOutputStream(new File(exportPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据索引获取 sheet
     * @param sheetIndex
     * @return
     */
    public XSSFSheet getSheet(int sheetIndex) {
        return this.excel.getSheetAt(sheetIndex);
    }

    /**
     * 根据 sheet name 获取 sheet
     * @param name
     * @return
     */
    public XSSFSheet getSheet(String name) {
        return this.excel.getSheet(name);
    }

    /**
     * 获取 cell
     * @param sheet
     * @param row
     * @param col
     * @return
     */
    public XSSFCell getCell(XSSFSheet sheet, int row, int col) {
        return sheet.getRow(row).getCell(col);
    }

    /**
     * 获取 sheet 的最大行数
     * @param sheet
     * @return
     */
    public int getPhysicalNumberOfRows(XSSFSheet sheet) {
        return sheet.getPhysicalNumberOfRows();
    }

    /**
     * 根据行获取最大列数
     * @param row
     * @return
     */
    public int getPhysicalNumberOfCells(XSSFRow row) {
        return row.getPhysicalNumberOfCells();
    }

    /**
     * 根据 sheet 获取最大列数，默认选择 0 行
     * @param sheet
     * @return
     */
    public int getPhysicalNumberOfCells(XSSFSheet sheet) {
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }

}
