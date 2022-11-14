package com.zhy.easyexceldemo.rest;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.fastjson.JSON;
import com.zhy.easyexceldemo.common.BaseRest;
import com.zhy.easyexceldemo.common.Result;
import com.zhy.easyexceldemo.dto.UserExcelDto;
import com.zhy.easyexceldemo.dto.UserExcelErrDto;
import com.zhy.easyexceldemo.easyexcel.EasyExcelListener;
import com.zhy.easyexceldemo.easyexcel.EasyExcelUtils;
import com.zhy.easyexceldemo.easyexcel.ExcelCheckErrDto;
import com.zhy.easyexceldemo.pojo.User;
import com.zhy.easyexceldemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhy
 * @title: UserRest
 * @projectName easyexceldemo
 * @description: 用户rest
 * @date 2020/1/1611:30
 */
@RestController
@RequestMapping("/user")
public class UserRest extends BaseRest {


    @Value("${upload.linuxPath}")
    private String uploadPath;

    @Value("${upload.windowsPath}")
    private String uploadWindowsPath;


    @Value("${file.location}")
    private String fileLocation;

    @Resource
    private UserService userService;

    /**
     * @param response
     * @return void
     * @throws
     * @description: 导出测试
     * @author zhy
     * @date 2020/1/16 11:58
     */
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setName("张三");
        user1.setAge(10);
        user1.setBirthday(new Date());
        user1.setSex("男");
        userList.add(user1);
        List<UserExcelDto> userExcelDtos = JSON.parseArray(JSON.toJSONString(userList), UserExcelDto.class);
        EasyExcelUtils.webWriteExcel(response, userExcelDtos, UserExcelDto.class, "用户基本信息");
    }

    /**
     * @param response
     * @param file
     * @return com.zhy.easyexceldemo.common.Result
     * @throws
     * @description: 导入测试
     * @author zhy
     * @date 2020/1/16 11:59
     */
    @PostMapping("/importExcel")
    public Result importExcel(HttpServletResponse response, @RequestParam MultipartFile file) throws IOException {
        if (isWindows()) {
            uploadPath = uploadWindowsPath;
        }

        //上传目录地址
        String uploadDir = uploadPath + File.separatorChar;


        EasyExcelListener easyExcelListener = new EasyExcelListener(userService, UserExcelDto.class);
        EasyExcelFactory.read(file.getInputStream(), UserExcelDto.class, easyExcelListener).sheet().doRead();
        List<ExcelCheckErrDto<UserExcelDto>> errList = easyExcelListener.getErrList();
        if (!errList.isEmpty()) {//如果包含错误信息就导出错误信息
            List<UserExcelErrDto> excelErrDtos = errList.stream().map(excelCheckErrDto -> {
                UserExcelErrDto userExcelErrDto = JSON.parseObject(JSON.toJSONString(excelCheckErrDto.getT()), UserExcelErrDto.class);
                userExcelErrDto.setErrMsg(excelCheckErrDto.getErrMsg());
                return userExcelErrDto;
            }).collect(Collectors.toList());
            if(fileLocation.equals("web")) {
                EasyExcelUtils.webWriteExcel(response, excelErrDtos, UserExcelErrDto.class, "用户导入错误信息");
            } else {
                EasyExcelUtils.localWriteExcel(uploadDir, excelErrDtos, UserExcelErrDto.class, "用户导入错误信息","用户导入错误信息");
            }
        }
        return addSucResult();
    }

    public static boolean isWindows() {
        return System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS");
    }

}
