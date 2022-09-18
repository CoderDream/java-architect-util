package com.company.microserviceuser.service;

import java.util.List;

/**
 * Threadpool service.
 * 线程池接口 
 * @author xindaqi
 * @since 2020-10-16
 */

public interface IThreadPoolService {
    
    /**
     * 数据处理
     * @param params
     */
    public void dataProcessBatch(List<Integer> params);

}