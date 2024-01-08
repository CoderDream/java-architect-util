package com.coderdream.freeapps.util.sqliteutil;

import cn.hutool.core.collection.CollectionUtil;
import com.coderdream.freeapps.util.mf.DailyReportDetail;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * @author CoderDream
 */
public class SqliteDailyReportUtil {

    public static String DB_URL = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\resources\\data\\dict\\db\\dict.db";

    public static void main(String[] args) {

//        String fileName = BbcConstants.ROOT_FOLDER_NAME + "all_title_cn.txt";
//        List<String> strings = FileUtil.readLines(fileName, "UTF-8");
//
//        List<ArticleTitleEntity> articleTitleEntityList = new ArrayList<>();
//        ArticleTitleEntity articleTitleEntity;
//        for (String str : strings) {
////            System.out.println(str);
//            String[] arr = str.split("\t");
//            if (arr.length == 3) {
//                String[] arr2 = arr[1].split("\\|");
//                String[] arr3 = arr[2].split("\\|");
//
//                if (arr2.length == 2 && arr3.length == 2) {
//
//                    articleTitleEntity = new ArticleTitleEntity();
//
//                    articleTitleEntity.setArticleDate(arr[0]);
//                    articleTitleEntity.setTitleEn(arr2[0]);
//                    articleTitleEntity.setTitleCn(arr2[1]);
//                    articleTitleEntity.setDescEn(arr3[0]);
//                    articleTitleEntity.setDescCn(arr3[1]);
//
////                    System.out.println(articleTitleEntity);
//                    articleTitleEntityList.add(articleTitleEntity);
//                }
//            }
//        }
//        processInsert(articleTitleEntityList);
//        System.out.println(SqliteTitleUtil.processSelect("220303"));

//        List<String> list = Arrays.asList("220303", "220310");
//        List<Integer> list = Arrays.asList(220303, 220310);

//        System.out.println(SqliteTitleUtil.processSelectByString(list));

//        processInsert();

        // RB_00000762
//        List<String> dailyNoList = new ArrayList<>();
//        dailyNoList.add("RB_00000762");
//        dailyNoList.add("RB_00000763");
//        List<DailyReportDetail> dailyReportDetailList = processSelectByString(dailyNoList);
//        for (DailyReportDetail dailyReportDetail : dailyReportDetailList) {
//            System.out.println(dailyReportDetail);
//            dailyReportDetail.setInitAmount(100);
//
//            processUpdate(dailyReportDetail);
//        }
        String dailyReportDate = "2023-12-01";
        Map<String, Integer> stringIntegerMap = SqliteDailyReportUtil.processSelectMapByDate(dailyReportDate);
        for (Map.Entry<String, Integer> entry: stringIntegerMap.entrySet()) {
            System.out.println(entry.getKey() + ":\t" + entry.getValue());
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
     *
     * @param dailyReportDate
     * @return key 日期+门店ID+部件号 value 期末值
     */
    public static Map<String, Integer> processSelectMapByDate(String dailyReportDate) {
        Connection c = getConnect();
        Statement stmt = null;
        Map<String, Integer> map = new LinkedHashMap<>(); // key 日期+门店ID+部件号 value 期末值
        try {
            stmt = c.createStatement();
            String sql = "SELECT t2.store_id,t1.product_no,t1.end_amount "
                + "FROM daily_report_detail t1, daily_report t2 "
                + "WHERE t1.daily_no=t2.daily_no and t2.daily_report_date='" + dailyReportDate + "';";
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
                key = dailyReportDate + storeId + productNo;
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
    public static List<DailyReportDetail> processSelectByString(List<String> dailyNoList) {
        Connection c = getConnect();
        Statement stmt = null;
        String articleDate = "";
        String titleCn = "";
        Map<String, String> map = new LinkedHashMap<>();
        List<DailyReportDetail> dailyReportDetailList = new ArrayList<>();
        DailyReportDetail dailyReportDetail;
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
                dailyReportDetail = new DailyReportDetail();
                dailyReportDetail.setDailyNo(resultSet.getString("daily_no"));
                dailyReportDetail.setProductCategory(resultSet.getString("product_category"));
                dailyReportDetail.setProductType(resultSet.getString("product_type"));
                dailyReportDetail.setProductNo(resultSet.getString("product_no"));
                dailyReportDetail.setDesc(resultSet.getString("desc"));
                dailyReportDetail.setInitAmount(resultSet.getInt("init_amount"));
                dailyReportDetail.setIncomeAmount(resultSet.getInt("income_amount"));
                dailyReportDetail.setOnlineSale(resultSet.getInt("online_sale"));
                dailyReportDetail.setOfflineSale(resultSet.getInt("offline_sale"));
                dailyReportDetail.setSaleDiff(resultSet.getInt("sale_diff"));
                dailyReportDetail.setEndAmount(resultSet.getInt("end_amount"));
                dailyReportDetail.setLineComment(resultSet.getString("line_comment"));
                dailyReportDetail.setLineNo(resultSet.getString("line_no"));

                dailyReportDetailList.add(dailyReportDetail);
            }

            resultSet.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return dailyReportDetailList;
    }

    /**
     * @param dailyReportDate
     * @return
     */
    public static List<DailyReportDetail> processSelectListByString(String dailyReportDate) {
        Connection c = getConnect();
        Statement stmt;
        List<DailyReportDetail> dailyReportDetailList = new ArrayList<>();
        DailyReportDetail dailyReportDetail;
        try {
            stmt = c.createStatement();
            String sql = "SELECT t1.*,t2.store_id "
                + "FROM daily_report_detail t1, daily_report t2 "
                + "WHERE t1.daily_no=t2.daily_no and t2.daily_report_date='" + dailyReportDate + "';";

            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dailyReportDetail = new DailyReportDetail();
                dailyReportDetail.setDailyNo(resultSet.getString("daily_no"));
                dailyReportDetail.setProductCategory(resultSet.getString("product_category"));
                dailyReportDetail.setProductType(resultSet.getString("product_type"));
                dailyReportDetail.setProductNo(resultSet.getString("product_no"));
                dailyReportDetail.setDesc(resultSet.getString("desc"));
                dailyReportDetail.setInitAmount(resultSet.getInt("init_amount"));
                dailyReportDetail.setIncomeAmount(resultSet.getInt("income_amount"));
                dailyReportDetail.setOnlineSale(resultSet.getInt("online_sale"));
                dailyReportDetail.setOfflineSale(resultSet.getInt("offline_sale"));
                dailyReportDetail.setSaleDiff(resultSet.getInt("sale_diff"));
                dailyReportDetail.setEndAmount(resultSet.getInt("end_amount"));
                dailyReportDetail.setLineComment(resultSet.getString("line_comment"));
                dailyReportDetail.setLineNo(resultSet.getString("line_no"));
                dailyReportDetail.setStoreId(resultSet.getString("store_id"));

                dailyReportDetailList.add(dailyReportDetail);
            }

            resultSet.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return dailyReportDetailList;
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

    public static void processUpdate(DailyReportDetail dailyReportDetail) {
        Connection c = getConnect();
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = getConnect();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            dailyReportDetail.setEndAmount(dailyReportDetail.getInitAmount() + dailyReportDetail.getIncomeAmount()
                - dailyReportDetail.getOnlineSale() - dailyReportDetail.getOfflineSale()
                + dailyReportDetail.getSaleDiff());
            String sql =
                "UPDATE daily_report_detail set init_amount = " + dailyReportDetail.getInitAmount() + " ,end_amount="
                    + dailyReportDetail.getEndAmount() + "  where line_no="
                    + dailyReportDetail.getLineNo();
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

