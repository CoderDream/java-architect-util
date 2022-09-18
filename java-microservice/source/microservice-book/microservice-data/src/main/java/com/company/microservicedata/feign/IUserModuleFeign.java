package com.company.microservicedata.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import com.company.microservicedata.dto.*;
import com.company.microservicedata.vo.*;
import com.company.microservicedata.vo.common.*;
import com.company.microservicedata.enums.*;

/**
 * 调用User模块
 * @author xindaqi
 * @since 2020-12-13
 */
@FeignClient(value="microservice-user")
public interface IUserModuleFeign {

    /**
     * 调用User模块的测试接口，验证Feign
     * @param params 用户分页查询参数
     * @return 用户详情-分页展示
     */
    @RequestMapping("/api/v1/user/query/page")
    ResponseVO<List<UserDetailsVO>> queryUserByPage(@RequestBody QueryUserByPageInputDTO params);
    
}