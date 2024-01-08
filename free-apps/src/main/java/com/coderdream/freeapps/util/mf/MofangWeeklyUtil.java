package com.coderdream.freeapps.util.mf;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.coderdream.freeapps.util.sqliteutil.SqliteDailyReportUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MofangWeeklyUtil {

    public static void main(String[] args) {
//        m5();
//        genPrevDayMap();

//        updateInitAmount();
//        genDaily();
    }

    public static void m7() {

    }

    public static List<DailyReportDetail> m3() {
        ExcelReader reader = ExcelUtil.getReader(
            FileUtil.file("D:\\02_Work\\202301_兼职\\兼职大考说明2023\\日报明细_2024_01_01_16_32_47.xlsx"), "Sheet1");
        // 单词	英音	美音	释义	等级
        reader.addHeaderAlias("单号", "dailyNo");
        reader.addHeaderAlias("品类", "productCategory");
        reader.addHeaderAlias("机型", "productType");
        reader.addHeaderAlias("部件号", "productNo");
        reader.addHeaderAlias("描述", "desc");
        reader.addHeaderAlias("期初量", "initAmount");
        reader.addHeaderAlias("进货量", "incomeAmount");
        reader.addHeaderAlias("线上销量", "onlineSale");
        reader.addHeaderAlias("线下销量", "offlineSale");
        reader.addHeaderAlias("可销售差", "saleDiff");
        reader.addHeaderAlias("期末量", "endAmount");
        reader.addHeaderAlias("行备注", "lineComment");
        reader.addHeaderAlias("序号", "lineNo");
        List<DailyReportDetail> dailyDetailList = reader.readAll(DailyReportDetail.class);

        reader.close();
//
//        for (DailyReportDetail dailyDetail : dailyDetailList) {
//            System.out.println(dailyDetail);
//        }

        return dailyDetailList;
    }

    public static List<DailyReport> m4() {
        ExcelReader reader = ExcelUtil.getReader(
            FileUtil.file("D:\\02_Work\\202301_兼职\\兼职大考说明2023\\日报_2024_01_01_16_57_02.xlsx"), "Sheet1");
        // 单词	英音	美音	释义	等级
        reader.addHeaderAlias("单号", "dailyNo");
        reader.addHeaderAlias("门店ID", "storeId");
        reader.addHeaderAlias("门店名称", "storeName");
        reader.addHeaderAlias("状态", "dailyState");
        reader.addHeaderAlias("日期", "dailyReportDate");
        reader.addHeaderAlias("日报名称", "dailyReportName");
        List<DailyReport> dailyReportList = reader.readAll(DailyReport.class);

        reader.close();

//        for (DailyReport dailyReport : dailyReportList) {
//            System.out.println(dailyReport);
//        }

        return dailyReportList;
    }

    public static void m5() {
        List<DailyReport> dailyReportList = m4();
        List<DailyReportDetail> dailyDetailList = m3();
        Map<String, List<DailyReportDetail>> detailMap = new LinkedHashMap<>();
        List<DailyReportDetail> detailList = new ArrayList<>();
        for (DailyReportDetail dailyReportDetail : dailyDetailList) {
            detailList = detailMap.get(dailyReportDetail.getDailyNo());
            if (CollectionUtil.isEmpty(detailList)) {
                detailList = new ArrayList<>();
            }
            detailList.add(dailyReportDetail);
            detailMap.put(dailyReportDetail.getDailyNo(), detailList);
        }

        List<DailyReportVO> dailyReportVOList = new ArrayList<>();
        DailyReportVO dailyReportVO;
        for (DailyReport dailyReport : dailyReportList) {
            dailyReportVO = new DailyReportVO();
            BeanUtil.copyProperties(dailyReport, dailyReportVO);
            dailyReportVO.setDailyReportDetailList(detailMap.get(dailyReportVO.getDailyNo()));

            System.out.println(dailyReportVO);
        }

    }

    //

    /**
     * 【三种方法】Java生成指定范围内随机数 https://blog.csdn.net/weixin_43232955/article/details/108813725
     */
    public static void m2() {
        Random rand = new Random();
//            for (int i = 0; i < 10; i++) {
//                System.out.println(rand.nextInt(100) + 1);
//
//            }

        System.out.println("进货量\t线上销量\t线下销量\t可销售差\n");
        int n1 = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (int i = 1; i < 249; i++) {
            n1 = rand.nextInt(50) + 50;
            n2 = rand.nextInt(20) + 5;
            n3 = rand.nextInt(20) + 5;
            n4 = rand.nextInt(20) + 5;
            System.out.println(n1 + "\t" + n2 + "\t" + n3 + "\t" + n4);
        }
    }

    public static void m1() {
        System.out.println("门店ID\t状态\t日期\t日报名称");
        for (int i = 1; i < 32; i++) {
            if (i < 10) {
                System.out.println(
                    "MenDian_001" + "\t未提交\t" + "2023-12-0" + i + "\t门店A_" + "2023-12-0" + i + "_日报");
                System.out.println(
                    "MenDian_002" + "\t未提交\t" + "2023-12-0" + i + "\t门店B_" + "2023-12-0" + i + "_日报");
                System.out.println(
                    "MenDian_003" + "\t未提交\t" + "2023-12-0" + i + "\t门店C_" + "2023-12-0" + i + "_日报");
                System.out.println(
                    "MenDian_004" + "\t未提交\t" + "2023-12-0" + i + "\t门店D_" + "2023-12-0" + i + "_日报");
            } else {
                System.out.println(
                    "MenDian_001" + "\t未提交\t" + "2023-12-" + i + "\t门店A_" + "2023-12-" + i + "_日报");
                System.out.println(
                    "MenDian_002" + "\t未提交\t" + "2023-12-" + i + "\t门店B_" + "2023-12-" + i + "_日报");
                System.out.println(
                    "MenDian_003" + "\t未提交\t" + "2023-12-" + i + "\t门店C_" + "2023-12-" + i + "_日报");
                System.out.println(
                    "MenDian_004" + "\t未提交\t" + "2023-12-" + i + "\t门店D_" + "2023-12-" + i + "_日报");
            }
        }
    }

    public static void genDaily() {
        System.out.println("门店ID\t周季年\t状态");
        List<String> list = new ArrayList<>();
        list.add("FY23Q4W6");
        list.add("FY23Q4W7");
        list.add("FY23Q4W8");
        list.add("FY23Q4W9");
        list.add("FY23Q4W10");
        list.add("FY23Q4W11");
        list.add("FY23Q4W12");
        list.add("FY23Q4W13");

        for (String s : list) {
            System.out.println("MenDian_001\t" + s + "\t未提交");
            System.out.println("MenDian_002\t" + s + "\t未提交");
            System.out.println("MenDian_003\t" + s + "\t未提交");
            System.out.println("MenDian_004\t" + s + "\t未提交");
        }
    }

    public static Map<String, String> genPrevDayMap() {
        Map<String, String> result = new LinkedHashMap<>();
        // CharSequence dateStr
        CharSequence beginStr = "2023-12-01";
        CharSequence endStr = "2024-01-01";
        DateTime beginDate = new DateTime(beginStr);
        DateTime endDate = new DateTime(endStr);
//        DateTime prevDate =  DateUtil.offsetDay(endDate, -1);

        String prevDateDateStr = null;// prevDate.toDateStr();
        do {
            DateTime prevDate = DateUtil.offsetDay(endDate, -1);
            prevDateDateStr = prevDate.toDateStr();

            System.out.println(endDate.toDateStr() + "\t" + prevDateDateStr);
            result.put(endDate.toDateStr(), prevDateDateStr);
            endDate = prevDate;
        } while (!prevDateDateStr.equals(beginStr));

        return result;
    }

    public static void updateInitAmount() {

        Map<String, String> prevDayMap = genPrevDayMap();
//        for (Map.Entry<String, String> entry : prevDayMap.entrySet()) {
//            String currentDate = entry.getKey();
//            String prevDate = entry.getValue();
//        }

//        DateTime beginDate = DateTime.of("2023-12-02", "yyyy-MM-dd");
        DateTime currentDate = DateTime.of("2023-12-02", "yyyy-MM-dd");
        final int days = 29;
        for (int i = 0; i < days; i++) {
            currentDate = DateUtil.offsetDay(currentDate, 1);
            System.out.println("currentDate: \t" + currentDate.toDateStr());
            String prevDailyReportDate = prevDayMap.get(currentDate.toDateStr());
            Map<String, Integer> stringIntegerMap = SqliteDailyReportUtil.processSelectMapByDate(prevDailyReportDate);
            List<DailyReportDetail> dailyReportDetailList = SqliteDailyReportUtil.processSelectListByString(
                currentDate.toDateStr());
            Integer initAmount = 0;
            int endAmount = 0;
            String key = "";
            for (DailyReportDetail dailyReportDetail : dailyReportDetailList) {
                // 设置期初值 key 日期+门店ID+部件号 value 期末值
                key = prevDailyReportDate + dailyReportDetail.getStoreId() + dailyReportDetail.getProductNo();
                initAmount = stringIntegerMap.get(key);
                // 计算期末值
                endAmount = initAmount + dailyReportDetail.getIncomeAmount() - dailyReportDetail.getOnlineSale()
                    - dailyReportDetail.getOfflineSale() + dailyReportDetail.getSaleDiff();
                dailyReportDetail.setInitAmount(initAmount);
                dailyReportDetail.setEndAmount(endAmount);
                System.out.println("dailyReportDetail: " + dailyReportDetail);
                SqliteDailyReportUtil.processUpdate(dailyReportDetail);
            }
        }
    }
}
