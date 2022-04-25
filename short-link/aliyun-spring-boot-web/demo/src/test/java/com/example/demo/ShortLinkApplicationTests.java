package com.example.demo;

import com.example.demo.utils.ShortLinkComponent;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@SpringBootTest
@Slf4j
class ShortLinkApplicationTests {

    final static String LONG_LINK = "https://github.com/wangzheng0822/ratelimiter4j";
    @Resource
    private ShortLinkComponent shortLinkComponent;

    @Test
    void testMurmur3_128() {
        HashCode hashCode = Hashing.murmur3_128().hashString(LONG_LINK, StandardCharsets.UTF_8);
        System.out.println("128: " + hashCode.toString());
    }

    @Test
    void testMurmur3_32() {
        HashCode hashCode = Hashing.murmur3_32().hashString(LONG_LINK, StandardCharsets.UTF_8);
        System.out.println("32: " + hashCode.toString());
    }

    @Test
    void testMurmurHash() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            //String originalUrl = "https://vnn.net?id=" + CommonUtil.generateUUID();
            int num1 = random.nextInt(10);
            int num2 = random.nextInt(10000000);
            int num3 = random.nextInt(10000000);
            String originalUrl = LONG_LINK + num1 + "wnn" + num2 + ".net" + num3;
            String shortLinkCode = shortLinkComponent.createShortLinkCode(originalUrl);
            log.info("originalUrl:" + originalUrl + ", shortLinkCode: " + shortLinkCode);
        }
    }
}