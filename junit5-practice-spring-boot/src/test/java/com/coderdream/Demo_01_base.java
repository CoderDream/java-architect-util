package com.coderdream;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

/**
 * Created by CaiQingYuan on 2021/4/5 17:01
 */
public class Demo_01_base {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);

//    static String accessToken;
//    static int departmentId;

//    @BeforeAll
//    public static void getAccessToken() {
//        accessToken = given().log().all()
//                .when()
//                .param("corpid", "*******")
//                .param("corpsecret", "******")
//                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
//                .then().log().all()
//                .extract().response().path("access_token");
//        logger.info(accessToken);
//    }
//
//    @DisplayName("创建部门")
//    @Test
//    void createDepartment() {
//        String body = "{\n" +
//                "\"name\": \"广州研发中心002\",\n" +
//                "   \"name_en\": \"RDGZ0002\",\n" +
//                "   \"parentid\": 42,\n" +
//                "   \"order\": 1,\n" +
//                "   \"id\": 1006" +
//                "}\n";
//        Response response = given().log().all()
//                .contentType("application/json")
//                .body(body)
//                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
//                .then().log().all()
//                .extract()
//                .response();
//        departmentId = response.path("id");
//        logger.info(accessToken);
//        logger.info("" + departmentId);
//    }

    @DisplayName("获取验证码")
    @Test
    void testCaptchaImage() {

      //  new org.junit.jupiter.api.extension.ScriptEvaluationException();
        Response response = given().log().all()
                .contentType("application/json")
                .get("http://172.16.104.61:30572/api/data/sys/captchaImage")
                .then().log().all()
                .extract()
                .response();
//        departmentId = response.path("id");
//        logger.info(accessToken);
//        logger.info("" + departmentId);
    }


}
