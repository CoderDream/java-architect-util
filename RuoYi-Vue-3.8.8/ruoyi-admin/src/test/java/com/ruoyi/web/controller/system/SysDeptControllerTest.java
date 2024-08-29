package com.ruoyi.web.controller.system;

import static com.sun.jna.platform.mac.IOReturnException.getCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

class SysDeptControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private RedisCache redisCache;
//    唯一标识
    private String uuid = IdUtils.simpleUUID();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
//        mockMvc = MockMvcBuilders
//            .webAppContextSetup(webApplicationContext)
//            .apply(springSecurity()) //这里是导入的静态类中的方法，该方法能模拟springSecurityFilterChain请求
//            .build();

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void list() {
    }

    @org.junit.jupiter.api.Test
    void excludeChild() {
    }

//    @org.junit.jupiter.api.Test
//    void getInfo() {
//    }

    @org.junit.jupiter.api.Test
    void add() {
    }

    @org.junit.jupiter.api.Test
    void edit() {
    }

    @org.junit.jupiter.api.Test
    void remove() {
    }

//    @Test
//    public void loginTest() throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/login")
//                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .param("username","admin")
//                .param("password","admin123")
//                .param("code",getCode())
//                .param("uuid",uuid))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andDo(MockMvcResultHandlers.print());
//    }

//    @Test
//    public void getInfo() throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/system/dept/{deptId}",100)
//                // 设置返回值类型为utf-8，否则默认为ISO-8859-1
//                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .param("deptId", "100"))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            //.andExpect(MockMvcResultMatchers.content().string("Hello Tom!"))
//            .andDo(MockMvcResultHandlers.print());
//    }

}
