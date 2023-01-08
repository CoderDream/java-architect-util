package com.feng.echartsUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feng.bean.BakeEventList;
import com.feng.fileUtils.PropertiesFileUtil;
import com.feng.posterUtils.PosterUtil;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EchartsUtil
 * @Description TODO
 * @Author admin
 * @Date 2021/4/6 14:32
 * @Version 1.0
 */
public class EchartsUtil {
//    private static String url = "http://localhost:6666";
    private static String url = PropertiesFileUtil.getInstance("dev").get("poster.echarts.serverIpAndPort");
    private static final String SUCCESS_CODE = "1";


    /**
     * @return void
     * @Author fengfanli
     * @Description //TODO 生成 echarts 折线图
     * @Date 17:17 2021/4/7
     * @Param [bufferedImage, bakeEventListModels]
     **/
    public static void createEcharts(BufferedImage bufferedImage, List<BakeEventList> bakeEventLists) throws Exception {
        // 变量
        String echartTitle = "烘焙曲线图";
        String[] echartTime = new String[bakeEventLists.size()];
        Double[] echartValues = new Double[bakeEventLists.size()];
        for (int i = 0; i < bakeEventLists.size(); i++) {
            // x 轴
            echartTime[i] = bakeEventLists.get(i).getBakeTime();
            // 数据
            echartValues[i] = bakeEventLists.get(i).getBeanTemperature();
        }

        // 模板参数
        HashMap<String, Object> datas = new HashMap<>();
        datas.put("category", JSON.toJSONString(echartTime));
        datas.put("categories", JSON.toJSONString(echartValues));
        datas.put("title", echartTitle);
        // 生成option字符串
        String option = FreemarkerUtil.generateString("option.ftl", "echarts", datas);

        // 根据option参数
        String base64 = EchartsUtil.generateEchartsBase64(option);

        byte[] bytes = Base64.decodeBase64(base64);
        InputStream stream = new ByteArrayInputStream(bytes);
        BufferedImage echartImg = PosterUtil.drawInit(null, stream);
        PosterUtil.drawImageAndChangeBackgroundColorAndChangeSize(bufferedImage, echartImg, 75, 700, 600, 300);

        // 解析 base64 并保存到本地
        // generateImage(base64, "C:\\Users\\admin\\Desktop\\test1.png");
    }

    /**
     * @Author fengfanli
     * @Description //TODO base64 解码，并保存到 path 本地路径中
     * @Date 15:43 2021/4/8
     * @Param [base64, path]
     * @return void
     **/
    public static void generateImage(String base64, String path) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        try (OutputStream out = new FileOutputStream(path)){
            // 解密
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            out.write(b);
            out.flush();
        }
    }

    /**
     * @Author fengfanli
     * @Description //TODO 将模板字符串 发请求至 phantomjs 并返回 base64 编码的图片
     * @Date 15:46 2021/4/8
     * @Param [option]
     * @return java.lang.String
     **/
    public static String generateEchartsBase64(String option) throws IOException, IOException {
        String base64 = "";
        if (option == null) {
            return base64;
        }
        option = option.replaceAll("\\s+", "").replaceAll("\"", "'");

        // 将option字符串作为参数发送给 echartsConvert 服务器
        Map<String, String> params = new HashMap<>();
        params.put("opt", option);
        String response = HttpUtil.setPost(url, params, "utf-8");

        // 解析echartsConvert响应
        JSONObject responseJson = JSON.parseObject(response);
        String code = responseJson.getString("code");

        // 如果echartsConvert正常返回
        if (SUCCESS_CODE.equals(code)) {
            base64 = responseJson.getString("data");
        }
        // 未正常返回
        else {
            String string = responseJson.getString("msg");
            throw new RuntimeException(string);
        }

        return base64;
    }
}
