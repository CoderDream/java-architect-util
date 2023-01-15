package com.coderdream.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class ExcelUtil {

    public static String fileCheckForObjectStructure(MultipartFile file, String dataType) throws Exception {
        String res = "校验通过";
        InputStream inputStream = file.getInputStream();
        String filename = file.getOriginalFilename();
        Workbook workbook = getWorkbook(inputStream, filename);
        res = checkObjectStructureSheetHead(res, workbook, dataType);
        return res;
    }

    public static String fileCheck(MultipartFile file, String dataType) throws Exception {
        String res = "校验通过";
        InputStream inputStream = file.getInputStream();
        String filename = file.getOriginalFilename();
        Workbook workbook = getWorkbook(inputStream, filename);
        res = checkSheetHead(res, workbook, dataType);
        return res;
    }

    public static Boolean sheetNameCheck(MultipartFile file, List<String> dataTypeList) throws Exception {
        Boolean checkFlag = false;
        InputStream inputStream = file.getInputStream();
        String filename = file.getOriginalFilename();
        Workbook workbook = getWorkbook(inputStream, filename);
        for (String dataType:dataTypeList) {
            if( getSheet(workbook, dataType) != null) {
                return true;
            }
        }
        return checkFlag;
    }

    private static String checkSheetHead(String res, Workbook workbook, String dataType) throws Exception {
        List<String> headCellName;
        String sheetName;
        ImportSheetNameEnum importSheetNameEnum = ImportSheetNameEnum.init(dataType);
        if (importSheetNameEnum == null) {
            //logger.error("数据类型不正确。");
            //throw new RRException("数据类型不正确。");
        }
        switch (importSheetNameEnum) {
            case STRING:
                sheetName = importSheetNameEnum.getName();
                headCellName = new ArrayList<>(Arrays.asList("条目名称", "条目编码", "值"));
                res = checkHeadCell(workbook, sheetName, res, headCellName);
                break;
            case CURVE:
                sheetName = importSheetNameEnum.getName();
                headCellName = new ArrayList<>(Arrays.asList("条目名称", "来源系统", "源数据编码", "V0", "V1", "V2", "V3"));
                res = checkHeadCell(workbook, sheetName, res, headCellName);
                break;
            case CURVE_META:
                sheetName = importSheetNameEnum.getName();
                headCellName = new ArrayList<>(Arrays.asList("条目名称", "来源系统", "源数据编码", "维度", "V0名称", "V1名称", "V2名称", "V3名称"));
                res = checkHeadCell(workbook, sheetName, res, headCellName);
                break;
            case SEQUENCE:
                sheetName = importSheetNameEnum.getName();
                headCellName = new ArrayList<>(Arrays.asList("条目名称", "条目编码", "时间", "值"));
                res = checkHeadCell(workbook, sheetName, res, headCellName);
                break;
            case FORECAST:
                sheetName = importSheetNameEnum.getName();
                headCellName = new ArrayList<>(Arrays.asList("条目名称", "条目编码", "步长", "依据时间", "时间", "值"));
                res = checkHeadCell(workbook, sheetName, res, headCellName);

                break;
        }
        return res;
    }

    private static String checkObjectStructureSheetHead(String res, Workbook workbook, String dataType) throws Exception {
        List<String> headCellName;
        String sheetName;
        ImportSheetNameEnum importSheetNameEnum = ImportSheetNameEnum.init(dataType);
        if (importSheetNameEnum == null) {
            //logger.error("数据类型不正确。");
            //throw new RRException("数据类型不正确。");
        }
        switch (importSheetNameEnum) {
            case OBJECT_INSTANCE:
                sheetName = importSheetNameEnum.getName();
                headCellName = new ArrayList<>(Arrays.asList("类型", "对象名称", "对象码全码", "英文标识", "备注"));
                res = checkObjectStructureHeadCell(workbook, sheetName, res, headCellName);
                break;
            case ATTR_TYPE:
                sheetName = importSheetNameEnum.getName();
                headCellName = new ArrayList<>(Arrays.asList("类型", "类型结构", "属性类型", "属性类型简码", "属性类型全码", "是否公共", "英文标识", "备注"));
                res = checkObjectStructureHeadCell(workbook, sheetName, res, headCellName);
                break;
            case ATTR:
                sheetName = importSheetNameEnum.getName();
                headCellName = new ArrayList<>(Arrays.asList("类型", "类型结构", "属性类型", "属性类型简码", "属性", "属性简码", "属性全码", "是否公共", "英文标识", "是否显示", "备注"));
                res = checkObjectStructureHeadCell(workbook, sheetName, res, headCellName);
                break;
            case ATTR_ITEM:
                sheetName = importSheetNameEnum.getName();
                headCellName = new ArrayList<>(Arrays.asList("属性条目", "属性条目简码", "属性条目全码", "英文标识", "类型", "对象", "类型结构", "属性类型", "属性类型简码", "属性", "属性简码", "数据类型", "算力层级", "数据来源", "备注"));
                res = checkObjectStructureHeadCell(workbook, sheetName, res, headCellName);
                break;
        }
        return res;
    }

    private static String checkHeadCell(Workbook workbook, String sheetName, String res, List<String> headCellName) throws Exception {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet != null) {
            Row head = workbook.getSheet(sheetName).getRow(0);
            if (head.getPhysicalNumberOfCells() != headCellName.size()) {
                throw new RRException(sheetName + "Sheet头校验不通过，实际头大小：" + head.getPhysicalNumberOfCells() + "headCellName size: " + headCellName.size());
            } else {
                for (int i = 0; i < head.getPhysicalNumberOfCells(); i++) {
                    if (!head.getCell(i).toString().equals(headCellName.get(i))) {
                        throw new RRException(sheetName + "Sheet头校验不通过");
                    }
                }
            }
        } else {
            throw new RRException(sheetName + "Sheet头校验不通过，Sheet页没有找到！");
        }
        return res;
    }

    private static String checkObjectStructureHeadCell(Workbook workbook, String sheetName, String res, List<String> headCellName) throws Exception {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet != null) {
            Row head = workbook.getSheet(sheetName).getRow(0);
            if (head.getPhysicalNumberOfCells() != headCellName.size()) {
                throw new RRException(sheetName + "Sheet头校验不通过，实际头大小：" + head.getPhysicalNumberOfCells() + "headCellName size: " + headCellName.size());
            } else {
                for (int i = 0; i < head.getPhysicalNumberOfCells(); i++) {
//                    if (head.getPhysicalNumberOfCells() != headCellName.size()) {
//                        throw new RRException(sheetName + "Sheet头校验不通过");
//                    }
                    if (!head.getCell(i).toString().equals(headCellName.get(i))) {
                        throw new RRException(sheetName + "Sheet头校验不通过");
                    }
                }
            }
        }
        return res;
    }



    private static Sheet getSheet(Workbook workbook, String dataType) throws Exception {
        Sheet sheet;
        String sheetName;

        ImportSheetNameEnum importSheetNameEnum = ImportSheetNameEnum.init(dataType);

        if (importSheetNameEnum == null) {
            //logger.error("数据类型不正确。");
            throw new RRException("数据类型不正确。");
        } else {
            sheetName = importSheetNameEnum.getName();
            sheet = workbook.getSheet(sheetName);
        }

        return sheet;
    }

    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            wb = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            wb = new XSSFWorkbook(inStr);
        } else {
            throw new RRException("解析的文件格式有误！");
        }
        return wb;
    }

    public static void setCellValueByModel(XSSFSheet sheet, List<Map<String, Object>> mapList) {
        //获取头
        Row rowHead = sheet.getRow(0);
        //表格列数
        int cellNums = rowHead.getPhysicalNumberOfCells();
        for (int i = 0; i < mapList.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Map<String, Object> map = mapList.get(i);
            for (int j = 0; j < cellNums; j++) {
                String name = "";
                Cell cell = rowHead.getCell(j);
                if (cell != null) {
                    Object object = map.get(cell.toString());

                    if (object != null) {
                        name = object.toString();
                    } else {
                        // System.out.println("##### map.get(" + cell.toString() + ") is null, current j:" + j);
                    }
                } else {
                    // System.out.println("##### Cell is null, current  j:" + j);
                }

                row.createCell(j).setCellValue(name);
            }
        }
    }
}
