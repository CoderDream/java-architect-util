package cn.xupengzhuang.chapter12;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

@Slf4j
public class DateTimeFormatterTests {

    @Test
    public void test1(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        LocalDateTime now = LocalDateTime.now();
        String s = now.format(formatter);
        log.info("{}",s);
    }

    @Test
    public void test2(){
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ENGLISH);
        LocalDate date1 = LocalDate.of(2022, 3, 22);
        String s1 = date1.format(fmt1);
        log.info("{}",s1);

        DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        String s2 = date1.format(fmt2);
        log.info("{}",s2);

        DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.CHINA);
        String s3 = date1.format(fmt3);
        log.info("{}",s3);
    }

    @Test
    public void test3(){
        DateTimeFormatter fmt1 = new DateTimeFormatterBuilder()
                .appendText(ChronoField.YEAR)
                .appendLiteral("年")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral("")
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral("号")
                .parseCaseInsensitive()
                .toFormatter(Locale.CHINA);

        LocalDate now = LocalDate.now();
        String s = now.format(fmt1);
        log.info("{}",s);
    }
}
