package com.coderdream.freeapps;

import com.coderdream.freeapps.component.MinioUtil;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;

@SpringBootTest
public class MinioUtilTest {


    @Resource
    private MinioUtil minioUtil;

    @Test
    public void testFileUploader() {
//        String[] pictureUrls = new String[]{"https://is5-ssl.mzstatic.com/image/thumb/Purple125/v4/9d/7d/7d/9d7d7d1f-001f-ff93-46aa-7adfef8fdaaa/pr_source.jpg/600x0w.jpg"};
//        String path = "id1443533088";
//        String[] fileNames = new String[]{"9d7d7d1f-001f-ff93-46aa-7adfef8fdaaa_pr_source.jpg"};
//        DownloadUtil.downloadPicture(pictureUrls, path, fileNames);
//        File file = new File(path + File.separator + fileNames[0]);
//        minioUtil.upload(getMultipartFile(file));
    }

    private static MultipartFile getMultipartFile(File file){
        FileInputStream fileInputStream = null;
        MultipartFile multipartFile = null;
        try {
            fileInputStream = new FileInputStream(file);
            multipartFile = new MockMultipartFile(file.getName(),file.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return multipartFile;
    }
}
