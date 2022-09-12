package com.coderdream.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coderdream.mybatisplusdemo.mapper.UserMapper;
import com.coderdream.mybatisplusdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() {
        //查询用户名包含a，年龄在20到30之间，邮箱信息不为null的用户信息
        //SELECT id,user_name AS name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", "a")
                .between("age", 20, 30)
                .isNotNull("email");//column 是数据库字段名
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test2() {
        //查询用户信息，按照年龄的降序排序，若年龄相同，则按照id升序排序
        // SELECT id,user_name AS name,age,email,is_deleted FROM user WHERE is_deleted=0 ORDER BY age DESC,id ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age") //降序排列
                .orderByAsc("id");//升序排列
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test3() {
        //删除邮箱地址为null的用户信息
        //UPDATE user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("result：" + result);
    }

    @Test
    public void test4() {
        // 将（年龄大于20或邮箱为null）并且用户名中包含有a的用户信息修改
        // UPDATE user SET user_name=?,email=? WHERE is_deleted=0 AND (user_name LIKE ? OR email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); //设置条件
        queryWrapper.gt("age", 19) //gt greater than 大于
                .like("user_name", "a") //因为 是并且关系 因此 直接写
                .or()// or 或
                .isNull("email");
        User user = new User();
        user.setName("小a");  // 修改的内容
        user.setEmail("xiaoA@163.com"); // 修改的内容
        int result = userMapper.update(user, queryWrapper); //第一个参数是实体内容，第二个参数是查询条件
        System.out.println("result：" + result);
    }

    @Test
    public void test42() {
        // 将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改  优先级发生变化  （年龄大于20或邮箱为null）优先判断
        //lambda表达式内的逻辑优先运算
        //UPDATE user SET user_name=?, email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); //设置条件
        queryWrapper.like("user_name", "a")
                .and(i -> i.gt("age", 19) // i ->   lambda表达式内的逻辑优先运算
                        .or().isNull("email"));
        User user = new User();
        user.setName("小a2");  // 修改的内容
        user.setEmail("xiaoA2@163.com"); // 修改的内容
        int result = userMapper.update(user, queryWrapper); //第一个参数是实体内容，第二个参数是查询条件
        System.out.println("result：" + result);
    }

    @Test
    public void test5() {
        //查询用户的用户名，年龄，邮箱信息
        //SELECT user_name,age,email FROM user WHERE is_deleted=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); //设置条件
        queryWrapper.select("user_name", "age", "email"); //按照设置只查询部分字段
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test6() {
        //查询id小于等于100的用户信息
        //SELECT id,user_name AS name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (id IN (select id from user where id <=100))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); //设置条件
        queryWrapper.inSql("id", "select id from user where id <=100");//column 是数据库字段名 String inValue 是in 比较的数据————SQL语句
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test7() {
        // 将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        //UPDATE user SET user_name=?,email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("user_name", "a")
                .and(i -> i.gt("age", 19).or().isNull("email"));
        updateWrapper.set("user_name", "updateWrapper修改").set("email", "abc@163.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result：" + result);
    }

    @Test
    public void test8() {
        //定义查询条件，有可能为null（用户未输入或未选择）
        String username = "a";
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //StringUtils.isNotBlank()判断某字符串是否不为空字符串且长度不为0且不由空白符(whitespace)构成
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("user_name", username); //如果不为空 则为"a"的模糊查询
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin); //ge greater than or equal 大于等于
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd); // less than or equal 小于等于
        }
        //SELECT id,user_name AS name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (user_name LIKE ? AND age >= ? AND age <= ?)
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test9() {
        //定义查询条件，有可能为null（用户未输入或未选择）
        String username = "a";
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //StringUtils.isNotBlank()判断某字符串是否不为空且长度不为0且不由空白符(whitespace)构成
        queryWrapper.like(StringUtils.isNotBlank(username), "user_name", username)
                .ge(ageBegin != null, "age", ageBegin)  // greater than or equal 大于等于
                .le(ageEnd != null, "age", ageEnd); // less than or equal 小于等于
        //SELECT id,user_name AS name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (user_name LIKE ? AND age >= ? AND age <= ?)
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test10() {
        //LambdaQueryWrapper
        String username = "a";
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        // SELECT id,user_name AS name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (user_name LIKE ? AND age >= ? AND age <= ?)
        //selectList方法放的参数是最顶级的抽象类————Wrapper，各子类都可以进行赋值
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test11() {
        // 将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        //LambdaUpdateWrapper 参数写的是函数接口
        //UPDATE user SET user_name=?,email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getName, "a")
                .and(i -> i.gt(User::getAge, 19).or().isNull(User::getEmail));
        updateWrapper.set(User::getName, "LambdaUpdateWrapper修改").set(User::getEmail, "abc改@163.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result：" + result);
    }
}
