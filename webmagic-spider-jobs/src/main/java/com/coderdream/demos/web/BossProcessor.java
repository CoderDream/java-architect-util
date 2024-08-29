package com.coderdream.demos.web;

import cn.hutool.core.util.StrUtil;
import com.coderdream.demos.web.pojo.BossJobDetail;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author CoderDream
 */
public class BossProcessor implements PageProcessor {

    public static AtomicInteger pageNum = new AtomicInteger(2);

    @Override
    public void process(Page page) {
        //去抓取职位列表
        List<Selectable> nodes = page.getHtml().css("li.job-card-wrapper").nodes();

        if (nodes != null && nodes.size() > 0 || page.getUrl().get().contains("geek/job")) {
            //有值就是工作列表页
            //遍历所有的列表项，拿超链接
            for (Selectable node : nodes) {
                String s = node.css("a.job-card-left").links().get();
                page.addTargetRequest(s);

            }
            page.addTargetRequest(
                Constants.BASE_URL + "&page=" + pageNum.getAndIncrement());

        } else {
            //工作详情页  处理我们想要的信息，我这里都用了CSS选择器
            Selectable biggest = page.getHtml().css("div#wrap");
            BossJobDetail bossJobDetail = new BossJobDetail();
            Selectable primary = biggest.css("div.info-primary");
            String jobStatus = primary.css("div.job-status").get();
            String workName = primary.css("div.name h1").get();
            String salary = primary.css("div.name span").get();
            String year = primary.css("p span.text-experiece").get();
            String graduate = primary.css("p span.text-desc.text-degree").get();
            Selectable tagContainer = biggest.css("div.job-banner div.tag-container-new");
            String freeTags = tagContainer.css("div.job-tags span").get();

            // 公司基本信息
            Selectable companyBasicInfo = biggest.css("div.sider-company");
            String companyTitle = companyBasicInfo.css("div.company-info").get();
//            String companyStage = companyBasicInfo.css("p").all().get(1);
//            String companyScale = companyBasicInfo.css("p").all().get(2);
//            String companyIndustry = companyBasicInfo.css("p").all().get(3);
            bossJobDetail.setCompanyTitle(Jsoup.parse(companyTitle).text());
//            bossJobDetail.setCompanyStage(Jsoup.parse(companyStage).text());
//            bossJobDetail.setCompanyScale(Jsoup.parse(companyScale).text());
//            bossJobDetail.setCompanyIndustry(Jsoup.parse(companyIndustry).text());

            Selectable jobDetail = biggest.css("div.job-detail");

            List<String> jobKeywordList = jobDetail.css("ul.job-keyword-list li").all();

            String workContent = jobDetail.css("div.job-detail-section div.job-sec-text").get();

            String bossName = jobDetail.css("h2.name").get();
            String bossActiveTime = jobDetail.css("h2.name span").get();
            String bossRole = jobDetail.css("div.boss-info-attr").get();

            Selectable jobSider = biggest.css("div.job-sider");
            String companyName = jobSider.css("div.sider-company a[ka=job-detail-company_custompage]").get();
            String workAddress = jobDetail.css("div.location-address").get();

            Selectable jobLocation = biggest.css("div.job-location");
            String dataLat = jobLocation.css(".job-location-map", "data-lat").get();

            Selectable businessInfoBox = biggest.css("div.level-list-box");

            String  companyFullName = businessInfoBox.css("ul li.company-name").get();
            String  companyUser = businessInfoBox.css("ul li.company-user").get();
            String  resTime = businessInfoBox.css("ul li.res-time").get();
            String  companyType = businessInfoBox.css("ul li.company-type").get();
            String  manageState = businessInfoBox.css("ul li.manage-state").get();
            String  companyFund = businessInfoBox.css("ul li.company-fund").get();

            bossJobDetail.setCompanyUser(Jsoup.parse(companyUser).text());
            bossJobDetail.setResTime(Jsoup.parse(resTime).text());
            bossJobDetail.setCompanyType(Jsoup.parse(companyType).text());
            bossJobDetail.setCompanyStage(Jsoup.parse(manageState).text());
            bossJobDetail.setCompanyFund(Jsoup.parse(companyFund).text());

            bossJobDetail.setJobStatus(Jsoup.parse(jobStatus).text());
            bossJobDetail.setJobName(Jsoup.parse(workName).text());
            bossJobDetail.setSalary(Jsoup.parse(salary).text());
            bossJobDetail.setExperience(Jsoup.parse(year).text());
            bossJobDetail.setDegree(Jsoup.parse(graduate).text());
            bossJobDetail.setFreeTags(Jsoup.parse(freeTags).text());

            String s  = jobKeywordList.toString();
            bossJobDetail.setJobKeywordList(Jsoup.parse(s).text());
            bossJobDetail.setJobSecText(Jsoup.parse(workContent).text());

            bossJobDetail.setBossName(Jsoup.parse(bossName).text());
            bossJobDetail.setBossActiveTime(Jsoup.parse(bossActiveTime).text());
            bossJobDetail.setBossRole(Jsoup.parse(bossRole).text());

            bossJobDetail.setCompanyName(Jsoup.parse(companyName).text());

            bossJobDetail.setLocationAddress(Jsoup.parse(workAddress).text());
            bossJobDetail.setDataLat(Jsoup.parse(dataLat).text());

            bossJobDetail.setCreateTime(new Date());
            bossJobDetail.setLastModifiedTime(new Date());

            String url = page.getUrl().get();
            if (StrUtil.isNotEmpty(url)) {
                bossJobDetail.setJobUrl(url);
                // https://www.zhipin.com/job_detail/d55956585ae170880Xd609u_FFU~.html?lid=8iXpQvJTt4S.search.1&securityId=-E4FHnpNk2OY_-81sTIdhtXqrYbEJTeUHzuxAf6ljbrrJWsia9H0QqYS3JUboL_Uywvef0k4ceF4D-xuSZn9FXOCzMybmZxjWPJWv6mlFMfKepE~&sessionId=
                bossJobDetail.setEncryptJobId(url.substring(url.lastIndexOf("/") + 1, url.indexOf(".html")));
            }
//            if(StrU)
//            bossJobDetail.setJobUrl();

            page.putField("bossJobDetail", bossJobDetail);
        }

    }

    //可以对爬虫进行一些配置
    private Site site = Site.me()
        // 单位是秒
        .setCharset("UTF-8")//编码
        .setSleepTime(3000)//抓取间隔时间,可以解决一些反爬限制
        .setTimeOut(1000 * 10)//超时时间
        .setRetrySleepTime(3000)//重试时间
        .setRetryTimes(3);//重试次数

    @Override
    public Site getSite() {
        return site;
    }
}
