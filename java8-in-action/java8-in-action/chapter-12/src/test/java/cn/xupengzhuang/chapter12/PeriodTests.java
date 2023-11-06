package cn.xupengzhuang.chapter12;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;

@Slf4j
public class PeriodTests {
    @Test
    public void test1(){
        Period period = Period.between(LocalDate.of(1996, 12, 12), LocalDate.of(1997, 10, 23));
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        log.info("year={},months={},days={}",years,months,days);
    }

    @Test
    public void test2(){
        Period threeDays = Period.ofDays(3);
        log.info("{}",threeDays.getDays());

        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        log.info("{}",twoYearsSixMonthsOneDay.getYears());
        log.info("{}",twoYearsSixMonthsOneDay.getMonths());
        log.info("{}",twoYearsSixMonthsOneDay.getDays());

    }
}
