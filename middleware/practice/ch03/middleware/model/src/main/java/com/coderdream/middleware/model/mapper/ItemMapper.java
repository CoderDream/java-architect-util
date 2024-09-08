package com.coderdream.middleware.model.mapper;

import com.coderdream.middleware.model.entity.Item;
import org.apache.ibatis.annotations.Param;

/**
 * @author CoderDream
 */
public interface ItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    Item selectByCode(@Param("code") String code);
}
