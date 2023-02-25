package com.coderdream.easyexcelpractise.demo.read;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.easyexcelpractise.Result;
import com.coderdream.easyexcelpractise.TestFileUtil;
import com.coderdream.easyexcelpractise.client.ObjectTypeMetaServiceClient;
import com.keepsoft.microservice.dto.ObjectTypeMetaReqDto;
import com.keepsoft.microservice.dto.ObjectTypeMetaRespDto;
import com.coderdream.easyexcelpractise.enums.SheetNameEnum;
import com.coderdream.easyexcelpractise.service.MetaImportService;
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
import java.util.List;

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

    @Autowired
    private ObjectTypeMetaServiceClient objectTypeServiceClient;

    @Resource
    private MetaImportService metaImportService;

    @Test
    public void cellDataRead_01() {
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "temp" + File.separator + "对象导入摸板_闸门.xlsx";
        SheetNameEnum sheetNameEnum = SheetNameEnum.init("基础属性");

        String shortFileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.lastIndexOf("."));
        String objectTypeName = shortFileName.substring(shortFileName.lastIndexOf("_") + 1, shortFileName.length());
        String structureTypeCode = sheetNameEnum.getLabel();
        String structureTypeName = sheetNameEnum.getName();
        System.out.println(objectTypeName);
        ObjectTypeMetaReqDto objectTypeMetaReqDto = new ObjectTypeMetaReqDto();
        objectTypeMetaReqDto.setObjectTypeName(objectTypeName);

        String objectTypeCode = getObjectTypeCode(objectTypeMetaReqDto); // 获取对象类型编码

        metaImportService.processFile(fileName, structureTypeName, objectTypeCode);
    }

    private String getObjectTypeCode(ObjectTypeMetaReqDto objectTypeMetaReqDto) {
        String objectTypeCode = "";
        Result<Page<ObjectTypeMetaRespDto>> result = objectTypeServiceClient.list(objectTypeMetaReqDto);
        System.out.println(result.getResult().getRecords().get(0));
        if (result != null && result.getResult() != null && !CollectionUtils.isEmpty(result.getResult().getRecords())) {
            ObjectTypeMetaRespDto objectTypeMetaRespDto = result.getResult().getRecords().get(0);
            objectTypeCode = objectTypeMetaRespDto.getObjectTypeCode(); // 类型编码
        }
        return objectTypeCode;
    }


    @Test
    public void cellDataRead_02() {
//        String path = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\模型标准化_数据整理\\样本数据";        //要遍历的路径
        String path = TestFileUtil.getPath() + "demo" + File.separator + "data" + File.separator;
        List<String> sheetNameList = new ArrayList<>();
        sheetNameList.add("基础属性");
        sheetNameList.add("数据属性");
        sheetNameList.add("对象实例");
        metaImportService.processFileForFolder(path, sheetNameList);
    }

    /**
     * 测试数据 20221018
     */
    @Test
    public void cellDataRead_03() {
//        String path = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\模型标准化_数据整理\\样本数据";        //要遍历的路径
        String path = TestFileUtil.getPath() + "demo" + File.separator + "04" + File.separator;
        List<String> sheetNameList = new ArrayList<>();
//        sheetNameList.add("基础属性");
//        sheetNameList.add("数据属性");
        sheetNameList.add("对象实例");
        metaImportService.processFileForFolder(path, sheetNameList);
    }

    @Test
    public void cellDataRead_04() {
//        String path = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\模型标准化_数据整理\\样本数据";        //要遍历的路径
        String path = TestFileUtil.getPath() + "demo" + File.separator + "szz" + File.separator;
        List<String> sheetNameList = new ArrayList<>();
        sheetNameList.add("基础属性");
        sheetNameList.add("数据属性");
        sheetNameList.add("对象实例");
        metaImportService.processFileForFolder(path, sheetNameList);
    }

    /**
     * 测试数据 20221024
     */
    @Test
    public void cellDataRead_05() {
//        String path = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\模型标准化_数据整理\\样本数据";        //要遍历的路径
        String path = TestFileUtil.getPath() + "demo" + File.separator + "16" + File.separator;
        List<String> sheetNameList = new ArrayList<>();
//        sheetNameList.add("基础属性");
//        sheetNameList.add("数据属性");
        sheetNameList.add("对象实例");
        metaImportService.processFileForFolder(path, sheetNameList);
    }

    @Test
    public void cellDataRead_06() {
        String path = TestFileUtil.getPath() + "demo" + File.separator + "20" + File.separator; // 水位站
        List<String> sheetNameList = new ArrayList<>();
        sheetNameList.add("基础属性");
        sheetNameList.add("数据属性");
        sheetNameList.add("对象实例");
        metaImportService.processFileForFolder(path, sheetNameList);
    }
//
//    @Test
//    public void cellDataRead_03() {
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "temp" + File.separator + "对象导入摸板_闸门.xlsx";
//        SheetNameEnum sheetNameEnum = SheetNameEnum.init("基础属性");
//
//
////        processFile(fileName, sheetNameEnum.getName());
//
//        String shortFileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.lastIndexOf("."));
//        String objectTypeName = shortFileName.substring(shortFileName.lastIndexOf("_") + 1, shortFileName.length());
//        String structureTypeCode = sheetNameEnum.getLabel();
//        String structureTypeName = sheetNameEnum.getName();
//        System.out.println(objectTypeName);
//
////        processFile(fileName, structureTypeName);
//    }
//
//
//
//    public List<AttrInfoEntity> queryPageList(List<AttrInfoEntity> list, Integer pageNo,
//                                              Integer pageSize) {
//        //1.计算出需要跳过多少条数据（流切片的起始位置）
//        int startPosition = (pageNo - 1) * pageSize;
//        Stream<AttrInfoEntity> stream = list.stream().skip(startPosition).limit(pageSize);
//        List<AttrInfoEntity> resultAccountList = stream.collect(Collectors.toList());
//        return resultAccountList;
//    }
//
//
//
//
//    @Test
//    public void cellDataRead_04() {
//        String path = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\模型标准化_数据整理\\样本数据";        //要遍历的路径
////        File file = new File(path);        //获取其file对象
////        File[] fs = file.listFiles();    //遍历path下的文件和目录，放在File数组中
////        for (File f : fs) {                    //遍历File[]数组
////            if (!f.isDirectory())        //若非目录(即文件)，则打印
////                System.out.println(f);
////        }
//
////        List<String> names = fileTraversalNotRecursion(path);
////        System.out.println(names.size());
////        for (String fileName : names) {
////            System.out.println(fileName);
////            processFile(fileName);
////        }
//    }
//
//    public List<String> fileTraversalNotRecursion(String path) {//非递归
//        List<String> names = new ArrayList<>();
//        File file = new File(path);
//        if (null != file) {
//            if (file.exists()) {
//                LinkedList<String> list = new LinkedList<>();//利用linkedlist的属性,链表结构
//
//                File[] files = file.listFiles();
//                if (null == files || 0 == files.length) {
//                    System.out.println("给文件夹下面没有文件");
//                } else {
//                    for (int i = 0, size = files.length; i < size; i++) {
//                        File temp = files[i];
//                        if (temp.isFile()) {
//                            names.add(temp.toString());
//                        } else {
//                            list.add(temp.getAbsolutePath());
//                        }
//
//                    }
//
//                    //遍历文件夹下面的所有文件
//                    while (!list.isEmpty()) {
//                        String tempPath = list.removeFirst();
//                        File temp = new File(tempPath);
//                        File[] tf = temp.listFiles();
//                        for (File ff : tf) {
//                            if (ff.isFile()) {
//                                names.add(ff.toString());
//                            } else {
//                                list.add(ff.getAbsolutePath());
//                            }
//
//                        }
//
//                    }
//                }
//
//            }
//        }
//        return names;
//    }

}
