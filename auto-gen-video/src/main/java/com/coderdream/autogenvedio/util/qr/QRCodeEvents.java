package com.coderdream.autogenvedio.util.qr;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 * 利用Google的ZXing工具包，生成和解析二维码图片
 */
public class QRCodeEvents {

    public static void main(String[] args) {

        //设置二维码内容 或者 链接
        String text = "https://www.baidu.com";
        //二维码图片的宽
        int width = 100;
        //二维码图片的高
        int height = 100;
        //二维码图片的格式
        String format = "png";
        try {
            //生成二维码图片，并返回图片路径
            String pathName;// =  generateQRCode(text, width, height, format);
//            System.out.println("生成二维码的图片路径： " + pathName);

            pathName = "D:\\12_iOS_Android\\202301\\20230128\\qr\\qr_01_id1521801011.png";
            //解析二维码图片，并返回图片内容
            String content = parseQRCode(pathName);
            System.out.println("解析出二维码的图片的内容为： " + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据内容，生成指定宽高、指定格式的二维码图片
     *
     * @param text   内容
     * @param width  宽
     * @param height 高
     * @param format 图片格式
     * @return 生成的二维码图片路径
     * @throws Exception
     */

    private static String generateQRCode(String text, int width, int height, String format) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        String pathName = "D:/new.png";//保存路径加+名字
        File outputFile = new File(pathName);
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        return pathName;
    }

    /**
     * 解析指定路径下的二维码图片
     *
     * @param filePath 二维码图片路径
     * @return
     */
    public static String parseQRCode(String filePath) {
        String content = "";
        try {
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            MultiFormatReader formatReader = new MultiFormatReader();
            Result result = formatReader.decode(binaryBitmap, hints);

//            System.out.println("result 为：" + result.toString());
//            System.out.println("resultFormat 为：" + result.getBarcodeFormat());
//            System.out.println("resultText 为：" + result.getText());

            //设置返回值
            content = result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
