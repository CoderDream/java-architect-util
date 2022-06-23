package com.example.demo;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class UploadController {

    @Resource
    private MinioClient minioClient;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file){

        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder().object(file.getOriginalFilename())
                    .bucket("test")
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(),file.getSize(),-1).build();

            minioClient.putObject(objectArgs);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}