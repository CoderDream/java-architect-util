package com.coderdream.freeapps.util.sqliteutil;

import cn.hutool.core.collection.CollectionUtil;
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
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * @author CoderDream
 */
public class SqliteWeekCalendarUtil {

    public static String DB_URL = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\resources\\data\\dict\\db\\dict.db";

    public static void main(String[] args) {
//        String weekSeasonYear = "FY23Q4W06";
//        List<WeeklyReportDetail> weeklyReportDetails = processSelectListByString(weekSeasonYear);
//        for (WeeklyReportDetail weeklyReportDetail : weeklyReportDetails) {
//            System.out.println(weeklyReportDetail);
//        }

        Map<String, String> yearSeasonWeekEndDateMap = SqliteWeekCalendarUtil.getYearSeasonWeekEndDateMap();
        for (Map.Entry<String, String> entry : yearSeasonWeekEndDateMap.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }

    }

//    public static void testConnect(){
//        Connection c = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:" + DB_URL);
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
//        }
//        System.out.println("Opened database successfully");
//    }

    public static Connection getConnect() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + DB_URL);
            c.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

    public static void createTable() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE COMPANY " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " AGE            INT     NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static void processSelect() {
        Connection c = getConnect();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM daily_report_detail;");
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

    public static String processSelect(String articleDate) {
        Connection c = getConnect();
        Statement stmt = null;
        String titleCn = "";
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM daily_report_detail WHERE article_date='" + articleDate + "';");

            while (rs.next()) {
                titleCn = rs.getString("title_cn");
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return titleCn;
    }


    public static Map<String, String> getYearSeasonWeekEndDateMap() {
        Connection c = getConnect();
        Statement stmt = null;
        String yearSeasonWeek = "";
        String endDate = "";
        Map<String, String> map = new LinkedHashMap<>();
        try {
            stmt = c.createStatement();

            String sql = "SELECT t1.year_season_week, t1.end_date FROM week_calendar t1";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                yearSeasonWeek = resultSet.getString("year_season_week");
                endDate = resultSet.getString("end_date");
                System.out.println(yearSeasonWeek + ":\t" + endDate);
                map.put(yearSeasonWeek, endDate);
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

    public static Map<String, String> getEndDateYearSeasonWeekMap() {
        Connection c = getConnect();
        Statement stmt = null;
        String yearSeasonWeek = "";
        String endDate = "";
        Map<String, String> map = new LinkedHashMap<>();
        try {
            stmt = c.createStatement();
            String sql = "SELECT t1.year_season_week, t1.end_date FROM week_calendar t1";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                yearSeasonWeek = resultSet.getString("year_season_week");
                endDate = resultSet.getString("end_date");
                System.out.println(yearSeasonWeek + ":\t" + endDate);
                map.put(endDate, yearSeasonWeek);
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

    public static Map<String, String> processSelectByInteger(List<Integer> articleDateList) {
        Connection c = getConnect();
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
        Connection c = getConnect();
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
        Connection c = getConnect();
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
     * @param weeklyReportDate
     * @return
     */
    public static List<WeeklyReportDetail> processSelectListByString(String weekSeasonYear) {
        Connection c = getConnect();
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


    public static void processInsert(List<ArticleTitleEntity> articleTitleEntityList) {
        Connection c = getConnect();
        Statement stmt = null;
        String articleDate = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + DB_URL);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "";

            if (CollectionUtil.isNotEmpty(articleTitleEntityList)) {
                for (ArticleTitleEntity articleTitleEntity : articleTitleEntityList) {
                    sql = "INSERT INTO daily_report_detail (article_date,title_en,title_cn,desc_en,desc_cn) " +
                        "VALUES (?,?,?,?,?);";
                    System.out.println(sql);
//                    stmt.executeUpdate(sql);
//                    stmt.p

//                    String sql2 = "SELECT * FROM user_login WHERE user_password=? AND (user_name=? OR user_telNumber=?)";
                    PreparedStatement preparedStatement = c.prepareStatement(sql);
                    articleDate = articleTitleEntity.getArticleDate();
                    preparedStatement.setString(1, articleTitleEntity.getArticleDate());
                    preparedStatement.setString(2, articleTitleEntity.getTitleEn());
                    preparedStatement.setString(3, articleTitleEntity.getTitleCn());
                    preparedStatement.setString(4, articleTitleEntity.getDescEn());
                    preparedStatement.setString(5, articleTitleEntity.getDescCn());
                    boolean execute = preparedStatement.execute();
                    System.out.println(execute);
                }
            }
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage() + ":\t" + articleDate);
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public static void processInsert() {
        Connection c = getConnect();
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + DB_URL);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public static void processUpdate(WeeklyReportDetail weeklyReportDetail) {
        Connection c = getConnect();
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = getConnect();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
//            weeklyReportDetail.setEndAmount(weeklyReportDetail.getInitAmount() + weeklyReportDetail.getIncomeAmount()
//                - weeklyReportDetail.getOnlineSale() - weeklyReportDetail.getOfflineSale()
//                + weeklyReportDetail.getSaleDiff());
            String sql = "";
//                "UPDATE daily_report_detail set init_amount = " + weeklyReportDetail.getInitAmount() + " ,end_amount="
//                    + weeklyReportDetail.getEndAmount() + "  where line_no="
//                    + weeklyReportDetail.getLineNo();
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

    public static void processDelete() {
        Connection c = getConnect();
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

