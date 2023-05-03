package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value ="t_wechat_info")
@Data
public class WechatInfo {

    /**
     * 页面URL
     */
    private String href;

    /**
     * 昨日URL
     */
    private String yesterdayUrl;
    /**
     * 限免日期
     */
    private String freeDate;
    /**
     * 限免应用序号
     */
    private String freeAppNo;
    /**
     * 应用Id
     */
    private String appId;
    /**
     * 应用名称
     */
    private String title;
    /**
     * 应用价格
     */
    private String priceStr;
    /**
     * 应用平台
     */
    private String capabilityStr;
    /**
     * 应用简介
     */
    private String description;
    /**
     * 内购截图
     */
    private String inPurchaseSnapshot;
    /**
     * 应用截图
     */
    private String snapshot;
    /**
     * 二维码地址
     */
    private String qrUrl;
    /**
     * 应用地址
     */
    private String appUrl;
}
