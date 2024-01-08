package com.coderdream.freeapps.util.mf;

import lombok.Data;

/**
 * 日报明细_2024_01_01_16_32_47.xlsx
 * @author CoderDream
 */
@Data
public class DailyReport {
// 单号
// 单号	门店ID	门店名称	状态	日期	日报名称
// 品类
// 机型	部件号	描述	期初量	进货量	线上销量	线下销量	可销售差	期末量	行备注	序号

    /** 单号 */
    private String dailyNo;
    //门店ID
    private String storeId;
    //门店名称
    private String storeName;
    //状态
    private String dailyState;
    //日期
    private String dailyReportDate;
    //日报名称
    private String dailyReportName;

    //日报名称
    private String lineNo;
}
