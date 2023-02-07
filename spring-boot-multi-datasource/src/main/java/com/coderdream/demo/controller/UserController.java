package com.coderdream.demo.controller;


import com.coderdream.demo.service.StStbprpBService;
import com.coderdream.demo.service.UserService;
import com.coderdream.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author MoBai·杰
 * @since 2023-01-08
 */
@Slf4j
@Api(tags = "用户")
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/allUser")
    @ApiOperation(value = "导出所有的表的数据")
    public Result<Map<String, Object>> exportAllUser(HttpServletResponse response) throws Exception {
        System.out.println("Call @@@@");
        Map<String, Object> result = new LinkedHashMap<>();

        List<Map<String, Object>> listA = userService.selectAll();
        for (Map<String, Object> map : listA) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String mapKey = entry.getKey();
                Object mapValue = entry.getValue();
                System.out.println("Data from MySQL: " + mapKey + "：" + mapValue);
            }
        }
        System.out.println();
        result.put("mysql", listA);
        List<Map<String, Object>> listB = userService.selectByCondition();
        for (Map<String, Object> map : listB) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String mapKey = entry.getKey();
                Object mapValue = entry.getValue();
                System.out.println("Data from Oracle: " + mapKey + "：" + mapValue);
            }
        }
        result.put("oracle", listB);
        return new Result<>(result);
    }

}

