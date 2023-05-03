package com.coderdream.freeapps.util.ppt;


import com.coderdream.freeapps.common.exception.BusinessException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
//import com.ruoyi.common.exception.BusinessException;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
//import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.xslf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fmi110
 * @description ppt 转图片；图片转pdf 工具
 * @date 2021/8/19 20:16
 */

public class PptTransferUtil {
//    protected static final Logger log = LoggerFactory.getLogger(PptTransferUtil.class);
//
//    public static int width  = 720;
//    public static int height = 540;
//
//    public static void PPTtoImage(String filePath, String fileName) throws Exception {
//        File f = new File(filePath + fileName);
//        if (f.exists()) {
//            if (f.getName().endsWith(".pptx") || f.getName().endsWith(".PPTX")) {
//                pptx2Image(filePath, fileName);
//            } else {
//                ppt2Image(filePath, fileName);
//            }
//        } else {
//            throw new BusinessException("文档不存在");
//        }
//    }
//
//    /**
//     * 将pptx转换为图片,直接保存在内存中,ppt很大时可能会oom!!!
//     *
//     * @param is ppt 输入流
//     * @return
//     * @throws IOException
//     */
//    public static List<byte[]> pptx2Image(InputStream is) throws IOException {
//        // 获取系统可用字体
//        GraphicsEnvironment e          = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        String[]            fontNames  = e.getAvailableFontFamilyNames();
//        List                availFonts = CollectionUtils.arrayToList(fontNames);
//
//        XMLSlideShow ppt      = new XMLSlideShow(is);
//        Dimension    pgsize   = ppt.getPageSize();
//        int          pageSize = ppt.getSlides().size();
//        List<byte[]> imgList  = new ArrayList<>(pageSize);
//
//        log.info("ppt 总页数：{}， 尺寸: width={},height={}", pageSize, pgsize.width, pgsize.height);
//
//        for (int i = 0; i < pageSize; i++) {
//            //防止中文乱码
//            for (XSLFShape shape : ppt.getSlides().get(i).getShapes()) {
//                if (shape instanceof XSLFTextShape) {
//                    XSLFTextShape tsh = (XSLFTextShape) shape;
//                    for (XSLFTextParagraph p : tsh) {
//                        for (XSLFTextRun r : p) {
//                            String fontFamily = r.getFontFamily();
//                            //log.info(">>>>>原始ppt字体：{}", fontFamily);
//                            fontFamily = "宋体";
//                            //log.info(">>>>>ppt字体修改为：{}", fontFamily);
//                            r.setFontFamily(fontFamily);
////                            if (!availFonts.contains(fontFamily)) {
////                                fontFamily = "宋体";
////                                log.info(">>>>>ppt字体修改为：{}", fontFamily);
////                                r.setFontFamily(fontFamily);
////                            }
//                        }
//                    }
//                }
//            }
//            BufferedImage img      = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
//            Graphics2D    graphics = img.createGraphics();
//            // clear the drawing area
//            graphics.setPaint(Color.white);
//            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
//            // render
//            ppt.getSlides().get(i).draw(graphics);
//            // save the output
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            javax.imageio.ImageIO.write(img, "png", out);
//            imgList.add(out.toByteArray());
//            IOUtils.closeQuietly(out);
//        }
//        if (is != null) {
//            IOUtils.closeQuietly(is);
//        }
//        return imgList;
//    }
//
//    /**
//     * 将pptx转成图片,保存在同一目录的image目录下
//     *
//     * @param filePath ppt文件路径
//     * @param fileName ppt 文件名
//     * @throws IOException
//     */
//    public static void pptx2Image(String filePath, String fileName) throws Exception {
//
//        // 获取系统可用字体
//        GraphicsEnvironment e          = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        String[]            fontNames  = e.getAvailableFontFamilyNames();
//        List                availFonts = CollectionUtils.arrayToList(fontNames);
//
//        FileInputStream is  = new FileInputStream(filePath + fileName);
//        XMLSlideShow    ppt = new XMLSlideShow(is);
//
//        Dimension pgsize   = ppt.getPageSize();
//        int       pageSize = ppt.getSlides().size();
//        log.info("ppt 总页数：{}， 尺寸: width={},height={}", pageSize, pgsize.width, pgsize.height);
//
//        for (int i = 0; i < pageSize; i++) {
//
//            //防止中文乱码
//            for (XSLFShape shape : ppt.getSlides().get(i).getShapes()) {
//                if (shape instanceof XSLFTextShape) {
//                    XSLFTextShape tsh = (XSLFTextShape) shape;
//                    for (XSLFTextParagraph p : tsh) {
//                        for (XSLFTextRun r : p) {
//                            String fontFamily = r.getFontFamily();
//                            //log.info(">>>>>原始ppt字体：{}", fontFamily);
//                            fontFamily = "宋体";
//                            //log.info(">>>>>ppt字体修改为：{}", fontFamily);
//                            r.setFontFamily(fontFamily);
////                            if (!availFonts.contains(fontFamily)) {
////                                fontFamily = "宋体";
////                                log.info(">>>>>ppt字体修改为：{}", fontFamily);
////                                r.setFontFamily(fontFamily);
////                            }
//                        }
//                    }
//                }
//            }
//            BufferedImage img      = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
//            Graphics2D    graphics = img.createGraphics();
//            // clear the drawing area
//            graphics.setPaint(Color.white);
//            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
//            // render
//            ppt.getSlides().get(i).draw(graphics);
//            // save the output
//            String pptname    = fileName.substring(0, fileName.lastIndexOf("."));
//            String newimgPath = filePath + "image/" + pptname + "/";
//            File   imgPath    = new File(newimgPath);
//            if (!imgPath.exists()) {//图片目录不存在则创建
//                imgPath.mkdirs();
//            }
//            String           file = newimgPath + (i + 1) + ".png";
//            FileOutputStream out  = new FileOutputStream(file);
//            javax.imageio.ImageIO.write(img, "png", out);
//            IOUtils.closeQuietly(out);
//        }
//        if (is != null) {
//            IOUtils.closeQuietly(is);
//        }
//    }
//
//    /**
//     * 将ppt转换为图片,直接保存在内存中,ppt很大时可能会oom!!!
//     *
//     * @param is ppt 输入流
//     * @return
//     * @throws IOException
//     */
//    public static List<byte[]> ppt2Image(InputStream is) throws IOException {
//        // 获取系统可用字体
//        GraphicsEnvironment e          = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        String[]            fontNames  = e.getAvailableFontFamilyNames();
//        List                availFonts = CollectionUtils.arrayToList(fontNames);
//
//        HSLFSlideShow ppt      = new HSLFSlideShow(new HSLFSlideShowImpl(is));
//        Dimension     pgsize   = ppt.getPageSize();
//        int           pageSize = ppt.getSlides().size();
//        List<byte[]>  imgList  = new ArrayList<>(pageSize);
//
//        log.info("ppt 总页数：{}， 尺寸: width={},height={}", pageSize, pgsize.width, pgsize.height);
//
//        for (int i = 0; i < pageSize; i++) {
//            //防止中文乱码
//            for (HSLFShape shape : ppt.getSlides().get(i).getShapes()) {
//                if (shape instanceof HSLFTextShape) {
//                    HSLFTextShape tsh = (HSLFTextShape) shape;
//                    for (HSLFTextParagraph p : tsh) {
//                        for (HSLFTextRun r : p) {
//                            String fontFamily = r.getFontFamily();
//                            //log.info(">>>>>原始ppt字体：{}", fontFamily);
//                            fontFamily = "宋体";
//                            //log.info(">>>>>ppt字体修改为：{}", fontFamily);
//                            r.setFontFamily(fontFamily);
////                            if (!availFonts.contains(fontFamily)) {
////                                fontFamily = "宋体";
////                                log.info(">>>>>ppt字体修改为：{}", fontFamily);
////                                r.setFontFamily(fontFamily);
////                            }
//                        }
//                    }
//                }
//            }
//            BufferedImage img      = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
//            Graphics2D    graphics = img.createGraphics();
//            // clear the drawing area
//            graphics.setPaint(Color.white);
//            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
//            // render
//            ppt.getSlides().get(i).draw(graphics);
//            // save the output
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            javax.imageio.ImageIO.write(img, "png", out);
//            imgList.add(out.toByteArray());
//            IOUtils.closeQuietly(out);
//        }
//        if (is != null) {
//            IOUtils.closeQuietly(is);
//        }
//        return imgList;
//    }
//
//    /**
//     * 将ppt转成图片,保存在同一目录的image目录下
//     *
//     * @param filePath ppt文件路径
//     * @param fileName ppt 文件名
//     * @throws IOException
//     */
//    public static void ppt2Image(String filePath, String fileName) throws IOException {
//        // 获取系统可用字体
//        GraphicsEnvironment e          = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        String[]            fontNames  = e.getAvailableFontFamilyNames();
//        List                availFonts = CollectionUtils.arrayToList(fontNames);
//
//        HSLFSlideShow ppt = new HSLFSlideShow(new HSLFSlideShowImpl(filePath + fileName));
//
//        Dimension pgsize = ppt.getPageSize();
//        for (int i = 0; i < ppt.getSlides().size(); i++) {
//            //防止中文乱码
//            for (HSLFShape shape : ppt.getSlides().get(i).getShapes()) {
//                if (shape instanceof HSLFTextShape) {
//                    HSLFTextShape tsh = (HSLFTextShape) shape;
//                    for (HSLFTextParagraph p : tsh) {
//                        for (HSLFTextRun r : p) {
//                            String fontFamily = r.getFontFamily();
//                            //log.info(">>>>>原始ppt字体：{}", fontFamily);
//                            fontFamily = "宋体";
//                            //log.info(">>>>>ppt字体修改为：{}", fontFamily);
//                            r.setFontFamily(fontFamily);
////                            if (!availFonts.contains(fontFamily)) {
////                                fontFamily = "宋体";
////                                log.info(">>>>>ppt字体修改为：{}", fontFamily);
////                                r.setFontFamily(fontFamily);
////                            }
//
//                        }
//                    }
//                }
//            }
//            BufferedImage img      = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
//            Graphics2D    graphics = img.createGraphics();
//            // clear the drawing area
//            graphics.setPaint(Color.white);
//            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
//            // render
//            ppt.getSlides().get(i).draw(graphics);
//
//            // save the output
//            String pptname    = fileName.substring(0, fileName.lastIndexOf("."));
//            String newimgPath = filePath + "image/" + pptname + "/";
//            File   imgPath    = new File(newimgPath);
//            if (!imgPath.exists()) {//图片目录不存在则创建
//                imgPath.mkdirs();
//            }
//            String           file = newimgPath + (i + 1) + ".png";
//            FileOutputStream out  = new FileOutputStream(file);
//            javax.imageio.ImageIO.write(img, "png", out);
//            IOUtils.closeQuietly(out);
//            //resizeImage(filename, filename, width, height);
//        }
//
//    }
//
//    /***
//     * 功能 :调整图片大小
//     * @param srcImgPath 原图片路径
//     * @param distImgPath  转换大小后图片路径
//     * @param width   转换后图片宽度
//     * @param height  转换后图片高度
//     */
//    public static void resizeImage(String srcImgPath, String distImgPath,
//        int width, int height) throws IOException {
//        File          srcFile = new File(srcImgPath);
//        Image         srcImg  = ImageIO.read(srcFile);
//        BufferedImage buffImg = null;
//        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        buffImg.getGraphics().drawImage(
//            srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
//            0, null);
//        ImageIO.write(buffImg, "JPEG", new File(distImgPath));
//    }
//
//    /**
//     * 将图片转换成pdf
//     *
//     * @return
//     * @throws Exception
//     */
//    public static byte[] img2PDF(List<byte[]> images) throws Exception {
//
////        Document              doc    = new Document();
////        Document doc = new Document(PageSize.A4, 0, 0, 36.0F, 36.0F);//普通a4
//        com.itextpdf.text.Rectangle pageSize = new com.itextpdf.text.Rectangle(0, 0, width, height); // 自定义页面大小
//        Document                    doc      = new Document(pageSize, 0, 0, 0, 0);
//        log.info(">>>>A4尺寸：width={}，height={}", pageSize.getWidth(), pageSize.getHeight());
//        ByteArrayOutputStream pdfOut = new ByteArrayOutputStream();
//        PdfWriter.getInstance(doc, pdfOut);
//        doc.open();
//        float scale = pageSize.getWidth() / width;
//        for (byte[] image : images) {
//            com.itextpdf.text.Image pic = com.itextpdf.text.Image.getInstance(image);
//            pic.setScaleToFitLineWhenOverflow(true);
//            // pic.scaleAbsolute(width, height);
//            pic.scaleToFit(pageSize.getWidth(), height * scale);
//            doc.add(pic);
//        }
//
//        doc.close();
//        byte[] pdf = pdfOut.toByteArray();
//        IOUtils.closeQuietly(pdfOut);
//        return pdf;
//    }
//
//    /**
//     * ppt转化为pdf
//     * @param is ppt 输入流
//     * @return pdf 字节文件
//     * @throws Exception
//     */
//    public static byte[] ppt2PDF(InputStream is) throws Exception {
//        return img2PDF(ppt2Image(is));
//    }
//    /**
//     * pptx转化为pdf
//     * @param is pptx 输入流
//     * @return pdf 字节文件
//     * @throws Exception
//     */
//    public static byte[] pptx2PDF(InputStream is) throws Exception {
//        return img2PDF(pptx2Image(is));
//    }
//
//    /**
//     * 图片转pdf
//     *
//     * @param img
//     * @param descfolder
//     * @return
//     * @throws Exception
//     */
//    public static String img2PDF(String imagePath, BufferedImage img, String descfolder) throws Exception {
//        String pdfPath = "";
//        try {
//            //图片操作
//            com.itextpdf.text.Image image = null;
//            File                    file  = new File(descfolder);
//
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//
//            pdfPath = descfolder + "/" + System.currentTimeMillis() + ".pdf";
//            String   type = imagePath.substring(imagePath.lastIndexOf(".") + 1);
//            Document doc  = new Document(null, 0, 0, 0, 0);
//
//            //更换图片图层
//            BufferedImage bufferedImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
//            bufferedImage.getGraphics().drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
//            bufferedImage = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_LINEAR_RGB), null).filter(bufferedImage, null);
//
//            //图片流处理
//            doc.setPageSize(new com.itextpdf.text.Rectangle(bufferedImage.getWidth(), bufferedImage.getHeight()));
//            log.info(bufferedImage.getWidth() + "()()()()()" + bufferedImage.getHeight());
//            ByteArrayOutputStream out  = new ByteArrayOutputStream();
//            boolean               flag = ImageIO.write(bufferedImage, type, out);
//            byte[]                b    = out.toByteArray();
//            image = com.itextpdf.text.Image.getInstance(b);
//
//            //写入PDF
//            log.info("写入PDf:" + pdfPath);
//            FileOutputStream fos = new FileOutputStream(pdfPath);
//            PdfWriter.getInstance(doc, fos);
//            doc.open();
//            doc.add(image);
//            doc.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (BadElementException e) {
//            e.printStackTrace();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        return pdfPath;
//    }
//
//    public static void mergePDF(String[] files, String desfolder, String mergeFileName) throws Exception {
//
//        PDFMergerUtility mergePdf = new PDFMergerUtility();
//
//        for (String file : files) {
//            if (file.toLowerCase().endsWith("pdf"))
//                mergePdf.addSource(file);
//        }
//
//        mergePdf.setDestinationFileName(desfolder + "/" + mergeFileName);
//        mergePdf.mergeDocuments();
//        log.info("merge over");
//
//    }
//
//    public static void main(String[] args) throws Exception {
//        // 读入PPT文件
//        String filePath = "D:/test/";
//        String fileName = "docker.pptx";
//
//        List<byte[]> images = pptx2Image(new FileInputStream(new File(filePath + fileName)));
//        // 输出图片
//        for (int i = 1; i <= images.size(); i++) {
//            String path = filePath + i + ".png";
//            FileOutputStream output = new FileOutputStream(path);
//            IOUtils.write(images.get(i-1), output);
//            IOUtils.closeQuietly(output);
//        }
//
//        // 输出pdf
//        byte[]           pdf = pptx2PDF(new FileInputStream(new File(filePath + fileName)));
//        FileOutputStream out = new FileOutputStream("D:/test/docker.pdf");
//        IOUtils.write(pdf, out);
//        IOUtils.closeQuietly(out);
//
//    }

}
