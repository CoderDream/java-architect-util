package com.example.demo.controller;


import com.example.demo.util.CompressDownloadUtil;
import com.example.demo.util.MultipartFileToFile;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author lixiaojin
 * @date 2021/11/21 13:17
 */
@Api(tags = "文件相关接口")
@RestController
@RequestMapping("/api/v1/file_down")
@Slf4j
public class DownloadFileController {


    /**
     * 压缩本地文件
     */
    @GetMapping("/download_1")
    public void downloadOne(HttpServletResponse response) {

        // 1、本地文件列表
        List<File> files = new ArrayList<>();
        files.add(new File("D:\\data\\test.png"));
        files.add(new File("D:\\data\\test.png"));
        files.add(new File("D:\\data\\Test.pdf"));
        // 检查需要下载多文件列表中文件路径是否都存在
        for (File file : files) {
            if (!file.exists()) {
                // 需要下载的文件中存在不存在地址
                return;
            }
        }

        // 2、响应头的设置
        String uid = UUID.randomUUID().toString().replace("-", "");
        //String downloadName = UUIDUtil.getUuid() + ".zip";
        String downloadName = uid + ".zip";
        CompressDownloadUtil.setDownloadResponse(response, downloadName);

        try {
            // 3、将多个文件压缩写进响应的输出流
            CompressDownloadUtil.compressZip(files, response.getOutputStream());
        } catch (IOException e) {
            log.error(CompressDownloadUtil.class.getName(), "downloadallfiles", e);
        }

    }


    /**
     * 压缩远程服务器文件
     */
    @GetMapping("/download_2")
    public void downloadTwo(HttpServletResponse response) {
        // 1、文件路径，可以由前端传入
        List<String> fileList = new ArrayList<>();
        fileList.add("http://199.199.2.135/filer/2021-11/test_20211119160019989.png");
        fileList.add("http://199.199.2.135/filer/2021-11/test_20211119160019989.png");
        fileList.add("http://199.199.2.135/filer/2021-11/确认表_20211112173714929.pdf");

        //-- 2、响应头的设置
        String uid = UUID.randomUUID().toString().replace("-", "");
        //String downloadName = UUIDUtil.getUuid() + ".zip";
        String downloadName = uid + ".zip";
        CompressDownloadUtil.setDownloadResponse(response, downloadName);


        try {
            //-- 3、将多个文件压缩写进响应的输出流
            CompressDownloadUtil.compressZipByName(fileList, response.getOutputStream());
        } catch (IOException e) {
            log.error(CompressDownloadUtil.class.getName(), "downloadallfiles", e);
        }

    }

    /**
     * 压缩上传的文件
     */
    @PostMapping(value = "/download_3", headers = "content-type=multipart/form-data")
    public void downloadThree(MultipartFile[] files, HttpServletResponse response) throws Exception {
        // 1、文件路径，可以由前端传入
        List<File> fileList = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            File file = MultipartFileToFile.multipartFileToFile(files[i]);
            fileList.add(file);
        }

        //-- 2、响应头的设置
        String uid = UUID.randomUUID().toString().replace("-", "");
        //String downloadName = UUIDUtil.getUuid() + ".zip";
        String downloadName = uid + ".zip";
        CompressDownloadUtil.setDownloadResponse(response, downloadName);

        try {
            //-- 3、将多个文件压缩写进响应的输出流
            CompressDownloadUtil.compressZip(fileList, response.getOutputStream());
        } catch (IOException e) {
            log.error(CompressDownloadUtil.class.getName(), "downloadallfiles", e);
        }

    }

}
