package com.coderdream.mybatisplusdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.mybatisplusdemo.dto.RiverGeoReqDto;
import com.coderdream.mybatisplusdemo.dto.RiverGeoRespDto;
import com.coderdream.mybatisplusdemo.entity.RiverGeoEntity;
import com.coderdream.mybatisplusdemo.mapper.RiverGeoMapper;
import com.coderdream.mybatisplusdemo.service.RiverGeoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RiverGeoServiceImpl extends ServiceImpl<RiverGeoMapper, RiverGeoEntity>
        implements RiverGeoService {
    @Resource
    private RiverGeoMapper riverGeoMapper;

    @Override
    public int insertOrUpdateBatch(List<RiverGeoEntity> entities) {
        return riverGeoMapper.insertOrUpdateBatch(entities);
    }

    @Override
    public List<RiverGeoRespDto> list(RiverGeoReqDto riverGeoReqDto) {
        List<RiverGeoRespDto> respDtoList = new ArrayList<>();
        QueryWrapper<RiverGeoEntity> queryWrapper = new QueryWrapper<>();
        if (riverGeoReqDto.getLEVEL() != null) {
            queryWrapper.eq("LEVEL", riverGeoReqDto.getLEVEL());
        }
//        if (StrUtil.isNotEmpty(riverGeoReqDto.getDataType())) {
//            queryWrapper.eq("data_type", riverGeoReqDto.getDataType());
//        }
//        queryWrapper.orderByDesc("task_id", "data_type"); 默认按id升序

//        List<ObjectAttrRespDto> result = new ArrayList<>();

        JSONObject jsonObject;
        List<RiverGeoEntity> entityList = riverGeoMapper.selectList(queryWrapper);
        RiverGeoRespDto respDto;
        for (RiverGeoEntity entity : entityList) {
            respDto = new RiverGeoRespDto();
            BeanUtils.copyProperties(entity, respDto);



            respDtoList.add(respDto);
        }

        return respDtoList;
    }

}
