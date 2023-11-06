package cn.xupengzhuang.chapter12;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class LocalTimeTests {

    @Test
    public void test1(){
        LocalTime now = LocalTime.now();
        log.info("now={}",now);
        log.info("hour={},minute={},second={}",now.getHour(),now.getMinute(),now.getSecond());
    }

    @Test
    public void test2(){
        String now = "12:12:12";
        LocalTime time = LocalTime.parse(now);
        log.info("hour={}",time.getHour());

        LocalTime time2 = LocalTime.parse("12-13-10", DateTimeFormatter.ofPattern("HH-mm-ss"));
        log.info("hour={},minute={},second={}",time2.getHour(),time2.getMinute(),time2.getSecond());

    }


}
