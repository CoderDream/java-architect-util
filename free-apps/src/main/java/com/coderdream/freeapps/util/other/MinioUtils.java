package com.coderdream.freeapps.util.other;

import com.alibaba.fastjson.JSONObject;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Slf4j
//@Component
public class MinioUtils {

//    @Autowired
//    private MinioClient client;
//    @Autowired
//    private MinioProp minioProp;
//
//    /**
//     * 创建bucket
//     *
//     * @param bucketName bucket名称
//     */
//    @SneakyThrows
//    public void createBucket(String bucketName) {
//        if (!client.bucketExists(bucketName)) {
//            client.makeBucket(bucketName);
//        }
//    }
//
//    /**
//         * 上传微信头像
//     *
//             * @param url 网络图片地址
//     * @return
//             */
//    public String uploadFileWithNetFile(String url) {
//        if (StringUtils.isBlank(url)) {
//            return null;
//        }
//        try {
//            InputStream inputStream = FileUtils.returnBitMap(url);
//            // 判断上传文件是否为空
//            if (null == inputStream) {
//                return null;
//            }
//            // 判断存储桶是否存在，不存在则创建
//            createBucket(MinioConst.MINIO_BUCKET);
//            // uuid 生成文件名
//            String uuid = String.valueOf(UUID.randomUUID());
//            // 新的文件名
//            String fileName = "wxAvatar/" + DateUtils.getYyyymmdd() + "/" + uuid + ".jpg";
//            // 开始上传
//            client.putObject(MinioConst.MINIO_BUCKET, fileName, inputStream, "jpg");
//            // 返回图片的访问路径
//            return minioProp.getEndpoint() + "/" + MinioConst.MINIO_BUCKET + "/" + fileName;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
