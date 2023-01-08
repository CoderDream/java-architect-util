package com.feng.ErWeiMaUtils;

import com.alibaba.fastjson.JSONObject;
import com.feng.echartsUtils.HttpUtil;
import com.feng.fileUtils.PropertiesFileUtil;

import java.io.InputStream;

/**
 * @ClassName ErWeiMaUtils
 * @Description TODO
 * @Author admin
 * @Date 2021/6/15 15:33
 * @Version 1.0
 */
public class ErWeiMaUtil {
    /**
     * @return java.io.InputStream
     * @Author fengfanli
     * @Description //TODO 从微信平台 获取小程序二维码
     * @Date 16:53 2021/4/1
     * @Param []
     **/
    public static InputStream getErWeiMa(Integer id, String pagePath) {
        // 获取二维码：1、先获取access_token 2、在获取 二维码
        String appId = PropertiesFileUtil.getInstance("app").get("wx.app.ip");
        String appSecret = PropertiesFileUtil.getInstance("app").get("wx.app.secret");
        System.out.println("appId==" + appId + ";appSecret==" + appSecret);
        // 获取 access_token
        String getUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        System.out.println("getUrl==" + getUrl);
        String str = HttpUtil.sendGet(getUrl);
        JSONObject json = (JSONObject) JSONObject.parse(str);
        String access_token = (String) json.get("access_token");
        System.out.println("access_token:" + access_token);
        // 获取 二维码图片流
        String postURL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + access_token;
        System.out.println("postURL==" + postURL);
        JSONObject jsonObject = new JSONObject();
        // 这里往二维码里放入前端传过来的 id，前端获取之后，根据id 去获取详情
        jsonObject.put("scene", id);
        jsonObject.put("page", pagePath);
        String scene = JSONObject.toJSONString(jsonObject);
        InputStream inputStream = null;
        inputStream = HttpUtil.sendPostForErWeiMa(postURL, scene);
        return inputStream;
    }
}
