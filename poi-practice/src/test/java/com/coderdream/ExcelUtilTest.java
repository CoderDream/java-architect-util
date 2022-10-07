package com.coderdream;

import com.coderdream.bean.User;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class ExcelUtilTest {

    @Test
    public void testImportExcel() throws Exception {
        // String filename = "D:\\DailyReport\\20210902\\User.xlsx";
        String filename = getClass().getResource("/").getPath() + "User.xlsx";
        InputStream is = new FileInputStream(new File(filename));
        ExcelUtil excelUtil = new ExcelUtil(User.class);
        List<User> list = excelUtil.importExcel(is);
        for (User user : list) {
            System.out.printf("User: " + user);
        }

//        POILogFactory p;
    }
}
