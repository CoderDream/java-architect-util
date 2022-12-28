package com.example.demo.controller;

import com.example.demo.util.CreateFileUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import com.alibaba.fastjson.JSONArray;

@Api(tags = "文件下载相关接口")
@RestController
@RequestMapping("/api/v1/txt/download")
@Slf4j
public class DownloadTxtController {

    @Value("${upload.linuxPath}")
    private String uploadPath;

    @Value("${upload.windowsPath}")
    private String uploadWindowsPath;

    public static boolean isWindows() {
        return System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS");
    }

    @GetMapping(value = "/file/{fileName}")
    public ResponseEntity<FileSystemResource> getFile(@PathVariable("fileName") String fileName) throws FileNotFoundException {
        String errorFileName = "";
        String path = "";
        if (isWindows()) {
            uploadPath = uploadWindowsPath;
        }

        //上传目录地址
        String uploadDir = uploadPath + File.separatorChar;
        path = uploadDir + File.separatorChar;

        fileName += ".json";
        File file = new File(path, fileName);
        if (file.exists()) {
            return export(file);
        }
        System.out.println(file);
        return null;
    }

    @PostMapping(value = "/downloadFile")
    public ResponseEntity<FileSystemResource> downloadFile() throws FileNotFoundException {

        List<Object> agencyList = new ArrayList<Object>();
        Map<String, Object> agencyMap = new HashMap<>();
        agencyMap.put("agencyName","123");
        agencyMap.put("agencyAddress", "123");
        agencyMap.put("companyName", "123");
        agencyMap.put("logoImageId", "123");
        agencyMap.put("auctionAddress", "123");
        agencyMap.put("logoImage", "123");
        agencyList.add(agencyMap);

        String errorFileName = "";
        String path = "";
        if (isWindows()) {
            uploadPath = uploadWindowsPath;
        }

        //上传目录地址
        String uploadDir = uploadPath + File.separatorChar;
        path = uploadDir + File.separatorChar;

        String fileName  = "agency";
        JSONArray jsonObject = new JSONArray(agencyList);
        String jsonString1 = jsonObject.toString();
        CreateFileUtil.createJsonFile(jsonString1, path, fileName);

        File file = new File(path, fileName + ".json");
        if (file.exists()) {
            return export(file);
        }
        System.out.println(file);
        return null;
    }

    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }

}
