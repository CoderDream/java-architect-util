package com.coderdream.freeapps.util.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**
 * 模拟点击，动态获取页面信息
 * @Author: Uncle liu
 * @Date: 2019-10-07 15:39
 */
public class JsoupHttpClient {

    private static class innerWebClient{
        private static final  WebClient webClient = new WebClient();
    }

    /**
     * 获取指定网页实体
     * @param url
     * @return
     */
    public static HtmlPage getHtmlPage(String url){
        //调用此方法时加载WebClient
        WebClient webClient = innerWebClient.webClient;
        //用例1设置方式
        // 取消 JS 支持
//        webClient.setJavaScriptEnabled(false);
//        // 取消 CSS 支持
//        webClient.setCssEnabled(false);
        //用例2设置方式
        // 取消 JS 支持
        webClient.getOptions().setJavaScriptEnabled(false);
        // 取消 CSS 支持
        webClient.getOptions().setCssEnabled(false);
        HtmlPage page=null;
        try{
            // 获取指定网页实体
            page = (HtmlPage) webClient.getPage(url);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return page;
    }


    public static void main(String[] args) throws Exception {
       m2();
    }

    public static void m2() {
        // 获取指定网页实体
        String url = "https://www.baidu.com/";
        url = "https://app.diandian.com/rank/ios/1-1-173-75-3?market_id=1&genre_id=173&country_id=75&device_id=1&page=11";
//        url = "https://api.diandian.com/pc/app/v1/rank?market_id=1&genre_id=173&country_id=75&device_id=1&page=11&time=1682784000&rank_type=1&brand_id=3&k=RhIXcUdbBEsFaU8XFnBGWwVABGlCCwBmX1sCQQFnQhEQdU5CFFUeOhMLAGZfRURCGCwWTU0=";

        url = "https://api.diandian.com/pc/app/v1/rank?market_id=1&genre_id=173&country_id=75&device_id=1&page=10&time=1682784000&rank_type=1&brand_id=3&k=RhIXcUZbBEsFaU8XFnBGWwVABGlCCwBmX1sCQQFnQhEQdUNCFFUeOhMLAGZfRURCGCwWTU0%3D";
        HtmlPage page = getHtmlPage(url);

        System.out.println(page.asText());  //asText()是以文本格式显示
        System.out.println(page.asXml());   //asXml()是以xml格式显示
//        // 获取搜索输入框
//        HtmlInput input = page.getHtmlElementById("kw");
//        // 往输入框 “填值”
//        input.setValueAttribute("绿林寻猫");
//        // 获取搜索按钮
//        HtmlInput btn = page.getHtmlElementById("su");
//        // “点击” 搜索
//        HtmlPage page2 = btn.click();
        // 选择元素
//        List<Object> byXPath = page2.getElementById("//h3[@class='t']/a");
//        for(Object o:byXPath){
//            HtmlElement htmlElement = (HtmlElement) o;
//            System.out.println(htmlElement.asText());
//        }
    }

    public static void m1() throws IOException {
        // 获取指定网页实体
        String url = "https://www.baidu.com/";
        HtmlPage page = getHtmlPage(url);
        System.out.println(page.asText());  //asText()是以文本格式显示
        System.out.println(page.asXml());   //asXml()是以xml格式显示
        // 获取搜索输入框
        HtmlInput input = page.getHtmlElementById("kw");
        // 往输入框 “填值”
        input.setValueAttribute("绿林寻猫");
        // 获取搜索按钮
        HtmlInput btn = page.getHtmlElementById("su");
        // “点击” 搜索
        HtmlPage page2 = btn.click();
        // 选择元素
        List<Object> byXPath = page2.getByXPath("//h3[@class='t']/a");
        for(Object o:byXPath){
            HtmlElement htmlElement = (HtmlElement) o;
            System.out.println(htmlElement.asText());
        }
    }

}
