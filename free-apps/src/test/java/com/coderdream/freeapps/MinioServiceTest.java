package com.coderdream.freeapps;

import com.coderdream.freeapps.service.MinioService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MinioServiceTest {

    @Resource
    private MinioService minioService;

    @Test
    public void tesUpload() {
        minioService.upload();
    }

    @Test
    public void tesProcessDaily() {
        minioService.processDaily();
    }

    @Test
    public void testFileUploader() {
        try {
            // https://coderme.myds.me:39000
            // Create a minioClient with the MinIO server playground, its access key and secret key. http://172.16.104.61:99/
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://172.16.104.61:9900")
                            .credentials("RK0YThYyA4JFeXKF", "xjCcIITHgn7GUqgGcjiWyyH93YXxWaRu")
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("public").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("public").build());
            } else {
                System.out.println("Bucket 'public' already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("public")
                            .object("credentials.json")
                            .filename("free-apps.log")
                            .build());
            System.out.println("E:/Downloads/credentials.json' is successfully uploaded as " + "object 'credentials.json' to bucket 'public'.");
//        } catch (MinioException e) {
//            System.out.println("Error occurred: " + e);
//            System.out.println("HTTP trace: " + e.httpTrace());
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
