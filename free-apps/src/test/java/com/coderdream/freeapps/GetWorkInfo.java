package com.coderdream.freeapps;

import com.coderdream.freeapps.util.callapi.Company;
import com.coderdream.freeapps.util.callapi.WorkInfo;
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
            String url = "https://www.zhipin.com/c101280100/?query=" + workName + "&page=" + i + "&ka=page-" + i;

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
        ExcelExportUtil.exportToFile("E:\\re.xls", all );
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
            "wd_guid=b8baa218-7b6e-45b1-a065-d0fbfee51267; historyState=state; YD00951578218230%3AWM_NI=dfYzwkm2KtPBgaY4uTo2s4ODlKbAIPyJdonG9bHOe%2FiTeQgDVglu1eqpsD3yTzmWxv4uhgQ9yHFvanII%2B3QjxpZjjgc3lWum9QZ0FRXoWhbJhB9SA3IXbiTmG3BG2ZttcEM%3D; YD00951578218230%3AWM_NIKE=9ca17ae2e6ffcda170e2e6ee8ec1538db9bbb1ec3c839e8ea6c44b968f8f86c17983f1a4ace55d939584b0ea2af0fea7c3b92aa8ba979bf661afee8595d34aa5bf97d5f860adeebdd5ce50b095aea2cc5cbb96ac90ed4dfbbe88b0e825ab8cabb2e65096b9a496b46fb3b0bda5b27398a6a3b8fb7ef8aba888d433bb988a97e4668b97e5d4d462bcb0968be96da1eda4b8f867fb8f96d1c55a9cb19989e142ac9ef7b4c4398b88ae8ad76785ed8ea8b152b8acada6d037e2a3; YD00951578218230%3AWM_TID=XPis0RjnJ8FEAQEQFFPBpVuxebXQbfKx; gdxidpyhxdE=Hq9C3SmowzhscqR%2F16W2sjNq%5C3MAaPu8HoI0aEfHNdjfJGNJ%2B%2FRN06%2Fmrlu6drqxkQtlKjVJbWR6ZcpDzhkLLhgVw%2FpLhCuTWCo35hnSmIPAhgiBZL3mf1zUzCDR5bnc5lu8Nu2hRKeUWCY%2BAIGK4Q6Izuw2NGTu4rVrHY1tYc%2B%2B4mvh%3A1721362941437; wt2=D26KaN0ehav0eQiTXypPP36eXxNTqzVTcyKqLVHGD1hFkske7KrFRiVsv9kBck5hK4K6eFxEtInNCS2WQy-Dz3A~~; wbg=0; zp_at=KI2Y62nFzZnRWx2ZMfZt4LDuiUs4hymhnuwKFIGaWok~; __g=-; bst=V2Q9ogE-P601dgXdJrzRwYLyOw7DnXwA~~|Q9ogE-P601dgXdJrzRwYLyOw7DPUzQ~~; __c=1721438342; __l=l=%2Fwww.zhipin.com%2Fjob_detail%2F1e62322779fbc3831HN70tW7EFBY.html&r=&g=&s=3&friend_source=0&s=3&friend_source=0; __a=53996332.1701820773.1721396069.1721438342.65.12.9.65; __zp_stoken__=e7b3fQ0XDrsOHwrzDgkoyGBQRFxJOLEZFN3FNQzFPQkhDRUE8TkNFSSI8M8Klw4BVKsK5asOQPUUwRUFPRUNLQUJKIEVNxYDCvDxGNMOCwrxQKsK7bcOMYcKuw4ATLhIgw4ETwprDhjPCssOCMjHDq8OARzxOPMKgw45MwrfDhsONV8KxwrzDiVDCtjxGPCY9QB4UbmNARlFXXBJQbVBqZFQSX1hbMDxNQUXCscOxxIA7RxkfGxQfFgwQFwwWDBAYGxgaHh0aHRsfGBs7RcKvw4ddwqTCrMO3xIvEpsKlw4XCoV7CmMKiwqLCuMKgYsKhwqLCusOFwpzCssKbwr3CqMOHwq3DisOEw4Jrw4NXXFdcwr1fb8KHasONXMKGaVVtb8OIGxodZBloQB7Dk8Kew5Y%3D");

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

