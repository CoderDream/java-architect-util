package com.coderdream.freeapps.controller;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

//@RestController
@Slf4j
@Api(tags = "Minio管理")
@Tag(name = "Minio管理")
//@RequestMapping("/minio")
//@RestController
@RequiredArgsConstructor
public class MinioController {

    @Resource
//    private MinioClient minioClient;

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

//            minioClient.putObject(objectArgs);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
