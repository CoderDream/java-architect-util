package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.dto.AppDTO;
import com.coderdream.freeapps.dto.AppQueryPageDTO;
import com.coderdream.freeapps.mapper.AppMapper;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.struct.AppStruct;
import com.coderdream.freeapps.vo.AppVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AppServiceImpl extends
        ServiceImpl<AppMapper, App> implements AppService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AppServiceImpl.class);
    @Resource
    private AppMapper appMapper;

    private final AppStruct appStruct;

    @Override
    public IPage<AppVO> queryPage(AppQueryPageDTO dto) {
        IPage<AppVO> appPage = this.lambdaQuery().page(dto)
                .convert(app -> appStruct.modelToVO(app));
        return appPage;
    }

    @Override
    public List<AppVO> queryList(AppDTO dto) {
        List<App> appList = this.lambdaQuery().list();
        return appStruct.modelToVO(appList);
    }

    @Override
    public AppVO get(Long id) {
        return appStruct.modelToVO(this.getById(id));
    }

    @Override
    public Boolean add(AppDTO dto) {
        return this.save(appStruct.dtoToModel(dto));
    }

    @Override
    public Boolean edit(AppDTO dto) {
        return this.updateById(appStruct.dtoToModel(dto));
    }

    @Override
    public Boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public int insertSelective(App app) {
        return appMapper.insertSelective(app);
    }

    @Override
    public int insertOrUpdateBatch(List<App> appList) {
        return appMapper.insertOrUpdateBatch(appList);
    }

    @Override
    public List<App> selectList(App app) {

        QueryWrapper<App> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(app.getAppId())) {
            queryWrapper.eq("app_id", app.getAppId());
        }
        List<App> result = appMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public List<App> selectNoSnapshot() {

        QueryWrapper<App> queryWrapper = new QueryWrapper<>();

//        queryWrapper.eq("status", DeviceConstant.DeviceStatus.WORK)
//                .or().eq("status", DeviceConstant.DeviceStatus.FAULT);

        queryWrapper.eq("del_flag", 0);
        queryWrapper.isNull("snapshot_url").or().likeLeft("snapshot_url", ": []}");
        List<App> result = appMapper.selectList(queryWrapper);
        return result;
    }



    @Override
    public List<App> selectDeletedAppList() {
        QueryWrapper<App> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 1);
        List<App> result = appMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public List<App> selectTodoList(App app) {
        QueryWrapper<App> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
//        queryWrapper.last("limit 2");
        List<App> result = appMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public IPage<App> selectPage(Page<App> page) {
        QueryWrapper<App> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 1);
        return appMapper.selectPage(page, queryWrapper);
    }

}