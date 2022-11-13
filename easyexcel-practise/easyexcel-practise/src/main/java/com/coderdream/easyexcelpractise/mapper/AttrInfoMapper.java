package com.coderdream.easyexcelpractise.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.easyexcelpractise.entity.AttrInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttrInfoMapper extends BaseMapper<AttrInfoEntity> {

//    public void insertBatch(List<AttrInfoEntity> attrInfoEntities);
}
