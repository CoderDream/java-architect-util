package com.coderdream.easyexcelpractise.demo.read;

import com.alibaba.excel.EasyExcel;
import com.coderdream.easyexcelpractise.Result;
import com.coderdream.easyexcelpractise.TestFileUtil;
import com.coderdream.easyexcelpractise.client.ForecastDataServiceClient;
import com.coderdream.easyexcelpractise.data.os.ForecastData;
import com.coderdream.easyexcelpractise.entity.ForecastDataEntity;
import com.coderdream.easyexcelpractise.listener.os.BStringDataListener;
import com.coderdream.easyexcelpractise.listener.os.ForecastDataListener;
import com.coderdream.easyexcelpractise.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 预报数据
 *
 * @author Jiaju Zhuang
 */
//@Ignore
//@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ForecastDataServiceTest {

    @Resource
    private ForecastDataServiceClient forecastDataServiceClient;


    @Test
    public void cellDataRead_01() {
//        String fileName = TestFileUtil.getPath() + "record" + File.separator  + "ts" + File.separator+ "闸门基础属性条目及值.xlsx";
//        process(fileName);
    }

    private void process(String fileName) {
        List<ForecastData> result = EasyExcel.read(fileName,
                ForecastData.class, new ForecastDataListener()).sheet().doReadSync();
        List<ForecastDataEntity> entities = transferToEntity(result);

        List<ForecastDataEntity> forecastDataEntities = new ArrayList<>();
        ForecastDataEntity forecastDataEntity;
        for (ForecastDataEntity entity : entities) {
            forecastDataEntity = new ForecastDataEntity();
            BeanUtils.copyProperties(entity, forecastDataEntity);
            forecastDataEntities.add(forecastDataEntity);
        }
        Result<Integer> forecastDataSize = forecastDataServiceClient.insertOrUpdateBatch(forecastDataEntities);

        System.out.println("forecastDataSize: " + forecastDataSize);
    }

    @Test
    public void batchProcess_01() {
        String path = TestFileUtil.getPath() + "record" + File.separator + "ts" + File.separator;
        List<String> names = FileUtils.fileTraversalNotRecursion(path);
        System.out.println(names.size());
        for (String fileName : names) {
            process(fileName);
        }
    }

    @Test
    public void batchProcess_02() {
        String path = TestFileUtil.getPath() + "record" + File.separator + "1027" + File.separator;
        List<String> names = FileUtils.fileTraversalNotRecursion(path);
        System.out.println(names.size());
        for (String fileName : names) {
            process(fileName);
        }
    }

    List<ForecastDataEntity> transferToEntity(List<ForecastData> result) {
        List<ForecastDataEntity> list = new ArrayList<>();
        ForecastDataEntity entity;
        for (ForecastData data : result) {
            entity = new ForecastDataEntity();
            if (data.getRegId() != null) {
                entity.setRegId(data.getRegId().getData());
            }
            if (data.getForecastTime() != null) {
                entity.setForecastTime(data.getForecastTime().getData());
            }
            if (data.getFutureTime() != null) {
                entity.setFutureTime(data.getFutureTime().getData());
            }
            if (data.getForecastValue() != null) {
                entity.setForecastValue(data.getForecastValue().getData());
            }
            list.add(entity);
        }

        return list;
    }

}