package com.feng.controller;

import com.alibaba.fastjson.JSON;
import com.feng.echartsUtils.EchartsUtil;
import com.feng.echartsUtils.FreemarkerUtil;
import freemarker.template.TemplateException;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.HashMap;

/**
 * @ClassName CreateEcharts
 * @Description TODO
 * @Author admin
 * @Date 2021/4/8 15:40
 * @Version 1.0
 */
public class Test2_CreateEcharts {

    /**
     * @return void
     * @Author fengfanli
     * @Description //TODO 创建一个echarts图
     * @Date 18:06 2021/4/8
     * @Param [args]
     **/
    public static void main(String[] args) throws ClientProtocolException, IOException, TemplateException {
        // 变量
        String title = "水果";
        String[] categories = new String[]{"苹果", "香蕉", "西瓜"};
        int[] values = new int[]{3, 2, 1};

        // 模板参数
        HashMap<String, Object> datas = new HashMap<>();
        datas.put("categories", JSON.toJSONString(categories));
        datas.put("values", JSON.toJSONString(values));
        datas.put("title", title);

        // 生成option字符串
        String option = FreemarkerUtil.generateString("option.ftl", "echarts", datas);

        // 根据option参数
        String base64 = EchartsUtil.generateEchartsBase64(option);

        System.out.println("BASE64:" + base64);
        EchartsUtil.generateImage(base64, "D:\\test2212.png");
    }
}
