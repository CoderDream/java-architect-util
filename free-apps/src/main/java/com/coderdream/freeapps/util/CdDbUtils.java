package com.coderdream.freeapps.util;


import cn.hutool.core.util.StrUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Iterator;

/**
 * @author Jack Li https://blog.csdn.net/qqqwwweeerasd/article/details/126034409
 * @description 读取excel文件内容生成数据库表ddl
 * @date 2022/3/27 19:54
 */
public class CdDbUtils {

    public static void main(String[] args) {
        String fileName = "D:\\02_Work\\贵仁科技\\04_荆楚江河\\初始化数据\\API结构-模板.xlsx";
        getDataFromExcel(fileName);
    }


    /**
     * 读取excel文件内容生成数据库表ddl
     *
     * @param filePath excel文件的绝对路径
     */
    public static void getDataFromExcel(String filePath) {
        if (!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx")) {
            System.out.println("文件不是excel类型");
        }
        InputStream fis = null;
        Workbook wookbook = null;
        try {
            fis = new FileInputStream(filePath);
            if (filePath.endsWith(".xls")) {
                try {
                    //2003版本的excel，用.xls结尾
                    wookbook = new HSSFWorkbook(fis);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (filePath.endsWith(".xlsx")) {
                try {
                    //2007版本的excel，用.xlsx结尾
                    wookbook = new XSSFWorkbook(fis);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Iterator<Sheet> sheets = wookbook.sheetIterator();
            while (sheets.hasNext()) {
                StringBuilder ddl = new StringBuilder();
                // 是否自增
                boolean autoIncrement = false;
                Sheet sheet = sheets.next();
                System.out.println("--------------------------当前读取的sheet页：" + sheet.getSheetName()
                    + "--------------------------");
                // 当前读取行的行号
                int rowId = 1;
                Iterator<Row> rows = sheet.rowIterator();
                String tableEnglishName = "";
                String tableChineseName = "";
                while (rows.hasNext()) {
                    Row row = rows.next();
                    //获取表英文名
                    if (rowId == 1) {
                        Cell cell1 = row.getCell(0);
                        if (!"表英文名".equals(cell1.getStringCellValue())) {
                            System.out.println("第一行第一格应该为“表英文名”!");
                            return;
                        }
                        Cell cell2 = row.getCell(1);
                        tableEnglishName = cell2.getStringCellValue();
                        ddl.append("CREATE TABLE " + "`" + tableEnglishName + "` (" + "\r\n");
                        rowId++;
                        continue;
                    }
                    //获取表中文名
                    if (rowId == 2) {
                        Cell cell1 = row.getCell(0);
                        if (!"表中文名".equals(cell1.getStringCellValue())) {
                            System.out.println("第2行第一格应该为“表中文名”!");
                            return;
                        }
                        Cell cell2 = row.getCell(1);
                        tableChineseName = cell2.getStringCellValue();
                        rowId++;
                        continue;
                    }
                    //校验属性列名称和顺序
                    if (rowId == 3) {
                        if (row.getPhysicalNumberOfCells() != 6) {
                            System.out.println("第2行应该只有6个单元格!");
                            return;
                        }
                        Iterator<Cell> cells = row.cellIterator();
                        StringBuilder tableField = new StringBuilder();
                        while (cells.hasNext()) {
                            tableField.append(cells.next().getStringCellValue().trim());
                        }
                        if (!"字段名类型长度,小数点是否为主键是否自增注释".equals(tableField.toString())) {
                            System.out.println("第3行应该为 字段名 类型 长度,小数点 是否为主键 是否自增 注释 !");
                            return;
                        }
                        rowId++;
                        continue;
                    }
                    if (!row.cellIterator().hasNext()) {
                        break;
                    }
                    // 字段名
                    XSSFCell cell = (XSSFCell) row.getCell(0);
                    if (cell == null) {
                        break;
                    }
                    String rawValue = cell.getRawValue();
                    String fieldName = row.getCell(0).getStringCellValue();
                    if (fieldName == null | "".equals(fieldName)) {
                        break;
                    }
                    // 字段类型
                    String fieldType = row.getCell(1).getStringCellValue();
                    // 字段长度
                    Cell cell3 = row.getCell(2);
                    String fieldLength = "255";
                    if (cell3 != null) {
                        cell3.setCellType(CellType.STRING);
                        fieldLength = cell3.getStringCellValue();
                    }
                    // 是否为主键
                    Cell cell4 = row.getCell(3);
                    // 是否自增
                    Cell cell5 = row.getCell(4);
                    // 字段注释
                    String fieldComment = row.getCell(5).getStringCellValue();

                    ddl.append(
                        "`" + fieldName + "` "
                            + fieldType
                            + ((!"0".equals(fieldLength) && StrUtil.isNotEmpty(fieldLength)) ? "(" + fieldLength + ")"
                            : "")
                            + (cell4 != null && "Y".equals(cell4.getStringCellValue()) ? " PRIMARY KEY " : "")
                            + (cell5 != null && "Y".equals(cell5.getStringCellValue()) ? " AUTO_INCREMENT " : "")
                            + " COMMENT '" + fieldComment + "'"
                            + (rows.hasNext() ? ",\r\n" : "\r\n")
                    );
                    if (cell4 != null && "Y".equals(cell5.getStringCellValue())) {
                        autoIncrement = true;
                    }

                    rowId++;
                }
                if (ddl.toString().endsWith(",\r\n")) {
                    ddl = ddl.deleteCharAt(ddl.length() - 3);
                    ddl.append("\r\n");
                }
                ddl.append(") ENGINE=InnoDB " + (autoIncrement ? "AUTO_INCREMENT=1" : "") + " DEFAULT CHARSET=utf8 "
                    + (!"".equals(tableChineseName) ? "COMMENT = '" + tableChineseName + "'" : "") + ";\r\n");
                ddl.append("-- --------------------------------------------------------------------------------\r\n");
                System.out.println(ddl.toString());
                writeMessageToFile(ddl.toString());
            }
            System.out.println("运行成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeMessageToFile(String message) {
        try {
            File file = new File("ddl.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getName(), true);
            fileWriter.write(message);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
