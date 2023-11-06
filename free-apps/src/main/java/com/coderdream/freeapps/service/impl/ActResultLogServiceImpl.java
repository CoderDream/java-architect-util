package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.ActResultLogMapper;
import com.coderdream.freeapps.model.ActResultLogEntity;
import com.coderdream.freeapps.service.ActResultLogService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【t_act_result_log】的数据库操作Service实现
* @createDate 2023-06-07 10:52:09
*/
@Service
public class ActResultLogServiceImpl extends ServiceImpl<ActResultLogMapper, ActResultLogEntity>
    implements ActResultLogService{


    //导出逻辑代码
    //导出逻辑代码
    public void dataExport300w(HttpServletResponse response) {

//        OutputStream outputStream = null;
//        try {
//            long startTime = System.currentTimeMillis();
//            System.out.println("导出开始时间:" + startTime);
//
//            outputStream = response.getOutputStream();
//            ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);
//            String fileName = new String(("excel100w").getBytes(), "UTF-8");
//
//            //title
//            Table table = new Table(1);
//            List<List<String>> titles = new ArrayList<List<String>>();
//            titles.add(Arrays.asList("onlineseqid"));
//            titles.add(Arrays.asList("businessid"));
//            titles.add(Arrays.asList("becifno"));
//            titles.add(Arrays.asList("ivisresult"));
//            titles.add(Arrays.asList("createdby"));
//            titles.add(Arrays.asList("createddate"));
//            titles.add(Arrays.asList("updateby"));
//            titles.add(Arrays.asList("updateddate"));
//            titles.add(Arrays.asList("risklevel"));
//            table.setHead(titles);
//
//            //模拟统计查询的数据数量这里模拟100w
//            int count = 3000001;
//            //记录总数:实际中需要根据查询条件进行统计即可
//            Integer totalCount = actResultLogMapper.find ActResultLogEntityByCondations(count);
//            //每一个Sheet存放100w条数据
//            Integer sheetDataRows = ExcelConstants.PER_SHEET_ROW_COUNT;
//            //每次写入的数据量20w
//            Integer writeDataRows = ExcelConstants.PER_WRITE_ROW_COUNT;
//            //计算需要的Sheet数量
//            Integer sheetNum = totalCount % sheetDataRows == 0 ? (totalCount / sheetDataRows) : (totalCount / sheetDataRows + 1);
//            //计算一般情况下每一个Sheet需要写入的次数(一般情况不包含最后一个sheet,因为最后一个sheet不确定会写入多少条数据)
//            Integer oneSheetWriteCount = sheetDataRows / writeDataRows;
//            //计算最后一个sheet需要写入的次数
//            Integer lastSheetWriteCount = totalCount % sheetDataRows == 0 ? oneSheetWriteCount : (totalCount % sheetDataRows % writeDataRows == 0 ? (totalCount / sheetDataRows / writeDataRows) : (totalCount / sheetDataRows / writeDataRows + 1));
//
//            //开始分批查询分次写入
//            //注意这次的循环就需要进行嵌套循环了,外层循环是Sheet数目,内层循环是写入次数
//            List<List<String>> dataList = new ArrayList<>();
//            for (int i = 0; i < sheetNum; i++) {
//                //创建Sheet
//                Sheet sheet = new Sheet(i, 0);
//                sheet.setSheetName("测试Sheet1" + i);
//                //循环写入次数: j的自增条件是当不是最后一个Sheet的时候写入次数为正常的每个Sheet写入的次数,如果是最后一个就需要使用计算的次数lastSheetWriteCount
//                for (int j = 0; j < (i != sheetNum - 1 ? oneSheetWriteCount : lastSheetWriteCount); j++) {
//                    //集合复用,便于GC清理
//                    dataList.clear();
//                    //分页查询一次20w
//                    PageHelper.startPage(j + 1 + oneSheetWriteCount * i, writeDataRows);
//                    List<ActResultLogEntity> reslultList = actResultLogMapper.findByPage100w();
//                    if (!CollectionUtils.isEmpty(reslultList)) {
//                        reslultList.forEach(item -> {
//                            dataList.add(
//                                Arrays.asList(item.getOnlineseqid(), item.getBusinessid(), item.getBecifno(), item.getIvisresult(), item.getCreatedby(), Calendar.getInstance().getTime().toString(), item.getUpdateby(), Calendar.getInstance().getTime().toString(), item.getRisklevel()));
//                        });
//                    }
//                    //写数据
//                    writer.write0(dataList, sheet, table);
//                }
//            }
//
//            // 下载EXCEL
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes("gb2312"), "ISO-8859-1") + ".xlsx");
//            response.setContentType("multipart/form-data");
//            response.setCharacterEncoding("utf-8");
//            writer.finish();
//            outputStream.flush();
//            //导出时间结束
//            long endTime = System.currentTimeMillis();
//            System.out.println("导出结束时间:" + endTime + "ms");
//            System.out.println("导出所用时间:" + (endTime - startTime) / 1000 + "秒");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}




