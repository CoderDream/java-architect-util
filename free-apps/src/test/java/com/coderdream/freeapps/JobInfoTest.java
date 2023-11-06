package com.coderdream.freeapps;

import cn.hutool.core.collection.CollectionUtil;
import com.coderdream.freeapps.enums.DistrictFlagEnum;
import com.coderdream.freeapps.model.BossJobLogEntity;
import com.coderdream.freeapps.model.JobInfoEntity;
import com.coderdream.freeapps.service.BossJobLogService;
import com.coderdream.freeapps.service.JobInfoService;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.callapi.CallApiUtil;
import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class JobInfoTest {

    @Resource
    private JobInfoService jobInfoService;

    @Resource
    private BossJobLogService bossJobLogService;

    @Test
    public void testUpdateColumns() {
        log.info("updateColumns result: " + jobInfoService.updateColumns());
    }

    @Test
    public void testGetCookie() {
        readCookie();
    }

    private String readCookie() {
        List<String> stringList = CdFileUtils.readFileContent(
            CdFileUtils.getResourceRealPath() + File.separatorChar + "cookie.txt");
        if (CollectionUtil.isNotEmpty(stringList)) {
            String cookie = stringList.get(0);
            log.error("updateColumns result: " + cookie);
            return cookie;
        }

        return "";
    }

    private String readCookie(DistrictFlagEnum districtFlagEnum) {
        String cookie = "";
        cookie += "lastCity=101200100; ";
        cookie += "wd_guid=95a92633-74e8-477e-bba4-956a27994955; ";
        cookie += "historyState=state; ";
        cookie += "_bl_uid=77lz5fjt8pUaXy5j5p1LlF3ptbt2; ";
        cookie += "wt2=DwMimGfLSGmiOdxoeUkaMJuHCFhpwvyiogspq1bwwtlQbOZjYGnS8hVF5mfvcSJKiAgQjlv5aOvqZS4RIOl-UbA~~; ";
        cookie += "wbg=0; ";
        cookie += "__g=-; ";
        cookie += "Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1686053687,1686122412,1686186963,1686272336; ";
        switch (districtFlagEnum) {
            case WU_HAN:
                cookie += "__l=l=%2Fwww.zhipin.com%2Fweb%2Fgeek%2Fjob%3Fquery%3DJava%26city%3D101200100%26areaBusiness%3D420106%253A2225_2568_157_1243_2859_34575429_466_83979040_2617%26page%3D1&r=&g=&s=3&friend_source=0&s=3&friend_source=0; ";
                break;
            case HONG_SHAN:
                cookie += "__l=l=%2Fwww.zhipin.com%2Fweb%2Fgeek%2Fjob%3Fquery%3DJava%26city%3D101200100%26areaBusiness%3D420106%253A2225_2568_157_1243_2859_34575429_466_83979040_2617%26page%3D1&r=&g=&s=3&friend_source=0&s=3&friend_source=0; ";
                break;
            case NAN_HU:
                cookie += "__l=l=%2Fwww.zhipin.com%2Fweb%2Fgeek%2Fjob%3Fquery%3DJava%26city%3D101200100%26areaBusiness%3D420106%253A2225_2568_157_1243_2859_34575429_466_83979040_2617%26page%3D1&r=&g=&s=3&friend_source=0&s=3&friend_source=0; ";
                break;
            case GUANG_GU:
                cookie += "__l=l=%2Fwww.zhipin.com%2Fweb%2Fgeek%2Fjob%3Fquery%3DJava%26city%3D101200100%26areaBusiness%3D420106&r=&g=&s=3&friend_source=0&s=3&friend_source=0; ";
                break;
        }

        cookie += "Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1686276219; ";
        cookie += "__c=1686272092; ";
        cookie += "__a=99514548.1678799963.1686186888.1686272092.240.12.27.240; ";

        List<String> stringList = CdFileUtils.readFileContent(
            CdFileUtils.getResourceRealPath() + File.separatorChar + "cookie.txt");
        if (CollectionUtil.isNotEmpty(stringList)) {
            cookie += stringList.get(0);
            log.error("updateColumns result: " + cookie);
            return cookie;
        }

        return "";
    }

    @Test
    public void testJavaWuhan() {
        String referer = "https://www.zhipin.com/web/geek/job?query=Java&city=101200100&page=";
        String url = "https://www.zhipin.com/wapi/zpgeek/search/joblist.json?scene=1&query=Java&city=101200100&experience=&payType=&partTime=&degree=&industry=&scale=&stage=&position=&jobType=&salary=&multiBusinessDistrict=&multiSubway="; // &page=" + currentPage;
        Integer currentPage = 9;
        String cookie = readCookie(DistrictFlagEnum.WU_HAN);
        List<Object> result = CallApiUtil.getJobInfoList(referer, url, currentPage, cookie,
            DistrictFlagEnum.WU_HAN.getLabel());
        List<JobInfoEntity> jobInfoEntityList = (List<JobInfoEntity>) result.get(0);
        List<BossJobLogEntity> bossJobLogEntityList = (List<BossJobLogEntity>) result.get(1);
        log.info("jobInfoEntityList result: " + jobInfoService.insertOrUpdateBatch(jobInfoEntityList));
        log.info("bossJobLogEntityList result: " + bossJobLogService.insertOrUpdateBatch(bossJobLogEntityList));
    }

    @Test
    public void testJavaWucang() {
        String referer = "https://www.zhipin.com/web/geek/job?query=Java&city=101200100&areaBusiness=420106&page=";
        String url = "https://www.zhipin.com/wapi/zpgeek/search/joblist.json?scene=1&query=Java&city=101200100&experience=&payType=&partTime=&degree=&industry=&scale=&stage=&position=&jobType=&salary=&multiBusinessDistrict=420106&multiSubway=";
        Integer currentPage = 9;
        String cookie = readCookie();
        List<Object> result = CallApiUtil.getJobInfoList(referer, url, currentPage, cookie,
            DistrictFlagEnum.WU_CANG.getLabel());
        List<JobInfoEntity> jobInfoEntityList = (List<JobInfoEntity>) result.get(0);
        List<BossJobLogEntity> bossJobLogEntityList = (List<BossJobLogEntity>) result.get(1);
        log.info("jobInfoEntityList result: " + jobInfoService.insertOrUpdateBatch(jobInfoEntityList));
        log.info("bossJobLogEntityList result: " + bossJobLogService.insertOrUpdateBatch(bossJobLogEntityList));
    }

    @Test
    public void testJavaNanhu() {
        String referer = "https://www.zhipin.com/web/geek/job?query=Java&city=101200100&areaBusiness=420106%3A2225_2568_157_1243_2859_34575429_466_83979040_2617&page=";
        String url = "https://www.zhipin.com/wapi/zpgeek/search/joblist.json?scene=1&query=Java&city=101200100&experience=&payType=&partTime=&degree=&industry=&scale=&stage=&position=&jobType=&salary=&multiBusinessDistrict=420106:2225_2568_157_1243_2859_34575429_466_83979040_2617&multiSubway=";
        Integer currentPage = 1;
        String cookie = readCookie(DistrictFlagEnum.NAN_HU);
        List<Object> result = CallApiUtil.getJobInfoList(referer, url, currentPage, cookie,
            DistrictFlagEnum.NAN_HU.getLabel());
        List<JobInfoEntity> jobInfoEntityList = (List<JobInfoEntity>) result.get(0);
        List<BossJobLogEntity> bossJobLogEntityList = (List<BossJobLogEntity>) result.get(1);
        log.info("jobInfoEntityList result: " + jobInfoService.insertOrUpdateBatch(jobInfoEntityList));
        log.info("bossJobLogEntityList result: " + bossJobLogService.insertOrUpdateBatch(bossJobLogEntityList));
    }

    @Test
    public void testJavaHongshan() {
        String referer = "https://www.zhipin.com/web/geek/job?query=Java&city=101200100&areaBusiness=420111&page=";
        String url = "https://www.zhipin.com/wapi/zpgeek/search/joblist.json?scene=1&query=Java&city=101200100&experience=&payType=&partTime=&degree=&industry=&scale=&stage=&position=&jobType=&salary=&multiBusinessDistrict=420111&multiSubway=";
        Integer currentPage = 9;
        String cookie = "lastCity=101200100; wd_guid=95a92633-74e8-477e-bba4-956a27994955; historyState=state; _bl_uid=77lz5fjt8pUaXy5j5p1LlF3ptbt2; wt2=DwMimGfLSGmiOdxoeUkaMJuHCFhpwvyiogspq1bwwtlQbOZjYGnS8hVF5mfvcSJKiAgQjlv5aOvqZS4RIOl-UbA~~; wbg=0; __g=-; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1686013187,1686053687,1686122412,1686186963; __l=l=%2Fwww.zhipin.com%2Fweb%2Fgeek%2Fjob%3Fquery%3DJava%26city%3D101200100%26areaBusiness%3D420111%26page%3D1&r=&g=&s=3&friend_source=0&s=3&friend_source=0; geek_zp_token=V1Q9ogE-P601dgXdNhyRwRKSOx6Dnfww~~; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1686190274; __c=1686186888; __a=99514548.1678799963.1686122415.1686186888.209.11.65.209; __zp_stoken__=3da3efAwdNhNTEyMzNEhsRHU9c3UBOzAaSEYsezE2bW43JDk0WzREKE9TfSk%2FKTldWn9fYH4mJApzfUoAMQk1fDt8V0AZQhJpOwgnTxA7N0VjGCNaZw5AThYNRScCAhEuGEZtGwwGUANtTTk%3D";

        List<Object> result = CallApiUtil.getJobInfoList(referer, url, currentPage, cookie,
            DistrictFlagEnum.HONG_SHAN.getLabel());
        List<JobInfoEntity> jobInfoEntityList = (List<JobInfoEntity>) result.get(0);
        List<BossJobLogEntity> bossJobLogEntityList = (List<BossJobLogEntity>) result.get(1);
        log.info("jobInfoEntityList result: " + jobInfoService.insertOrUpdateBatch(jobInfoEntityList));
        log.info("bossJobLogEntityList result: " + bossJobLogService.insertOrUpdateBatch(bossJobLogEntityList));
    }

    @Test
    public void testJavaGuanggu() {
        String referer = "https://www.zhipin.com/web/geek/job?query=Java&city=101200100&areaBusiness=420111%3A93174606";
        String url = "https://www.zhipin.com/wapi/zpgeek/search/joblist.json?scene=1&query=Java&city=101200100&experience=&payType=&partTime=&degree=&industry=&scale=&stage=&position=&jobType=&salary=&multiBusinessDistrict=420111:93174606&multiSubway=";
        Integer currentPage = 9;
        String cookie = readCookie(DistrictFlagEnum.GUANG_GU);

        List<Object> result = CallApiUtil.getJobInfoList(referer, url, currentPage, cookie,
            DistrictFlagEnum.GUANG_GU.getLabel());
        List<JobInfoEntity> jobInfoEntityList = (List<JobInfoEntity>) result.get(0);
        List<BossJobLogEntity> bossJobLogEntityList = (List<BossJobLogEntity>) result.get(1);
        log.info("jobInfoEntityList result: " + jobInfoService.insertOrUpdateBatch(jobInfoEntityList));
        log.info("bossJobLogEntityList result: " + bossJobLogService.insertOrUpdateBatch(bossJobLogEntityList));
    }

    @Test
    public void testJavaGuanggu2() {
        String referer = "https://www.zhipin.com/web/geek/job?query=Java&city=101200100&areaBusiness=420111%3A93174606_418_34582864_908_1182_933_371_34575013";
        String url = "https://www.zhipin.com/wapi/zpgeek/search/joblist.json?scene=1&query=Java&city=101200100&experience=&payType=&partTime=&degree=&industry=&scale=&stage=&position=&jobType=&salary=&multiBusinessDistrict=420111:93174606_418_34582864_908_1182_933_371_34575013&multiSubway=";
        Integer currentPage = 9;
        String cookie = "lastCity=101200100; wt2=DwMimGfLSGmiOdxoeUkaMJuHCFhpwvyiogspq1bwwtlQbOZjYGnS8hVF5mfvcSJKiAgQjlv5aOvqZS4RIOl-UbA~~; wbg=0; __g=-; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1686013187,1686053687,1686122412,1686186963; __l=l=%2Fwww.zhipin.com%2Fweb%2Fgeek%2Fjob%3Fquery%3DJava%26city%3D101200100%26areaBusiness%3D420111%26page%3D1&r=&g=&s=3&friend_source=0&s=3&friend_source=0; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1686191498; __zp_stoken__=3da3efAwdNhNTRTs5KH5sRHU9c3UBai07dWAsezE2bWQPM0gmSzREKE9TY0REcW8wWn9fYH4mJCAvCEoAJxFoNCNhCnRBJTxBHR0FTxA7N0VjGE4MP3UtUBYNRScCEhEuGEZtGwwGUANtTTk%3D";

        List<Object> result = CallApiUtil.getJobInfoList(referer, url, currentPage, cookie,
            DistrictFlagEnum.GUANG_GU2.getLabel());
        List<JobInfoEntity> jobInfoEntityList = (List<JobInfoEntity>) result.get(0);
        List<BossJobLogEntity> bossJobLogEntityList = (List<BossJobLogEntity>) result.get(1);
        log.info("jobInfoEntityList result: " + jobInfoService.insertOrUpdateBatch(jobInfoEntityList));
        log.info("bossJobLogEntityList result: " + bossJobLogService.insertOrUpdateBatch(bossJobLogEntityList));
    }
}
