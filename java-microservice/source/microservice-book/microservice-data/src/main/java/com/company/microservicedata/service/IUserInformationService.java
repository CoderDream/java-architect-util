package com.company.microservicedata.service;

import javax.servlet.http.HttpServletRequest;

/**
 * User模块数据处理Service
 * @author xindaqi
 * @since 2020-12-20
 */

public interface IUserInformationService {

    String downloadExcel(HttpServletRequest request);

    /**
     * 加法
     * 
     * @param a
     * @param b
     * @return
     */
    int add(int a, int b);
}
