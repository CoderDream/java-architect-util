package com.coderdream.freeapps.util.other;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.pojo.JobInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
//        String fileName = "D:\\02_Work\\贵仁科技\\04_荆楚江河\\初始化数据\\API结构-模板.xlsx";
//        getDataFromExcel(fileName);

        JobInfo jobInfo = new JobInfo();
        jobInfo.setPositionName("职位名称");
        jobInfo.setWorkYear("工作年限");
        List fieldsInfo = getFieldsInfo(jobInfo);
        if (CollectionUtils.isNotEmpty(fieldsInfo)) {
//            for (Object o : fieldsInfo) {
//                System.out.println(o);
//            }
            List list = insertSqlList("t_lg_job_info", fieldsInfo);
            if (CollectionUtils.isNotEmpty(list)) {
                for (Object o : list) {
                    System.out.println(o);
                }
            }
        }
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
                            System.out.println("第一行第一格应该为【表英文名】!");
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
                            System.out.println("第2行第一格应该为【表中文名】!");
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

    /**
     * 1. 根据一个对象，获取对象的属性类型，属性名称，属性值得集合；
     *
     * @param o
     * @return
     */
    public static List getFieldsInfo(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        List list = new ArrayList();
        Map infoMap = null;
        for (int i = 0; i < fields.length; i++) {
            infoMap = new HashMap();
            infoMap.put("type", fields[i].getType().toString());
            infoMap.put("name", fields[i].getName());
            infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
            list.add(infoMap);
        }
        return list;
    }

    /**
     * 2. 根据对象的属性名，获取对象相应的属性值；
     *
     * @param fieldName
     * @param o
     * @return
     */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 3. 指定表名，拼写SQL（空值得跳过）；
     *
     * @param tableName
     * @param qualification
     * @return
     */
    public static List insertSqlList(String tableName, List qualification) {
        List sqlList = new ArrayList();
        for (int i = 0; i < qualification.size(); i++) {
            List list = getFieldsInfo(qualification.get(i));
            String values = "";
            String fields = "";
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) != null) {
                    // 拼写sql
                    Map map = (Map) list.get(j);
                    String name = map.get("name").toString();
                    String type = map.get("type").toString();
                    Object value = map.get("value");

                    // fields += name + ",";
                    if (type != null && null != value) {
                        fields += name + ",";
                        if (type.contains("Integer") || type.contains("Double")) {
                            values += value + ",";
                        } else if (type.contains("DATE")) {
                            if (value instanceof Date) {
                                SimpleDateFormat sf = new SimpleDateFormat(
                                    "yyyy-MM-dd HH:mm:ss");
                                value = sf.format(value);
                            }
                            values += "to_date(‘" + value
                                + "’,’yyyy-MM-dd hh24:mi:ss’),";
                        } else {
                            if (value.toString().contains("’")) {
                                value = value.toString().replaceAll("’", "\"");
                                values += "’" + value + "’,";
                            } else {
                                values += "’" + value + "’,";
                            }
                        }
                    }
                }
            }
            if(StrUtil.isNotEmpty(fields)) {
                fields = fields.substring(0, fields.length() - 1);
            }
            if(StrUtil.isNotEmpty(values)) {
                values = values.substring(0, values.length() - 1);
            }
            String sql = "insert into " + tableName + "(" + fields + ") values(" + values + ")";
            sqlList.add(sql);
        }
        return sqlList;
    }

}
