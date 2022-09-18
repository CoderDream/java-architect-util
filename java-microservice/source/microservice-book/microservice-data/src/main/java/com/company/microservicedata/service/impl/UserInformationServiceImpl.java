package com.company.microservicedata.service.impl;

import com.company.microservicedata.service.IUserInformationService;
import com.company.microservicedata.util.ExcelProcessUtil;
import com.company.microservicedata.vo.UserDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import com.company.microservicedata.annotation.MethodCostTimeStatisticAnnotation;


/**
 * User模块数据处理实现
 * @author xindaqi
 * @since 2020-12-20
 */
@Service
public class UserInformationServiceImpl implements IUserInformationService {

    @Autowired
    private ExcelProcessUtil excelProcessUtil;

    @Override
    public String downloadExcel(HttpServletRequest request) {
        String savePath = "/Users/xindaqi/xinPrj/java/webStudy/microservice-book/microservice-data/src/main/resources/template/";
        String fileName = "下载-Excel.xlsx";
        excelProcessUtil.rawWrite(UserDetailsVO.class, savePath, fileName);
        String downloadLink = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI() + "/" + fileName;
        return downloadLink;
    }

    @Override
    @MethodCostTimeStatisticAnnotation
    public int add(int a, int b) {
        return a + b;
    }
}
