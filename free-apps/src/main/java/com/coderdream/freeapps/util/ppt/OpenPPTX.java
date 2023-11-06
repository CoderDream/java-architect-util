package com.coderdream.freeapps.util.ppt;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class OpenPPTX {

    public static void main(String[] args) {
        String fileName = "apps-12.pptx";
        XMLSlideShow pptx = readPptx(fileName);
        readElements(pptx);
    }

    public static XMLSlideShow readPptx(String fileName) {
        File directory = new File("src/main/resources");
        String reportPath;
        try {
            reportPath = directory.getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // reportPath + "/ppt/apps-12.pptx";
        String filePath = reportPath + "/ppt/" + fileName;

        filePath = "D:\\05_Document\\LeYun\\10_Document\\03_转正\\许林个人简介.pptx";
//        try {
//            FileInputStream fis = new FileInputStream(filePath); // 打开ppt文件流
//            HSLFSlideShow ppt = new HSLFSlideShow(fis); // 创建HSLFSlideShow对象
//            fis.close(); // 关闭文件流
//            // 处理pptX文件
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        XMLSlideShow pptx = null;
        try {
            FileInputStream fis = new FileInputStream(filePath); // 打开pptX文件流
            pptx = new XMLSlideShow(fis); // 创建XMLSlideShow对象
            fis.close(); // 关闭文件流
            // 处理pptX文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pptx;
    }

    public static void readElements(XMLSlideShow pptx) {
        int index = 0;
        for (XSLFSlide slide : pptx.getSlides()) {
            index++;
            System.out.println("index:" + index);
            // 处理每个幻灯片
            // 获取幻灯片中的所有形状遍历
            for (XSLFShape shape : slide.getShapes()) {
                System.out.println("shape class: " + shape.getClass());
                if (shape instanceof XSLFTextBox) {
                    XSLFTextBox xslfTextBox = (XSLFTextBox) shape;
                    // 文本框
                    System.out.println(xslfTextBox.getText());
                    System.out.println("我是：XSLFTextBox");
                }
                if (shape instanceof XSLFPictureShape) {
                    System.out.println("我是：XSLFPictureShape");
                    // 图片
                }
                if (shape instanceof XSLFTable) {
//                    System.out.println("我是：XSLFTable");
                    // 表格
                    XSLFTable xslfTable = (XSLFTable) (shape);
                    List<XSLFTableRow> xslfTableRowList = xslfTable.getRows();
                    if (CollectionUtils.isNotEmpty(xslfTableRowList)) {
                        for (XSLFTableRow xSLFTableRow : xslfTableRowList) {
//                            System.out.println(xSLFTableRow.toString());
                            List<XSLFTableCell> cells = xSLFTableRow.getCells();
                            if (CollectionUtils.isNotEmpty(cells)) {
                                for (XSLFTableCell xslfTableCell : cells) {
                                    System.out.println(xslfTableCell.toString());
                                }
                            }
                        }
                    }
                }

                if (shape instanceof XSLFTextShape) {
                    // 表格
                    XSLFTextShape xslfTextShape = (XSLFTextShape) shape; // 获取文本框形状

//                    System.out.println("我是：XSLFTextShape: ");

                    System.out.println(xslfTextShape.getText());
                    int x = 10;
                    int y = 10;
                    int width = 100;
                    int height = 100;
                    Rectangle2D anchor = new Rectangle2D.Double(x, y, width, height); // 新的位置和大小
                    xslfTextShape.setAnchor(anchor); // 设置新的位置和大小
                }
                //……
            }
        }
    }

    /**
     * 复制ppt单页
     *
     * @param template 模板页
     * @param pptx     ppt
     * @param newIndex 复制页放置位置
     * @return 复制页
     */
    private XSLFSlide copySlide(XSLFSlide template, XMLSlideShow pptx, int newIndex) {
        // 创建新幻灯片且填充内容
        XSLFSlide newSlide = pptx.createSlide().importContent(template);
        // 排序（在PPT中的第几页）
        pptx.setSlideOrder(newSlide, newIndex);
        return newSlide;
    }


}

