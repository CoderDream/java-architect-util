package com.coderdream.easyexcelpractise.demo.read;

import com.alibaba.excel.EasyExcel;
import com.coderdream.easyexcelpractise.entity.IdMapEntity;
import com.coderdream.easyexcelpractise.entity.SampleEntity;
import com.coderdream.easyexcelpractise.mapper.IdMapMapper;
import com.coderdream.easyexcelpractise.mapper.SampleMapper;
import com.coderdream.easyexcelpractise.TestFileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 读的常见写法
 *
 * @author Jiaju Zhuang
 */
//@Ignore
//@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SpringBootReadTest {

    @Resource
    private SampleMapper sampleMapper;

    @Autowired
    private IdMapMapper idMapMapper;

    @Test
    public void cellDataRead_03() {
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "葛洲坝大江8F机组有功.xlsx";
        processFile(fileName);
    }

    private void processFile(String fileName) {
        List<SampleCellDataReadDemoData> result = EasyExcel.read(fileName, SampleCellDataReadDemoData.class,
                new SampleCellDataDemoHeadDataListener()).sheet().doReadSync();

        String shortFileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.lastIndexOf("."));
        System.out.println(shortFileName);
        List<SampleEntity> list = transferToEntityList(result);
        Integer pageNo = 1;
        Integer pageSize = 1000;
        int size = list.size();
        if (size > 0) {
            SampleEntity sampleEntity = list.get(0);
            String sendId = sampleEntity.getSendId();
            IdMapEntity idMapEntity = new IdMapEntity();
            idMapEntity.setAttrItemName(shortFileName);
            idMapEntity.setSendId(sendId);
            idMapMapper.insert(idMapEntity);
        }
        System.out.println(size);
        while (size > pageNo * pageSize) {
            sampleMapper.insertBatch(queryPageList(list, pageNo, pageSize));
            pageNo++;
        }

        // 最后一页
        sampleMapper.insertBatch(queryPageList(list, pageNo, pageSize));
    }

    public List<SampleEntity> queryPageList(List<SampleEntity> list, Integer pageNo,
                                            Integer pageSize) {
        //1.计算出需要跳过多少条数据（流切片的起始位置）
        int startPosition = (pageNo - 1) * pageSize;
        Stream<SampleEntity> stream = list.stream().skip(startPosition).limit(pageSize);
        List<SampleEntity> resultAccountList = stream.collect(Collectors.toList());
        return resultAccountList;
    }

    List<SampleEntity> transferToEntityList(List<SampleCellDataReadDemoData> result) {
        List<SampleEntity> list = new ArrayList<>();
        SampleEntity sampleEntity;
        if (!CollectionUtils.isEmpty(result)) {
            for (SampleCellDataReadDemoData sampleCellDataReadDemoData : result) {
                sampleEntity = new SampleEntity();
                String sendId = sampleCellDataReadDemoData.getSenid().getData();
                sampleEntity.setSendId(sendId);
                Date dataTime = sampleCellDataReadDemoData.getTime().getData();
                sampleEntity.setDataTime(dataTime);
                BigDecimal value = sampleCellDataReadDemoData.getV().getNumberValue();
                sampleEntity.setValue(value);
                BigDecimal avgValue = sampleCellDataReadDemoData.getAvgs().getNumberValue();
                sampleEntity.setAvgValue(avgValue);
                BigDecimal maxValue = sampleCellDataReadDemoData.getMaxs().getNumberValue();
                sampleEntity.setMaxValue(maxValue);
                Date maxValueTime = sampleCellDataReadDemoData.getMaxt().getData();
                sampleEntity.setMaxValueTime(maxValueTime);
                BigDecimal minValue = sampleCellDataReadDemoData.getMins().getNumberValue();
                sampleEntity.setMinValue(minValue);
                Date minValueTime = sampleCellDataReadDemoData.getMint().getData();
                sampleEntity.setMinValueTime(minValueTime);
                BigDecimal s = sampleCellDataReadDemoData.getS().getNumberValue();
                sampleEntity.setS(s);
                BigDecimal avgs = sampleCellDataReadDemoData.getAvgs().getNumberValue();
                sampleEntity.setAvgs(avgs);
                BigDecimal maxs = sampleCellDataReadDemoData.getMaxs().getNumberValue();
                sampleEntity.setMaxs(maxs);
                if (sampleCellDataReadDemoData.getMins() != null) {
                    BigDecimal mins = sampleCellDataReadDemoData.getMins().getNumberValue();
                    sampleEntity.setMins(mins);
                }
                if (sampleCellDataReadDemoData.getSpan() != null) {
                    BigDecimal span = sampleCellDataReadDemoData.getSpan().getNumberValue();
                    sampleEntity.setSpan(span);
                }
                if (sampleCellDataReadDemoData.getDq() != null) {
                    BigDecimal dq = sampleCellDataReadDemoData.getDq().getNumberValue();
                    sampleEntity.setDq(dq);
                }

                list.add(sampleEntity);
            }
        }

        return list;
    }


    @Test
    public void cellDataRead_04() {
        String path = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\模型标准化_数据整理\\样本数据";        //要遍历的路径
//        File file = new File(path);        //获取其file对象
//        File[] fs = file.listFiles();    //遍历path下的文件和目录，放在File数组中
//        for (File f : fs) {                    //遍历File[]数组
//            if (!f.isDirectory())        //若非目录(即文件)，则打印
//                System.out.println(f);
//        }

        List<String> names = fileTraversalNotRecursion(path);
        System.out.println(names.size());
        for (String fileName : names) {
            System.out.println(fileName);
            processFile(fileName);
        }
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