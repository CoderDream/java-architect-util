package com.coderdream.freeapps.util.ppt.pptutil;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.PictureData.PictureType;
import org.apache.poi.sl.usermodel.TextParagraph.TextAlign;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;

public class PPTHelper1 {

    private static int PAGESIZE = 3; // 页面大小

    private static String BACKGROUND = "C:\\ProgramData\\Microsoft\\Device Stage\\Device\\{113527a4-45d4-4b6f-b567-97838f1b04b0}\\background.png"; // 背景图片路径，图片格式要一致

    private static String[] th = new String[] { "岗位名称", "基本要求", "薪资待遇" };

    @SuppressWarnings("resource")
    public static void createPPTX(String[][] title, String[][] data, String writePath) {
        try {
            System.out.println("-------------------------开始生成！------------------------");
            XMLSlideShow pptx = new XMLSlideShow();

            // 计算页数
            int pages = data.length / PAGESIZE;
            // 如果有余数，页数加1
            if (data.length % PAGESIZE > 0) {
                pages += 1;
            }
            // 初始化每页PPT
            XSLFSlide[] slides = new XSLFSlide[pages];
            for (int i = 0; i < pages; i++) {
                slides[i] = pptx.createSlide();
            }
            // 循环生成每页PPT
            for (int k = 0; k < pages; k++) {
                // 设置每页的背景
                byte[] pictureData = IOUtils.toByteArray(new FileInputStream(BACKGROUND));
                XSLFPictureData pd = pptx.addPicture(pictureData, PictureType.PNG);
                XSLFPictureShape pic = slides[k].createPicture(pd);
                // 图片全铺
                pic.setAnchor(new Rectangle2D.Double(0, 0, 720, 550));

                XSLFTable titleTB = slides[k].createTable();// 创建Title表格
                createTitle(titleTB, title);

                XSLFTable headTB = slides[k].createTable();// 创建表头
                createTH(headTB, th);

                XSLFTable dataTB = slides[k].createTable();// 创建Data表格
                if (pages == 1) {
                    createData(dataTB, data, 0, data.length);
                } else {
                    int start = k * PAGESIZE;// 起始位置
                    int end = (k + 1) * PAGESIZE;// 结束位置
                    if (end >= data.length) {
                        end = data.length;// 如果end>data的长度，取Data的长度
                    }
                    createData(dataTB, data, start, end);
                }

                XSLFTable text = slides[k].createTable();// 生成页码
                createPageNum(text, k + 1, pages);
            }

            pptx.write(new FileOutputStream(writePath));
            System.out.println("-------------------------生成成功！------------------------");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 创建标题
    private static void createTitle(XSLFTable table, String[][] title) {
        table.setAnchor(new Rectangle2D.Double(0, 10, 600, 100));
        for (int i = 0; i < title.length; i++) {
            XSLFTableRow tableRow = table.addRow(); // 创建表格行
            for (int j = 0; j < title[i].length; j++) {
                XSLFTableCell tableCell = tableRow.addCell();// 创建表格单元格
                XSLFTextParagraph p = tableCell.addNewTextParagraph();
                XSLFTextRun tr = p.addNewTextRun();
                tr.setText(String.valueOf(title[i][j]));
                tr.setBold(true);
                tr.setFontColor(Color.white);
                tr.setFontFamily("宋体 (正文)");
                tr.setFontSize(18.);
            }
        }
        table.setColumnWidth(0, 132);
        table.setColumnWidth(1, 580);
        table.setRowHeight(0, 35);
        table.setRowHeight(1, 35);
        table.setRowHeight(2, 35);
        System.out.println("-------------------------创建标题成功！------------------------");
    }

    // 创建表头
    private static void createTH(XSLFTable table, String[] th) {
        table.setAnchor(new Rectangle2D.Double(0, 140, 600, 100));
        XSLFTableRow tableRow = table.addRow(); // 创建表格行
        for (int i = 0; i < th.length; i++) {
            XSLFTableCell tableCell = tableRow.addCell();// 创建表格单元格
            XSLFTextParagraph p = tableCell.addNewTextParagraph();
            p.setTextAlign(TextAlign.CENTER);
            tableCell.setVerticalAlignment(VerticalAlignment.MIDDLE);
            XSLFTextRun tr = p.addNewTextRun();
            tr.setText(String.valueOf(th[i]));
            tr.setBold(true);
            tr.setFontFamily("幼圆");
            tr.setFontSize(18.);
        }
        table.setColumnWidth(0, 132);
        table.setColumnWidth(1, 312);
        table.setColumnWidth(2, 276);
        table.setRowHeight(0, 10);
        System.out.println("-------------------------创建表头成功！------------------------");
    }

    // 创建表格数据
    private static void createData(XSLFTable table, String[][] data, int start, int end) {
        table.setAnchor(new Rectangle2D.Double(0, 180, 600, 420));
        for (int i = start, k = 0; i < end; i++, k++) {
            XSLFTableRow tableRow = table.addRow(); // 创建表格行
            for (int j = 0; j < data[i].length; j++) {
                XSLFTableCell tableCell = tableRow.addCell();// 创建表格单元格
                XSLFTextParagraph p = tableCell.addNewTextParagraph();
                if (j == 2) {
                    p.setTextAlign(TextAlign.LEFT);
                } else {
                    p.setTextAlign(TextAlign.CENTER);
                }
                tableCell.setVerticalAlignment(VerticalAlignment.MIDDLE);

                XSLFTextRun tr = p.addNewTextRun();
                if (data[i][j] != null) {
                    String value = String.valueOf(data[i][j]);
                    if (value.length() > 80) {
                        tr.setText(value.substring(0, 80) + "...");
                    } else {
                        tr.setText(String.valueOf(data[i][j]));
                    }

                }
                tr.setFontFamily("仿宋");
                tr.setFontSize(16.);
            }
            // 设置行高 因为每页的行数不固定，动态设置
            table.setRowHeight(k, 125);
        }
        // 设置列宽
        table.setColumnWidth(0, 132);
        table.setColumnWidth(1, 312);
        table.setColumnWidth(2, 276);
        System.out.println("-------------------------加载数据成功！------------------------");
    }

    // 创建右上角页码
    private static void createPageNum(XSLFTable table, int k, int pages) {
        table.setAnchor(new Rectangle2D.Double(560, 10, 200, 0));
        XSLFTableRow tableRow = table.addRow(); // 创建表格行
        XSLFTableCell tableCell = tableRow.addCell();// 创建表格单元格
        XSLFTextParagraph p = tableCell.addNewTextParagraph();
        p.setTextAlign(TextAlign.CENTER);
        tableCell.setVerticalAlignment(VerticalAlignment.MIDDLE);
        XSLFTextRun tr = p.addNewTextRun();
        tr.setText(String.valueOf("第" + k + "页/共" + pages + "页"));
        tr.setBold(true);
        tr.setFontColor(Color.YELLOW);
        tr.setFontFamily("H-新雅兰");
        tr.setFontSize(18.);
        table.setColumnWidth(0, 200);
    }

    public static void main(String[] args) throws IOException {

        String[][] title = { { "单位名称：", "北京XXXXXXXXXXXXX有限公司" }, { "单位地址：", "北京市XXXXXXXXXXXXXXXX" },
            { "联系电话：", "XXXXXXXX  XXXXXXX" } };
        String[][] data = {
            { "物业客服", "5人，男女不限，22-35岁，大专以上学历，有物业相关经验者优先。负责办理业主入住、装修手续，接待住户投诉和报修电话、咨询服务",
                "1.缴纳五项社会保险\n2.用工形式：直签\n3.签订合同_2_年\n4.月薪：2500-3000（有提成）\n5.其他福利：饭补、绩效奖金浮动" },
            { "社区报纸编辑", "1人，男女不限，25-40岁，大专以上学历，有相关工作经验者优先，负责报纸排版、组稿约稿、稿件的采编和图文编辑",
                "1.缴纳五项社会保险\n2.用工形式：直签\n3.签订合同_2_年\n4.月薪：3500-5000\n5.其他福利：饭补、绩效奖金浮动" },
            { "酒店前台", "男女不限，5人，有无经验均可，可接受应届毕业生，中专以上学历，会使用办公软件，包吃、包住、上五险。",
                "1.缴纳五项社会保险\n2.用工形式：直签\n3.签订合同_2_年\n4.月薪：3000-3500\n5.其他福利：饭补、绩效奖金浮动" },
            { "家政服务员", "女，6人，五官端正，18-50岁，初中以上学历，有相关工作经验者优先，本地户口优先。包吃，上五险，可根据个人表现晋升增薪。",
                "1.缴纳五项社会保险\n2.用工形式：直签\n3.签订合同_2_年\n4.月薪：2300-3000\n5.其他福利：饭补、绩效奖金  " },
            { "保安员", "男，20人，18-40岁，身高175及以上，初中及以上学历,无不良嗜好，无纹身，退伍军人优先，包吃住，五险。",
                "1.缴纳五项社会保险\n2.用工形式：直签/派遣\n3.签订合同_2_年\n4.月薪：2800-3500\n5.其他福利：包吃住、绩效奖金" },
            { "保洁员", "男女不限，40-55岁，初中及以上学历，有工作经验者优先，包吃，上五险，可根据个人表现晋升增薪。",
                "1.缴纳五项社会保险\n2.用工形式：直签/派遣\n3.签订合同_2_年\n4.月薪：2500-3500\n5.其他福利：包吃、绩效奖金" } };
        String writePath = "D:\\北京东方太阳城俱乐部有限公司招聘会简章.ppt";
        createPPTX(title, data, writePath);

    }
}
