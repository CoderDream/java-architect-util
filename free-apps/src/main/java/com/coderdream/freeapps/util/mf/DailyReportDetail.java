package com.coderdream.freeapps.util.mf;

import lombok.Data;

/**
 * 日报明细_2024_01_01_16_32_47.xlsx
 * @author CoderDream
 */
@Data
public class DailyReportDetail {
// 单号
// 单号	门店ID	门店名称	状态	日期	日报名称
// 品类
// 机型	部件号	描述	期初量	进货量	线上销量	线下销量	可销售差	期末量	行备注	序号

    /** 单号 */
    private String dailyNo;
    //品类
    private String productCategory;
    //机型
    private String productType;
    //部件号
    private String productNo;
    //描述
    private String desc;
    //期初量
    private Integer initAmount;
    //进货量
    private Integer incomeAmount;
    //线上销量
    private Integer onlineSale;
    //线下销量
    private Integer offlineSale;
    //可销售差
    private Integer saleDiff;
    //期末量
    private Integer endAmount;
    //行备注
    private String lineComment;
    //序号
    private String lineNo;
    //门店ID
    private String storeId;
}
