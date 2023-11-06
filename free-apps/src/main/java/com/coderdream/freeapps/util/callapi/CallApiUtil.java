package com.coderdream.freeapps.util.callapi;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.model.BossJobLogEntity;
import com.coderdream.freeapps.model.JobInfoEntity;
import com.coderdream.freeapps.util.other.CdConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
//@RefreshScope
public class CallApiUtil {

    public static void main(String[] args) {
//        CallApiUtil.callApi();
        // "https://blog.csdn.net/weixin_30846347/article/details/114722557?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-114722557-blog-101452322.235%5Ev36%5Epc_relevant_default_base3&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-114722557-blog-101452322.235%5Ev36%5Epc_relevant_default_base3&utm_relevant_index=2"
        String url = "https://www.zhipin.com/wuhan/";// getCCC
//        String cookies = CallApiUtil.getCookies(url);
        String cookies = CallApiUtil.getCCC(url);
        log.info(cookies);

//        Connection conn = Jsoup.connect(url);
//        conn.method(Method.GET);
//        conn.followRedirects(false);
//        Response response = null;
//        try {
//            response = conn.execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(response.cookies());

//        HttpClient client = new HttpClient();
//
//        GetMethod getMethod = new GetMethod(url);
//
//        try {
//
//            client.executeMethod(getMethod);
//
//            cookies = client.getState().getCookies();
//
//            getMethod.releaseConnection();
//
//            System.out.println("+++++++++++>>"+cookies[0]+">>>"+cookies[1]+"++++");
//
//        } catch (IOException e1) {
//
//            e1.printStackTrace();
//
//        }
    }

    public static String getCCC(String url) {
        HttpResponse res = HttpRequest.post(url).execute();
//预定义的头信息
        Console.log(res.header(Header.CONTENT_ENCODING));
//自定义头信息
        Console.log(res.header("Accept-Language"));
        Console.log(res.header("Cookie"));

        return res.header("Cookie");
    }

    public static List<Object> getJobInfoList(String referer, String url, Integer currentPage, String cookie,
        String districtFlag) {
        List<Object> result = new ArrayList<>();
        List<JobInfoEntity> jobInfoEntityList = new ArrayList<>();
        List<BossJobLogEntity> bossJobLogEntityList = new ArrayList<>();
        for (int i = currentPage; i < currentPage + 4; i++) {
            Map<String, Object> requestParam = new LinkedHashMap<>();
            requestParam.put("page", i);
            requestParam.put("pageSize", 30);

            Map<String, String> requestHead = new HashMap<>();
            requestHead.put("Accept", "application/json, text/javascript, */*; q=0.01");
            requestHead.put("Accept-Encoding", "gzip, deflate, br");
            requestHead.put("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
            requestHead.put("Referer", referer + currentPage);
            requestHead.put("Sec-Ch-Ua",
                "\"Google Chrome\";v=\"113\", \"Chromium\";v=\"113\", \"Not-A.Brand\";v=\"24\"");
            requestHead.put("Cookie", cookie);

            requestHead.put("Sec-Ch-Ua-Mobile", "?0");
//        requestHead.put("Sec-Ch-Ua-Mobile", "?0");
//        requestHead.put("Sec-Ch-Ua-Mobile", "?0");
//        requestHead.put("Sec-Ch-Ua-Mobile", "?0");
//        requestHead.put("", "");
//        requestHead.put("", "");
            requestHead.put("Sec-Ch-Ua-Platform", "\"Windows\"");
            requestHead.put("Sec-Fetch-Dest", "empty");
            requestHead.put("Sec-Fetch-Mode", "cors");
            requestHead.put("Sec-Fetch-Site", "same-origin");
            requestHead.put("Token", "OGVrEqCrhqv3ZxE");
            requestHead.put("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");
            requestHead.put("X-Requested-With", "XMLHttpRequest");

            //访问地址
            BossJobInfo bossJobInfo = HttpUtil.get(requestParam, requestHead, url, BossJobInfo.class, 5);

            // 请求无返回或无状态码返回
            if (bossJobInfo == null || bossJobInfo.getCode() == null) {
                log.error("未知异常，请求无返回");
                return null;
            }

            ZpData zpData = bossJobInfo.getZpData();
            if (zpData != null) {
                log.error("zpData: " + zpData);
            }

            //List<Map<String, Object>> mapList = (List<Map<String, Object>>) zpData;

            List<JobInfo> jobInfoList = zpData.getJobList();

            // 如果没有数据，设置出错信息
            if (CollectionUtils.isNotEmpty(jobInfoList)) {
                JobInfoEntity jobInfoEntity;
                BossJobLogEntity bossJobLogEntity;
                for (JobInfo jobInfo : jobInfoList) {
                    log.error("" + jobInfo);
                    jobInfoEntity = new JobInfoEntity();
                    BeanUtils.copyProperties(jobInfo, jobInfoEntity);
                    jobInfoEntity.setJobLabels(String.join(",", jobInfo.getJobLabels()));
//                jobInfoEntity.setIconFlagList(String.join(",",jobInfo.getIconFlagList()));
                    jobInfoEntity.setSkills(String.join(",", jobInfo.getSkills()));
                    jobInfoEntity.setWelfareList(String.join(",", jobInfo.getWelfareList()));
                    jobInfoEntity.setCreateDate(new Date());
                    transJobInfoEntityValues(jobInfoEntity);
                    jobInfoEntityList.add(jobInfoEntity);

                    bossJobLogEntity = new BossJobLogEntity();
                    BeanUtils.copyProperties(jobInfo, bossJobLogEntity);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    bossJobLogEntity.setSpiderDate(dateFormat.format(new Date()));
                    bossJobLogEntity.setDistrictFlag(districtFlag);
                    bossJobLogEntity.setCreateDate(new Date());
                    transBossJobLogEntityValues(bossJobLogEntity);
                    bossJobLogEntityList.add(bossJobLogEntity);
                }
            }
        }

        result.add(jobInfoEntityList);
        result.add(bossJobLogEntityList);
        // 设置数据
        return result;
    }


    public static List<Map<String, Object>> callApi() {
        Map<String, Object> requestParam = new LinkedHashMap<>();

        String token = "";

        Map<String, String> requestHead = new HashMap<>();
        requestHead.put("Accept", "application/json, text/javascript, */*; q=0.01");
        requestHead.put("Accept-Encoding", "gzip, deflate, br");
        requestHead.put("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        requestHead.put("Referer",
            "https://www.zhipin.com/web/geek/job?query=java%E9%AB%98%E7%BA%A7&city=101200100&areaBusiness=420111%3A908");
        requestHead.put("Sec-Ch-Ua", "\"Google Chrome\";v=\"113\", \"Chromium\";v=\"113\", \"Not-A.Brand\";v=\"24\"");
        requestHead.put("Cookie",
            "lastCity=101200100; wd_guid=95a92633-74e8-477e-bba4-956a27994955; historyState=state; _bl_uid=77lz5fjt8pUaXy5j5p1LlF3ptbt2; wt2=DwMimGfLSGmiOdxoeUkaMJuHCFhpwvyiogspq1bwwtlQbOZjYGnS8hVF5mfvcSJKiAgQjlv5aOvqZS4RIOl-UbA~~; wbg=0; __g=-; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1685444753,1685495555,1685581140,1685669412; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1685670718; __c=1685669412; __l=l=%2Fwww.zhipin.com%2Fweb%2Fgeek%2Fjob%3Fquery%3DJava%26city%3D101200100%26areaBusiness%3D420111%253A908&r=&g=&s=3&friend_source=0&s=3&friend_source=0; __a=99514548.1678799963.1685581140.1685669412.53.5.9.53; geek_zp_token=V1Q9ogE-P601dgXdNhyhsfLi2z7zjSxA~~; __zp_stoken__=40daeGnw4NCMqLTcNT3JwdlFsS1kLXTtmK2ZhOyAZZFQgKVJAEi1Na1dTbiZPU3F%2BD2RWBhlfN2dkUXpAMg93QmBQfysBHy0UHwRbDwFfSiVUAxJ9Ij8CFiUmTWNadBg1TV1kfRxWXQVyYXQ%3D");

        requestHead.put("Sec-Ch-Ua-Mobile", "?0");
//        requestHead.put("Sec-Ch-Ua-Mobile", "?0");
//        requestHead.put("Sec-Ch-Ua-Mobile", "?0");
//        requestHead.put("Sec-Ch-Ua-Mobile", "?0");
//        requestHead.put("", "");
//        requestHead.put("", "");
        requestHead.put("Sec-Ch-Ua-Platform", "\"Windows\"");
        requestHead.put("Sec-Fetch-Dest", "empty");
        requestHead.put("Sec-Fetch-Mode", "cors");
        requestHead.put("Sec-Fetch-Site", "same-origin");
        requestHead.put("Token", "OGVrEqCrhqv3ZxE");
        requestHead.put("User-Agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");
        requestHead.put("X-Requested-With", "XMLHttpRequest");

        //访问地址
        String url = "https://www.zhipin.com/wapi/zpgeek/search/joblist.json?scene=1&query=Java&city=101200100&experience=&payType=&partTime=&degree=&industry=&scale=&stage=&position=&jobType=&salary=&multiBusinessDistrict=420111:908&multiSubway=&page=2&pageSize=100";
        // 访问第三方接口
//        log.info(requestParam.toString());
        BossJobInfo bossJobInfo = HttpUtil.get(requestParam, requestHead, url,
            BossJobInfo.class, 5);

        // 请求无返回或无状态码返回
        if (bossJobInfo == null || bossJobInfo.getCode() == null) {
            log.error("未知异常，请求无返回");
            return null;
        }

        ZpData zpData = bossJobInfo.getZpData();
        if (zpData != null) {
            log.error("zpData: " + zpData);
        }

        //List<Map<String, Object>> mapList = (List<Map<String, Object>>) zpData;

        List<JobInfo> jobInfoList = zpData.getJobList();

        // 如果没有数据，设置出错信息
        if (CollectionUtils.isNotEmpty(jobInfoList)) {
            for (JobInfo jobInfo : jobInfoList) {
                log.error("" + jobInfo);
            }
        }
        // 设置数据
        return null;
    }

    public static String getCookies(String url) {
        // 全局请求设置
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        // 创建cookie store的本地实例
        CookieStore cookieStore = new BasicCookieStore();
        // 创建HttpClient上下文
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);

        // 创建一个HttpClient
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig)
            .setDefaultCookieStore(cookieStore).build();

        CloseableHttpResponse res = null;

        // 创建一个get请求用来获取必要的Cookie，如_xsrf信息
        HttpGet get = new HttpGet(url);
        try {
            res = httpClient.execute(get, context);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 获取常用Cookie,包括_xsrf信息
        StringBuffer cookie = new StringBuffer();
        for (Cookie c : cookieStore.getCookies()) {
            //拼接所有cookie变成一个字符串；
            cookie.append(c.getName() + "=" + c.getValue() + ";");
            System.out.println(c.getName() + ": " + c.getValue());
        }

        String cookieres = cookie.toString();
        cookieres = cookieres.substring(0, cookieres.length() - 1);
        try {
            res.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cookieres;
    }


    public static List<String> fileReadStcd() {
        List<String> stringList = new ArrayList<>();
        File file = new File("D:\\stcd.txt");//定义一个file对象，用来初始化FileReader
        FileReader reader;//定义一个fileReader对象，用来初始化BufferedReader
        try {
            reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
//            StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
            String s = "";
            while ((s = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
//                sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
                stringList.add(s.trim());
//                System.out.println(s);
            }
            bReader.close();
//            String str = sb.toString();
//            System.out.println(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stringList;
    }

    public static void transBossJobLogEntityValues(BossJobLogEntity bossJobLogEntity) {
        String jobExperience;
        String salaryDesc;
        int avg;
        Integer max;
        String brandScaleName;
        Integer min;
        int month = 12;
        salaryDesc = bossJobLogEntity.getSalaryDesc();
        if (StrUtil.isNotEmpty(salaryDesc)) {
            if (salaryDesc.contains(CdConstants.MIDDLE_DOT)) {
                String[] arr = salaryDesc.split(CdConstants.MIDDLE_DOT);
                salaryDesc = arr[0];
                month = Integer.parseInt(arr[1].replace("薪", ""));
            }

            if (salaryDesc.contains(CdConstants.MIDDLE_MINUS)) {
                String[] arr = salaryDesc.split(CdConstants.MIDDLE_MINUS);
                min = Integer.parseInt(arr[0]);
                if (arr[1].contains("K")) {
                    max = Integer.parseInt(arr[1].replace("K", ""));
                    bossJobLogEntity.setSalaryMin(min * month);
                    bossJobLogEntity.setSalaryMax(max * month);
                    avg = (max + min) * month / 2;
                    bossJobLogEntity.setSalaryAvg(avg);
                } else if (arr[1].contains("元/时")) {
                    max = Integer.parseInt(arr[1].replace("元/时", ""));
                    bossJobLogEntity.setSalaryMin(min * 8 * 12 * 22 / 1000);
                    bossJobLogEntity.setSalaryMax(max * 8 * 12 * 22 / 1000);
                    avg = (max + min) * 8 * 12 * 22 / 2000;
                    bossJobLogEntity.setSalaryAvg(avg);
                } else if (arr[1].contains("元/天")) {
                    max = Integer.parseInt(arr[1].replace("元/天", ""));
                    bossJobLogEntity.setSalaryMin(min * 12 * 22 / 1000);
                    bossJobLogEntity.setSalaryMax(max * 12 * 22 / 1000);
                    avg = (max + min) * 12 * 22 / 2000;
                    bossJobLogEntity.setSalaryAvg(avg);
                }
            }
        }

        jobExperience = bossJobLogEntity.getJobExperience();
        if (StrUtil.isNotEmpty(jobExperience)) {
            if (jobExperience.contains(CdConstants.MIDDLE_MINUS)) {
                String[] arr = jobExperience.split(CdConstants.MIDDLE_MINUS);
                bossJobLogEntity.setJobExperienceMax(Integer.parseInt(arr[1].replace("年", "")));
            }

            if (jobExperience.contains("年以上")) {
                bossJobLogEntity.setJobExperienceMax(Integer.parseInt(jobExperience.replace("年以上", "")));
            }
        }
        brandScaleName = bossJobLogEntity.getBrandScaleName();
        if (StrUtil.isNotEmpty(brandScaleName)) {
            if (brandScaleName.contains(CdConstants.MIDDLE_MINUS)) {
                String[] arr = brandScaleName.split(CdConstants.MIDDLE_MINUS);
                bossJobLogEntity.setBrandScale(Integer.parseInt(arr[1].replace("人", "")));
            }
        }
    }

    public static void transJobInfoEntityValues(JobInfoEntity jobInfoEntity) {
        String jobExperience;
        String salaryDesc;
        int avg;
        Integer max;
        String brandScaleName;
        Integer min;
        int month = 12;
        salaryDesc = jobInfoEntity.getSalaryDesc();
        if (StrUtil.isNotEmpty(salaryDesc)) {
            if (salaryDesc.contains(CdConstants.MIDDLE_DOT)) {
                String[] arr = salaryDesc.split(CdConstants.MIDDLE_DOT);
                salaryDesc = arr[0];
                month = Integer.parseInt(arr[1].replace("薪", ""));
            }

            if (salaryDesc.contains(CdConstants.MIDDLE_MINUS)) {
                String[] arr = salaryDesc.split(CdConstants.MIDDLE_MINUS);
                min = Integer.parseInt(arr[0]);
                if (arr[1].contains("K")) {
                    max = Integer.parseInt(arr[1].replace("K", ""));
                    jobInfoEntity.setSalaryMin(min * month);
                    jobInfoEntity.setSalaryMax(max * month);
                    avg = (max + min) * month / 2;
                    jobInfoEntity.setSalaryAvg(avg);
                } else if (arr[1].contains("元/时")) {
                    max = Integer.parseInt(arr[1].replace("元/时", ""));
                    jobInfoEntity.setSalaryMin(min * 8 * 12 * 22 / 1000);
                    jobInfoEntity.setSalaryMax(max * 8 * 12 * 22 / 1000);
                    avg = (max + min) * 8 * 12 * 22 / 2000;
                    jobInfoEntity.setSalaryAvg(avg);
                } else if (arr[1].contains("元/天")) {
                    max = Integer.parseInt(arr[1].replace("元/天", ""));
                    jobInfoEntity.setSalaryMin(min * 12 * 22 / 1000);
                    jobInfoEntity.setSalaryMax(max * 12 * 22 / 1000);
                    avg = (max + min) * 12 * 22 / 2000;
                    jobInfoEntity.setSalaryAvg(avg);
                }
            }
        }

        jobExperience = jobInfoEntity.getJobExperience();
        if (StrUtil.isNotEmpty(jobExperience)) {
            if (jobExperience.contains(CdConstants.MIDDLE_MINUS)) {
                String[] arr = jobExperience.split(CdConstants.MIDDLE_MINUS);
                jobInfoEntity.setJobExperienceMax(Integer.parseInt(arr[1].replace("年", "")));
            }

            if (jobExperience.contains("年以上")) {
                jobInfoEntity.setJobExperienceMax(Integer.parseInt(jobExperience.replace("年以上", "")));
            }
        }
        brandScaleName = jobInfoEntity.getBrandScaleName();
        if (StrUtil.isNotEmpty(brandScaleName)) {
            if (brandScaleName.contains(CdConstants.MIDDLE_MINUS)) {
                String[] arr = brandScaleName.split(CdConstants.MIDDLE_MINUS);
                jobInfoEntity.setBrandScale(Integer.parseInt(arr[1].replace("人", "")));
            }
        }
    }

}
