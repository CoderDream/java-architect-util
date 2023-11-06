package com.coderdream.freeapps.util.ppt.excelutil;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.coderdream.freeapps.dto.TopList;
import com.coderdream.freeapps.model.WordEntity;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CdExcelUtil {

    public static void main(String[] args) {
    }


    public static List<TopList> genTopList(String fileName) {
//        String filename = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\00_导入数据\\02_荆楚河道\\01_对象结构\\河道-对象结构-20230420-堤防地理信息映射.xlsx";

//        filename = "D:\\02_Work\\贵仁科技\\05_梯调科学计算模型标准化对象\\00_导入数据\\02_荆楚河道\\01_对象结构\\河道-对象结构-20230420-堤防地理信息映射 - mysql2.xlsx";
        //
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(fileName), "Sheet1");
        reader.addHeaderAlias("排名", "raking");
        reader.addHeaderAlias("应用ID", "appId");
        reader.addHeaderAlias("应用名称", "title");
        reader.addHeaderAlias("总排名", "topRaking");
        reader.addHeaderAlias("分类", "category");
        reader.addHeaderAlias("分类排名", "categoryRaking");
        reader.addHeaderAlias("开发者", "author");
        reader.addHeaderAlias("相比昨日", "compareYesterday");
        List<TopList> topLists = reader.read(1, 2, TopList.class);
        reader.close();

        return topLists;
    }


}




