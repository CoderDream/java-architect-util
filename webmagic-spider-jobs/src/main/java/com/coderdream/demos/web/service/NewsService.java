package com.coderdream.demos.web.service;

import com.coderdream.demos.web.pojo.News;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【t_news】的数据库操作Service
* @createDate 2024-07-31 17:26:04
*/
@Service
public interface NewsService extends IService<News> {

}
