package cn.xupengzhuang.chapter12;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

@Slf4j
public class LocalDateTimeTests {

    @Test
    public void test1(){
        LocalDateTime dateTime = LocalDateTime.of(2022, Month.MARCH, 22, 12, 17, 10);
        log.info("year={},month={},day={}",dateTime.getYear(),dateTime.getMonth(),dateTime.getDayOfMonth());
        log.info("hour={},minute={},second={}",dateTime.getHour(),dateTime.getMinute(),dateTime.getSecond());
    }

    @Test
    public void test2(){
        LocalDateTime now = LocalDateTime.now();
        log.info("hour={},minute={},second={}",now.getHour(),now.getMinute(),now.getSecond());
    }

    @Test
    public void test3(){
        String birthday = "1996-12-12 10:12:10";
        LocalDateTime dateTime = LocalDateTime.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int year = dateTime.getYear();
        log.info("year={}",year);
    }

    @Test
    public  void test4(){
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        log.info("now format={}",format);
    }

    @Test
    public void test5(){
        LocalDate date = LocalDate.of(2022, 3, 1);
        LocalTime time = LocalTime.of(13, 10, 12);
        //LocalDate和LocalTime进行合并，组成LocalDateTime
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        log.info("{}",dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //向LocalDate对象传递一个time，组成LocalDateTime
        LocalDateTime dt2 = date.atTime(time);
        log.info("{}",dt2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //向LocalTime对象传递一个Date，组成LocalDateTime
        LocalDateTime dt3 = time.atDate(date);
        log.info("{}",dt3.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

}
