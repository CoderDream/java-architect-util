package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.dto.AppDTO;
import com.coderdream.freeapps.dto.AppQueryPageDTO;
import com.coderdream.freeapps.dto.DailyPptInfo;
import com.coderdream.freeapps.mapper.AppMapper;
import com.coderdream.freeapps.mapper.FreeHistoryMapper;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.struct.AppStruct;
import com.coderdream.freeapps.util.BaseUtils;
import com.coderdream.freeapps.util.CdListUtils;
import com.coderdream.freeapps.util.CdStringUtils;
import com.coderdream.freeapps.util.Constants;
import com.coderdream.freeapps.util.JSoupUtil;
import com.coderdream.freeapps.util.ppt.CdPptxUtils;
import com.coderdream.freeapps.util.ppt.pptutil.PPTUtil;
import com.coderdream.freeapps.vo.AppVO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import org.springframework.util.CollectionUtils;


@Service
@RequiredArgsConstructor
@Slf4j
public class AppServiceImpl extends
    ServiceImpl<AppMapper, App> implements AppService {

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
        return 0;// appMapper.insertSelective(app);
    }

    @Override
    public int insertOrUpdateBatch(List<App> appList) {
        int count = 0;
        if (!CollectionUtils.isEmpty(appList)) {
            log.info("本次批量执行的记录条数: " + appList.size());
            // 分批处理
            List<List<App>> lists = CdListUtils.splitTo(appList, Constants.BATCH_INSERT_UPDATE_ROWS);
            for (List<App> list : lists) {
                count += appMapper.insertOrUpdateBatch(list);
            }
        }

        return count;
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
    public List<App> selectNoAppIconUrl() {
        QueryWrapper<App> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
        queryWrapper.isNull("app_icon_url");
        List<App> result = appMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public List<App> selectNoUsFlag() {
        QueryWrapper<App> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
        queryWrapper.isNull("us_flag");
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
//        queryWrapper.eq("del_flag", 0);
        queryWrapper.ne("del_flag", 1);
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

    @Override
    public int processNoUsFlag() {
        int result = 0;
        List<App> noSnapshotListApp = selectNoUsFlag();
        List<App> newList;

        if (!CollectionUtils.isEmpty(noSnapshotListApp)) {
            log.info("本次任务开始前有效的应用数: " + noSnapshotListApp.size());
            // 分批处理
            List<List<App>> lists = CdListUtils.splitTo(noSnapshotListApp, Constants.BATCH_INSERT_UPDATE_ROWS);
            for (List<App> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    if (!CollectionUtils.isEmpty(list)) {
                        newList = new ArrayList<>();
                        for (App tempApp : list) {
                            App appNew = JSoupUtil.crawlerApp(tempApp.getAppId(), tempApp.getUsFlag());
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

        QueryWrapper<App> queryWrapperApp = new QueryWrapper<>();
        queryWrapperApp.eq("del_flag", 0);
        List<App> appList = appMapper.selectList(queryWrapperApp);

        Set<String> dbAppIdSet = new LinkedHashSet<>();
        if (!CollectionUtils.isEmpty(appList)) {
            List<String> appIdList = appList.stream().map(App::getAppId).collect(Collectors.toList());
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

        List<App> newList;
        if (!CollectionUtils.isEmpty(wechatAppIdList)) {
            log.info("本次任务开始前有效的应用数: " + wechatAppIdList.size());
            if (!CollectionUtils.isEmpty(wechatAppIdList)) {
                List<List<String>> lists = CdListUtils.splitTo(wechatAppIdList, Constants.BATCH_INSERT_UPDATE_ROWS);
                for (List<String> list : lists) {
                    // 分批处理
                    if (!CollectionUtils.isEmpty(list)) {
                        newList = new ArrayList<>();
                        for (String appId : list) {
                            if (dbAppIdSet.contains(appId)) {
                                continue;
                            }

                            App appNew = JSoupUtil.crawlerApp(appId, null);
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
            String templateFullPath = CdPptxUtils.getTemplateFullPath(Constants.PPT_TEMPLATE_FILE_NAME);
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
