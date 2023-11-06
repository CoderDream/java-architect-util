package cn.xupengzhuang.chapter12;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.TemporalAdjusters.*;

@Slf4j
public class TemporalAdjusterTests {
    @Test
    public void test1(){
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.MONDAY));
        log.info("{}",date2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Test
    public void test2(){
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = date1.with(lastDayOfMonth());
        log.info("{}",date2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Test
    public void test3(){
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = date1.with(dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        log.info("{}",date2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }




}
