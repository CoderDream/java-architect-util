package com.coderdream.freeapps.util.mf;

import lombok.Data;

/**
 * 日报明细_2024_01_01_16_32_47.xlsx
 * @author CoderDream
 */
@Data
public class WeeklyReportDetail {

    /** 单号 */
    private String weeklyNo;
    //品类
    private String productCategory;
    //机型
    private String productType;
    //部件号
    private String productNo;

    //WK1需求原值  通用场景
    //Original value of demand
    private Integer wk1OriginalDemandValue;
    //WK1需求拟增减
    private Integer wk1Modify;
    //WK1需求实际值 Actual value of demand
    private Integer wk1ActualDemandValue;
    //WK1增减说明
    private String wk1Comment;

    //WK2需求原值
    private Integer wk2OriginalDemandValue;
    //WK2需求拟增减
    private Integer wk2Modify;
    //WK2需求实际值 Actual value of demand
    private Integer wk2ActualDemandValue;
    //WK2增减说明
    private String wk2Comment;

    //WK3需求原值
    private Integer wk3OriginalDemandValue;
    //WK3需求实际值 Actual value of demand
    private Integer wk3ActualDemandValue;

    //WK1第一次需求计划
    private Integer wk1FirstPlan;
    //WK1第二次需求计划
    private Integer wk1SecondPlan;
    //WK2第一次需求计划
    private Integer wk2FirstPlan;
    //行备注
    private String lineComment;
    //序号
    private String lineNo;
    //门店ID
    private String storeId;
    //年周季
    private String weekSeasonYear;
}
