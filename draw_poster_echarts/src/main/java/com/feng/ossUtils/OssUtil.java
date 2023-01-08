package com.feng.ossUtils;

import com.aliyun.oss.OSSClient;
import com.feng.fileUtils.PropertiesFileUtil;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;

public class OssUtil {

    private static final String ZHENG_OSS_ALIYUN_OSS_ROTATION = PropertiesFileUtil.getInstance("oss-client").get("feng.oss.aliyun");
    private static final String ACCESS_KEY_ID = PropertiesFileUtil.getInstance("oss-client").get("accessKeyId");
    private static final String ACCESS_KEY_SECRET = PropertiesFileUtil.getInstance("oss-client").get("accessKeySecret");
    private static final String IMG_REQUEST_DOMAIN = PropertiesFileUtil.getInstance("oss-client").get("feng.oss.aliyun.request");

    public static String uploadImg(String key, InputStream ins) {
        try {
            OSSClient ossClient = new OSSClient(ZHENG_OSS_ALIYUN_OSS_ROTATION, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            ossClient.putObject("xfximg", key, ins);
            ossClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }

    public static String getImgUrl(String k) {
        return IMG_REQUEST_DOMAIN + "/" + k;
    }

    /**
     * 多个imgOss用;拼接时，域名处理
     * @param imgOss
     * @return
     */
    public static String dealImgUrl(String imgOss) {
        if (StringUtils.isBlank(imgOss)) {
            return null;
        }
        String[] imgArr = imgOss.split(";");
        StringBuilder imgUrl = new StringBuilder();
        for (String s : imgArr) {
            imgUrl.append(getImgUrl(s)).append(";");
        }
        return imgUrl.substring(0, imgUrl.length() - 1);
    }
}

