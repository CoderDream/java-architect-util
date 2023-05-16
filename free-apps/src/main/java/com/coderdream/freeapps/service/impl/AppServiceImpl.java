package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.dto.AppDTO;
import com.coderdream.freeapps.dto.AppQueryPageDTO;
import com.coderdream.freeapps.dto.DailyPptInfo;
import com.coderdream.freeapps.mapper.AppMapper;
import com.coderdream.freeapps.mapper.FreeHistoryMapper;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.struct.AppStruct;
import com.coderdream.freeapps.util.BaseUtils;
import com.coderdream.freeapps.util.CdListUtils;
import com.coderdream.freeapps.util.CdConstants;
import com.coderdream.freeapps.util.JSoupUtil;
import com.coderdream.freeapps.util.ppt.CdPptxUtils;
import com.coderdream.freeapps.util.ppt.excelutil.CdExcelUtils;
import com.coderdream.freeapps.util.ppt.pptutil.PPTUtil;
import com.coderdream.freeapps.vo.AppVO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import org.springframework.util.CollectionUtils;


@Service
@RequiredArgsConstructor
@Slf4j
public class AppServiceImpl extends
    ServiceImpl<AppMapper, AppEntity> implements AppService {

    @Resource
    private AppMapper appMapper;

    private final AppStruct appStruct;

    @Resource
    private FreeHistoryService freeHistoryService;

    @Resource
    private FreeHistoryMapper freeHistoryMapper;

    @Override
    public IPage<AppVO> queryPage(AppQueryPageDTO dto) {
        IPage<AppVO> appPage = this.lambdaQuery().page(dto)
            .convert(app -> appStruct.modelToVO(app));
        return appPage;
    }

    @Override
    public List<AppVO> queryList(AppDTO dto) {
        List<AppEntity> appList = this.lambdaQuery().list();
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
    public int insertSelective(AppEntity app) {
        return 0;// appMapper.insertSelective(app);
    }

    @Override
    public int insertOrUpdateBatch(List<AppEntity> appList) {
        int count = 0;
        if (!CollectionUtils.isEmpty(appList)) {
            log.info("本次批量执行的记录条数: " + appList.size());
            // 分批处理
            List<List<AppEntity>> lists = CdListUtils.splitTo(appList, CdConstants.BATCH_INSERT_UPDATE_ROWS);
            for (List<AppEntity> list : lists) {
                count += appMapper.insertOrUpdateBatch(list);
            }
        }

        return count;
    }

    @Override
    public List<AppEntity> selectList(AppEntity app) {
        QueryWrapper<AppEntity> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(app.getAppId())) {
            queryWrapper.eq("app_id", app.getAppId());
        }
        List<AppEntity> result = appMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public List<AppEntity> selectNoAppIconUrl() {
        QueryWrapper<AppEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
        queryWrapper.isNull("app_icon_url");
        List<AppEntity> result = appMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public List<AppEntity> selectNoUsFlag() {
        QueryWrapper<AppEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
        queryWrapper.isNull("us_flag");
        List<AppEntity> result = appMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public List<AppEntity> selectNoSnapshot() {

        QueryWrapper<AppEntity> queryWrapper = new QueryWrapper<>();

//        queryWrapper.eq("status", DeviceConstant.DeviceStatus.WORK)
//                .or().eq("status", DeviceConstant.DeviceStatus.FAULT);

        queryWrapper.eq("del_flag", 0);
        queryWrapper.isNull("snapshot_url").or().likeLeft("snapshot_url", ": []}");
        List<AppEntity> result = appMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public List<AppEntity> selectDeletedAppList() {
        QueryWrapper<AppEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 1);
        List<AppEntity> result = appMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public List<AppEntity> selectTodoList(AppEntity app) {
        QueryWrapper<AppEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("del_flag", 0);
        queryWrapper.ne("del_flag", 1);
//        queryWrapper.last("limit 2");
        List<AppEntity> result = appMapper.selectList(queryWrapper);
        return result;
    }

    public boolean updateBatchByQueryWrapper(Collection<AppEntity> entityList, Function<AppEntity, QueryWrapper> queryWrapperFunction) {
        String sqlStatement = this.getSqlStatement(SqlMethod.UPDATE);
        return this.executeBatch(entityList, DEFAULT_BATCH_SIZE, (sqlSession, entity) -> {
            ParamMap param = new ParamMap();
            param.put(Constants.ENTITY, entity);
            param.put(Constants.WRAPPER, queryWrapperFunction.apply(entity));
            sqlSession.update(sqlStatement, param);
        });
    }

    @Override
    public int initTopFlag() {
        int result = 0;
        QueryWrapper<AppEntity> queryWrapper = new QueryWrapper<>();
        Set<String> appIdSet = CdExcelUtils.genTotalTopAppIdSet();
        queryWrapper.eq("del_flag", 0);
        queryWrapper.in("app_id", appIdSet);
        queryWrapper.select("app_id", "top_flag");
        List<AppEntity> appList = appMapper.selectList(queryWrapper);

        for (AppEntity app : appList) {
            app.setTopFlag(1);
        }

//        this.updateBatchByQueryWrapper(appList, appEntity->new QueryWrapper<>().eq("top_flag",appEntity.getTopFlag()));


//        this.updateBatchByQueryWrapper(appList, appEntity->new QueryWrapper<>().eq("app_id",appEntity.getAppId()));

        int count = 0;
        if (!CollectionUtils.isEmpty(appList)) {
            log.info("本次批量执行的记录条数: " + appList.size());
            // 分批处理
            List<List<AppEntity>> lists = CdListUtils.splitTo(appList, CdConstants.BATCH_UPDATE_ROWS);
            for (List<AppEntity> list : lists) {
                count += appMapper.insertOrUpdateTopFlagBatch(list);
            }
        }

        result = appList.size();

        return result;
    }

    @Override
    public int updateDescriptionCn() {
        int result = 0;
        QueryWrapper<AppEntity> queryWrapper = new QueryWrapper<>();
//        Set<String> appIdSet = CdExcelUtils.genTotalTopAppIdSet();
        queryWrapper.eq("del_flag", 0);
//        queryWrapper.in("app_id", appIdSet);
//        queryWrapper.select("app_id", "top_flag");
        List<AppEntity> appList = appMapper.selectList(queryWrapper);

        for (AppEntity app : appList) {
            app.setTopFlag(1);
        }

//        this.updateBatchByQueryWrapper(appList, appEntity->new QueryWrapper<>().eq("top_flag",appEntity.getTopFlag()));


//        this.updateBatchByQueryWrapper(appList, appEntity->new QueryWrapper<>().eq("app_id",appEntity.getAppId()));

        int count = 0;
        if (!CollectionUtils.isEmpty(appList)) {
            log.info("本次批量执行的记录条数: " + appList.size());
            // 分批处理
            List<List<AppEntity>> lists = CdListUtils.splitTo(appList, CdConstants.BATCH_UPDATE_ROWS);
            for (List<AppEntity> list : lists) {
                count += appMapper.insertOrUpdateTopFlagBatch(list);
            }
        }

        result = appList.size();

        return result;
    }

    @Override
    public IPage<AppEntity> selectPage(Page<AppEntity> page) {
        QueryWrapper<AppEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 1);
        return appMapper.selectPage(page, queryWrapper);
    }

    @Override
    public int processNoUsFlag() {
        int result = 0;
        List<AppEntity> noSnapshotListApp = selectNoUsFlag();
        List<AppEntity> newList;

        if (!CollectionUtils.isEmpty(noSnapshotListApp)) {
            log.info("本次任务开始前有效的应用数: " + noSnapshotListApp.size());
            // 分批处理
            List<List<AppEntity>> lists = CdListUtils.splitTo(noSnapshotListApp, CdConstants.BATCH_INSERT_UPDATE_ROWS);
            for (List<AppEntity> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    if (!CollectionUtils.isEmpty(list)) {
                        newList = new ArrayList<>();
                        for (AppEntity tempApp : list) {
                            AppEntity appNew = JSoupUtil.crawlerApp(tempApp.getAppId(), tempApp.getUsFlag());
                            newList.add(appNew);

                            Integer period = new Random().nextInt(2000) + 500;
                            try {
                                Thread.sleep(period);   // 休眠3秒
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        Integer period = new Random().nextInt(4000) + 500;
                        try {
                            Thread.sleep(period);   // 休眠3秒
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (!CollectionUtils.isEmpty(newList)) {
                            System.out.println("###");
                            result += insertOrUpdateBatch(newList);
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public int processWechat() {
        int result = 0;

        QueryWrapper<AppEntity> queryWrapperApp = new QueryWrapper<>();
        queryWrapperApp.eq("del_flag", 0);
        List<AppEntity> appList = appMapper.selectList(queryWrapperApp);

        Set<String> dbAppIdSet = new LinkedHashSet<>();
        if (!CollectionUtils.isEmpty(appList)) {
            List<String> appIdList = appList.stream().map(AppEntity::getAppId).collect(Collectors.toList());
            dbAppIdSet.addAll(appIdList);
        }

        QueryWrapper<FreeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT app_id");
        queryWrapper.eq("data_source", "wechat");
        List<FreeHistory> freeHistoryList = freeHistoryMapper.selectList(queryWrapper);

        Set<String> wechatAppIdSet = new LinkedHashSet<>();
        List<String> wechatAppIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(freeHistoryList)) {
            wechatAppIdList = freeHistoryList.stream().map(FreeHistory::getAppId).collect(Collectors.toList());
            for (String appId : wechatAppIdList) {
                if (!dbAppIdSet.contains(appId)) {
                    wechatAppIdSet.add(appId);
                }
            }
        }

        List<AppEntity> newList;
        if (!CollectionUtils.isEmpty(wechatAppIdList)) {
            log.info("本次任务开始前有效的应用数: " + wechatAppIdList.size());
            if (!CollectionUtils.isEmpty(wechatAppIdList)) {
                List<List<String>> lists = CdListUtils.splitTo(wechatAppIdList, CdConstants.BATCH_INSERT_UPDATE_ROWS);
                for (List<String> list : lists) {
                    // 分批处理
                    if (!CollectionUtils.isEmpty(list)) {
                        newList = new ArrayList<>();
                        for (String appId : list) {
                            if (dbAppIdSet.contains(appId)) {
                                continue;
                            }

                            AppEntity appNew = JSoupUtil.crawlerApp(appId, null);
                            newList.add(appNew);

                            Integer period = new Random().nextInt(300) + 500;
                            try {
                                Thread.sleep(period);   // 休眠3秒
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        Integer period = new Random().nextInt(1000) + 500;
                        try {
                            Thread.sleep(period);   // 休眠3秒
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (!CollectionUtils.isEmpty(newList)) {
                            System.out.println("###");
                            result += insertOrUpdateBatch(newList);
                        }
                    }
                }
            }
        }
        return result;
    }


    @Override
    public int genDailyPpt() {
//        try {
////            File file=new File("example.pptx");
//////            if(!file.exists()) {
//////                file.createNewFile();
//////            }
//////            FileInputStream fis = new FileInputStream(file); // 打开pptX文件流
////
////            XMLSlideShow pptx = new XMLSlideShow(); // 创建XMLSlideShow对象
////
////
////            pptx.write(new FileOutputStream(file));
////            fis.close(); // 关闭文件流
//
//
//            XMLSlideShow ppt =  new XMLSlideShow(); // 创建XMLSlideShow对象
//            FileOutputStream fos = new FileOutputStream("文件路径.pptx");
//            ppt.write(fos);
//            fos.close();
//
//            // 处理pptX文件
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 1.
        try {
            // PPT模板
            String templateFullPath = CdPptxUtils.getTemplateFullPath(CdConstants.PPT_TEMPLATE_FILE_NAME);
            FileInputStream fileInputStreamTemplate = new FileInputStream(templateFullPath);
            XMLSlideShow pptTemplate = new XMLSlideShow(fileInputStreamTemplate);
            // 新PPT
            XMLSlideShow xmlSlideShowNew = new XMLSlideShow();

//            XMLSlideShow pptx = new XMLSlideShow(fileInputStreamOld); // 创建XMLSlideShow对象
//            XSLFSlide template =

            List<XSLFSlide> slides = pptTemplate.getSlides();
            if (CollectionUtils.isEmpty(slides)) {
                log.error("PPTX列表为空");
                return 0;
            }
            XSLFSlide firstSlide = slides.get(0);
            XSLFSlide firstXSLFSlide = CdPptxUtils.copySlide(firstSlide, xmlSlideShowNew, 0);

            List<DailyPptInfo> dailyPptInfoList = appMapper.selectDailyPptInfo();

            int size = 0;
            Integer totalPrice = 0;
            for (DailyPptInfo dailyPptInfo : dailyPptInfoList) {
                totalPrice += dailyPptInfo.getYesterdayPrice();
                size = dailyPptInfoList.size();
            }
            // 例如：
            int listSize = size / 3 + (size % 3 == 0 ? 0 : 1);

            XSLFSlide listPage = slides.get(1);
            for (int i = 1; i < listSize + 1; i++) {
                CdPptxUtils.copySlide(listPage, xmlSlideShowNew, i);
            }

            System.out.println(totalPrice);

            String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String path = BaseUtils.getPath();
            String newFileName = File.separator + path + File.separator + dateStr + ".pptx";

            FileOutputStream fos = new FileOutputStream(newFileName);
            xmlSlideShowNew.write(fos);
            fos.close(); // 关闭文件流

            String todayStr = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            // 构造数据
            Map<String, Object> keyMap = new HashMap<String, Object>();
            keyMap.put("today", todayStr);
            keyMap.put("total", totalPrice);

            PPTUtil pptUtil = new PPTUtil(newFileName);
            List<XSLFTextParagraph> paragraphs = pptUtil.getParagraphsFromSlide(
                pptUtil.getSlides().get(0));    // 获取该幻灯片内所有段落
            for (XSLFTextParagraph paragraph : paragraphs) {
                pptUtil.replaceTagInParagraph(paragraph, keyMap);   // 对该段落中所有标签 {**} 进行替换
            }
            for (XSLFTextParagraph paragraph : paragraphs) {
                System.out.println(paragraph.getText());
            }
            pptUtil.writePPT(newFileName);

            fileInputStreamTemplate.close(); // 关闭文件流
            // 处理pptX文件
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
