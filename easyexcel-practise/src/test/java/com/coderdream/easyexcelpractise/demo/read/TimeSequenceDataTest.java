package com.coderdream.easyexcelpractise.demo.read;

import com.alibaba.excel.EasyExcel;
import com.coderdream.easyexcelpractise.TestFileUtil;
import com.coderdream.easyexcelpractise.client.TimeSequenceDataServiceClient;
import com.coderdream.easyexcelpractise.data.SampleCellDataReadDemoData;
import com.coderdream.easyexcelpractise.entity.TimeSequenceDataEntity;
import com.coderdream.easyexcelpractise.listener.SampleCellDataDemoHeadDataListener;
import com.coderdream.easyexcelpractise.mapper.CurveMapper;
import com.coderdream.easyexcelpractise.mapper.IdMapMapper;
import com.coderdream.easyexcelpractise.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 读的常见写法 读特征曲线 二维曲线
 *
 * @author Jiaju Zhuang
 */
//@Ignore
//@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TimeSequenceDataTest {

    @Resource
    private CurveMapper curveMapper;

    @Autowired
    private IdMapMapper idMapMapper;

    @Resource
    private TimeSequenceDataServiceClient timeSequenceDataServiceClient;

    @Test
    public void cellDataRead_01() {
        String fileName = TestFileUtil.getPath() + "demo" + File.separator  + "yc" + File.separator+ "三峡左岸3F机组压差100.xlsx";
        List<SampleCellDataReadDemoData> result = EasyExcel.read(fileName,
                SampleCellDataReadDemoData.class, new SampleCellDataDemoHeadDataListener()).sheet().doReadSync();
        List<TimeSequenceDataEntity> list = transferToEntity(result);
        timeSequenceDataServiceClient.insertBatch(list);
    }

    @Test
    public void batchProcess_01() {
        String path = TestFileUtil.getPath() + "demo" + File.separator + "yc" + File.separator;
        List<String> names = FileUtils.fileTraversalNotRecursion(path);
        System.out.println(names.size());
        for (String fileName : names) {
            List<SampleCellDataReadDemoData> result = EasyExcel.read(fileName,
                    SampleCellDataReadDemoData.class, new SampleCellDataDemoHeadDataListener()).sheet().doReadSync();
            List<TimeSequenceDataEntity> list = transferToEntity(result);
            timeSequenceDataServiceClient.insertBatch(list);
        }
    }

    @Test
    public void batchProcess_02() {
        String path = TestFileUtil.getPath() + "demo" + File.separator + "ylz" + File.separator;
        List<String> names = FileUtils.fileTraversalNotRecursion(path);
        System.out.println(names.size());
        for (String fileName : names) {
            List<SampleCellDataReadDemoData> result = EasyExcel.read(fileName,
                    SampleCellDataReadDemoData.class, new SampleCellDataDemoHeadDataListener()).sheet().doReadSync();
            List<TimeSequenceDataEntity> list = transferToEntity(result);
            timeSequenceDataServiceClient.insertBatch(list);
        }
    }

     /**
     * 水质站
     */
    @Test
    public void batchProcess_03() {
        String path = TestFileUtil.getPath() + "demo" + File.separator + "qxz" + File.separator;
        List<String> names = FileUtils.fileTraversalNotRecursion(path);
        System.out.println(names.size());
        for (String fileName : names) {
            List<SampleCellDataReadDemoData> result = EasyExcel.read(fileName,
                    SampleCellDataReadDemoData.class, new SampleCellDataDemoHeadDataListener()).sheet().doReadSync();
            List<TimeSequenceDataEntity> list = transferToEntity(result);
            timeSequenceDataServiceClient.insertBatch(list);
        }
    }

    List<TimeSequenceDataEntity> transferToEntity(List<SampleCellDataReadDemoData> result) {

        List<TimeSequenceDataEntity> list = new ArrayList<>();
        TimeSequenceDataEntity entity;
        for (SampleCellDataReadDemoData data : result) {
            entity = new TimeSequenceDataEntity();
            if (data.getSenid() != null) {
                entity.setSendId(data.getSenid().getData());
            }
            if (data.getTime() != null) {
                entity.setDataTime(data.getTime().getData());
            }

            if (data.getV() != null) {
                entity.setValue(new BigDecimal(Double.toString(data.getV().getData())) );
            }


            if (data.getAvgv() != null) {
                entity.setAvgValue(new BigDecimal(Double.toString(data.getAvgv().getData())) );
            }

            if (data.getMaxv() != null) {
                entity.setMaxValue(new BigDecimal(Double.toString(data.getMaxv().getData())) );
            }

            if (data.getMaxt() != null) {
                entity.setMaxValueTime(data.getMaxt().getData());
            }

            if (data.getMinv() != null) {
                entity.setMinValue(new BigDecimal(Double.toString(data.getMinv().getData())) );
            }

            if (data.getMint() != null) {
                entity.setMinValueTime(data.getMint().getData());
            }

            if (data.getS() != null) {
                entity.setS(new BigDecimal(Double.toString(data.getS().getData())) );
            }

            if (data.getAvgs() != null) {
                entity.setAvgs(new BigDecimal(Double.toString(data.getAvgs().getData())) );
            }
            if (data.getMaxs() != null) {
                entity.setMaxs(new BigDecimal(Double.toString(data.getMaxs().getData())) );
            }
            if (data.getMins() != null) {
                entity.setMins(new BigDecimal(Double.toString(data.getMins().getData())) );
            }
            if (data.getSpan() != null) {
                entity.setSpan(new BigDecimal(Double.toString(data.getSpan().getData())) );
            }
            if (data.getDq() != null) {
                entity.setDq(new BigDecimal(Double.toString(data.getDq().getData())) );
            }
            list.add(entity);
        }

        return list;
    }


    @Test
    public void cellDataRead_04() {
        String path = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\模型标准化_数据整理\\样本数据";        //要遍历的路径

        List<String> names = fileTraversalNotRecursion(path);
        System.out.println(names.size());
//        for (String fileName : names) {
//            System.out.println(fileName);
//            processFile(fileName);
//        }
    }

    public List<String> fileTraversalNotRecursion(String path) {//非递归
        List<String> names = new ArrayList<>();
        File file = new File(path);
        if (null != file) {
            if (file.exists()) {
                LinkedList<String> list = new LinkedList<>();//利用linkedlist的属性,链表结构

                File[] files = file.listFiles();
                if (null == files || 0 == files.length) {
                    System.out.println("给文件夹下面没有文件");
                } else {
                    for (int i = 0, size = files.length; i < size; i++) {
                        File temp = files[i];
                        if (temp.isFile()) {
                            names.add(temp.toString());
                        } else {
                            list.add(temp.getAbsolutePath());
                        }
                    }

                    //遍历文件夹下面的所有文件
                    while (!list.isEmpty()) {
                        String tempPath = list.removeFirst();
                        File temp = new File(tempPath);
                        File[] tf = temp.listFiles();
                        for (File ff : tf) {
                            if (ff.isFile()) {
                                names.add(ff.toString());
                            } else {
                                list.add(ff.getAbsolutePath());
                            }
                        }
                    }
                }
            }
        }
        return names;
    }
}