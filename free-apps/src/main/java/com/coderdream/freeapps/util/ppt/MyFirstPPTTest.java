package com.coderdream.freeapps.util.ppt;

//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import org.apache.poi.hslf.model.Picture;
//import org.apache.poi.util.IOUtils;
//import org.apache.poi.xslf.usermodel.XMLSlideShow;
//import org.apache.poi.xslf.usermodel.XSLFPictureData;
//import org.apache.poi.xslf.usermodel.XSLFPictureShape;
//import org.apache.poi.xslf.usermodel.XSLFSlide;

/**
 * PPT简单导出
 * Created by Ay on 2016/6/14.
 */
public class MyFirstPPTTest {


//    public static void main(String[] args) throws Exception{
//        /** 文件路径 **/
//        String filePath = "D://MyPPT.pptx";
//        String imagePath = "D://a.png";
//        String imagePath2 = "D://b.png";
//        String imagePath3 = "D://c.png";
//        /** 加载PPT **/
//        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(filePath));
//
//        /** 创建一个slide，理解为PPT里的每一页 **/
//        XSLFSlide slide = ppt.createSlide();
//        XSLFSlide slide2 = ppt.createSlide();
//        XSLFSlide slide3 = ppt.createSlide();
//        /** 生成二进制数组 **/
//        byte[] pictureData = IOUtils.toByteArray(new FileInputStream(imagePath));
//        byte[] pictureData2 = IOUtils.toByteArray(new FileInputStream(imagePath2));
//        byte[] pictureData3 = IOUtils.toByteArray(new FileInputStream(imagePath3));
//        /** 添加图片,返回索引 **/
//        int pictureIndex =  ppt.addPicture(pictureData, XSLFPictureData.PICTURE_TYPE_PNG);
//        int pictureIndex2 =  ppt.addPicture(pictureData2, XSLFPictureData.PICTURE_TYPE_PNG);
//        int pictureIndex3 =  ppt.addPicture(pictureData3, XSLFPictureData.PICTURE_TYPE_PNG);
//        /** 打印信息 **/
//        System.out.println("pictureIndex " + pictureIndex);// pictureIndex   0
//        System.out.println("pictureIndex2 " + pictureIndex2);//  pictureIndex2  1
//        System.out.println("pictureIndex3 " + pictureIndex3);//  pictureIndex3  2
//        /** 创建图片 **/
//        XSLFPictureShape pictureShape = slide.createPicture(pictureIndex);
//        XSLFPictureShape pictureShape2 = slide2.createPicture(pictureIndex2);
//        XSLFPictureShape pictureShape3 = slide3.createPicture(pictureIndex3);
//
//        /** 设置图片的位置 四个参数分别为 x y width height  **/
//        pictureShape.setAnchor(new java.awt.Rectangle(50, 50, 500, 300));
//        pictureShape2.setAnchor(new java.awt.Rectangle(50, 150, 500, 300));
//        pictureShape3.setAnchor(new java.awt.Rectangle(50, 250, 500, 300));
//
//        /** 获取图片类别 **/
//        int pictureType = pictureShape.getPictureData().getPictureType();
//
//        switch (pictureType){
//            case Picture.JPEG:
//                System.out.println("the type of picture is : " + "JPEG");
//                break;
//            case Picture.PNG:
//                System.out.println("the type of picture is :" + "PNG");
//                break;
//        }
//
//        System.out.println(pictureType);
//
//        /** 输出文件 **/
//        ppt.write(new FileOutputStream(filePath));
//    }

}
