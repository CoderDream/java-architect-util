package com.coderdream.freeapps;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.freeapps.dto.AppQueryPageDTO;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.CrawlerHistory;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.CrawlerHistoryService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.service.PriceHistoryService;
import com.coderdream.freeapps.util.JSoupUtil;
import com.coderdream.freeapps.vo.AppVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
public class RestTemplateServiceTest {

    @Resource
    private RestTemplate restTemplate;
    @Autowired
    private AppService appService; //这里可能爆红，但是运行没问题

    @Autowired
    private FreeHistoryService freeHistoryService; //这里可能爆红，但是运行没问题

    @Resource
    private CrawlerHistoryService crawlerHistoryService; //这里可能爆红，但是运行没问题

    @Resource
    private PriceHistoryService priceHistoryService; //这里可能爆红，但是运行没问题

    //    @Test
//    public void testGetCount() {
//        //查询总记录数
//        //SELECT COUNT( * ) FROM user
//        long count = userService.count();
//        System.out.println("总记录数：" + count);
//    }
//
    @Test
    public void testInsert() {
        String url = "https://mergeek.com/free/apps?last_id=Q8LDRAkqB7mp40P3";

        url = "https://mergeek.com/free/apps?last_id=NJk4jmDaO2n01b5r";
        List<String> idList = new ArrayList<>();
        extracted(url, idList);

        System.out.println(idList.size());
        System.out.println("users");
    }

    private void extracted(String url, List<String> idList) {
        String id;
        Map<String, LinkedHashMap> result;
        Map<String, ArrayList> data;
        List<LinkedHashMap> apps;
        String link;

        String lastId;

        result = restTemplate.getForObject(url, Map.class);
        data = result.get("data");
        if (data != null) {
            apps = data.get("apps");
            if (!CollectionUtils.isEmpty(apps)) {

//                Set<Map.Entry<Integer, String>> set = apps.entrySet();
//                Iterator<Map.Entry<Integer, String>> iterator = set.iterator();
//                while (iterator.hasNext()) {
//                    Map.Entry<Integer, String> entry = iterator.next();
//                    System.out.println(entry.getKey() + "=" + entry.getValue());
//                }

                int size = apps.size();


                for (int i = 0; i < size; i++) {
                    LinkedHashMap<String, String> map = apps.get(i);
                    link = map.get("link");
                    if (StrUtil.isNotEmpty(link)) {
                        int indexId = link.lastIndexOf("id=");
                        id = link.substring(indexId+3);
                        System.out.println(id);
                        idList.add(id);
                        System.out.println(link);
                        url = "https://mergeek.com/free/apps?last_id=" + id;
//                        extracted(url, idList);

                        if (i == size - 1) {
                            result = restTemplate.getForObject(url, Map.class);
                            data = result.get("data");
                            if (data != null) {
                                apps = data.get("apps");
                                if (!CollectionUtils.isEmpty(apps)) {
                                    for (LinkedHashMap<String, String> map2 : apps) {
                                        link = map2.get("link");
                                        if (StrUtil.isNotEmpty(link)) {
                                            indexId = link.lastIndexOf("id=");
                                            id = link.substring(indexId+3);
                                            System.out.println(id);
                                            idList.add(id);
                                            System.out.println(link);
                                            url = "https://mergeek.com/free/apps?last_id=" + id;
//                                            extracted(url, idList);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}