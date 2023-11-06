package com.coderdream.freeapps;

import com.coderdream.freeapps.enums.DistrictFlagEnum;
import com.coderdream.freeapps.model.BossJobLogEntity;
import com.coderdream.freeapps.model.JobInfoEntity;
import com.coderdream.freeapps.service.BossJobLogService;
import com.coderdream.freeapps.service.JobInfoService;
import com.coderdream.freeapps.util.callapi.CallApiUtil;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BossJobLogTest {

    @Resource
    private BossJobLogService bossJobLogService;

    @Test
    public void testUpdateColumns() {
        log.info("updateColumns result: " + bossJobLogService.updateColumns());
    }

}
