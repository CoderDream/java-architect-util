package com.coderdream.demo.dao;

import com.coderdream.demo.pojo.StStbprpB;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MoBai·杰
 * @since 2023-01-08
 */
public interface StStbprpBMapper extends BaseMapper<StStbprpB> {
    List<Map<String, Object>> getObjectSheetValue();
}
