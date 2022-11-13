package com.coderdream.easyexcelpractise.demo.read;

import com.alibaba.excel.EasyExcel;
import com.coderdream.easyexcelpractise.TestFileUtil;
import com.coderdream.easyexcelpractise.data.os.ObjectBStringData;
import com.coderdream.easyexcelpractise.entity.ObjectAttrItemIndexEntity;
import com.coderdream.easyexcelpractise.entity.ObjectBStringEntity;
import com.coderdream.easyexcelpractise.listener.os.BStringDataListener;
import com.coderdream.easyexcelpractise.mapper.BStringMapper;
import com.coderdream.easyexcelpractise.mapper.CurveMapper;
import com.coderdream.easyexcelpractise.mapper.IdMapMapper;
import com.coderdream.easyexcelpractise.mapper.ObjectAttrItemIndexMapper;
import com.coderdream.easyexcelpractise.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.File;
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
public class ObjectBStringTest {

    @Resource
    private BStringMapper bStringMapper;

    @Resource
    private ObjectAttrItemIndexMapper objectAttrItemIndexMapper;


    @Test
    public void cellDataRead_01() {
        String fileName = TestFileUtil.getPath() + "basic" + File.separator  + "05" + File.separator+ "闸门基础属性条目及值.xlsx";
        process(fileName);
    }

    private void process(String fileName) {
        List<ObjectBStringData> result = EasyExcel.read(fileName,
                ObjectBStringData.class, new BStringDataListener()).sheet().doReadSync();
        List<ObjectBStringEntity> objectBStringEntities = transferToEntity(result);

        List<ObjectAttrItemIndexEntity> objectAttrItemIndexEntities = new ArrayList<>();
        ObjectAttrItemIndexEntity objectAttrItemIndexEntity;
        for (ObjectBStringEntity entity : objectBStringEntities) {
            objectAttrItemIndexEntity = new ObjectAttrItemIndexEntity();
            String attrItemFullCode = entity.getAttrItemFullCode();
            String[] codeArray = attrItemFullCode.split(" ");
            String objectFullCode = codeArray[0] + " " + codeArray[1] + " 0 00 00 00";
            objectAttrItemIndexEntity.setObjectFullCode(objectFullCode);
            objectAttrItemIndexEntity.setAttrItemFullCode(attrItemFullCode);
            objectAttrItemIndexEntity.setDataType("string");
            objectAttrItemIndexEntities.add(objectAttrItemIndexEntity);
        }
        int objectAttrItemIndexSize = objectAttrItemIndexMapper.insertOrUpdateBatch(objectAttrItemIndexEntities);
        int objectBStringSize = bStringMapper.insertOrUpdateBatch(objectBStringEntities);

        System.out.println("objectAttrItemIndexSize: " + objectAttrItemIndexSize + "; objectBStringSize: " + objectBStringSize);
    }

    @Test
    public void batchProcess_01() {
        String path = TestFileUtil.getPath() + "basic" + File.separator + "04" + File.separator;
        List<String> names = FileUtils.fileTraversalNotRecursion(path);
        System.out.println(names.size());
        for (String fileName : names) {
            process(fileName);
        }
    }

    List<ObjectBStringEntity> transferToEntity(List<ObjectBStringData> result) {

        List<ObjectBStringEntity> list = new ArrayList<>();
        ObjectBStringEntity entity;
        for (ObjectBStringData data : result) {
            entity = new ObjectBStringEntity();
            if (data.getAttrItemFullCode() != null) {
                entity.setAttrItemFullCode(data.getAttrItemFullCode().getData());
            }
            if (data.getValue() != null) {
                entity.setValue(data.getValue().getData());
            }

            list.add(entity);
        }

        return list;
    }

}