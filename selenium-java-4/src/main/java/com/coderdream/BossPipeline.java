package com.coderdream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class BossPipeline implements Pipeline {

    private final static String excel2003L = ".xls";    //2003- 版本的excel
    private final static String excel2007U = ".xlsx";   //2007+ 版本的excel
    public static Integer integer = new Integer (0);
    public static List<WorkInf> workInfList = new ArrayList<>();

    @Override
    public void process(ResultItems result, Task task) {
        //设计存储过程
        WorkInf workInf = result.get("workInf");
        if (workInf == null) {
            return;
        }
        synchronized (BossPipeline.class) {
            workInfList.add(workInf);
            /*try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //多线程的话在这里获取一下锁
            int howManyWorkStart = 10;
            if (workInfList.size() >= howManyWorkStart) {
                //追加存到Excel中
                try {
                    String path = "D:\\temp\\BOSS_Jobs_template.xlsx";
                    FileInputStream fileInputStream = new FileInputStream(path);
                    XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);//得到文档对象
                    Sheet sheet = workbook.getSheetAt(0);
                    int lastRowNum = sheet.getLastRowNum();
                    for (int i = 1; i <= howManyWorkStart; i++) {
                        WorkInf inf = workInfList.get(i - 1);
                        Row row = sheet.createRow(lastRowNum + i);
                        for (int j = 0; j < 9; j++) {
                            Cell cell = row.createCell(j);
                            switch (j) {
                                case 0:
                                    cell.setCellValue(inf.getUrl());
                                    break;
                                case 1:
                                    cell.setCellValue(inf.getWorkName());
                                    break;
                                case 2:
                                    cell.setCellValue(inf.getWorkSalary());
                                    break;
                                case 3:
                                    cell.setCellValue(inf.getWorkAddress());
                                    break;
                                case 4:
                                    cell.setCellValue(inf.getWorkContent());
                                    break;
                                case 5:
                                    cell.setCellValue(inf.getWorkYear());
                                    break;
                                case 6:
                                    cell.setCellValue(inf.getGraduate());
                                    break;
                                case 7:
                                    cell.setCellValue(inf.getHRTime());
                                    break;
                                case 8:
                                    cell.setCellValue(inf.getCompanyName());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(path);
                    fileOutputStream.flush();
                    workbook.write(fileOutputStream);
                    fileOutputStream.close();
                    //清空List
                    workInfList.clear();
                    System.out.println("===============workInfSize=======================");
                    System.out.println("当前workInfList的长度为：" + workInfList.size() + " ，完成一轮爬取");
                    System.out.println("==================================================");
                    integer++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (integer > 500) {
                System.exit(0);
            }
        }
    }

    /*根据文件的后缀名去确定workbook的类型*/
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);  //2003-
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);  //2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }
}
