package cn.xupengzhuang.chapter12;

import java.time.DayOfWeek;
import java.time.temporal.*;

/**
 * 获取下一个工作日的日期
 */
public class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        //获取当前日期
        DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        //正常情况下，增加一天
        int dayToAdd = 1;
        if (dayOfWeek == DayOfWeek.FRIDAY){
            //如果当前日期是周五，增加3天
            dayToAdd = 3;
        }else if (dayOfWeek == DayOfWeek.SATURDAY){
            //如果当前日期是周六，增加2天。
            dayToAdd = 2;
        }
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }
}
