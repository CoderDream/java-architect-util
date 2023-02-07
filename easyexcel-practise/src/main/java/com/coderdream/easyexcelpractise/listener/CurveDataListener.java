package com.coderdream.easyexcelpractise.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.coderdream.easyexcelpractise.data.CurveData;
import com.coderdream.easyexcelpractise.mapper.CurveMapper;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * 读取头
 *
 * @author Jiaju Zhuang
 */
@Slf4j
public class CurveDataListener implements ReadListener<CurveData> {

    @Resource
    private CurveMapper curveMapper;

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    private List<CurveData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void invoke(CurveData data, AnalysisContext context) {
//        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        } else {
            cachedDataList.add(data);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        log.info("存储数据库成功！");
    }
}
