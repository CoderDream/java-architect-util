package com.coderdream.freeapps.util.other;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;


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
//        String url = "https://apps.apple.com/us/app/id1273210532";
//        String path = FileSystemView.getFileSystemView().getHomeDirectory() + File.separator + "testQrcode";
//        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";
//        createQrCode(url, path, fileName);


        List<String> urlList = new ArrayList<>();
//        urlList.add("https://apps.apple.com/us/app/fonts-install-new-fonts/id1509011708");
//        urlList.add("https://apps.apple.com/us/app/bgh-bears-good-habits/id1472298590");
//        urlList.add("https://apps.apple.com/cn/app/goodak-edit-photo-editor-cam/id540662922?uo=4&at=1001lsTF&ct=iOS_detail_share_540662922");
//        urlList.add("https://apps.apple.com/us/app/marple/id288689440?l=zh");
//        urlList.add("https://apps.apple.com/us/app/kegel-trainer-for-mens-health/id1660375539?l=zh");
//        urlList.add("https://apps.apple.com/us/app/%E8%B6%85%E7%BA%A7%E6%8A%A0%E5%9B%BE-super-photocut/id966457795?l=zh&mt=12");

        String path = BaseUtils.getPath();
//        String txtFileName = File.separator + path + File.separator + dateStr + ".txt";
//
//        List<String> rawStrList = TxtUtils.readTxtFileToList(txtFileName);
//        for (String rawStr : rawStrList) {
//            int flag = rawStr.lastIndexOf("https://apps.apple.com");
//            if (flag != -1) {
//                urlList.add(rawStr);
//            }
//        }

        urlList = GenerateAppInfo.genUrlCnList();

        for (String url : urlList) {
            System.out.println(url);
        }

        genQR(urlList, path);
    }


    public static void genQR(List<String> urlList, String path) {
        int i = 0;
        for (String url : urlList) {
            String id = StringUtils.parseAppId(url);
            i++;
            String fileName = i + "_qr_" + id + ".jpg";
            System.out.println("fileName: " + fileName);
            createQrCode(url, path + File.separator + "qr", fileName);
        }
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
                writeToFile(bitMatrix, "png", file);
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
