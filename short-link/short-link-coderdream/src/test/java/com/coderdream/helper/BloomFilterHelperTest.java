package com.coderdream.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BloomFilterHelperTest {

    @Resource
    private BloomFilterHelper bloomFilterHelper;

    @Test
    public void testCache() {
        assertFalse(bloomFilterHelper.mightContain("123"));
        bloomFilterHelper.put("123");
        assertEquals(true, bloomFilterHelper.mightContain("123"));
    }
}
