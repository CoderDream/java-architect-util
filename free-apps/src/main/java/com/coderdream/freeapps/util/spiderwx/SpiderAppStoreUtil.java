package com.coderdream.freeapps.util.spiderwx;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 文章爬取工具类
 *
 * @author ZhangYuanqiang
 * @since 2020/01/04
 */
public class SpiderAppStoreUtil {

    // 微信公众号文章域名
    private static final String WX_DOMAIN = "https://apps.apple.com";
    // 文章返回前端统一key常量
    private static final String KEY_TITLE = "title"; // 文章标题
    private static final String KEY_COVER_URL = "coverLink"; // 文章封面图链接
    private static final String KEY_REFER_NAME = "referName"; // 文章出处作者
    private static final String KEY_REFER_URL = "referLink"; // 文章出处链接
    private static final String KEY_TAGS = "tags"; // 文章内容
    private static final String KEY_NAME = "name"; // 标签名称
    private static final String KEY_TEXT = "text"; // 文本信息
    private static final String KEY_HREF = "href"; // a标签链接

    /**
     * 测试主方法
     */
    public static void main(String args[]) {
        String url = "https://apps.apple.com/cn/app/id1491603360?from=iospocket.com&mt=8";
        Resp<JSONObject> resp = getArticle(url);
        if (resp.isSuccess()) {
            System.out.println(resp.getBody());
        } else {
            System.out.println(resp.getMsg());
        }
    }

    /**
     * 根据文章链接抓取文章内容
     *
     * @param url 文章链接
     * @return 文章内容
     */
    public static Resp<JSONObject> getArticle(String url) {
        // 检测链接是否合法
        String msg = checkUrl(url);
        if (msg != null) {
            return Resp.error(msg);
        }
        // 请求与响应
        String resp = HttpTool.get(url, getWxHeaderMap());
        if (resp == null || resp.trim().length() == 0) {
            return Resp.error("文章获取失败，请检查链接是否正确");
        }
        // 解析
        Resp<JSONObject> articleResp = getWxArticleContent(resp, url);
        if (articleResp.isError()) {
            return Resp.error(articleResp.getMsg());
        }
        return articleResp;
    }

    /**
     * 检测文章链接是否合法
     */
    public static String checkUrl(String url) {
        if (url == null) {
            return "请输入文章链接";
        }
        if (!url.startsWith(WX_DOMAIN)) {
            return "请输入微信公众号文章链接";
        }
        return null;
    }


    /**
     * 微信公众号请求头设置
     */
    public static Map<String, String> getWxHeaderMap() {
        Map<String, String> map = new HashMap<>(new LinkedHashMap<>());
        map.put("Accept", "text/html, application/xhtml+xml, image/jxr, */*");
        map.put("Accept-Encoding", "gzip, deflate");
        map.put("Accept-Language", "zh-Hans-CN, zh-Hans; q=0.8, en-US; q=0.5, en; q=0.3");
        map.put("Host", "mp.weixin.qq.com");
        map.put("If-Modified-Since", "Sat, 04 Jan 2020 12:23:43 GMT");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
        return map;
    }

    /**
     * 解析微信公众号文章
     *
     * @param resp 请求文章响应
     * @param url 文章链接
     * @return 文章信息
     */
    public static Resp<JSONObject> getWxArticleContent(String resp, String url) {
        try {
            Document document = Jsoup.parse(resp);
            // 文章出处（作者）
            String referName = document.getElementsByClass("profile_nickname").get(0).text();
            // 文章封面图链接
            String coverUrl = document.select("meta[property=\"og:image\"]").get(0).attr("content");
            // 文章标题
            String title = document.getElementById("activity-name").text();
            // 文章内容
            Element content = document.getElementsByClass("rich_media_area_primary_inner").get(0);
            JSONObject json = new JSONObject(new LinkedHashMap<>());
            json.put(KEY_TITLE, title);
            json.put(KEY_COVER_URL, coverUrl);
            json.put(KEY_REFER_NAME, referName);
            json.put(KEY_REFER_URL, url);
            JSONArray tags = new JSONArray();
            Elements sections = content.select("*");
            for (Element element : sections) {
                if (element.children().isEmpty()) {
                    getChildTag(element, tags);
                }
            }
            json.put(KEY_TAGS, tags);
            return Resp.success(json);
        } catch (Exception e) {
            e.printStackTrace();
            return Resp.error("文章解析失败");
        }
    }

    public static void getChildTag(Element element, JSONArray tags) {
        JSONObject tag = new JSONObject(new LinkedHashMap<>());
        String tagName = element.tagName();
        tag.put(KEY_NAME, tagName);
        switch (tagName) {
            case "span":
                tag.put(KEY_TEXT, element.text());
                tags.add(tag);
                break;

            case "section":
                tag.put(KEY_TEXT, element.text());
                tags.add(tag);
                break;

            case "strong":
                tag.put(KEY_TEXT, element.text());
                tags.add(tag);
                break;

            case "img":
                Attributes attrs = element.attributes();
                if (attrs != null) {
                    for (Attribute attr : attrs) {
                        tag.put(attr.getKey().replace("-", ""), attr.getValue());
                    }
                }
                tags.add(tag);
                break;

            case "a":
                tag.put(KEY_HREF, element.attr("href"));
                tag.put(KEY_TEXT, element.attr("textvalue"));
                tags.add(tag);
                break;

            case "br":
                tags.add(tag);
                break;

            case "p":
                tag.put(KEY_TEXT, element.text());
                tags.add(tag);
                break;
            default:
                break;
        }
    }

}
