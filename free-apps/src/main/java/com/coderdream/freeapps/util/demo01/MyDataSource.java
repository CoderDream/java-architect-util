package com.coderdream.freeapps.util.demo01;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
/*
 * author:合肥工业大学 管院学院 钱洋
 *1563178220@qq.com
 */
public class MyDataSource {

    public static DataSource getDataSource(String connectURI){

        BasicDataSource ds = new BasicDataSource();
        //MySQL的jdbc驱动
//        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");              //所要连接的数据库名
        ds.setPassword("123456");                //MySQL的登陆密码
        ds.setUrl(connectURI);

        return ds;

    }

}
