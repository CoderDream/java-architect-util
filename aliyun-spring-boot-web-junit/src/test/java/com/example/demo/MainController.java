package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//spring测试类
@RunWith(SpringRunner.class)
@SpringBootTest
public class MainController {

    @Autowired
    private WebApplicationContext webApplicationContext;

    //SpringMVC单元的独立测试类
    private MockMvc mockMvc;

    @Before
    public void before() {
        //创建独立测试类
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //模拟客户端网址发送请求，查询user
    //@Test
    public void test() throws Exception {
        //发起一个Get请求
        String str = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        //传入参数
                        .param("username", "xing")
                        //json形式发送请求
                        .contentType(MediaType.APPLICATION_JSON))
                //期待服务器返回什么 期待返回状态码为200
                .andExpect(MockMvcResultMatchers.status().isOk())
                //期待服务器返回json中数组长度为3
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        System.out.println(str);

        //网页地址/user/1不存在，则返回404
    }

    //模拟客户端，发送请求，匹配用户信息
    //@Test
    public void getInfo() throws Exception {
        //发起一个Get请求
        String str = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                        //json形式发送请求
                        .contentType(MediaType.APPLICATION_JSON))
                //期待服务器返回什么 期待返回状态码为200
                .andExpect(MockMvcResultMatchers.status().isOk())
                //期待服务器返回json的username值为xing
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("xing"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(str);
    }

    //模拟客户端，发送添加用户请求
    //@Test
    public void addUser() throws Exception {

        //发起一个post请求
        mockMvc.perform(MockMvcRequestBuilders.post("/user/1")
                        //json形式发送请求
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"xing\"}"))
                //期待服务器返回什么 期待返回状态码为200
                .andExpect(MockMvcResultMatchers.status().isOk())
                //期待服务器返回json值的id为1
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));

    }

    //模拟客户端，发送修改用户请求  put
    //@Test
    public void updateUser() throws Exception {

        //发起一个put请求
        mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                        //json形式发送请求
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"xing\",\"id\":\"1\"}"))
                //期待服务器返回什么 期待返回状态码为200
                .andExpect(MockMvcResultMatchers.status().isOk())
                //期待服务器返回json值的id为1
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));

    }

    //模拟客户端，发送删除用户请求
    @Test
    public void deleteUser() throws Exception {

        //发起一个delete请求
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
                        //json形式发送请求
                        .contentType(MediaType.APPLICATION_JSON))
                //期待服务器返回什么 期待返回状态码为200
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
