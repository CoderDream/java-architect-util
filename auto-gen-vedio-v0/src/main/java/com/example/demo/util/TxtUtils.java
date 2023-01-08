package com.example.demo.util;


import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xu
 * @Description
 * @date: 2019年8月5日 下午5:26:36
 */
public class TxtUtils {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TxtUtils.class);

    /**
     * 读取txt文件
     *
     * @Description
     * @author gxx
     * @date: 2019年8月5日 下午5:47:11
     */
    public static String readTxtFile(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(path));
            inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String s = null;
            while ((s = br.readLine()) != null) {
                stringBuilder.append(s);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 读取txt文件
     *
     * @Description
     * @author gxx
     * @date: 2019年8月5日 下午5:47:11
     */
    public static List<String> readTxtFileToList(String path) {
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(path));
            inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String s = null;
            while ((s = br.readLine()) != null) {
                stringBuilder.append(s);
                if (StrUtil.isNotEmpty(s)) {
                    result.add(s);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }

    /**
     * 写入txt文件，覆盖原有内容
     *
     * @Description
     * @author gxx
     * @date: 2019年8月5日 下午5:38:02
     */
    public static void writeTxtFile(String path, String content) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(path));
            fileOutputStream.write(content.getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }


    public static void main(String[] args) {
//		String path = "D:\\uploadFiles\\version.txt";
        String path = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\0_svn\\湖北省初始化数据\\气象站\\weather_stations.prj";
        String readTxtFile = readTxtFile(path);
        System.out.println(readTxtFile);
    }
}
