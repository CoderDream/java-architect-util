package com.coderdream.freeapps.util.other;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.dto.ProxyInfo;
import com.coderdream.freeapps.dto.RecommendApp;
import com.coderdream.freeapps.dto.TopList;
import com.coderdream.freeapps.model.WordEntity;
import com.coderdream.freeapps.model.WordInfo;
import com.coderdream.freeapps.util.bbc.AbbrevComplete;
import com.coderdream.freeapps.util.bbc.Talker;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.beam.vendor.grpc.v1p43p2.com.google.gson.internal.LinkedHashTreeMap;
import org.apache.poi.hssf.record.DVALRecord;

/**
 * @author CoderDream
 */
@Slf4j
public class CdExcelUtil {

    public static void main(String[] args) {

//        for (int i = 0; i < 100; i++) {
//            Random r = new Random();
//            int i1 = r.nextInt(10); // 生成[0,3]区间的整数
//            System.out.print(i1 + " ");
//        }
//        String folderPath = "D:\\99_自媒体创业\\diandian";
//        List<String> allFileNames = CdFileUtils.getAllFileNames(folderPath);
//        if (CollectionUtils.isNotEmpty(allFileNames)) {
//            for (String fileName : allFileNames) {
//                log.info(fileName);
//                List<TopList> topLists = CdExcelUtils.genTopList(fileName);
//                if (CollectionUtils.isNotEmpty(topLists)) {
//                    for (TopList topList : topLists) {
//                        log.info(topList.toString());
//                    }
//                }
//            }
//        }
//        log.info("size: " + genTotalTopList().size());

//        List<String> appIdList = CdExcelUtils.genTotalTopAppIdList();
//        if (CollectionUtils.isNotEmpty(appIdList)) {
//            for (String appId : appIdList) {
//                log.info(appId);
//            }
//        }
//        m1();

        Map<String, Set<String>> map = new LinkedHashMap<>();
        Set<String> items1 = null;
        Set<String> items2 = null;
        Set<String> items3 = null;
        Set<String> items4 = null;
        Set<String> items5 = null;
        Set<String> items6 = null;
        Set<String> items7 = null;
        Set<String> items8 = null;
        Set<String> items9 = null;
        String key = "";
        String str = "";
        List<Medicine> medicines = genMedicineList();
        String[] strings;
        for (Medicine medicine : medicines) {
//            System.out.println(medicine);

            key = "别名";
            items1 = map.get(key);
            if (items1 == null) {
                items1 = new LinkedHashSet<>();
            }
            str = medicine.get别名();
            str = str.replace(" | ", "#");
            strings = str.split("#");
            items1.addAll(Arrays.asList(strings));
            map.put(key, items1);

            key = "作用机制";
            items2 = map.get(key);
            if (items2 == null) {
                items2 = new LinkedHashSet<>();
            }
            str = medicine.get作用机制();
            str = str.replace(" | ", "#");
            strings = str.split("#");
            items2.addAll(Arrays.asList(strings));
            map.put(key, items2);

            key = "药物类型";
            items3 = map.get(key);
            if (items3 == null) {
                items3 = new LinkedHashSet<>();
            }
            str = medicine.get药物类型();
            str = str.replace(" | ", "#");
            strings = str.split("#");
            items3.addAll(Arrays.asList(strings));
            map.put(key, items3);

            key = "在研适应症_疾病名";
            items4 = map.get(key);
            if (items4 == null) {
                items4 = new LinkedHashSet<>();
            }
            str = medicine.get在研适应症_疾病名();
            if (StrUtil.isNotEmpty(str)) {
                str = str.replace(" | ", "#");
                strings = str.split("#");
                items4.addAll(Arrays.asList(strings));
                map.put(key, items4);
            }

            key = "在研机构";
            items5 = map.get(key);
            if (items5 == null) {
                items5 = new LinkedHashSet<>();
            }
            str = medicine.get在研机构();
            str = str.replace(" | ", "#");
            strings = str.split("#");
            items5.addAll(Arrays.asList(strings));
            map.put(key, items5);

            key = "药物获批国家和地区";
            items6 = map.get(key);
            if (items6 == null) {
                items6 = new LinkedHashSet<>();
            }
            str = medicine.get药物获批国家和地区();
            if (StrUtil.isNotEmpty(str)) {
                str = str.replace(" | ", "#");
                strings = str.split("#");
                items6.addAll(Arrays.asList(strings));
                map.put(key, items6);
            }

            key = "治疗领域";
            items7 = map.get(key);
            if (items7 == null) {
                items7 = new LinkedHashSet<>();
            }
            str = medicine.get治疗领域();
            if (StrUtil.isNotEmpty(str)) {
                str = str.replace(" | ", "#");
                strings = str.split("#");
                items7.addAll(Arrays.asList(strings));
                map.put(key, items7);
            }

            key = "特殊审评";
            items8 = map.get(key);
            if (items8 == null) {
                items8 = new LinkedHashSet<>();
            }
            str = medicine.get特殊审评();
            if (StrUtil.isNotEmpty(str)) {
                str = str.replace(" | ", "#");
                strings = str.split("#");
                items8.addAll(Arrays.asList(strings));
                map.put(key, items8);
            }

            key = "靶点_基因名";
            items9 = map.get(key);
            if (items9 == null) {
                items9 = new LinkedHashSet<>();
            }
            str = medicine.get靶点_基因名();
            if (StrUtil.isNotEmpty(str)) {
                str = str.replace(" x ", "#");
                strings = str.split("#");
                items9.addAll(Arrays.asList(strings));
                map.put(key, items9);
            }
        }

        System.out.println(map.size());

        for (String keyStr : map.keySet()) {
            Set<String> strings1 = map.get(keyStr);
            Set<String> linkSet = new TreeSet<>(Comparator.naturalOrder());
            linkSet.addAll(strings1);
            System.out.println("### " + keyStr);
            for (String s : linkSet) {
                System.out.println("\t" + s);
            }
            System.out.println();
        }
    }

    public static void m1() {
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String path = BaseUtils.getPath();
        String txtFileName = File.separator + path + File.separator + dateStr + ".txt";

        FileWriter fileWriter = FileWriter.create(new File(txtFileName));

        List<RecommendApp> recommendAppList = CdExcelUtil.genRecommendAppList();
        if (CollectionUtils.isNotEmpty(recommendAppList)) {
            for (RecommendApp recommendApp : recommendAppList) {
//                System.out.println(recommendApp);
                System.out.println(recommendApp.getTitle());
                fileWriter.writeLines(Collections.singletonList(recommendApp.getTitle()), true);
                System.out.println("¥" + recommendApp.getYesterdayPrice() + "➱0");
                fileWriter.writeLines(Arrays.asList("¥" + recommendApp.getYesterdayPrice() + "➱0"), true);
                String cnFlag = recommendApp.getCnFlag();
                String cnMessage = "";
                if (StrUtil.isNotEmpty(cnFlag) && "0".equals(cnFlag) && StrUtil.isEmpty(recommendApp.getDescriptionCn())
                    && !CdStringUtils.isContainChinese(recommendApp.getDescriptionUs())) {
                    cnMessage = "无中文。"; // 无中文。
                }
                if (recommendApp.getUsFlag() != null && "1".equals(recommendApp.getUsFlag())) {
                    System.out.print("美区限免。");

                    if (StrUtil.isNotEmpty(recommendApp.getDescription())) {
                        System.out.println(recommendApp.getDescription());
                        fileWriter.writeLines(Arrays.asList("美区限免。" + cnMessage + recommendApp.getDescription()),
                            true);
                    } else {
                        System.out.println(recommendApp.getDescriptionCn());
                        fileWriter.writeLines(Arrays.asList("美区限免。" + cnMessage + recommendApp.getDescriptionCn()),
                            true);
                    }

                    System.out.println("https://apps.apple.com/us/app/" + recommendApp.getAppId());
                    fileWriter.writeLines(Arrays.asList("https://apps.apple.com/us/app/" + recommendApp.getAppId()),
                        true);
                } else {
//                    System.out.println(recommendApp.getDescriptionCn());
                    if (StrUtil.isNotEmpty(recommendApp.getDescription())) {
                        fileWriter.writeLines(Arrays.asList(cnMessage + recommendApp.getDescription()), true);
                    } else {
                        fileWriter.writeLines(Arrays.asList(cnMessage + recommendApp.getDescriptionCn()), true);
                    }
                    System.out.println("https://apps.apple.com/cn/app/" + recommendApp.getAppId());
                    fileWriter.writeLines(Arrays.asList("https://apps.apple.com/cn/app/" + recommendApp.getAppId()),
                        true);
                }
                System.out.println();
                fileWriter.writeLines(Arrays.asList(""), true);
            }
        }
    }

    public static Set<String> genTotalTopAppIdSet() {
        Set<String> appIdSet = new LinkedHashSet<>();
        List<TopList> totalTopList = genTotalTopList();
        if (CollectionUtils.isNotEmpty(totalTopList)) {
            for (TopList topList : totalTopList) {
//                log.info(topList.toString());
                appIdSet.add(topList.getAppId()); // id前面加id
            }
        }

        return appIdSet;
    }

    public static List<String> genTotalTopAppIdList() {
        List<String> appIdList = new ArrayList<>();
        List<TopList> totalTopList = genTotalTopList();
        if (CollectionUtils.isNotEmpty(totalTopList)) {
            Set<String> appIdSet = new LinkedHashSet<>();
            for (TopList topList : totalTopList) {
                log.info(topList.toString());
                appIdSet.add(topList.getAppId()); // id前面加id
            }
            appIdList.addAll(appIdSet);
        }

        return appIdList;
    }

    public static List<TopList> genTotalTopList() {
        List<TopList> result = new ArrayList<>();
        String folderPath = "D:\\99_自媒体创业\\diandian";
        List<String> allFileNames = CdFileUtils.getAllFileNames(folderPath);
        if (CollectionUtils.isNotEmpty(allFileNames)) {
            for (String fileName : allFileNames) {
//                log.info(fileName);
                List<TopList> topLists = CdExcelUtil.genTopList(fileName);
                if (CollectionUtils.isNotEmpty(topLists)) {
                    for (TopList topList : topLists) {
//                        log.info(topList.toString());
                        topList.setAppId("id" + topList.getAppId()); // id前面加id
                        result.add(topList);
                    }
                }
            }
        }

        return result;
    }

    public static List<TopList> genTopList(String fileName) {
//        String filename = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\00_导入数据\\02_荆楚河道\\01_对象结构\\河道-对象结构-20230420-堤防地理信息映射.xlsx";

//        filename = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\00_导入数据\\02_荆楚河道\\01_对象结构\\河道-对象结构-20230420-堤防地理信息映射 - mysql2.xlsx";
        //
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(fileName), "Sheet1");
        reader.addHeaderAlias("排名", "raking");
        reader.addHeaderAlias("应用ID", "appId");
        reader.addHeaderAlias("应用名称", "title");
        reader.addHeaderAlias("总排名", "topRaking");
        reader.addHeaderAlias("分类", "category");
        reader.addHeaderAlias("分类排名", "categoryRaking");
        reader.addHeaderAlias("开发者", "author");
        reader.addHeaderAlias("相比昨日", "compareYesterday");
        List<TopList> topLists = reader.read(1, 2, TopList.class);
        reader.close();

        return topLists;
    }

    //

    public static List<RecommendApp> genRecommendAppList() {
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String path = BaseUtils.getPath();
        String fileName = File.separator + path + File.separator + dateStr + ".xlsx";
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(fileName), "Sheet2");
        // 应用ID	应用名称	投票数	美区标志	中文简介	英文简介	简介	字幕	历史价格	今日价格
        reader.addHeaderAlias("应用ID", "appId");
        reader.addHeaderAlias("应用名称", "title");
        reader.addHeaderAlias("投票数", "rateAmount");
        reader.addHeaderAlias("美区标志", "usFlag");
        reader.addHeaderAlias("中文标志", "cnFlag");
        reader.addHeaderAlias("中文简介", "descriptionCn");
        reader.addHeaderAlias("英文简介", "descriptionUs");
        reader.addHeaderAlias("简介", "description");
        reader.addHeaderAlias("字幕", "descriptionZm");
        reader.addHeaderAlias("历史价格", "yesterdayPrice");
        reader.addHeaderAlias("今日价格", "todayPrice");
//        List<RecommendApp> recommendAppList = reader.read(1, 1, RecommendApp.class);
        List<RecommendApp> recommendAppList = reader.readAll(RecommendApp.class);
        reader.close();

        return recommendAppList;
    }

    public static List<String> genHostList(String filePath) {
//        String fileName = File.separator + path + File.separator + dateStr + ".xlsx";
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(filePath), "Sheet1");
        // 应用ID	应用名称	投票数	美区标志	中文简介	英文简介	简介	字幕	历史价格	今日价格
        reader.addHeaderAlias("host", "host");
//        List<RecommendApp> recommendAppList = reader.read(1, 1, RecommendApp.class);
        List<Talker> myHostList = reader.readAll(Talker.class);
        reader.close();

        List<String> stringList = myHostList.stream().map(Talker::getTalker).collect(Collectors.toList());

        return stringList;
    }

//    @Data
//    class MyHost {
//
//        private String host;
//    }

    public static Map<String, String> genAbbrevCompleteMap(String filePath) {
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(filePath), "Sheet1");
        // 应用ID	应用名称	投票数	美区标志	中文简介	英文简介	简介	字幕	历史价格	今日价格
        reader.addHeaderAlias("abbrev", "abbrev");
        reader.addHeaderAlias("complete", "complete");
        List<AbbrevComplete> abbrevCompleteList = reader.readAll(AbbrevComplete.class);
        reader.close();
        Map<String, String> result = new LinkedHashTreeMap<>();
        for (AbbrevComplete abbrevComplete : abbrevCompleteList) {
            String abbrev = abbrevComplete.getAbbrev();
            if ("I'm".equals(abbrev) || "I've".equals(abbrev) || "I'd".equals(abbrev) || "I'll".equals(abbrev)
                || "I’m".equals(abbrev) || "I’ve".equals(abbrev) || "I’d".equals(abbrev) || "I’ll".equals(abbrev)) {
                result.put(abbrevComplete.getAbbrev(), abbrevComplete.getComplete());
            } else {
                result.put(abbrevComplete.getAbbrev().toLowerCase(), abbrevComplete.getComplete().toLowerCase());
            }
        }

        return result;
    }

    //

    public static List<ProxyInfo> genProxyInfoList() {
        String fileName =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "IP.xlsx";
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(fileName), "Sheet1");
        reader.addHeaderAlias("IP", "ip");
        reader.addHeaderAlias("PORT", "port");
//        reader.addHeaderAlias("投票数", "rateAmount");
//        reader.addHeaderAlias("美区标志", "usFlag");
//        reader.addHeaderAlias("中文标志", "cnFlag");
//        reader.addHeaderAlias("中文简介", "descriptionCn");
//        reader.addHeaderAlias("英文简介", "descriptionUs");
//        reader.addHeaderAlias("简介", "description");
//        reader.addHeaderAlias("字幕", "descriptionZm");
//        reader.addHeaderAlias("历史价格", "yesterdayPrice");
//        reader.addHeaderAlias("今日价格", "todayPrice");
        List<ProxyInfo> proxyInfoList = reader.readAll(ProxyInfo.class);
        reader.close();

        return proxyInfoList;
    }

    public static List<Medicine> genMedicineList() {
        String fileName =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "IP.xlsx";
        fileName = "D:\\02_Work\\202301_兼职\\04_项目\\项目_004_智慧芽demo\\数据_V1.xlsx";
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(fileName), "Sheet1");

        reader.addHeaderAlias("序号", "序号");
        reader.addHeaderAlias("药物", "药物");
        reader.addHeaderAlias("别名", "别名");
        reader.addHeaderAlias("靶点（基因名）", "靶点_基因名");
        reader.addHeaderAlias("作用机制", "作用机制");
        reader.addHeaderAlias("药物类型", "药物类型");
        reader.addHeaderAlias("在研适应症（疾病名）", "在研适应症_疾病名");
        reader.addHeaderAlias("在研机构", "在研机构");
        reader.addHeaderAlias("最高研发阶段", "最高研发阶段");
        reader.addHeaderAlias("最高研发阶段（中国）", "最高研发阶段_中国");
        reader.addHeaderAlias("存在交易", "存在交易");
        reader.addHeaderAlias("药物获批国家和地区", "药物获批国家和地区");
        reader.addHeaderAlias("ATC", "ATC");
        reader.addHeaderAlias("治疗领域", "治疗领域");
        reader.addHeaderAlias("原研机构", "原研机构");
        reader.addHeaderAlias("特殊审评", "特殊审评");
        reader.addHeaderAlias("获批时间", "获批时间");
        reader.addHeaderAlias("获批时间 （中国）", "获批时间_中国");
        reader.addHeaderAlias("抗体（ADC）", "抗体_ADC");
        reader.addHeaderAlias("连接子（ADC）", "连接子_ADC");
        reader.addHeaderAlias("载荷（ADC）", "载荷_ADC");
        reader.addHeaderAlias("DAR（ADC）", "DAR_ADC");
        reader.addHeaderAlias("抗体类型（ADC）", "抗体类型_ADC");
        reader.addHeaderAlias("偶联位点（ADC）", "偶联位点_ADC");
        reader.addHeaderAlias("定点偶联（ADC）", "定点偶联_ADC");

//        reader.addHeaderAlias("IP", "ip");
//        reader.addHeaderAlias("PORT", "port");
//        reader.addHeaderAlias("投票数", "rateAmount");
//        reader.addHeaderAlias("美区标志", "usFlag");
//        reader.addHeaderAlias("中文标志", "cnFlag");
//        reader.addHeaderAlias("中文简介", "descriptionCn");
//        reader.addHeaderAlias("英文简介", "descriptionUs");
//        reader.addHeaderAlias("简介", "description");
//        reader.addHeaderAlias("字幕", "descriptionZm");
//        reader.addHeaderAlias("历史价格", "yesterdayPrice");
//        reader.addHeaderAlias("今日价格", "todayPrice");
        List<Medicine> proxyInfoList = reader.readAll(Medicine.class);
        reader.close();

        return proxyInfoList;
    }

    //

    public static void dm() {
        String filename = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\00_导入数据\\02_荆楚河道\\01_对象结构\\河道-对象结构-20230420-堤防地理信息映射.xlsx";

        filename = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\00_导入数据\\02_荆楚河道\\01_对象结构\\河道-对象结构-20230420-堤防地理信息映射 - mysql2.xlsx";
        // 排名	应用ID	应用名称	总排名	分类	分类排名	开发者	相比昨日
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(filename), "属性-API");
        reader.addHeaderAlias("属性", "columnName");
        reader.addHeaderAlias("字段标识", "columnLabel");
        reader.addHeaderAlias("所属表", "tableName");
        reader.addHeaderAlias("表标识", "tableLabel");
        reader.addHeaderAlias("备注", "remark");
        List<Sheet0> members = reader.read(1, 2, Sheet0.class);
//        System.out.println("cc = " + members);
        Map<String, String> nameKeyMap = new LinkedHashMap<>();
        for (Sheet0 sheet0 : members) {
            if (StrUtil.isNotEmpty(sheet0.getColumnName()) && StrUtil.isNotEmpty(sheet0.getColumnLabel())) {
                nameKeyMap.put(sheet0.getColumnName().trim(), sheet0.getColumnLabel().trim());
            }
        }
        reader.close();
        filename = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\00_导入数据\\02_荆楚河道\\01_对象结构\\河道-对象结构-20230420-堤防地理信息映射.xlsx";
        ExcelReader reader1 = ExcelUtil.getReader(FileUtil.file(filename), "Sheet1");
        reader1.addHeaderAlias("属性名称", "attrName");
        reader1.addHeaderAlias("属性全码", "attrFullCode");
        List<Sheet1> members1 = reader1.readAll(Sheet1.class);
//        System.out.println("cc = " + members1);
        reader1.close();
        List<String> stringList = new ArrayList<>();
        String str;
        for (Sheet1 sheet1 : members1) {
            String labelKey = nameKeyMap.get(sheet1.getAttrName());
            if (StrUtil.isNotEmpty(labelKey)) {
                str = "\"" + sheet1.getAttrFullCode().trim() + "\":\"" + labelKey.trim() + "\"";
                stringList.add(str);
            } else {
                System.out.println("#####");
            }
        }
        String result = "{" + String.join(",", stringList) + "}";
        System.out.println(result);
    }


    public static void mysql() {
        String filename = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\00_导入数据\\02_荆楚河道\\01_对象结构\\河道-对象结构-20230420-堤防地理信息映射.xlsx";

        filename = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\00_导入数据\\02_荆楚河道\\01_对象结构\\河道-对象结构-20230420-堤防地理信息映射 - mysql2.xlsx";
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(filename), "属性-API");
        reader.addHeaderAlias("属性", "columnName");
        reader.addHeaderAlias("字段标识", "columnLabel");
        reader.addHeaderAlias("所属表", "tableName");
        reader.addHeaderAlias("表标识", "tableLabel");
        reader.addHeaderAlias("备注", "remark");
        List<Sheet0> members = reader.readAll(Sheet0.class);
//        System.out.println("cc = " + members);
        Map<String, String> nameKeyMap = new LinkedHashMap<>();
        for (Sheet0 sheet0 : members) {
            if (StrUtil.isNotEmpty(sheet0.getColumnName()) && StrUtil.isNotEmpty(sheet0.getColumnLabel())) {
                nameKeyMap.put(sheet0.getColumnName().trim(), sheet0.getColumnLabel().trim());
            }
        }
        reader.close();
        filename = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\00_导入数据\\02_荆楚河道\\01_对象结构\\河道-对象结构-20230420-堤防地理信息映射 - mysql.xlsx";
        ExcelReader reader1 = ExcelUtil.getReader(FileUtil.file(filename), "Sheet1");
        reader1.addHeaderAlias("属性名称", "attrName");
        reader1.addHeaderAlias("属性全码", "attrFullCode");
        List<Sheet1> members1 = reader1.readAll(Sheet1.class);
//        System.out.println("cc = " + members1);
        reader1.close();
        List<String> stringList = new ArrayList<>();
        String str;
        for (Sheet1 sheet1 : members1) {
            String labelKey = nameKeyMap.get(sheet1.getAttrName());
            if (StrUtil.isNotEmpty(labelKey)) {
                str = "\"" + sheet1.getAttrFullCode().trim() + "\":\"" + labelKey.trim() + "\"";
                stringList.add(str);
            } else {
                System.out.println("#####");
            }
        }
        String result = "{" + String.join(",", stringList) + "}";
        System.out.println(result);
    }

    //

    /**
     * @return 单词列表
     */
    public static List<WordEntity> genWordEntityList(String filePath, String sheetName) {
//        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
//        String path = BaseUtils.getPath();
//        String fileName = File.separator + path + File.separator + dateStr + ".xlsx";
//        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(fileName), "Sheet1");
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(filePath), sheetName);
        // 单词	英音	美音	释义	等级
        reader.addHeaderAlias("单词", "word");
        reader.addHeaderAlias("英音", "uk");
        reader.addHeaderAlias("美音", "us");
        reader.addHeaderAlias("释义", "comment");
        reader.addHeaderAlias("等级", "level");
        List<WordEntity> recommendAppList = reader.readAll(WordEntity.class);
        for (WordEntity wordEntity : recommendAppList) {
            wordEntity.setWord(wordEntity.getWord().toLowerCase());
            wordEntity.setComment(wordEntity.getComment().replaceAll("\n", ";"));
        }
        reader.close();

        return recommendAppList;
    }

    public static List<WordInfo> genWordInfoList(String filePath, String sheetName) {
//        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
//        String path = BaseUtils.getPath();
//        String fileName = File.separator + path + File.separator + dateStr + ".xlsx";
//        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(fileName), "Sheet1");
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(filePath), sheetName);
        // 单词	英音	美音	释义	等级
        reader.addHeaderAlias("单词", "word");
        reader.addHeaderAlias("英音", "uk");
        reader.addHeaderAlias("美音", "us");
        reader.addHeaderAlias("释义", "comment");
        reader.addHeaderAlias("等级", "level");
        reader.addHeaderAlias("次数", "times");
        List<WordInfo> recommendAppList = reader.readAll(WordInfo.class);
        for (WordInfo wordInfo : recommendAppList) {
            wordInfo.setWord(wordInfo.getWord().toLowerCase());
            wordInfo.setComment(wordInfo.getComment().replaceAll("\n", ";"));
            wordInfo.setLevelStr(wordInfo.getLevel());
        }
        reader.close();

        return recommendAppList;
    }

    public static List<AbbrevComplete> genAbbrevCompleteList(String filePath) {
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(filePath), "Sheet1");
        reader.addHeaderAlias("abbrev", "abbrev");
        reader.addHeaderAlias("complete", "complete");
        List<AbbrevComplete> abbrevCompleteList = reader.readAll(AbbrevComplete.class);
        for (AbbrevComplete abbrevComplete : abbrevCompleteList) {
            abbrevComplete.setAbbrev(abbrevComplete.getAbbrev().toLowerCase());
            abbrevComplete.setComplete(abbrevComplete.getComplete().toLowerCase());
        }

        reader.close();

        return abbrevCompleteList;
    }
}

@Data
class Sheet0 { // 表字段名	字段标识	所属表	表标识	备注

    private String columnName;
    private String columnLabel;
    private String tableName;
    private String tableLabel;
    private String remark;
}

@Data
class Sheet1 { // ATTR_NAME	ATTR_FULL_CODE

    private String attrName;
    private String attrFullCode;
}

@Data
class Medicine {

    private String 序号;
    private String 药物;
    private String 别名;
    private String 靶点_基因名;
    private String 作用机制;
    private String 药物类型;
    private String 在研适应症_疾病名;
    private String 在研机构;
    private String 最高研发阶段;
    private String 最高研发阶段_中国;
    private String 存在交易;
    private String 药物获批国家和地区;
    private String ATC;
    private String 治疗领域;
    private String 原研机构;
    private String 特殊审评;
    private String 获批时间;
    private String 获批时间_中国;
    private String 抗体_ADC;
    private String 连接子_ADC;
    private String 载荷_ADC;
    private String DAR_ADC;
    private String 抗体类型_ADC;
    private String 偶联位点_ADC;
    private String 定点偶联_ADC;


}
