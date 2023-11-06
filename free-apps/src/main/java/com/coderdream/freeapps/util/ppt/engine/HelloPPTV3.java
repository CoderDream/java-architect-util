package com.coderdream.freeapps.util.ppt.engine;


import cn.hutool.json.JSONObject;
import com.coderdream.freeapps.util.ppt.CdPptxUtils;
import io.gitee.jinceon.core.DataSource;
import io.gitee.jinceon.core.SimpleEngine;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import org.apache.poi.xslf.usermodel.XSLFShape;

/**
 * @author CoderDream
 */
public class HelloPPTV3 {

    public static void main(String[] args) {

        // 1. create engine instance 创建引擎
        SimpleEngine engine = new SimpleEngine(CdPptxUtils.getTemplatePath() + "6minV2.pptx");

        // 2. add data to dataSource 填充数据
        DataSource dataSource = new DataSource();
        byte[] png = new byte[0];
        try {
            png = Files.readAllBytes(Paths.get(CdPptxUtils.getResourcePath() + "230504.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataSource.setVariable("img1", png);
        engine.setDataSource(dataSource);

        // 3. render data to template 将数据渲染到模板上
        engine.process();

        // 4. save result
        engine.save(CdPptxUtils.getTemplatePath() + "6minV2-render.pptx");
    }

//    public void screenshot(JSONObject object, XSLFShape xslfShape, StreamWriter streamWriter) {
////        if (logger.isInfoEnable())
////            logger.info("无法解析PPTx元素[{}]。", xslfShape);
//
//        int width = 1920; // object.getIntValue("width");
//        int height = 1080; // object.getIntValue("height");
//        if (width <= 0 || height <= 0)
//            return;
//
//        try {
//            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
//            Graphics2D graphics2D = image.createGraphics();
//            xslfShape.draw(graphics2D, new Rectangle2D.Double(0, 0, width, height));
//            graphics2D.dispose();
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            ImageIO.write(image, "PNG", outputStream);
//            outputStream.close();
//
//            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
////            object.put(Parser.TYPE_IMAGE, streamWriter.write("image/png", "", inputStream));
//            inputStream.close();
//        } catch (Exception e) {
//       //     logger.warn(e, "截取PPTx形状为图片时发生异常！");
//        }
//    }

}

