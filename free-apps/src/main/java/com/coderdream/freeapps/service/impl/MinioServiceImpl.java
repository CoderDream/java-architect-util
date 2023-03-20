package com.coderdream.freeapps.service.impl;

import com.coderdream.freeapps.component.MinioUtil;
import com.coderdream.freeapps.config.MinioProperties;
import com.coderdream.freeapps.model.Snapshot;
import com.coderdream.freeapps.service.MinioService;
import com.coderdream.freeapps.service.SnapshotService;
import com.coderdream.freeapps.util.Constants;
import com.coderdream.freeapps.util.DownloadPictureUtil;
import com.coderdream.freeapps.util.ListUtils;
import lombok.RequiredArgsConstructor;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MinioServiceImpl.class);
    @Resource
    private MinioProperties minioProperties;

    @Resource
    private SnapshotService snapshotService;

    @Override
    public void upload() {
        String[] pictureUrls = new String[]{"https://is5-ssl.mzstatic.com/image/thumb/Purple125/v4/9d/7d/7d/9d7d7d1f-001f-ff93-46aa-7adfef8fdaaa/pr_source.jpg/600x0w.jpg"};
        String path = "id1443533088";
        String[] fileNames = new String[]{"9d7d7d1f-001f-ff93-46aa-7adfef8fdaaa_pr_source.jpg"};
        DownloadPictureUtil.downloadPicture(pictureUrls, path, fileNames);
//        String filename = path + File.separator + fileNames[0];
        String filename = path + "/" + fileNames[0];
        File file = new File(filename);
//        minioUtil.upload(getMultipartFile(file));

        try {
            MinioUtil.uploadFile(minioProperties.getBucket(), getMultipartFile(file), filename);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void uploadSnapshot(List<Snapshot> snapshotList) {
        // 应用ID
        processList(snapshotList);
    }

    @Override
    public void processDaily() {
        Snapshot snapshotQuery = new Snapshot();
        snapshotQuery.setDownloadFlag(0);
        List<Snapshot> snapshotTodoList = snapshotService.selectList(snapshotQuery);

        processList(snapshotTodoList);
    }

    private void processList(List<Snapshot> snapshotTodoList) {
        long startTime = System.currentTimeMillis();
        List<Snapshot> snapshotDoneList;
        if (!CollectionUtils.isEmpty(snapshotTodoList)) {
            logger.info("本批次处理的记录数: " + snapshotTodoList.size());
            // 分批处理
            List<List<Snapshot>> lists = ListUtils.splitTo(snapshotTodoList, Constants.BATCH_SNAPSHOT_ROWS);
            for (List<Snapshot> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    snapshotDoneList = new ArrayList<>();
                    Snapshot doneSnapshot;
                    // 应用ID
                    String path;
                    //文件名称
                    String filename;
                    //截图地址
                    String snapshotUrl;
                    //上传文件名称（包含appId作为文件夹）
                    String uploadFilename;
                    if (!CollectionUtils.isEmpty(list)) {
                        for (Snapshot snapshot : list) {
                            path = snapshot.getAppId();
                            filename = snapshot.getFilename();
                            snapshotUrl = snapshot.getSnapshotUrl();
                            DownloadPictureUtil.downloadPicture(snapshotUrl, path, filename);

                            logger.info("图片下载成功: " + filename);
                            //
                            uploadFilename = path + "/" + filename;
                            File file = new File(uploadFilename);
                            try {
                                MinioUtil.uploadFile(minioProperties.getBucket(), getMultipartFile(file), uploadFilename);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            doneSnapshot = new Snapshot();
                            BeanUtils.copyProperties(snapshot, doneSnapshot);
                            doneSnapshot.setDownloadFlag(1);
                            doneSnapshot.setLastModifiedDate(new Date());
                            snapshotDoneList.add(doneSnapshot);
                            logger.info("图片上传成功: " + uploadFilename);
                        }
                    }
                    // 更新状态
                    if (!CollectionUtils.isEmpty(snapshotDoneList)) {
                        snapshotService.insertOrUpdateBatch(snapshotDoneList);
                    }
                }
            }
        }

        // 耗时
        long endTime = System.currentTimeMillis();
        long period = endTime - startTime;

        final long milliseconds = period;
        final long day = TimeUnit.MILLISECONDS.toDays(milliseconds);

        final long hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));

        final long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));

        final long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));

        final long ms = TimeUnit.MILLISECONDS.toMillis(milliseconds)
                - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(milliseconds));

        String message = String.format("%d Days %d Hours %d Minutes %d Seconds %d Milliseconds",
                day, hours, minutes, seconds, ms);
        logger.info("本次任务耗时: " + period + " 毫秒");
        logger.info("本次任务耗时: " + message);
    }

    private static MultipartFile getMultipartFile(File file) {
        FileInputStream fileInputStream;
        MultipartFile multipartFile = null;
        try {
            fileInputStream = new FileInputStream(file);
            multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return multipartFile;
    }
}
