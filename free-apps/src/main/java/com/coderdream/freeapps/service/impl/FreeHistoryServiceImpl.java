package com.coderdream.freeapps.service.impl;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.dto.FreeHistoryDTO;
import com.coderdream.freeapps.dto.FreeHistoryQueryPageDTO;
import com.coderdream.freeapps.mapper.FreeHistoryMapper;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.struct.FreeHistoryStruct;
import com.coderdream.freeapps.util.CdFileUtils;
import com.coderdream.freeapps.util.spiderwx.WechatUtil;
import com.coderdream.freeapps.vo.FreeHistoryVO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@RequiredArgsConstructor
public class FreeHistoryServiceImpl extends
    ServiceImpl<FreeHistoryMapper, FreeHistory> implements FreeHistoryService {

    private final FreeHistoryStruct freeHistoryStruct;

    @Resource
    private FreeHistoryMapper freeHistoryMapper;

    @Override
    public IPage<FreeHistoryVO> queryPage(FreeHistoryQueryPageDTO dto) {
        IPage<FreeHistoryVO> freeHistoryPage = this.lambdaQuery().page(dto)
            .convert(freeHistory -> freeHistoryStruct.modelToVO(freeHistory));
        return freeHistoryPage;
    }

    @Override
    public List<FreeHistoryVO> queryList(FreeHistoryDTO dto) {
        List<FreeHistory> freeHistoryList = this.lambdaQuery().list();
        return freeHistoryStruct.modelToVO(freeHistoryList);
    }

    @Override
    public FreeHistoryVO get(Long id) {
        return freeHistoryStruct.modelToVO(this.getById(id));
    }

    @Override
    public Boolean add(FreeHistoryDTO dto) {
        return this.save(freeHistoryStruct.dtoToModel(dto));
    }

    @Override
    public Boolean edit(FreeHistoryDTO dto) {
        return this.updateById(freeHistoryStruct.dtoToModel(dto));
    }

    @Override
    public Boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public int insertSelective(FreeHistory freeHistory) {
        return freeHistoryMapper.insertSelective(freeHistory);
    }

    @Override
    public int insertOrUpdateBatch(List<FreeHistory> freeHistoryList) {
        return freeHistoryMapper.insertOrUpdateBatch(freeHistoryList);
    }

    @Override
    public int updateRecommendFlagBatch(List<FreeHistory> freeHistoryList) {
        return freeHistoryMapper.updateRecommendFlagBatch(freeHistoryList);
    }

    @Override
    public List<FreeHistory> selectList(FreeHistory freeHistory) {
        QueryWrapper<FreeHistory> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(freeHistory.getAppId())) {
            queryWrapper.eq("app_id", freeHistory.getAppId());
        }
        List<FreeHistory> result = freeHistoryMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public int processWechat() {
        int result = 0;
        // 读取配置文件
        List<FreeHistory> freeHistoryList = new ArrayList<>();
        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "wechat";
        String url = "";
        FileReader fileReader = FileReader.create(new File(folderPath + File.separatorChar + "wx.txt"));
        List<String> lines = fileReader.readLines();
        if (!CollectionUtils.isEmpty(lines)) {
            String[] strings = null;
            for (String str : lines) {
                strings = str.split(" ");
                if (strings == null || strings.length != 2) {
                    log.error("字符串不合法");
                    continue;
                }
                // 获取当天
                url = strings[1];
                // 递归调用获取前一天，直到找不到为止
                WechatUtil.getFreeHistoryList(url, freeHistoryList);
//                WechatUtil.crawlerCode(url, freeHistoryList);
                if (!CollectionUtils.isEmpty(freeHistoryList)) {
                    result += insertOrUpdateBatch(freeHistoryList);
                }
            }
        }

        return result;
    }
}
