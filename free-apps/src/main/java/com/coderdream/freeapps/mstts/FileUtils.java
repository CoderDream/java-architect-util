package com.coderdream.freeapps.mstts;
//导入spring工具包，如果使用spring框架（我想大概率会使用）直接使用FileCopyUtils.java

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import org.springframework.util.FileCopyUtils;

public class FileUtils {

    /**
     * 文件转二进制
     *
     * @param filePath
     * @return
     */
    public static byte[] file2Bytes(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return new byte[0];
        }
        try (InputStream inputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ) {
            byte[] buf = new byte[1024];
            int len;
            if ((len = bufferedInputStream.read(buf)) >= 0) {
                outputStream.write(buf, 0, len);
            }
            return outputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制流转换为文件
     *
     * @param bytes
     * @return
     */
    public static String bytes2File(byte[] bytes, String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream(fileName);) {
            outputStream.write(bytes);
            return fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制流转换为文件
     *
     * @param bytes
     * @param fileName
     */
    public static void bytes2FileSpring(byte[] bytes, String fileName) {
        try {
            FileCopyUtils.copy(bytes, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件转base64
     *
     * @param filePath 文件路径
     */
    public static String fileToBase64(String filePath) {
        try (InputStream fileInputStream = new FileInputStream(new File(filePath));
            BufferedInputStream buf = new BufferedInputStream(fileInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();) {
            int len;
            byte[] bytes = new byte[1024];
            while ((len = buf.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, len);
            }
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将base64字符串转文件
     *
     * @param base64Str base64字符串
     */
    public static void base64ToFile(String base64Str, String fileName) {
        //解码
        byte[] bytes = Base64.getDecoder().decode(base64Str);
        try (FileOutputStream outputStream = new FileOutputStream(fileName);) {
            outputStream.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
