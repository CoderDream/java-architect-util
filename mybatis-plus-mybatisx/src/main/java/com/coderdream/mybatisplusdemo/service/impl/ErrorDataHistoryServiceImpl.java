package com.coderdream.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.mybatisplusdemo.entity.ErrorDataHistory;
import com.coderdream.mybatisplusdemo.service.ErrorDataHistoryService;
import com.coderdream.mybatisplusdemo.mapper.ErrorDataHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【error_data_history】的数据库操作Service实现
* @createDate 2023-02-27 15:54:35
*/
@Service
public class ErrorDataHistoryServiceImpl extends ServiceImpl<ErrorDataHistoryMapper, ErrorDataHistory>
    implements ErrorDataHistoryService{

}




