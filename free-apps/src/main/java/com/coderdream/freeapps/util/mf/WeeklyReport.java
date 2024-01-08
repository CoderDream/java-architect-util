package com.coderdream.freeapps.util.mf;

import lombok.Data;

/**
 * 日报明细_2024_01_01_16_32_47.xlsx
 * @author CoderDream
 */
@Data
public class WeeklyReport {
// 单号	周报名称	门店ID	门店名称	周季年	周次	开始日期	结束日期	状态	序号

    /** 单号 */
    private String weeklyNo;
    //周报名称
    private String weeklyReportName;
    //门店ID
    private String storeId;
    //门店名称
    private String storeName;

    //周季年
    private String weekSeasonYear;

    //周次
    private String weekTimes;

    //开始日期
    private String weeklyReportBeginDate;
    //结束日期
    private String weeklyReportEndDate;

    //状态
    private String weeklyState;
    //日报名称
    private String lineNo;
}
