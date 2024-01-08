package com.coderdream.freeapps.util.sqliteutil;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.coderdream.freeapps.util.mf.WeeklyReportDetail;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * 品类 机型 部件号 描述 WK1需求原值 WK1需求拟增减 WK1需求实际值 WK2需求原值 WK2需求拟增减 WK2需求实际值 WK3需求 WK1第一次需求计划 WK1第二次需求计划 WK2第一次需求计划 单号
 *
 * @author CoderDream
 */
public class SqliteWeeklyReportUtil {

    public static void main(String[] args) {

//        String weekSeasonYear = "FY23Q4W06";
//        List<WeeklyReportDetail> weeklyReportDetails = processSelectListByString(weekSeasonYear);
//        for (WeeklyReportDetail weeklyReportDetail : weeklyReportDetails) {
//            System.out.println(weeklyReportDetail);
//        }

//        initFirstWeek();
//        initSecondWeek();
        updateWeekly();
    }

    public static void updateWeekly() {
//        String weekSeasonYear = "FY23Q4W06";
        String initDate = "2023-11-11";
        String wk1 = null;
        String wk2 = null;
        String wk3 = null;
        int SIZE = 7; //7;
        for (int i = 1; i < SIZE; i++) {
            wk1 = DateUtil.offsetDay(new DateTime(initDate, "yyyy-MM-dd"), 7 * i - 7).toDateStr();
            wk2 = DateUtil.offsetDay(new DateTime(initDate, "yyyy-MM-dd"), 7 * i).toDateStr();
            wk3 = DateUtil.offsetDay(new DateTime(initDate, "yyyy-MM-dd"), 7 * i + 7).toDateStr();
            // wk1数据
            List<WeeklyReportDetail> wk1WeeklyReportDetails = SqliteWeeklyReportUtil.processSelectListByEndDate(wk1);
            // wk2数据
            List<WeeklyReportDetail> wk2WeeklyReportDetails = SqliteWeeklyReportUtil.processSelectListByEndDate(wk2);
            // wk3数据
            List<WeeklyReportDetail> wk3WeeklyReportDetails = SqliteWeeklyReportUtil.processSelectListByEndDate(wk3);

            // 根据门店ID和部件号，设置对应的对象
            String key = "";
            Map<String, WeeklyReportDetail> wk1Map = new LinkedHashMap<>();
            for (WeeklyReportDetail weeklyReportDetail : wk1WeeklyReportDetails) {
                key = weeklyReportDetail.getStoreId() + weeklyReportDetail.getProductNo();
                wk1Map.put(key, weeklyReportDetail);
            }

            // 根据门店ID和部件号，设置对应的对象
            Map<String, WeeklyReportDetail> wk2Map = new LinkedHashMap<>();
            for (WeeklyReportDetail weeklyReportDetail : wk2WeeklyReportDetails) {
                key = weeklyReportDetail.getStoreId() + weeklyReportDetail.getProductNo();
                wk2Map.put(key, weeklyReportDetail);
            }

            // 根据门店ID和部件号，找到对应的数值
            WeeklyReportDetail wk1WeeklyReportDetail = null;
            WeeklyReportDetail wk2WeeklyReportDetail = null;
            Random random = new Random();
            for (WeeklyReportDetail weeklyReportDetail : wk3WeeklyReportDetails) {
                System.out.println(weeklyReportDetail);
                // WK1需求拟增减（手填）
                weeklyReportDetail.setWk1Modify(random.nextInt(5) + 3);
                // WK2需求拟增减（手填）
                weeklyReportDetail.setWk2Modify(random.nextInt(5) + 5);
                // WK3需求（手填）
                weeklyReportDetail.setWk3OriginalDemandValue(random.nextInt(50) + 120);
                // WK3需求实际值（手填）
                weeklyReportDetail.setWk3ActualDemandValue(random.nextInt(50) + 120);

                wk1WeeklyReportDetail = wk1Map.get(weeklyReportDetail.getStoreId() + weeklyReportDetail.getProductNo());
                wk2WeeklyReportDetail = wk2Map.get(weeklyReportDetail.getStoreId() + weeklyReportDetail.getProductNo());
                // 如果找到了wk1，则赋值
                if (wk1WeeklyReportDetail != null && wk2WeeklyReportDetail != null) {
                    // WK1需求原值=wk2对象的【后续周2需求原值】
                    weeklyReportDetail.setWk1OriginalDemandValue(wk2WeeklyReportDetail.getWk2OriginalDemandValue());
                    // WK2需求原值=wk2对象的【后续周3需求原值】
                    weeklyReportDetail.setWk2OriginalDemandValue(wk2WeeklyReportDetail.getWk3OriginalDemandValue());
                    // WK1第二次需求计划=wk2的【后续周2需求实际值】
                    weeklyReportDetail.setWk1SecondPlan(wk2WeeklyReportDetail.getWk2ActualDemandValue());
                    // WK1第一次需求计划=wk1的【后续周3需求】
                    weeklyReportDetail.setWk1FirstPlan(wk1WeeklyReportDetail.getWk3OriginalDemandValue());
                    // WK2第一次需求计划=wk2的【后续周3需求】
                    weeklyReportDetail.setWk2FirstPlan(wk2WeeklyReportDetail.getWk3OriginalDemandValue());
                }

                processUpdate(weeklyReportDetail);
            }

        }
    }

    public static void initFirstWeek() {
//        String weekSeasonYear = "FY23Q4W06";
        String endDate = "2023-11-11";
        List<WeeklyReportDetail> weeklyReportDetails = SqliteWeeklyReportUtil.processSelectListByEndDate(endDate);
        for (WeeklyReportDetail weeklyReportDetail : weeklyReportDetails) {
            System.out.println(weeklyReportDetail);
            // WK1需求原值
            weeklyReportDetail.setWk1OriginalDemandValue(100);
            // WK1需求拟增减
            weeklyReportDetail.setWk1Modify(5);
            // WK2需求原值
            weeklyReportDetail.setWk2OriginalDemandValue(110);
            // WK2需求拟增减
            weeklyReportDetail.setWk2Modify(8);
            // WK3需求
            weeklyReportDetail.setWk3OriginalDemandValue(120);
            // WK3需求实际值（手填）
            weeklyReportDetail.setWk3ActualDemandValue(128);

            // WK1第二次需求计划（无数据）
            weeklyReportDetail.setWk1SecondPlan(0);
            // WK1第一次需求计划（无数据）
            weeklyReportDetail.setWk1FirstPlan(0);
            // WK2第一次需求计划（无数据）
            weeklyReportDetail.setWk2FirstPlan(0);

            processUpdate(weeklyReportDetail);
        }
    }

    public static void initSecondWeek() {
//        String weekSeasonYear = "FY23Q4W06";
        String wk1EndDate = "2023-11-11";
        String endDate = "2023-11-18";
        List<WeeklyReportDetail> wk1WeeklyReportDetails = SqliteWeeklyReportUtil.processSelectListByEndDate(wk1EndDate);
        // 根据门店ID和部件号，设置对应的对象
        String key = "";
        Map<String, WeeklyReportDetail> wk1Map = new LinkedHashMap<>();
        for (WeeklyReportDetail weeklyReportDetail : wk1WeeklyReportDetails) {
            System.out.println(weeklyReportDetail);
            key = weeklyReportDetail.getStoreId() + weeklyReportDetail.getProductNo();
            wk1Map.put(key, weeklyReportDetail);
        }
        // 根据门店ID和部件号，找到对应的数值
        List<WeeklyReportDetail> weeklyReportDetails = SqliteWeeklyReportUtil.processSelectListByEndDate(endDate);
        WeeklyReportDetail wk1WeeklyReportDetail = null;
        for (WeeklyReportDetail weeklyReportDetail : weeklyReportDetails) {
            System.out.println(weeklyReportDetail);
            // WK1需求拟增减（手填）
            weeklyReportDetail.setWk1Modify(5);
            // WK2需求拟增减（手填）
            weeklyReportDetail.setWk2Modify(8);
            // WK3需求（手填）
            weeklyReportDetail.setWk3OriginalDemandValue(120);
            // WK3需求实际值（手填）
            weeklyReportDetail.setWk3ActualDemandValue(122);

            wk1WeeklyReportDetail = wk1Map.get(weeklyReportDetail.getStoreId() + weeklyReportDetail.getProductNo());
            // 如果找到了wk1，则赋值
            if (wk1WeeklyReportDetail != null) {
                // WK1需求原值=wk1对象的WK2需求原值
                weeklyReportDetail.setWk1OriginalDemandValue(wk1WeeklyReportDetail.getWk2OriginalDemandValue());
                // WK2需求原值=wk1对象的WK3需求原值
                weeklyReportDetail.setWk2OriginalDemandValue(wk1WeeklyReportDetail.getWk3OriginalDemandValue());
                // WK1第二次需求计划
                weeklyReportDetail.setWk1SecondPlan(wk1WeeklyReportDetail.getWk2ActualDemandValue());
                // WK1第一次需求计划（无数据）
                weeklyReportDetail.setWk1FirstPlan(0);
                // WK2第一次需求计划
                weeklyReportDetail.setWk2FirstPlan(wk1WeeklyReportDetail.getWk3OriginalDemandValue());
            }

            processUpdate(weeklyReportDetail);
        }
    }


    public static Map<String, String> processSelectByInteger(List<Integer> articleDateList) {
        Connection c = SqliteUtil.getConnect();
        Statement stmt = null;
        String articleDate = "";
        String titleCn = "";
        Map<String, String> map = new LinkedHashMap<>();
        try {
            stmt = c.createStatement();
//            String sql = "SELECT * FROM daily_report_detail WHERE article_date IN (?)";
//            String sql = "SELECT * FROM daily_report_detail WHERE article_date IN (220303,220310)";

            String params = StringUtils.join(articleDateList, ",");
//            String[] arr = (String[])articleDateList.toArray();
//            Array a =
//            preparedStatement.setArray(1, arr);
            System.out.println("params: " + params);
//            preparedStatement.setString(1, params);
//            preparedStatement.setString(1, "'220303','220310'");

            String sql = "SELECT * FROM daily_report_detail WHERE article_date IN (" + params + ")";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                articleDate = resultSet.getString("article_date");
                titleCn = resultSet.getString("title_cn");
                System.out.println(articleDate + ":\t" + titleCn);
                map.put(articleDate, titleCn);
            }

            resultSet.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return map;
    }

    /**
     * @param weekSeasonYear
     * @return key 日期+门店ID+部件号 value 期末值
     */
    public static Map<String, Integer> processSelectMapByDate(String weekSeasonYear) {
        Connection c = SqliteUtil.getConnect();
        Statement stmt = null;
        Map<String, Integer> map = new LinkedHashMap<>(); // key 日期+门店ID+部件号 value 期末值
        try {
            stmt = c.createStatement();
            String sql =
                "SELECT t2.week_season_year, t2.store_id, t1.* FROM weekly_report_detail t1, weekly_report t2 WHERE t1.weekly_no=t2.weekly_no and t2.week_season_year='"
                    + weekSeasonYear + "';";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            String key = "";
            String storeId = "";
            String productNo = "";
            Integer endAmount = 0;
            while (resultSet.next()) {
                storeId = resultSet.getString("store_id");
                productNo = resultSet.getString("product_no");
                endAmount = resultSet.getInt("end_amount");
                key = weekSeasonYear + storeId + productNo;
                System.out.println(key + ":\t" + endAmount);

                map.put(key, endAmount);
            }

            resultSet.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return map;
    }

    /**
     * @param dailyNoList
     * @return
     */
    public static List<WeeklyReportDetail> processSelectByString(List<String> dailyNoList) {
        Connection c = SqliteUtil.getConnect();
        Statement stmt = null;
        String articleDate = "";
        String titleCn = "";
        Map<String, String> map = new LinkedHashMap<>();
        List<WeeklyReportDetail> weeklyReportDetailList = new ArrayList<>();
        WeeklyReportDetail weeklyReportDetail;
        try {
            stmt = c.createStatement();
//            String sql = "SELECT * FROM daily_report_detail WHERE article_date IN (?)";
            String params = null;

            if (CollectionUtil.isNotEmpty(dailyNoList)) {
                List<String> paramsList = new ArrayList<>();
                for (String s : dailyNoList) {
                    paramsList.add("'" + s + "'");
                }
                params = StringUtils.join(paramsList, ",");
            }

//            String[] arr = (String[])articleDateList.toArray();
//            Array a =
//            preparedStatement.setArray(1, arr);
            System.out.println("params: " + params);
//            preparedStatement.setString(1, params);
//            preparedStatement.setString(1, "'220303','220310'");

            String sql = "SELECT * FROM daily_report_detail";
            if (params != null) {
                sql += " WHERE daily_no IN (" + params + ")";
            }
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                weeklyReportDetail = getWeeklyReportDetail(resultSet);

                weeklyReportDetailList.add(weeklyReportDetail);
            }

            resultSet.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return weeklyReportDetailList;
    }

    /**
     * @param weekSeasonYear
     * @return
     */
    public static List<WeeklyReportDetail> processSelectListByString(String weekSeasonYear) {
        Connection c = SqliteUtil.getConnect();
        Statement stmt;
        List<WeeklyReportDetail> weeklyReportDetailList = new ArrayList<>();
        WeeklyReportDetail weeklyReportDetail;
        try {
            stmt = c.createStatement();
            String sql =
                "SELECT t2.week_season_year, t2.store_id, t1.* FROM weekly_report_detail t1, weekly_report t2 WHERE t1.weekly_no=t2.weekly_no and t2.week_season_year='"
                    + weekSeasonYear + "';";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                weeklyReportDetail = getWeeklyReportDetail(resultSet);

                weeklyReportDetailList.add(weeklyReportDetail);
            }

            resultSet.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return weeklyReportDetailList;
    }

    /**
     * @param endDate
     * @return
     */
    public static List<WeeklyReportDetail> processSelectListByEndDate(String endDate) {
        Connection c = SqliteUtil.getConnect();
        Statement stmt;
        List<WeeklyReportDetail> weeklyReportDetailList = new ArrayList<>();
        WeeklyReportDetail weeklyReportDetail;
        try {
            stmt = c.createStatement();
            String sql =
                "SELECT t2.week_season_year, t2.store_id, t1.* "
                    + "FROM weekly_report_detail t1, weekly_report t2, week_calendar t3 "
                    + "WHERE t1.weekly_no=t2.weekly_no "
                    + "and t2.week_season_year=t3.year_season_week "
                    + "and t3.end_date='" + endDate + "';";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                weeklyReportDetail = getWeeklyReportDetail(resultSet);

                weeklyReportDetailList.add(weeklyReportDetail);
            }

            resultSet.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return weeklyReportDetailList;
    }

    @NotNull
    private static WeeklyReportDetail getWeeklyReportDetail(ResultSet resultSet) throws SQLException {
        WeeklyReportDetail weeklyReportDetail;
        weeklyReportDetail = new WeeklyReportDetail();
        weeklyReportDetail.setWeeklyNo(resultSet.getString("weekly_no"));
        weeklyReportDetail.setProductCategory(resultSet.getString("product_category"));
        weeklyReportDetail.setProductType(resultSet.getString("product_type"));
        weeklyReportDetail.setProductNo(resultSet.getString("product_no"));
//week_season_year	store_id	product_category	product_type	product_no	product_desc

        weeklyReportDetail.setWk1OriginalDemandValue(resultSet.getInt("wk1_original_demand_value"));
        weeklyReportDetail.setWk1Modify(resultSet.getInt("wk1_modify"));
        weeklyReportDetail.setWk1ActualDemandValue(resultSet.getInt("wk1_actual_demand_value"));
        weeklyReportDetail.setWk1Comment(resultSet.getString("wk1_comment"));

        //

        weeklyReportDetail.setWk2OriginalDemandValue(resultSet.getInt("wk2_original_demand_value"));
        weeklyReportDetail.setWk2Modify(resultSet.getInt("wk2_modify"));
        weeklyReportDetail.setWk2ActualDemandValue(resultSet.getInt("wk2_actual_demand_value"));
        weeklyReportDetail.setWk2Comment(resultSet.getString("wk2_comment"));

        weeklyReportDetail.setWk3OriginalDemandValue(resultSet.getInt("wk3_original_demand_value"));
        weeklyReportDetail.setWk3ActualDemandValue(resultSet.getInt("wk3_actual_demand_value"));
        weeklyReportDetail.setWk1FirstPlan(resultSet.getInt("wk1_first_plan"));
        weeklyReportDetail.setWk1SecondPlan(resultSet.getInt("wk1_second_plan"));
        weeklyReportDetail.setWk2FirstPlan(resultSet.getInt("wk2_first_plan"));

        weeklyReportDetail.setWeekSeasonYear(resultSet.getString("week_season_year"));
        weeklyReportDetail.setLineNo(resultSet.getString("line_no"));
        weeklyReportDetail.setStoreId(resultSet.getString("store_id"));
        return weeklyReportDetail;
    }

    public static void processUpdate(WeeklyReportDetail weeklyReportDetail) {
        Connection c = SqliteUtil.getConnect();
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = SqliteUtil.getConnect();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            dealWithWeeklyReportDetail(weeklyReportDetail);

            String sql = "UPDATE weekly_report_detail "
                + "SET wk1_original_demand_value = " + weeklyReportDetail.getWk1OriginalDemandValue() + ", "
                + "wk1_modify = " + weeklyReportDetail.getWk1Modify() + ", "
                + "wk1_actual_demand_value = " + weeklyReportDetail.getWk1ActualDemandValue() + ", "
                + "wk1_comment = '" + weeklyReportDetail.getWk1Comment() + "', "
                + "wk2_original_demand_value = " + weeklyReportDetail.getWk2OriginalDemandValue() + ", "
                + "wk2_modify = " + weeklyReportDetail.getWk2Modify() + ", "
                + "wk2_actual_demand_value = " + weeklyReportDetail.getWk2ActualDemandValue() + ", "
                + "wk2_comment= '" + weeklyReportDetail.getWk2Comment() + "', "
                + "wk3_original_demand_value = " + weeklyReportDetail.getWk3OriginalDemandValue() + ", "
                + "wk3_actual_demand_value = " + weeklyReportDetail.getWk3ActualDemandValue() + ", "
                + "wk1_first_plan = " + weeklyReportDetail.getWk1FirstPlan() + ", "
                + "wk1_second_plan = " + weeklyReportDetail.getWk1SecondPlan() + ", "
                + "wk2_first_plan = " + weeklyReportDetail.getWk2FirstPlan()
                + " WHERE line_no = '" + weeklyReportDetail.getLineNo() + "';";
            System.out.println("Update: \t" + sql);
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    private static void dealWithWeeklyReportDetail(WeeklyReportDetail weeklyReportDetail) {
        //
        weeklyReportDetail.setWk1ActualDemandValue(
            weeklyReportDetail.getWk1OriginalDemandValue() + weeklyReportDetail.getWk1Modify());
        weeklyReportDetail.setWk2ActualDemandValue(
            weeklyReportDetail.getWk2OriginalDemandValue() + weeklyReportDetail.getWk2Modify());
    }

    public static void processDelete() {
        Connection c = SqliteUtil.getConnect();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from COMPANY where ID=2;";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println("AGE = " + age);
                System.out.println("ADDRESS = " + address);
                System.out.println("SALARY = " + salary);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}

