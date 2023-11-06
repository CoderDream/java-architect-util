package com.coderdream.freeapps.util.excel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.util.other.CdCsvUtil;
import com.coderdream.freeapps.util.other.CdTimeUtil;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

public class CdExcelCsvUtils {


    // 导出csv文件到指定路径
    public static void main(String[] args) {
        String fileName = "C:\\Users\\CoderDream\\Downloads2\\抖包袱大会.Dou.Bao.Fu.S01.2023.1080p.H264.AAC-OurTV\\抖包袱大会.Dou.Bao.Fu.2023.S01E01.1080p.H264.AAC-OurTV.mp4";

        fileName = "D:\\088_Workshop\\01_Doubaofu\\Doubaofu.xlsx";

        String outputFileName = "D:\\088_Workshop\\01_Doubaofu\\Doubaofu_output.xlsx";
        String outputFileNameCsv = "D:\\088_Workshop\\01_Doubaofu\\Doubaofu_output.csv";

//        exportDataToCsvFileOfPath(fileName, outputFileName);
        exportDataToCsvFileOfPathCsv(fileName, outputFileNameCsv);
    }

    public static void exportDataToCsvFileOfPathCsv(String fileName, String outputFileName) {
//        List<TILockdataMidModel> allLockData = tiLockdataMidMapper.getAllLockData();

        List<SplitInfo> splitInfoList = new ArrayList<>();

        List<String> contentList = new ArrayList<>();
        List<ShowInfo> showInfoList = getData(fileName);
        if (CollectionUtils.isNotEmpty(showInfoList)) {
            SplitInfo splitInfo;
            //开始时间
            String startTime;
            //结束时间
            String endTime;
            for (ShowInfo showInfo : showInfoList) {
                splitInfo = new SplitInfo();
                startTime = showInfo.getStartTime();
                endTime = showInfo.getEndTime();
                splitInfo.setStartSecond(CdTimeUtil.timeToSecond(startTime));
                splitInfo.setEndSecond(CdTimeUtil.timeToSecond(endTime));

                splitInfoList.add(splitInfo);
                contentList.add(splitInfo.getStartSecond() + "," + splitInfo.getEndSecond() + ",");
            }
        }

        CdCsvUtil.writeToCsv("", contentList, outputFileName, false);
    }

    public static void exportDataToCsvFileOfPath(String fileName, String outputFileName) {
//        List<TILockdataMidModel> allLockData = tiLockdataMidMapper.getAllLockData();

        List<ShowInfo> showInfoList = getData(fileName);
        ExcelWriter writer = ExcelUtil.getWriter(outputFileName);
        writer.addHeaderAlias("showId", "序号");
        writer.addHeaderAlias("showName", "节目名称");
        writer.addHeaderAlias("actorFirst", "表演者A");
        writer.addHeaderAlias("actorSecond", "表演者B");
        writer.addHeaderAlias("startTime", "开始时间");
        writer.addHeaderAlias("endTime", "结束时间");
        writer.addHeaderAlias("totalLength", "总时长");

        writer.write(showInfoList, true);
        //格式化文件名字模板
//        String fileName = String.format("%s-%s.xls", "student", DateUtil.format(new Date(), "yyyyMMdd"));
        //写入流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writer.flush(out);
        //关闭
        writer.close();
    }

    public static List<ShowInfo> getData(String fileName) {
        // 排名	应用ID	应用名称	总排名	分类	分类排名	开发者	相比昨日
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(fileName), "01");
        reader.addHeaderAlias("序号", "showId");
        reader.addHeaderAlias("节目名称", "showName");
        reader.addHeaderAlias("表演者A", "actorFirst");
        reader.addHeaderAlias("表演者B", "actorSecond");
        reader.addHeaderAlias("开始时间", "startTime");
        reader.addHeaderAlias("结束时间", "endTime");
        reader.addHeaderAlias("总时长", "totalLength");
        List<ShowInfo> showInfoList = reader.read(0, 1, ShowInfo.class);

        if (CollectionUtils.isNotEmpty(showInfoList)) {
            //开始时间
            String startTime;
            //结束时间
            String endTime;
            for (ShowInfo showInfo : showInfoList) {
                startTime = showInfo.getStartTime();
                endTime = showInfo.getEndTime();

                Integer total = CdTimeUtil.timeToSecond(endTime) - CdTimeUtil.timeToSecond(startTime);

                showInfo.setTotalLength(total + "");
            }
        }

        return showInfoList;
    }


}

@Data
class ShowInfo {

    // 序号
    private String showId;
    //节目名称
    private String showName;
    //表演者A
    private String actorFirst;
    //表演者B
    private String actorSecond;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //总时长
    private String totalLength;

}

@Data
class SplitInfo {
    //开始时间
    private Integer startSecond;
    //结束时间
    private Integer endSecond;
}
