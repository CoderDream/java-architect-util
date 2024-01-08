package com.coderdream.freeapps.util.sqliteutil;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import com.coderdream.freeapps.util.bbc.BbcConstants;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * @author CoderDream
 */
public class SqliteTitleUtil {

    public static String DB_URL = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\resources\\data\\dict\\db\\dict.db";

    public static void main(String[] args) {

        String fileName = BbcConstants.ROOT_FOLDER_NAME + "all_title_cn.txt";
        List<String> strings = FileUtil.readLines(fileName, "UTF-8");

        List<ArticleTitleEntity> articleTitleEntityList = new ArrayList<>();
        ArticleTitleEntity articleTitleEntity;
        for (String str : strings) {
//            System.out.println(str);
            String[] arr = str.split("\t");
            if (arr.length == 3) {
                String[] arr2 = arr[1].split("\\|");
                String[] arr3 = arr[2].split("\\|");

                if (arr2.length == 2 && arr3.length == 2) {

                    articleTitleEntity = new ArticleTitleEntity();

                    articleTitleEntity.setArticleDate(arr[0]);
                    articleTitleEntity.setTitleEn(arr2[0]);
                    articleTitleEntity.setTitleCn(arr2[1]);
                    articleTitleEntity.setDescEn(arr3[0]);
                    articleTitleEntity.setDescCn(arr3[1]);

//                    System.out.println(articleTitleEntity);
                    articleTitleEntityList.add(articleTitleEntity);
                }
            }
        }
        processInsert(articleTitleEntityList);
//        System.out.println(SqliteTitleUtil.processSelect("220303"));

//        List<String> list = Arrays.asList("220303", "220310");
//        List<Integer> list = Arrays.asList(220303, 220310);

//        System.out.println(SqliteTitleUtil.processSelectByString(list));

//        processInsert();
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM article_title;");
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM article_title WHERE article_date='" + articleDate + "';");

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
//            String sql = "SELECT * FROM article_title WHERE article_date IN (?)";
//            String sql = "SELECT * FROM article_title WHERE article_date IN (220303,220310)";

            String params = StringUtils.join(articleDateList, ",");
//            String[] arr = (String[])articleDateList.toArray();
//            Array a =
//            preparedStatement.setArray(1, arr);
            System.out.println("params: " + params);
//            preparedStatement.setString(1, params);
//            preparedStatement.setString(1, "'220303','220310'");

            String sql = "SELECT * FROM article_title WHERE article_date IN (" + params + ")";
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

    public static Map<String, String> processSelectByString(List<String> articleDateList) {
        Connection c = getConnect();
        Statement stmt = null;
        String articleDate = "";
        String titleCn = "";
        Map<String, String> map = new LinkedHashMap<>();
        try {
            stmt = c.createStatement();
//            String sql = "SELECT * FROM article_title WHERE article_date IN (?)";
//            String sql = "SELECT * FROM article_title WHERE article_date IN (220303,220310)";

            String params = StringUtils.join(articleDateList, ",");
//            String[] arr = (String[])articleDateList.toArray();
//            Array a =
//            preparedStatement.setArray(1, arr);
            System.out.println("params: " + params);
//            preparedStatement.setString(1, params);
//            preparedStatement.setString(1, "'220303','220310'");

            String sql = "SELECT * FROM article_title WHERE article_date IN (" + params + ")";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                articleDate = resultSet.getString("article_date");
                titleCn = resultSet.getString("title_cn");
                System.out.println(articleDate + ":\t" + titleCn);
                map.put(articleDate, titleCn);


//                daily_no
//                    product_category
//                product_type
//                    product_no
//                desc
//                    init_amount
//                income_amount
//                    online_sale
//                offline_sale
//                    sale_diff
//                end_amount
//                    line_comment
//                line_no

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
                    sql = "INSERT INTO article_title (article_date,title_en,title_cn,desc_en,desc_cn) " +
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

    public static void processUpdate() {
        Connection c = getConnect();
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
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

