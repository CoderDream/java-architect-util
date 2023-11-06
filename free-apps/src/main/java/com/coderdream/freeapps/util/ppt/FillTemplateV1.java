package com.coderdream.freeapps.util.ppt;

import com.alibaba.excel.util.FileUtils;
import com.coderdream.freeapps.util.bbc.BbcConstants;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.hslf.usermodel.HSLFPictureShape;
import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFConnectorShape;
import org.apache.poi.xslf.usermodel.XSLFFreeformShape;
import org.apache.poi.xslf.usermodel.XSLFGroupShape;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSimpleShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ObjectUtils;

/**
 * @author CoderDream
 */
public class FillTemplateV1 {

    public static void main(String[] args) {
        String pptxFileName = "6min.pptx";
        String folderName = "230504";
        String titleImage = folderName + ".jpg";
        String year = "20" + folderName.substring(0, 2);
        String titleImageWithPath =
            BbcConstants.ROOT_FOLDER_NAME + year + File.separator + folderName + File.separator + titleImage;

        String pptxFileNameNew =
            BbcConstants.ROOT_FOLDER_NAME + year + File.separator + folderName + File.separator + folderName + ".pptx";
        process(pptxFileName, pptxFileNameNew, titleImageWithPath);

        // 读取模板文件
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(CdPptxUtils.getTemplatePath() + pptxFileName);
//
//            getPPTXText(pptxFileName, pptxFileNameNew, fis, titleImageWithPath);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    public static void process(String pptxFileName, String pptxFileNameNew, String titleImageWithPath) {

        try {
            // 读取模板文件
            FileInputStream fis = new FileInputStream(CdPptxUtils.getTemplatePath() + pptxFileName);
            // 根据模板，创建一个新的ppt文档
            XMLSlideShow ppt = new XMLSlideShow(fis);

            // 得到每页ppt
            List<XSLFSlide> slides = ppt.getSlides();
            // 遍历ppt，填充模板
            for (int i = 0; i < slides.size(); i++) {
                // 遍历每页ppt中待填充的形状组件
                List<XSLFShape> xslfShapeList = slides.get(i).getShapes();
                for (XSLFShape shape : xslfShapeList) {
//                    System.out.println(shape.getClass());

                    String name = shape.getClass().getName();
                    System.out.println(name);
//                    switch  () {
//                        case
//                    }

                    if (shape instanceof XSLFGroupShape) {
                        List<XSLFShape> shapes = ((XSLFGroupShape) shape).getShapes();
                        for (XSLFShape shape1 : shapes) {
                            System.out.println(shape1.getClass());
                            if (shape1 instanceof XSLFGroupShape) {
                                List<XSLFShape> shapes2 = ((XSLFGroupShape) shape1).getShapes();
                                for (XSLFShape shape2 : shapes2) {
                                    System.out.println(shape2.getClass());
                                }
                            } else if (shape1 instanceof XSLFPictureShape) {
                                System.out.println("XSLFPictureShape 222");
                            }
                        }
                    }

                    if (shape instanceof XSLFAutoShape) {

                        System.out.println("XSLFAutoShape 333");

//                        List<XSLFShape> shapes = ((XSLFGroupShape) shape).getShapes();
//                        for (XSLFShape shape1 : shapes) {
//                            System.out.println(shape1.getClass());
//                            if (shape1 instanceof XSLFGroupShape) {
//                                List<XSLFShape> shapes2 = ((XSLFGroupShape) shape1).getShapes();
//                                for (XSLFShape shape2 : shapes2) {
//                                    System.out.println(shape2.getClass());
//                                }
//                            } else if (shape1 instanceof XSLFPictureShape) {
//                                System.out.println("XSLFPictureShape 222");
//                            }
//                        }
                    }

                    if (shape instanceof XSLFConnectorShape) {

                        System.out.println("XSLFConnectorShape 333");
                    }

//                    if (shape instanceof TextShape) {
//                        // 替换文本
//                        TextShape textShape = (TextShape) shape;
//                        TextRun textRun;
//                        String text = textShape.getText();
//                        switch (text) {
//                            case "username":
//                                textRun = textShape.setText("张三");
//                                textRun.setFontFamily("宋体(正文)");
//                                textRun.setFontSize(18.0);
//                                break;
//                            case "reportDate":
//                                textRun = textShape.setText("2022-10-30");
//                                textRun.setFontFamily("宋体(正文)");
//                                textRun.setFontSize(18.0);
//                                break;
//                            case "completedCnt":
//                                textRun = textShape.setText("16");
//                                textRun.setFontFamily("宋体(正文)");
//                                textRun.setFontSize(18.0);
//                                textRun.setFontColor(Color.green);
//                                break;
//                            case "UnCompletedCnt":
//                                textRun = textShape.setText("23");
//                                textRun.setFontFamily("宋体(正文)");
//                                textRun.setFontSize(18.0);v\IntelliJ_IDEA_2022.2\lib\idea_rt.jar=56249:D:\03_Dev\IntelliJ_IDEA_2022.2\bin -Dfile.encoding=UTF-8 -classpath C:\Users\CoderDream\AppData\Local\Temp\classpath2136583857.jar com.coderdream.freeapps.util.ppt.FillTemplateV1
//org.apache.poi.xslf.usermodel.XSLFAutoShape
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFTextBox
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFConnectorShape
//XSLFConnectorShape 333
//org.apache.poi.xslf.usermodel.XSLFGroupShape
//class org.apache.poi.xslf.usermodel.XSLFAutoShape
//class org.apache.poi.xslf.usermodel.XSLFTextBox
//class org.apache.poi.xslf.usermodel.XSLFConnectorShape
//class org.apache.poi.xslf.usermodel.XSLFAutoShape
//class org.apache.poi.xslf.usermodel.XSLFAutoShape
//org.apache.poi.xslf.usermodel.XSLFTextBox
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFPictureShape
//org.apache.poi.xslf.usermodel.XSLFAutoShape
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFConnectorShape
//XSLFConnectorShape 333
//org.apache.poi.xslf.usermodel.XSLFConnectorShape
//XSLFConnectorShape 333
//org.apache.poi.xslf.usermodel.XSLFGroupShape
//class org.apache.poi.xslf.usermodel.XSLFAutoShape
//class org.apache.poi.xslf.usermodel.XSLFGroupShape
//class org.apache.poi.xslf.usermodel.XSLFTextBox
//class org.apache.poi.xslf.usermodel.XSLFConnectorShape
//class org.apache.poi.xslf.usermodel.XSLFAutoShape
//org.apache.poi.xslf.usermodel.XSLFAutoShape
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFAutoShape
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFGroupShape
//class org.apache.poi.xslf.usermodel.XSLFAutoShape
//class org.apache.poi.xslf.usermodel.XSLFAutoShape
//class org.apache.poi.xslf.usermodel.XSLFTextBox
//class org.apache.poi.xslf.usermodel.XSLFConnectorShape
//org.apache.poi.xslf.usermodel.XSLFAutoShape
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFAutoShape
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFTextBox
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFTextBox
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFAutoShape
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFAutoShape
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFAutoShape
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFPictureShape
//org.apache.poi.xslf.usermodel.XSLFAutoShape
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFConnectorShape
//XSLFConnectorShape 333
//org.apache.poi.xslf.usermodel.XSLFConnectorShape
//XSLFConnectorShape 333
//org.apache.poi.xslf.usermodel.XSLFGroupShape
//class org.apache.poi.xslf.usermodel.XSLFAutoShape
//class org.apache.poi.xslf.usermodel.XSLFGroupShape
//class org.apache.poi.xslf.usermodel.XSLFTextBox
//class org.apache.poi.xslf.usermodel.XSLFConnectorShape
//class org.apache.poi.xslf.usermodel.XSLFAutoShape
//org.apache.poi.xslf.usermodel.XSLFAutoShape
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFAutoShape
//XSLFAutoShape 333
//org.apache.poi.xslf.usermodel.XSLFGroupShape
//class org.apache.poi.xslf.usermodel.XSLFAutoShape
//                                textRun.setFontColor(Color.red);
//                                break;
//                            case "planDate":
//                                textRun = textShape.setText("2022-11-25");
//                                textRun.setFontFamily("宋体(正文)");
//                                textRun.setFontSize(18.0);
//                                textRun.setFontColor(Color.blue);
//                                break;
//                            default:
//                                System.out.println("Unknown type");
//                                break;
//                        }
//                    } else if (shape instanceof PictureShape) {
//                        // 替换图片
//                        PictureData pictureData = ((PictureShape) shape).getPictureData();
//                        pictureData.setData(FileUtils.readFileToByteArray(new File(titleImageWithPath)));
//                    }
                }
            }

            //获取图片信息
            List pictureDateList = ppt.getPictureData();
            if (!ObjectUtils.isEmpty(pictureDateList)) {
                int size = pictureDateList.size();
                System.out.println("PictureData: " + size);
                if (pictureDateList.size() > 1) {
                    XSLFPictureData picture = (XSLFPictureData) pictureDateList.get(1);
                    picture.setData(FileUtils.readFileToByteArray(new File(titleImageWithPath)));
                }
            }

            // 将新的ppt写入到指定的文件中
            FileOutputStream outputStream = new FileOutputStream(pptxFileNameNew);
            ppt.write(outputStream);
            outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void getPPTXText(String fileName, String pptxFileNameNew, InputStream inputStream,
        String titleImageWithPath) throws Exception {
        try {
            SlideShow slideShow = null;
            if (fileName.toLowerCase().endsWith(".pptx")) {
                slideShow = new XMLSlideShow(inputStream);
            }
            List<XSLFSlide> slides = slideShow.getSlides();
            StringBuffer content = new StringBuffer();
            for (Object slide : slides) {
                if (slide instanceof XSLFSlide) {
                    List<XSLFShape> shapes = ((XSLFSlide) slide).getShapes();
                    for (XSLFShape sh : shapes) {
                        getShapesTxt(sh, content);
                    }
                }

            }
            //获取图片信息
            List pictureDateList = slideShow.getPictureData();
            if (!ObjectUtils.isEmpty(pictureDateList)) {
                if (pictureDateList.size() == 2) {
                    XSLFPictureData picture = (XSLFPictureData) pictureDateList.get(1);
//                    InputStream sbs = new ByteArrayInputStream(picture.getData());
//                        System.out.println( i+" : "+ sbs.toString());
                    picture.setData(FileUtils.readFileToByteArray(new File(titleImageWithPath)));
                }
//                for (int i = 0; i < pictureDateList.size(); i++) {
//                    if (fileName.toLowerCase().endsWith(".pptx")) {
//                        XSLFPictureData picture = (XSLFPictureData) pictureDateList.get(i);
//                        InputStream sbs = new ByteArrayInputStream(picture.getData());
////                        System.out.println( i+" : "+ sbs.toString());
//                        picture.setData(FileUtils.readFileToByteArray(new File(titleImageWithPath)));
//
//                        //                        // 替换图片
////                        PictureData pictureData = ((PictureShape) shape).getPictureData();
////                        pictureData.setData();
//                    }
//                }
            }

            // 将新的ppt写入到指定的文件中
            FileOutputStream outputStream = new FileOutputStream(pptxFileNameNew);
            slideShow.write(outputStream);
            outputStream.close();
        } finally {
            if (inputStream != null) {
                // 关闭输出流
                inputStream.close();
            }
        }

    }

    //获取文本信息
    private static void getShapesTxt(XSLFShape shape, StringBuffer content) {
        if (shape instanceof XSLFTextShape) {
            XSLFTextShape txtshape = (XSLFTextShape) shape;
            String sentence = txtshape.getText();
            content.append(sentence);
//	          System.out.println(txtshape.getText());
        } else if (shape instanceof XSLFAutoShape) {
            XSLFAutoShape autoShape = (XSLFAutoShape) shape;
            content.append(autoShape.getText());
        } else if (shape instanceof XSLFFreeformShape) {
            XSLFFreeformShape shape2 = (XSLFFreeformShape) shape;
            content.append(shape2.getText());
        } else if (shape instanceof XSLFConnectorShape) {
            XSLFConnectorShape shape2 = (XSLFConnectorShape) shape;
            content.append(shape2.getShapeName());
        } else if (shape instanceof XSLFPictureShape) {
            XSLFPictureShape shape2 = (XSLFPictureShape) shape;
            content.append(shape2.getShapeName());
        } else if (shape instanceof XSLFGroupShape) {//如果是组合类型，继续下钻直到取到文本
            XSLFGroupShape shape2 = (XSLFGroupShape) shape;
            for (XSLFShape xslfShape : shape2) {
                getShapesTxt(xslfShape, content);
            }
        } else if (shape instanceof XSLFSimpleShape) {
            XSLFSimpleShape shape2 = (XSLFSimpleShape) shape;
            content.append(shape2.getShapeName());
        }
    }

}
