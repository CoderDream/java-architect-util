package com.coderdream.mybatisplusdemo;

import com.coderdream.mybatisplusdemo.mapper.UserMapper;
import com.coderdream.mybatisplusdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper; //这里可能爆红，但是运行没问题

    @Test
    public void testSelectList() {
        //selectList()根据MyBatisPlus内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        //实现新增用户信息
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail("zhangsan@163.com");
        int result = userMapper.insert(user); //result 受影响的行数
        System.out.println("result:" + result);
        System.out.println("id:" + user.getId());//获取一下id
    }

    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(1630077830633693186L); //加L表示 Long类型
        System.out.println("result:" + result);
    }

    @Test
    public void testDeleteByMap() {
        //根据map集合中所设置的条件，删除用户信息
        //DELETE FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三"); // 条件
        map.put("age", 23); // 条件
        int result = userMapper.deleteByMap(map);
        System.out.println("result:" + result);
    }

    @Test
    public void testDeleteBatchIds() {
        //通过多个id实现批量删除
        //DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> list = Arrays.asList(1L, 2L, 3L);//将数据直接转换为集合
        int result = userMapper.deleteBatchIds(list);
        System.out.println("result:" + result);
    }

    @Test
    public void testUpdateById() {
        //修改用户信息
        //UPDATE user SET name=?, email=? WHERE id=?
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("lisi@162.com");
        int result = userMapper.updateById(user);
        System.out.println("result:" + result);
    }

    @Test
    public void testSelectById() {
        //通过id查询用户信息
        //SELECT id,name,age,email FROM user WHERE id=?
        User user = userMapper.selectById(4L);
        System.out.println("user:" + user);
    }

    @Test
    public void testSelectBatchIds() {
        //根据多个id查询多个用户信息
        // SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        List<User> users = userMapper.selectBatchIds(list);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap() {
        //根据map集合中的条件查询用户信息
        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Jack");
        map.put("age", 20);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectList_02(){
        // 查询所有数据信息
        // SELECT id,name,age,email FROM user
        //selectList()根据MyBatisPlus内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        List<User> list = userMapper.selectList(null);// queryWrapper条件构造器，可以写null
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectByMyself(){
        //自定义用法
        //select id,name,age,email from user where id = ?
        Map<String,Object> map = userMapper.selectMapById(1L);
        System.out.println(map);
    }

}
