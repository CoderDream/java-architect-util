package com.coderdream.easyexcelpractise.demo.read;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coderdream.easyexcelpractise.TestFileUtil;
import com.coderdream.easyexcelpractise.data.CurveData;
import com.keepsoft.microservice.dto.CurveDto;
import com.coderdream.easyexcelpractise.entity.CurveEntity;
import com.coderdream.easyexcelpractise.listener.CurveDataListener;
import com.coderdream.easyexcelpractise.mapper.CurveMapper;
import com.coderdream.easyexcelpractise.mapper.IdMapMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class SpringBootReadCurveTest {

    @Resource
    private CurveMapper curveMapper;

    @Autowired
    private IdMapMapper idMapMapper;

    @Test
    public void cellDataRead_03() {

//        "01 0002 B 04 01 01" "三峡净库容曲线";
//        "01 0002 B 04 A2 01" "三峡1#机组alstom";
//        Map<String, String> attrItemFullCodeMap = new LinkedHashMap<>();
//        attrItemFullCodeMap.put("三峡净库容曲线", "01 0002 B 04 01 01");
//        attrItemFullCodeMap.put("三峡1#机组alstom", "01 0002 B 04 A2 01");

//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "三峡净库容曲线.csv";
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "三峡净库容曲线.xlsx";
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "受电区-综合-上海 - 三合一.xlsx";

        String fileName = TestFileUtil.getPath() + "demo" + File.separator  + "xingzhengqu" + File.separator+ "受电区-综合-上海 - 三合一.xlsx";

//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "三峡1#机组alstom.csv";
        //       String fileName = TestFileUtil.getPath() + "demo" + File.separator + "白鹤滩发电流量.xlsx";
        processFile(fileName);
    }

    private void processFile(String fileName) {
        List<CurveData> result = EasyExcel.read(fileName, CurveData.class,
                new CurveDataListener()).sheet().doReadSync();

        String shortFileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.lastIndexOf("."));
        System.out.println(shortFileName);
        CurveEntity curveEntity = transferToEntity(result);

        // 最后一页 Stream.of("1", "2", "3").collect(Collectors.toList()); curveEntity
//        curveEntity.setAttrItemFullCode("01 0002 B 04 A2 01");
//        curveMapper.insertBatch(Stream.of(curveEntity).collect(Collectors.toList()));

        curveMapper.insert(curveEntity);
    }

    public List<CurveEntity> queryPageList(List<CurveEntity> list, Integer pageNo,
                                           Integer pageSize) {
        //1.计算出需要跳过多少条数据（流切片的起始位置）
        int startPosition = (pageNo - 1) * pageSize;
        Stream<CurveEntity> stream = list.stream().skip(startPosition).limit(pageSize);
        List<CurveEntity> resultAccountList = stream.collect(Collectors.toList());
        return resultAccountList;
    }

    CurveEntity transferToEntity(List<CurveData> result) {

        List<JSONObject> jsonObjectList = new ArrayList<>();
        List<CurveDto> curveDtoList = new ArrayList<>();
        CurveDto curveDtoTemp;
        for (CurveData curveData : result) {
            curveDtoTemp = new CurveDto();
            if (curveData.getCurveid() != null) {
                curveDtoTemp.setCurveid(curveData.getCurveid().getData());
            }
            if (curveData.getV0() != null) {
                curveDtoTemp.setV0(curveData.getV0().getData());
            } else {
                curveDtoTemp.setV0(0.0);
            }
            if (curveData.getV1() != null) {
                curveDtoTemp.setV1(curveData.getV1().getData());
            } else {
                curveDtoTemp.setV1(0.0);
            }
            if (curveData.getV2() != null) {
                curveDtoTemp.setV2(curveData.getV2().getData());
            } else {
                curveDtoTemp.setV2(0.0);
            }
            if (curveData.getV3() != null) {
                curveDtoTemp.setV3(curveData.getV3().getData());
            } else {

                curveDtoTemp.setV3(0.0);
            }
            if (curveData.getVersion() != null) {
                curveDtoTemp.setVersion(curveData.getVersion().getData());
            } else {
                curveDtoTemp.setVersion("");
            }
            curveDtoList.add(curveDtoTemp);
            jsonObjectList.add(JSONObject.parseObject(JSONObject.toJSONString(curveDtoTemp)));
        }

        System.out.println("######################");
//        String value = JSONObject.toJSONString(curveDtoList);
//        System.out.println(value);
        CurveEntity curveEntity = new CurveEntity();
        JSONArray jsonArray = new JSONArray(jsonObjectList);
        curveEntity.setValue(jsonArray);
        System.out.println(jsonArray.toJSONString());

        return curveEntity;
    }


    @Test
    public void cellDataRead_04() {
        String path = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\模型标准化_数据整理\\样本数据";        //要遍历的路径

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
