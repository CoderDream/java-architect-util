package com.coderdream.freeapps.util.ppt;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.springframework.core.io.ClassPathResource;

public class CdPptxUtils {

    public static void main(String[] args) {
        String targetPath = "result.pptx";
        CdPptxUtils.genPpt(targetPath);
    }

    public static void genPpt(String targetPath) {
        // 读取模板文件
        ClassPathResource resource = new ClassPathResource("templates/test.pptx");
        // 根据模板，创建一个新的ppt文档
        XMLSlideShow ppt;
        try {
            ppt = new XMLSlideShow(resource.getInputStream());

            // 得到每页ppt
            List<XSLFSlide> slides = ppt.getSlides();
            // 遍历ppt，填充模板
            for (int i = 0; i < slides.size(); i++) {
                // 遍历每页ppt中待填充的形状组件
                for (XSLFShape shape : slides.get(i).getShapes()) {
                    if (shape instanceof TextShape) {
                        // 替换文本
                        TextShape textShape = (TextShape) shape;
                        TextRun textRun;
                        String text = textShape.getText();
                        switch (text) {
                            case "username":
                                textRun = textShape.setText("张三");
                                textRun.setFontFamily("宋体(正文)");
                                textRun.setFontSize(18.0);
                                break;
                            case "reportDate":
                                textRun = textShape.setText("2022-10-30");
                                textRun.setFontFamily("宋体(正文)");
                                textRun.setFontSize(18.0);
                                break;
                            case "completedCnt":
                                textRun = textShape.setText("16");
                                textRun.setFontFamily("宋体(正文)");
                                textRun.setFontSize(18.0);
                                textRun.setFontColor(Color.green);
                                break;
                            case "UnCompletedCnt":
                                textRun = textShape.setText("23");
                                textRun.setFontFamily("宋体(正文)");
                                textRun.setFontSize(18.0);
                                textRun.setFontColor(Color.red);
                                break;
                            case "planDate":
                                textRun = textShape.setText("2022-11-25");
                                textRun.setFontFamily("宋体(正文)");
                                textRun.setFontSize(18.0);
                                textRun.setFontColor(Color.blue);
                                break;
                        }
                    } else if (shape instanceof PictureShape) {
                        // 替换图片
                        PictureData pictureData = ((PictureShape) shape).getPictureData();
                        pictureData.setData(FileUtils.readFileToByteArray(new File("D:\\images\\" + i + ".jpg")));
                    }
                }
            }

            // 将新的ppt写入到指定的文件中
            FileOutputStream outputStream = new FileOutputStream(targetPath);
            ppt.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 复制ppt单页
     *
     * @param template 模板页
     * @param ppt      ppt
     * @param newIndex 复制页放置位置
     * @return 复制页
     */
    public static XSLFSlide copySlide(XMLSlideShow slideShow, XSLFSlide slide) {
        List<XSLFShape> shapes = slide.getShapes();
        XSLFSlide s1 = slideShow.createSlide();
        if (shapes.size() > 0) {
            for (XSLFShape shape : shapes) {
                s1.importContent(shape.getSheet());
            }
        }
        return s1;
    }

    /**
     * 复制ppt单页
     *
     * @param template 模板页
     * @param ppt      ppt
     * @param newIndex 复制页放置位置
     * @return 复制页
     */
    public static XSLFSlide copySlide(XSLFSlide template, XMLSlideShow ppt, int newIndex) {
        // 创建新幻灯片且填充内容
        XSLFSlide newSlide = ppt.createSlide().importContent(template);
        // 排序（在PPT中的第几页）
        ppt.setSlideOrder(newSlide, newIndex);
        return newSlide;
    }

    /*
     * 获取PPT的所有图片，并进行更改
     */
    public static void fn() {
        // 获取PPT中的所有幻灯片
        XMLSlideShow ppt;
        try {
            // 通过输入流读取一个现有的PPT文件，生成PPT类
            String filePath = CdPptxUtils.getTemplateOne();
            ppt = new XMLSlideShow(new FileInputStream(filePath));
            List<XSLFSlide> slides = ppt.getSlides();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 获取PPT的所有图片，并进行更改
     */
    public static void fillPicture(XSLFSlide slide) {
        // 获取PPT中的所有幻灯片
        XMLSlideShow ppt;
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTemplateOne() {
        File directory = new File("src/main/resources");
        String reportPath;
        try {
            reportPath = directory.getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String filePath = reportPath + "/ppt/apps-12.pptx";
        return filePath;
    }

    public static String getTemplateFullPath(String fileName) {
        File directory = new File("src/main/resources");
        String realPath;
        try {
            realPath = directory.getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return realPath + "/ppt/" + fileName;
    }
}
