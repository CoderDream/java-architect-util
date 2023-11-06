package com.coderdream.freeapps.util.boss;

import com.xuxueli.poi.excel.ExcelExportUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class GetWorkInfo {

    private static List<WorkInfo> all = new LinkedList<>();

    public static void main(String[] args) {

        // 注意几个请求参数
        String workName = "java";//职业名称

        for (int i = 1; i < 5; i++) {//当前页码i
            String url = "https://www.zhipin.com/c101200100/?query=" + workName + "&page=" + i + "&ka=page-" + i;
            url = "https://www.zhipin.com/web/geek/job?query=Java&city=101200100&page=" + i;
            url =  "https://www.zhipin.com/c101280100/?query="+workName+"&page="+i+"&ka=page-"+i;
            String html = getHtml(url);
            cleanHtml(html);
        }

        System.out.println("一共获取到" + all.size() + "条与[" + workName + "相关]的招聘信息");
        for (WorkInfo workInfo : all) {

            System.out.println(workInfo);
        }

        //保存所有workinfo tag 到文本
        FileWriter writer;
        try {
            writer = new FileWriter("E:/tags.txt");

            for (WorkInfo workInfo : all) {
                for (String tag : workInfo.getTags()) {
                    writer.write(tag + "  ");
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //保存数据到excel
//        ExcelExportUtil.exportToFile(all, "E:\\re.xls"); TODO
    }

    /**
     * 模拟浏览器请求，获取网页内容
     *
     * @param url
     * @return
     */
    public static String getHtml(String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";

        // 通过址默认配置创建一个httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpGet远程连接实例
        HttpGet httpGet = new HttpGet(url);
        // 设置请求头信息，鉴权
        httpGet.setHeader("User-Agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36 Edg/89.0.774.54");
        //cookie需要手动到网站获取
        httpGet.setHeader("Cookie",
            "_bl_uid=2ak8Cl39wtUayvud03U7415wmn52; lastCity=101280100; wt2=DnjgeQY_6OeBVy04-sQp4F_CiAIn9dJpTlC0K33an6klsXW3bnjH4lQPp5AZUW0EQ3xLYz60xQVWaN623Hku8wg~~; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1622131357,1622216304,1622278889,1622299753; __g=-; __c=1622299753; __l=l=/www.zhipin.com/c101280100/?query&r=&g=&s=3&friend_source=0&s=3&friend_source=0; __a=83640636.1614948744.1622278840.1622299753.344.24.10.344; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1622305422; __zp_stoken__=4157cYS0UJAEtPDc2DHNwVwlPanNjEH1dPlJtWHRGBA4VJj90f1dVB0VIfFRaeB4FJmQSAFkUXBIrUElmDTACJgJgazwMRXNFNFsHCDEZPVNSWFxLYWgDbRAhN3kHUkN4FkcCPV9HVmdcYHlG; __zp_sseed__=+TM8nqRm+BG8IPVh+stoLe4NGHJh2C4AO17GaA5dk1s=; __zp_sname__=9f0a9905; __zp_sts__=1622305425603");

        // 设置配置请求参数
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
            .setConnectionRequestTimeout(35000)// 请求超时时间
            .setSocketTimeout(60000)// 数据读取超时时间
            .build();
        // 为httpGet实例设置配置
        httpGet.setConfig(requestConfig);
        // 执行get请求得到返回对象
        try {
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Object cleanHtml(String html) {
        org.jsoup.nodes.Document document = (org.jsoup.nodes.Document) Jsoup.parseBodyFragment(html);
        //获取body
        Element body = document.body();
        Elements jobPrimaries = body.getElementById("main").getElementsByClass("job-primary");

        //遍历所有的招聘信息
        for (int i = 0; i < jobPrimaries.size(); i++) {
            //第i条招聘信息
            Element job = jobPrimaries.get(i);
            //招聘概要信息
            WorkInfo workInfo = new WorkInfo();
            String jobName = job.getElementsByClass("job-name").get(0).text();
            String jobArea = job.getElementsByClass("job-area-wrapper").get(0).text();
            String jobPubTime = job.getElementsByClass("job-pub-time").get(0).text();
            String educationWork = job.getElementsByClass("job-limit").get(0).getElementsByTag("p").get(0).outerHtml();
            String education = educationWork.substring(educationWork.lastIndexOf("</em>") + 5,
                educationWork.indexOf("</p>"));
            String jobLimit = job.getElementsByClass("job-limit").get(0).getElementsByTag("p").get(0).text()
                .replace(education, "");
            String url = "https://www.zhipin.com" + job.getElementsByClass("primary-box").get(0).attr("href");
            List<String> tagList = new LinkedList<>();
            Elements tags = job.getElementsByClass("tag-item");
            for (Element tag : tags) {
                tagList.add(tag.text());
            }

            //公司信息
            Company company = new Company();
            String companyName = job.getElementsByClass("company-text").get(0).getElementsByTag("h3").text();
            String companyType = job.getElementsByClass("company-text").get(0).getElementsByTag("p").get(0)
                .getElementsByTag("a").text();
            String benefits = job.getElementsByClass("info-desc").get(0).text();

            company.setCompanyName(companyName);
            company.setCompanyType(companyType);
            company.setBenefits(benefits);

            workInfo.setJobName(jobName);
            workInfo.setJobArea(jobArea);
            workInfo.setJobPubTime(jobPubTime);
            workInfo.setEducation(education);
            workInfo.setJobLimit(jobLimit);
            workInfo.setTags(tagList);
            workInfo.setDetailUrl(url);
            workInfo.setCompany(company);

            all.add(workInfo);
        }
        return null;
    }
}
