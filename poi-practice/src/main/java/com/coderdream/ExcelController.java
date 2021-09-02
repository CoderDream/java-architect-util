package com.coderdream;

import com.coderdream.bean.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/9/2 17:29
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
public class ExcelController {

   // @GetMapping("export")
    public void export(HttpServletResponse response) throws IOException {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setName("张三");
        user1.setSex(1);
        User user2 = new User();
        user2.setId(2);
        user2.setName("李四");
        user2.setSex(2);

        // list数据由数据库查询出来 这里模仿查询出来的数据
        userList.add(user1);
        userList.add(user2);

        // 核心方法,将list数据导出
        ExcelUtil<User> util = new ExcelUtil<>(User.class);
        util.exportExcel(response,userList,"测试");

    }

//    public String importData(MultipartFile file) throws Exception {
//        ExcelUtil<User> util = new ExcelUtil<>(User.class);
//        List<User> userList = util.importExcel(file.getInputStream());
//        System.out.println(userList);
//        return "成功导入" + userList.size() + "条数据";
//    }
}
