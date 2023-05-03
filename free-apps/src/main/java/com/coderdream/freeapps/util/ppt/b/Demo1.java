package com.coderdream.freeapps.util.ppt.b;

import com.coderdream.freeapps.util.ppt.CdPptxUtils;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.TableCell.BorderEdge;
import org.apache.poi.sl.usermodel.TextParagraph.TextAlign;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFHyperlink;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class Demo1 {

    public static void main(String[] args) {
        // 创建一个空白PPT
        XMLSlideShow ppt = new XMLSlideShow();
        // 在空白的PPT中创建一个空白的幻灯片
        XSLFSlide slide = ppt.createSlide();
        XSLFSlide slide1 = CdPptxUtils.copySlide(ppt, slide);
        XSLFSlide slide2 = CdPptxUtils.copySlide(ppt, slide);
        try {
            /*
             * 在空幻灯片中插入一个文本框,然后在文本框中写入文字，
             * 并给文字添加一个超链接
             */
            // 在幻灯片中插入一个文本框
            XSLFTextShape ts = slide.createTextBox();
            // 设置文本框的位置和文本框大小
            ts.setAnchor(new Rectangle(150, 150, 200, 50));
            // 设置文本框里面的文字
            XSLFTextRun tr = ts.addNewTextParagraph().addNewTextRun();
            tr.setText("测试一下");
            // 给文本添加颜色
            tr.setFontColor(Color.RED);
            // 给文本添加超链接
            XSLFHyperlink link = tr.createHyperlink();
            link.setAddress("http://www.baidu.com");

            /*
             * 在幻灯片中添加表格
             */
            // 在幻灯片中插入一个表格
            XSLFTable table = slide1.createTable();
            // 设置表格的位置和表格大小
            table.setAnchor(new Rectangle(50, 100, 100, 100));
            for (int i = 0; i < 5; i++) {
                // 在表格中添加一行
                XSLFTableRow row = table.addRow();
                for (int j = 0; j < 5; j++) {
                    // 在行中添加一个单元格
                    XSLFTableCell cell = row.addCell();
                    // 设置单元格中的内容和样式
                    XSLFTextParagraph p = cell.addNewTextParagraph();
                    p.setTextAlign(TextAlign.CENTER);
                    XSLFTextRun tr1 = p.addNewTextRun();
                    tr1.setFontColor(Color.RED);
                    tr1.setText("测试" + i + j);
                    // 设置单元格边框的粗细
                    cell.setBorderWidth(BorderEdge.bottom, 2.0);
                    cell.setBorderWidth(BorderEdge.left, 2.0);
                    cell.setBorderWidth(BorderEdge.right, 2.0);
                    cell.setBorderWidth(BorderEdge.top, 2.0);
                    // 设置单元格边框的颜色
                    cell.setBorderColor(BorderEdge.bottom, Color.black);
                    cell.setBorderColor(BorderEdge.left, Color.black);
                    cell.setBorderColor(BorderEdge.right, Color.black);
                    cell.setBorderColor(BorderEdge.top, Color.black);
                }
            }
            /*
             * 在幻灯片中插入一张图片
             */
            // 图片文件
            File image = new File("D://2.jpg");
            // 图片文件输入流
            FileInputStream imageFis = new FileInputStream(image);
            // 获取图片大小
            int len = (int) image.length();
            // 创建一个字节数组，数组大小与图片文件大小一致
            byte[] imageData = new byte[len];
            // 将图片数据读进字节数组中
            imageFis.read(imageData);
            // 将图片添加到PPT中
            XSLFPictureData pd = ppt.addPicture(imageData, PictureData.PictureType.JPEG);
            // 将图片放到指定的幻灯片中
            XSLFPictureShape pic = slide2.createPicture(pd);
            // 设置图片框的放置的位置和大小
            pic.setAnchor(new Rectangle(50, 100, 200, 200));

            // 对新建的PPT保存到硬盘里
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
