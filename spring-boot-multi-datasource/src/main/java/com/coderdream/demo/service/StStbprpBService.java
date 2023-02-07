package com.coderdream.demo.service;

import com.coderdream.demo.pojo.StStbprpB;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MoBai·杰
 * @since 2023-01-08
 */
public interface StStbprpBService extends IService<StStbprpB> {

    /**
     * 导出文件
     */

    void exportAllTypeExcel(HttpServletResponse response, XSSFWorkbook workbook);
}
