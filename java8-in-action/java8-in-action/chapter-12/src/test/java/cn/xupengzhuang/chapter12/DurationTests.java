package cn.xupengzhuang.chapter12;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

@Slf4j
public class DurationTests {

    @Test
    public void test1(){
        LocalTime time1 = LocalTime.of(10, 0, 0);
        LocalTime time2 = LocalTime.of(22, 0, 0);
        Duration duration = Duration.between(time1, time2);
        log.info("{}",duration.getSeconds());
    }

    @Test
    public void test2(){
        LocalDateTime ldt1 = LocalDateTime.of(1996, 12, 12, 10, 0, 0);
        LocalDateTime ldt2 = LocalDateTime.of(1997, 10, 23, 10, 0, 0);
        Duration duration = Duration.between(ldt1, ldt2);
        log.info("{}",duration.getSeconds());

    }

    @Test
    public void tes3(){
        Instant instant1 = Instant.ofEpochSecond(2);
        Instant instant2 = Instant.ofEpochSecond(3);
        Duration duration = Duration.between(instant1, instant2);
        log.info("{}",duration.getSeconds());
    }

    @Test
    public void test4(){
        Duration duration = Duration.ofDays(3);
        log.info("{}",duration.getSeconds());

        Duration duration1 = Duration.of(3, ChronoUnit.DAYS);
        log.info("{}",duration.getSeconds());
    }

    @Test
    public void test5(){
        Duration duration = Duration.ofMinutes(3);
        Duration duration2 = Duration.of(3, ChronoUnit.MINUTES);
        log.info("{}",duration.getSeconds());
        log.info("{}",duration2.getSeconds());
    }

}
