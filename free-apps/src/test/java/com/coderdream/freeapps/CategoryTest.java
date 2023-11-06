package com.coderdream.freeapps;

import com.coderdream.freeapps.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CategoryTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    public void testInitCategory() {
        log.info("init result: " + categoryService.initCategory());
    }
}
