package com.coderdream.freeapps.util.demo01;

/*
 * author:合肥工业大学 管院学院 钱洋
 *1563178220@qq.com
 */

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MtimeThread extends Thread {

//    public static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://coderme.myds.me:33016/moviedata");
    public static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://coderme.myds.me:33016/free_apps");
    public static QueryRunner qr = new QueryRunner(ds);
    String startUrl = "";
    String Id = "";

    //构造函数，初始化使用
    public MtimeThread(String startUrl, String Id) {
        if (StringUtils.isNotEmpty(startUrl)) {
            this.startUrl = startUrl;
        } else {
            //this.startUrl = "http://movie.mtime.com/" + Id;
            this.startUrl = "https://apps.apple.com/cn/app/"+ Id+"?platform=iphone";
        }
        this.Id = Id;
    }

    public void run() {
        List<MtimeModel> moviedatas = new ArrayList<MtimeModel>();
        //这里采用jsoup直接模拟访问网页
        try {
            Document doc = Jsoup.connect(startUrl).userAgent("bbb").timeout(120000).get();
            moviedatas = MtimeParse.getData(doc);

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (MtimeModel mt : moviedatas) {
            System.out.println(
                "prmovieId:" + mt.getPrmovieId() + "  movieId:" + mt.getMovieId() + "  Title:" + mt.getTitle()
                    + "   url:" + mt.getUrl());
        }
        try {
            MYSQLControl.executeUpdate(moviedatas, Id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * https://blog.csdn.net/qy20115549/article/details/52648631
     * Java多线程网络爬虫(时光网为例)
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        ResultSetHandler<List<MtimeUrl>> h = new BeanListHandler<MtimeUrl>(MtimeUrl.class);
//        List<MtimeUrl> Starturls = qr.query("SELECT id,url FROM movie WHERE website='时光网' and is_crawler=0", h);
        String sql = "SELECT ANY_VALUE (tt.app_id) AS id,ANY_VALUE (tt.title) AS 应用名称,ANY_VALUE (tt.rate_amount) AS 投票数,ANY_VALUE (tt.us_flag) AS usFlag,ANY_VALUE (tt.cn_flag) AS 中文标志,ANY_VALUE (tt.description_cn) AS 中文简介,ANY_VALUE (tt.description_cl) AS 简介,ANY_VALUE (tt.description_zm) AS 字幕,ANY_VALUE (tt.yesterday_price) AS 历史价格,ANY_VALUE (tt.today_price) AS 今日价格 FROM (SELECT t1.app_id,t1.title,t0.rate_amount,t0.us_flag,t0.cn_flag,t0.description_cn,td.description_us,td.description_cl,td.description_my,td.description_zm,t1.price AS yesterday_price,t2.price_str AS today_price FROM t_price_history t1 INNER JOIN t_price_history t2 LEFT JOIN t_description td ON t1.app_id=td.app_id LEFT JOIN t_app t0 ON t0.app_id=t1.app_id WHERE TO_DAYS(NOW())-TO_DAYS(t1.crawler_date)=1 AND to_days(t2.crawler_date)=to_days(NOW()) AND t1.price_str<> t2.price_str AND t1.app_id=t2.app_id AND (t1.price_str<> 'Free' AND t1.price_str<> '免费') AND (t2.price_str='Free' OR t2.price_str='免费')) AS tt ORDER BY tt.rate_amount DESC,tt.yesterday_price DESC;";

        sql = "SELECT t0.app_id AS id,t0.us_flag AS usFlag FROM t_price_history t2 LEFT JOIN t_app t0 ON t0.app_id=t2.app_id WHERE to_days(t2.crawler_date)=to_days(NOW()) AND (t2.price_str='Free' OR t2.price_str='免费')";
//        List<MtimeUrl> Starturls = qr.query("SELECT id FROM t_movie2 WHERE is_crawler=0", h);
        List<MtimeUrl> Starturls = qr.query(sql, h);
        //创建固定大小的线程池
        ExecutorService exec = Executors.newFixedThreadPool(5);
        String id;
        String url;
        String usFlag;
        for (MtimeUrl mtimeUrl : Starturls) {
            id = mtimeUrl.getId();
            url = mtimeUrl.getUrl();
            usFlag = mtimeUrl.getUsFlag();
            if (StringUtils.isEmpty(url)) {
                if(usFlag.equals("1")) {
                    url =  "https://apps.apple.com/us/app/"+ id+"?platform=iphone";
                } else {
                    url =  "https://apps.apple.com/cn/app/"+ id+"?platform=iphone";
                }
            }

            //执行线程
            exec.execute(new MtimeThread(url, mtimeUrl.getId()));
        }
        //线程关闭
        exec.shutdown();
    }
}

