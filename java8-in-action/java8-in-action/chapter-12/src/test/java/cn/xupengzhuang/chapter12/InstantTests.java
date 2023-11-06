package cn.xupengzhuang.chapter12;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Instant;

@Slf4j
public class InstantTests {
    @Test
    public void test1(){
        Instant instant = Instant.ofEpochSecond(3);
        Instant instant1 = Instant.ofEpochSecond(3, 0);
        Instant instant2 = Instant.ofEpochSecond(2, 1_000_000_000);
        log.info("instant={}",instant.getEpochSecond());
        log.info("instant1={}",instant1.getEpochSecond());
        log.info("instant2={}",instant2.getEpochSecond());
    }
}
