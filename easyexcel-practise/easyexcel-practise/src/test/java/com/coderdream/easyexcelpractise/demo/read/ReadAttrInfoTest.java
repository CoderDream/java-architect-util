package com.coderdream.easyexcelpractise.demo.read;

import com.alibaba.excel.EasyExcel;
import com.coderdream.easyexcelpractise.TestFileUtil;
import com.coderdream.easyexcelpractise.data.AttrInfoData;
import com.coderdream.easyexcelpractise.entity.AttrInfoEntity;
import com.coderdream.easyexcelpractise.enums.SheetNameEnum;
import com.coderdream.easyexcelpractise.listener.AttrInfoDataListener;
import com.coderdream.easyexcelpractise.mapper.AttrInfoMapper;
import com.coderdream.easyexcelpractise.mapper.IdMapMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
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
public class ReadAttrInfoTest {

    @Resource
    private AttrInfoMapper attrInfoMapper;

    @Autowired
    private IdMapMapper idMapMapper;

    @Test
    public void cellDataRead_03() {
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "temp" + File.separator + "对象导入摸板_闸门.xlsx";
        SheetNameEnum sheetNameEnum = SheetNameEnum.init("基础属性");
        processFile(fileName, sheetNameEnum.getName());
    }

    private void processFile(String fileName, String sheetName) {
        List<AttrInfoData> result = EasyExcel.read(fileName, AttrInfoData.class,
                new AttrInfoDataListener()).sheet(sheetName).doReadSync();

        String shortFileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.lastIndexOf("."));
        System.out.println(shortFileName);
        List<AttrInfoEntity> list = transferToEntityList(result);



//        Integer pageNo = 1;
//        Integer pageSize = 1000;
//        int size = list.size();
//        if (size > 0) {
//            AttrInfoEntity attrInfoEntity = list.get(0);
//            String sendId = attrInfoEntity.getSendId();
//            IdMapEntity idMapEntity = new IdMapEntity();
//            idMapEntity.setAttrItemName(shortFileName);
//            idMapEntity.setSendId(sendId);
//            idMapMapper.insert(idMapEntity);
//        }
//        System.out.println(size);
//        while (size > pageNo * pageSize) {
//            attrInfoMapper.insertBatch(queryPageList(list, pageNo, pageSize));
//            pageNo++;
//        }
//
//        // 最后一页
//        attrInfoMapper.insertBatch(queryPageList(list, pageNo, pageSize));
    }

    public List<AttrInfoEntity> queryPageList(List<AttrInfoEntity> list, Integer pageNo,
                                            Integer pageSize) {
        //1.计算出需要跳过多少条数据（流切片的起始位置）
        int startPosition = (pageNo - 1) * pageSize;
        Stream<AttrInfoEntity> stream = list.stream().skip(startPosition).limit(pageSize);
        List<AttrInfoEntity> resultAccountList = stream.collect(Collectors.toList());
        return resultAccountList;
    }

    List<AttrInfoEntity> transferToEntityList(List<AttrInfoData> result) {
        List<AttrInfoEntity> list = new ArrayList<>();
        AttrInfoEntity attrInfoEntity;
        if (!CollectionUtils.isEmpty(result)) {
            for (AttrInfoData attrInfoData : result) {
                System.out.println(attrInfoData);
                attrInfoEntity = new AttrInfoEntity();
//                String sendId = attrInfoData.getSenid().getData();
//                attrInfoEntity.setSendId(sendId);
//                Date dataTime = attrInfoData.getTime().getData();
//                attrInfoEntity.setDataTime(dataTime);
//                BigDecimal value = attrInfoData.getV().getNumberValue();
//                attrInfoEntity.setValue(value);
//                BigDecimal avgValue = attrInfoData.getAvgs().getNumberValue();
//                attrInfoEntity.setAvgValue(avgValue);
//                BigDecimal maxValue = attrInfoData.getMaxs().getNumberValue();
//                attrInfoEntity.setMaxValue(maxValue);
//                Date maxValueTime = attrInfoData.getMaxt().getData();
//                attrInfoEntity.setMaxValueTime(maxValueTime);
//                BigDecimal minValue = attrInfoData.getMins().getNumberValue();
//                attrInfoEntity.setMinValue(minValue);
//                Date minValueTime = attrInfoData.getMint().getData();
//                attrInfoEntity.setMinValueTime(minValueTime);
//                BigDecimal s = attrInfoData.getS().getNumberValue();
//                attrInfoEntity.setS(s);
//                BigDecimal avgs = attrInfoData.getAvgs().getNumberValue();
//                attrInfoEntity.setAvgs(avgs);
//                BigDecimal maxs = attrInfoData.getMaxs().getNumberValue();
//                attrInfoEntity.setMaxs(maxs);
//                if (attrInfoData.getMins() != null) {
//                    BigDecimal mins = attrInfoData.getMins().getNumberValue();
//                    attrInfoEntity.setMins(mins);
//                }
//                if (attrInfoData.getSpan() != null) {
//                    BigDecimal span = attrInfoData.getSpan().getNumberValue();
//                    attrInfoEntity.setSpan(span);
//                }
//                if (attrInfoData.getDq() != null) {
//                    BigDecimal dq = attrInfoData.getDq().getNumberValue();
//                    attrInfoEntity.setDq(dq);
//                }

                list.add(attrInfoEntity);
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

//        List<String> names = fileTraversalNotRecursion(path);
//        System.out.println(names.size());
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