package com.coderdream.demo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.coderdream.demo.dto.EasyExcelDemoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener<EasyExcelDemoDto> {

    private static final Logger logger = LoggerFactory.getLogger(ExcelListener.class);

    public List<EasyExcelDemoDto> dataList = new ArrayList<>();

    // 每读取一行回调当前方法
    @Override
    public void invoke(EasyExcelDemoDto easyExcelTestDto, AnalysisContext analysisContext) {
        logger.info("read item: {}", easyExcelTestDto);
        dataList.add(easyExcelTestDto);
    }

    // excel数据读取完毕回调
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        logger.info("{}条数据，开始存储数据库！", dataList.size());
        logger.info("所有数据解析完成！");
    }

    public List<EasyExcelDemoDto> getDataList() {
        return dataList;
    }

}



