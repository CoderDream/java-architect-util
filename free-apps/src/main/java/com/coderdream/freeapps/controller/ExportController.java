package com.coderdream.freeapps.controller;

import com.coderdream.freeapps.common.entity.Result;
import com.coderdream.freeapps.dto.UserRequest;
import com.coderdream.freeapps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/23 10:02 下午
 */
@RestController
@RequiredArgsConstructor
public class ExportController {

    private final UserService userService;

    @PostMapping("/export")
    public Result<Object> register(@RequestBody UserRequest userRequest) {
//        File zipFile = CdExcelUtils.zipFile(workBook, xworkWork, fileNames);
//        String downloadFileName = URLEncoder.encode("XX商户XX公司XXXX报税单销售汇总信息.zip", "UTF-8");
//        response.setHeader("content-type", "application/octet-stream");
//        response.setContentType("application/x-execl");
//        response.setHeader("Content-Disposition",
//            "attachment;filename=" + new String((downloadFileName).getBytes(), "UTF-8"));
//        // 读取文件
//        InputStream in = new FileInputStream(zipFile);
//        ServletOutputStream outputStream = response.getOutputStream();
//        // 写文件
//        int b;
//        while ((b = in.read()) != -1) {
//            outputStream.write(b);
//        }
//        in.close();
//        outputStream.close();
//        //删除临时文件
//        zipFile.delete();


        return null;
    }

}
