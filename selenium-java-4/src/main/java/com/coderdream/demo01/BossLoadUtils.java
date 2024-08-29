package com.coderdream.demo01;


import java.io.IOException;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @Desc: 简单爬取boss直聘的基本招聘信息
 * @Author: me
 * @Created by: 2019/7/3 0003 16:48
 **/
class BossLoadUtils {

    private static String[] mJobTypes = {"android", "ios", "java", "python", "php", "h5", "大数据", "flutter", ""};
    private static int mIndex = 0;//切换不同岗位

    public static void main(String[] args) throws IOException, InterruptedException {
//        Connection connection = Jsoup.connect(
//            "https://www.zhipin.com/job_detail/?query=" + mJobTypes[mIndex] + "&city=101280100");
//        Response res = connection.execute();
//
//
//        System.out.println("cookies: " + res.cookies() +  Jsoup.parse(res.body()));


        //获取编辑推荐页
        Document document = Jsoup.connect(
                "https://www.zhipin.com/job_detail/?query=" + mJobTypes[mIndex] + "&city=101280100")
            //模拟火狐浏览器
            .userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
            .get();
        //获取工作列表数据


        Elements job_primary = document.select("div[class='job-primary']");
        //遍历取出每一条工作数据记录集合
        List<String> mJobInfoList = job_primary.eachText();
        printTabTitleBar();
        for (String jobInfo : mJobInfoList) {
            //可以根据空格做切割处理获取相关数据（注意：这里可以更精确的筛选对应标签取出对应值，我偷懒了）
            String[] mSplit = jobInfo.split(" ");
            JobInfo mJobInfo = new JobInfo(mSplit[0], mSplit[1], mSplit[2], mSplit[3], mSplit[4], mSplit[5], mSplit[6],
                mSplit[7]);
            printlnJobInfo(mJobInfo);
            Thread.sleep(10);
            printDashLine();
        }
    }

    private static void printTabTitleBar() {
        printlnTitle();
        printDashLine();
        StringBuffer sb = new StringBuffer();
        append(sb, "岗位名称", "薪资", "城市", "区", "办公地点及工作年限", "公司", "行业及人数", "招聘者");
        println(sb.toString());
        printDashLine();
    }

    private static void append(StringBuffer sb, String jobName, String salary, String city, String area,
        String locationAndYears, String company, String industryAndPersonNum, String recruiter) {
        sb.append(jobName).append("  |  ")
            .append(salary).append("  |  ")
            .append(city).append("  |  ")
            .append(area).append("  |  ")
            .append(locationAndYears).append("     |      ")
            .append(company).append("     |      ")
            .append(industryAndPersonNum).append("     |      ")
            .append(recruiter).append("|");
    }

    private static void printlnTitle() {
        println("-----------------------------------------------------------Java爬取boss直聘" + mJobTypes[mIndex]
            + "招聘信息-------------------------------------------------------");
    }

    private static void printlnJobInfo(JobInfo jobInfo) {
        StringBuffer sb = new StringBuffer();
        append(sb, jobInfo.jobName, jobInfo.salary, jobInfo.city, jobInfo.area, jobInfo.locationAndYears,
            jobInfo.company, jobInfo.industryAndPersonNum, jobInfo.recruiter);
        println(sb.toString());
    }

    private static void println(String log) {
        System.out.println(log);
    }

    private static void printDashLine() {
        println(
            "---------------------------------------------------------------------------------------------------------------------------------------------");
    }
}

class JobInfo {

    public String jobName;               //岗位名称
    public String salary;                //薪资
    public String city;                  //城市
    public String area;                  //区
    public String locationAndYears;      //办公地点及工作年限
    public String company;               //公司
    public String industryAndPersonNum;  //行业及人数
    public String recruiter;             //招聘者

    public JobInfo(String jobName, String salary, String city, String area, String locationAndYears, String company,
        String industryAndPersonNum, String recruiter) {
        this.jobName = jobName;
        this.salary = salary;
        this.city = city;
        this.area = area;
        this.locationAndYears = locationAndYears;
        this.company = company;
        this.industryAndPersonNum = industryAndPersonNum;
        this.recruiter = recruiter;
    }
}
