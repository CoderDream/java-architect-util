package com.coderdream.freeapps;

import com.coderdream.freeapps.model.MsTtsEntity;
import com.coderdream.freeapps.service.MsTtsService;
import com.coderdream.freeapps.util.mytts.SpeechUtils;
import java.util.Arrays;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
@Slf4j
public class MsTtsServiceTest {

    @Resource
    private MsTtsService msTtsService;

    @Test
    public void testGenDailyPpt() {
        MsTtsEntity msTtsEntity = SpeechUtils.genTodayMp3(38);
        Boolean result = msTtsService.saveOrUpdateBatch(Arrays.asList(msTtsEntity));
        log.info(result + "");
        JdbcTemplate a;
    }
}
