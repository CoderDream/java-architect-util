package cn.xupengzhuang.chapter12;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

@Slf4j
public class NextWorkingDayTests {

    @Test
    public void test1(){
        LocalDate now = LocalDate.now();
        LocalDate date2 = now.with(new NextWorkingDay());
        log.info("{}",date2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Test
    public void test2(){
        LocalDate now = LocalDate.of(2022,3,25);
        LocalDate date2 = now.with(new NextWorkingDay());
        log.info("{}",date2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Test
    public void test(){
        LocalDate now = LocalDate.of(2022,3,27);
        int i = now.get(ChronoField.DAY_OF_WEEK);
        System.out.println(i);
    }

    @Test
    public void test3(){
        LocalDate now = LocalDate.of(2022,3,26);
        LocalDate date2 = now.with(temporal -> {
            int dayToAdd = 1;
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            if (dow == DayOfWeek.FRIDAY){
                dayToAdd = 3;
            }else if (dow == DayOfWeek.SATURDAY){
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        log.info("{}",date2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Test
    public void test4(){
        LocalDate now = LocalDate.of(2022,3,25);
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.ofDateAdjuster(localDate -> {
            int dayToAdd = 1;
            DayOfWeek dow = DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK));
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return localDate.plus(dayToAdd, ChronoUnit.DAYS);
        });
        LocalDate date2 = now.with(temporalAdjuster);
        log.info("{}",date2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
