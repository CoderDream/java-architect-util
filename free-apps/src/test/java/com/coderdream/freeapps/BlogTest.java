package com.coderdream.freeapps;

import com.coderdream.freeapps.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BlogTest {
    @Autowired
    private BlogService blogService;

    @Test
    public void testDailyProcess() {
        log.info("testDailyProcess result: " + blogService.dailyProcess());
    }
}
