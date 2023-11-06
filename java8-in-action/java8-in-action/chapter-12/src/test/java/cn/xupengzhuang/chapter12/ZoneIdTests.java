package cn.xupengzhuang.chapter12;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;


@Slf4j
public class ZoneIdTests {

    @Test
    public void test1(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime1 = now.atZone(ZoneId.of("Asia/Shanghai"));
        log.info("{}",zonedDateTime1.format(fmt));
    }

    @Test
    public void test2(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        for (String zoneId : availableZoneIds){
            System.out.println(zoneId);
        }
    }

    @Test
    public void test3(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //获取当前上海的时间
        LocalDateTime shanghaiNow = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        //获取日本的当前时间
        LocalDateTime japanNow = LocalDateTime.now(ZoneId.of("Japan"));

        log.info("Shanghai={}",shanghaiNow.format(fmt));
        log.info("Japan={}",japanNow.format(fmt));
        log.info("系统默认时区={}",ZoneId.systemDefault().getId());
    }

    /**
     * Instant转为LocalDateTime
     */
    @Test
    public void test4(){
        Instant instant = Instant.now();
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
        log.info("Shanghai={}",ldt);
    }

    /**
     * LocalDateTime转为Instant
     */
    @Test
    public void test5(){
        LocalDateTime now = LocalDateTime.of(2022,3,1,10,0,0);
        Instant instant = now.toInstant(ZoneOffset.of("+00:00"));

        LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
        log.info("{}",timeFromInstant);
    }

    @Test
    public void test6(){
        LocalDateTime ldt = LocalDateTime.of(2022,3,1,10,0,0);
        OffsetDateTime odt = OffsetDateTime.of(ldt, ZoneOffset.of("+08:00"));
        log.info("{}",ldt);
        log.info("{}",odt);
    }

}
