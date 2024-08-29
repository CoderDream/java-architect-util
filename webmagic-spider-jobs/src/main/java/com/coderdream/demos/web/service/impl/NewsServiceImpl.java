package com.coderdream.demos.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.demos.web.pojo.News;
import com.coderdream.demos.web.service.NewsService;
import com.coderdream.demos.web.mapper.NewsMapper;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【t_news】的数据库操作Service实现
* @createDate 2024-07-31 17:26:04
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService{

}




