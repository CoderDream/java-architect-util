package com.coderdream.freeapps.util.ppt.b;

import com.coderdream.freeapps.util.ppt.CdPptxUtils;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class Demo2 {

    public static void main(String[] args) {
        XMLSlideShow ppt = null;
        try {
            // 通过输入流读取一个现有的PPT文件，生成PPT类
            String filePath = CdPptxUtils.getTemplateOne();
            ppt = new XMLSlideShow(new FileInputStream(filePath));
            // 在现有的PPT文件后面新建一个空白幻灯片
//            XSLFSlide slide = ppt.createSlide();


            /*
             * 获取PPT的所有文本框里的文字，并进行更改
             */
// 获取PPT中的所有幻灯片
            List<XSLFSlide> slides = ppt.getSlides();
// 遍历幻灯片
            for (XSLFSlide slide : slides) {
                // 获取幻灯片中的所有图形（文本框、表格、图形...）
                List<XSLFShape> shapes = slide.getShapes();
                // 遍历图形
                for (XSLFShape shape : shapes) {
                    // 判断该图形类是否是文本框类
                    if (shape instanceof XSLFTextShape) {
                        // 将图像类强制装换成文本框类
                        XSLFTextShape ts = (XSLFTextShape) shape;
                        // 获取文本框内的文字
                        String str = ts.getText();
                        System.out.println(str);

                        // 若想对文本框内的文字进行更改，还需要进行如下步骤
                        List<XSLFTextParagraph> textParagraphs = ts.getTextParagraphs();
                        for (XSLFTextParagraph tp : textParagraphs) {
                            List<XSLFTextRun> textRuns = tp.getTextRuns();
                            for (XSLFTextRun r : textRuns) {
                                if ("201809".equals(r.getRawText())) {
                                    // 对匹配到的字符串进行更改
                                    r.setText("2018-09");
                                    // 设置字体颜色
                                    r.setFontColor(Color.RED);
                                }
                            }
                        }
                    }
                }
            }

            /*
             * 获取PPT的所有表格里的文字，并进行更改
             */
// 获取PPT中的所有幻灯片
//            List<XSLFSlide> slides = ppt.getSlides();
            for (XSLFSlide slide : slides) {
                // 获取幻灯片中的所有图形（文本框、表格、图形...）
                List<XSLFShape> shapes = slide.getShapes();
                for (XSLFShape shape : shapes) {
                    // 判断该图形类是否是表格类
                    if (shape instanceof XSLFTable) {
                        // 将图像类强制装换成表格类
                        XSLFTable table = (XSLFTable) shape;
                        // 获取表格中的所有行
                        List<XSLFTableRow> rows = table.getRows();
                        for (XSLFTableRow tr : rows) {
                            // 获取行中的所有单元格
                            List<XSLFTableCell> cells = tr.getCells();
                            for (XSLFTableCell tc : cells) {
                                // 获取单元格内的文字
                                String str = tc.getText();
                                // 若想对表格内的文字进行更改，还需要进行如下步骤
                                List<XSLFTextParagraph> textParagraphs = tc.getTextParagraphs();
                                for (XSLFTextParagraph tp : textParagraphs) {
                                    List<XSLFTextRun> textRuns = tp.getTextRuns();
                                    for (XSLFTextRun r : textRuns) {
                                        if ("风险指标".equals(r.getRawText())) {
                                            // 对匹配到的字符串进行更改
                                            r.setText("测试修改文字");
                                            // 设置字体颜色
                                            r.setFontColor(Color.RED);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }


            /*
             * 获取PPT的所有图片，并进行更改
             */
            // 获取PPT中的所有幻灯片
            //            List<XSLFSlide> slides = ppt.getSlides();
            for (XSLFSlide slide : slides) {
                // 获取幻灯片中的所有图形（文本框、表格、图形...）
                List<XSLFShape> shapes = slide.getShapes();
                for (XSLFShape shape : shapes) {
                    // 判断该图形类是否是图片框类
                    if (shape instanceof XSLFPictureShape) {
                        /*
                         * 获取图片数据
                         */
                        // 将图像类强制装换成图片框类
                        XSLFPictureShape ps = (XSLFPictureShape) shape;
                        // 获取图片的字节码数据（可以利用输出流将该图片保存到硬盘里）
                        byte[] pictureData = ps.getPictureData().getData();
                        /*
                         * 更改图片
                         */
                        // 图片文件
                        File image = new File("D://2.jpg");
                        // 图片文件输入流
                        FileInputStream imageFis = new FileInputStream(image);
                        // 获取图片大小
                        int len = (int) image.length();
                        // 创建一个字节数组，数组大小与图片文件大小一致
                        byte[] imageData = new byte[len];

                        if (imageFis.read(imageData) != -1) {
                            // 更换图片必须图片设置索引，要不不生效
                            ps.getPictureData().setIndex(1);
                            ps.getPictureData().setData(imageData);
                        }
                        // 关闭输入流
                        imageFis.close();
                    }
                }
            }
            // 将修改后的PPT文件回写到硬盘
            ppt.write(new FileOutputStream("D://test3.pptx"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ppt != null) {
                try {
                    // 保存完之后要对PPT进行关闭操作
                    ppt.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
