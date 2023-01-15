package com.coderdream.demo.controller;


import com.coderdream.demo.service.StStbprpBService;
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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MoBai·杰
 * @since 2023-01-08
 */
@Slf4j
@Api(tags = "对象类型元数据操作")
@RestController
@RequestMapping("cxdz/st-stbprp-b")
public class StStbprpBController {

    @Resource
    private StStbprpBService stStbprpBService;

    @PostMapping(value = "/exportAllTypeExcel")
    @ApiOperation(value = "导出所有的表的数据")
    public void exportAllTypeExcel(HttpServletResponse response) throws Exception {
        System.out.println("Call @@@@");
        //获取文件
        InputStream f = this.getClass().getResourceAsStream("/templates/对象结构导入模板.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(f);
        stStbprpBService.exportAllTypeExcel(response, workbook);
    }

}

