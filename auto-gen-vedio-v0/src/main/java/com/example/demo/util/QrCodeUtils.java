package com.example.demo.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;


/* ******************  类说明  *********************
 * class       :  QrCodeUtil
 * @author     :  ncc
 * create time :  2018-3-29 下午06:17:11
 * @version    :  1.0
 * description :  https://www.iteye.com/blog/decao-2414944
 * @see        :
 * ************************************************/
public class QrCodeUtils {

    public static void main(String[] args) {
        String url = "https://apps.apple.com/us/app/id1273210532";
        String path = FileSystemView.getFileSystemView().getHomeDirectory() + File.separator + "testQrcode";
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";
        createQrCode(url, path, fileName);
    }

    /* ********************************************
     * method name   : createQrCode
     * description   : 根据链接生成二维码
     * @return       : String
     * @param        : @param url
     * @param        : @param path
     * @param        : @param fileName
     * @param        : @return
     * modified      : ncc ,  2018-3-29
     * @see          :
     * ********************************************/
    public static String createQrCode(String url, String path, String fileName) {
        try {
            Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 500, 500, hints);
            File file = new File(path, fileName);
            if (file.exists() || ((file.getParentFile().exists() || file.getParentFile().mkdirs()) && file.createNewFile())) {
                writeToFile(bitMatrix, "jpg", file);
                System.out.println("文件路径：" + file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ********************************************
     * method name   : writeToFile
     * description   : 写文件
     * @return       : void
     * @param        : @param matrix
     * @param        : @param format
     * @param        : @param file
     * @param        : @throws IOException
     * modified      : ncc ,  2018-3-29
     * @see          :
     * ********************************************/
    static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    /* ********************************************
     * method name   : writeToStream
     * description   : 写入流
     * @return       : void
     * @param        : @param matrix
     * @param        : @param format
     * @param        : @param stream
     * @param        : @throws IOException
     * modified      : ncc ,  2018-3-29
     * @see          :
     * ********************************************/
    static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    /* ********************************************
     * method name   : toBufferedImage
     * description   : 将字节码转换为文件
     * @return       : BufferedImage
     * @param        : @param matrix
     * @param        : @return
     * modified      : ncc ,  2018-3-29
     * @see          :
     * ********************************************/
    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }
}
