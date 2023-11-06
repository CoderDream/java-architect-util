package com.coderdream.freeapps;

import com.coderdream.freeapps.model.SubtitleEntity;
import com.coderdream.freeapps.service.SubtitleService;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.subtitle.SubtitleUtils;
import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SubtitleServiceTest {

    @Resource
    private SubtitleService subtitleService;

    @Test
    public void testGenInitRecord_01() {
        String videoName = "天津二周年第一场.20230612.seg1";
        String subtitleFileName = "2023061201.srt";
        List<SubtitleEntity> subtitleEntityList = SubtitleUtils.genSubtitleEntityList(videoName, subtitleFileName);
        for (SubtitleEntity subtitleEntity : subtitleEntityList) {
//            System.out.println(subtitleEntity);
        }

        int result = subtitleService.initRecord(subtitleEntityList);
        log.info(result + "");
//        result = subtitleService.saveOrUpdateBatch(subtitleEntityList);
//        log.info(result + "");
    }

    @Test
    public void testGenInitRecord_02() {
        String videoName = "天津二周年第一场.20230612.seg2";
        String subtitleFileName = "2023061202.srt";
        List<SubtitleEntity> subtitleEntityList = SubtitleUtils.genSubtitleEntityList(videoName, subtitleFileName);
        for (SubtitleEntity subtitleEntity : subtitleEntityList) {
            System.out.println(subtitleEntity);
        }

        int result = subtitleService.initRecord(subtitleEntityList);
        log.info(result + "");
    }

    @Test
    public void testGenInitRecord_03() {
        String videoName = "2023.东方卫视.开工.喜剧之夜";
        String subtitleFileName = "2023061802.srt";
        List<SubtitleEntity> subtitleEntityList = SubtitleUtils.genSubtitleEntityList(videoName, subtitleFileName);
        for (SubtitleEntity subtitleEntity : subtitleEntityList) {
            System.out.println(subtitleEntity);
        }

        int result = subtitleService.initRecord(subtitleEntityList);
        log.info(result + "");
    }

    @Test
    public void testGenInitRecord_20230629() {
        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "subtitle" + File.separatorChar + "2023"
                + File.separatorChar + "20230629";

        SrtInfo srtInfo = new SrtInfo("天津德云社成立二周年系列专场第三场.20230626.1_-seg1",
            "天津德云社成立二周年系列专场第三场.20230626.1_-seg1.srt");
        List<SubtitleEntity> subtitleEntityList = SubtitleUtils.genSubtitleEntityList(folderPath,
            srtInfo.getVideoName(), srtInfo.getSubtitleFileName());
        for (SubtitleEntity subtitleEntity : subtitleEntityList) {
            System.out.println(subtitleEntity);
        }
        int result = subtitleService.initRecord(subtitleEntityList);
        log.info(result + "");

        srtInfo = new SrtInfo("天津德云社成立二周年系列专场第三场.20230626.2_-seg2",
            "天津德云社成立二周年系列专场第三场.20230626.2_-seg2.srt");
        subtitleEntityList = SubtitleUtils.genSubtitleEntityList(folderPath, srtInfo.getVideoName(),
            srtInfo.getSubtitleFileName());
        for (SubtitleEntity subtitleEntity : subtitleEntityList) {
            System.out.println(subtitleEntity);
        }
        result = subtitleService.initRecord(subtitleEntityList);
        log.info(result + "");
    }
}

@Data
@AllArgsConstructor
class SrtInfo {

    private String videoName;
    private String subtitleFileName;
}
