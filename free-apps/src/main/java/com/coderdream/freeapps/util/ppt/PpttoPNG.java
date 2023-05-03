package com.coderdream.freeapps.util.ppt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

public class PpttoPNG {

    public static void main(String args[]) throws IOException {
        File directory = new File("src/main/resources");
        String reportPath = null;
        try {
            reportPath = directory.getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String filePath = reportPath + "/ppt/apps-12.pptx";

        //creating an empty presentation
        File file = new File(filePath);
        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));

        //getting the dimensions and size of the slide
        Dimension pgsize = ppt.getPageSize();
        List<XSLFSlide> slide = ppt.getSlides();
        BufferedImage img = null;
        for (int i = 0; i < slide.size(); i++) {
            img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();

            //clear the drawing area
            graphics.setPaint(Color.white);
            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

            //render
            slide.get(i).draw(graphics);

            if (img != null) {
                //creating an image file as output
                FileOutputStream out = new FileOutputStream("ppt_image_" + i + ".png");
                javax.imageio.ImageIO.write(img, "png", out);
                ppt.write(out);
                System.out.println("Image successfully created");
                out.close();
            }
        }


    }
}
