package cn.xupengzhuang.chapter12;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

@Slf4j
public class LocalDateTests {

    @Test
    public void test1(){
        LocalDate date = LocalDate.of(2022,1,1);
        int year = date.getYear();
        Month month = date.getMonth();
        int dayOfMonth = date.getDayOfMonth();
        log.info("year={},month={},dayOfMonth={}",year,month,dayOfMonth);
    }

    @Test
    public void test2(){
        LocalDate date = LocalDate.of(2022, 2, 1);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        log.info("datOfWeek={}",dayOfWeek);

        int dayOfYear = date.getDayOfYear();
        log.info("dayOfYear={}",dayOfYear);
    }

    @Test
    public void test3(){
        LocalDate date = LocalDate.of(2022, 2, 1);
        int lengthOfMonth = date.lengthOfMonth();
        int lengthOfYear = date.lengthOfYear();
        log.info("lengthOfMonth={},lengthOfYear={}",lengthOfMonth,lengthOfYear);

        boolean isLeapYear = date.isLeapYear();
        log.info("isLeapYear={}",isLeapYear);
    }

    @Test
    public void test4(){
        LocalDate now = LocalDate.now();
        log.info("now={}",now);
    }

    @Test
    public void test5(){
        LocalDate date = LocalDate.now();
        int year = date.get(ChronoField.YEAR);
        log.info("year={}",year);

        int monthOfYear = date.get(ChronoField.MONTH_OF_YEAR);
        log.info("monthOfYear={}",monthOfYear);
    }

    @Test
    public void test6(){
        LocalDate date1 = LocalDate.of(2022, 3, 1);
        //将date1的日调整为22号
        LocalDate date2 = date1.withDayOfMonth(22);
        log.info("year={},month={},day={}",date2.getYear(),date2.getMonth(),date2.getDayOfMonth());

        //将date2的年调整为2023年
        LocalDate date3 = date2.withYear(2023);
        log.info("year={},month={},day={}",date3.getYear(),date3.getMonth(),date3.getDayOfMonth());

        //将date3的月份调整为12月
        LocalDate date4 = date3.withMonth(12);
        log.info("year={},month={},day={}",date4.getYear(),date4.getMonth(),date4.getDayOfMonth());

        LocalDate finalDate = date4.with(ChronoField.YEAR, 2022).with(ChronoField.MONTH_OF_YEAR, 3).with(ChronoField.DAY_OF_MONTH, 22);
        log.info("year={},month={},day={}",finalDate.getYear(),finalDate.getMonth(),finalDate.getDayOfMonth());

    }

    @Test
    public void test7(){
        LocalDate date1 = LocalDate.of(2022, 3, 22);
        LocalDate date2 = date1.plus(1, ChronoUnit.YEARS);
        log.info("year={},month={},day={}",date2.getYear(),date2.getMonth(),date2.getDayOfMonth());

        LocalDate date3 = date2.plus(9,ChronoUnit.MONTHS);
        log.info("year={},month={},day={}",date3.getYear(),date3.getMonth(),date3.getDayOfMonth());

        LocalDate date4 = date3.plus(-1, ChronoUnit.YEARS).plus(-9, ChronoUnit.MONTHS).plus(-21, ChronoUnit.DAYS);
        log.info("year={},month={},day={}",date4.getYear(),date4.getMonth(),date4.getDayOfMonth());//2022-3-1
    }

    @Test
    public void test8(){
        LocalDate date1 = LocalDate.of(2022, 3, 22);
        LocalDate date2 = date1.minus(21, ChronoUnit.DAYS);
        log.info("year={},month={},day={}",date2.getYear(),date2.getMonth(),date2.getDayOfMonth());


    }

}
