package com.coderdream.freeapps;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.coderdream.freeapps.util.excel.bigdata.ExcelBean;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class EasyExcelTest {

    public static final String FILE_NAME = "D:\\test_" + System.currentTimeMillis() + ".xlsx";
    // 每个 sheet 写入的数据
    public static final int NUM_PER_SHEET = 300000;
    // 每次向 sheet 中写入的数据（分页写入）
    public static final int NUM_BY_TIMES = 50000;

    /**
     * 方法一：将数据写入到excel 直接调用api，适合小数据量 100W条数据33s
     */
    @Test
    public void writeExcelByApi() {
        String fileName = FILE_NAME;
        log.error("导出excel名称={}", fileName);
        long startTime = System.currentTimeMillis();
        // 直接调用api
        List<ExcelBean> date = getDate();
        EasyExcel.write(fileName, ExcelBean.class).sheet().doWrite(date);
        log.error("导出excel结束,数据量={}，耗时={}ms", date.size(), System.currentTimeMillis() - startTime);
        Long period = System.currentTimeMillis() - startTime;
        System.out.println("导出excel结束,数据量=" + date.size() + "，耗时=" + period + "ms");
    }

    /**
     * 方法二：导出多个sheet easyExcel 底层是 POI 实现的，POI 单个sheet 最多只能导出 1048576 行，超过该行数，会产生如下异常
     * java.lang.IllegalArgumentException: Invalid row number (1048576) outside allowable range (0..1048575)
     * <p>
     * 11:57:55.541 [main] INFO mytest.TestExcel - 写入sheet=sheet0，数据量300000-0=300000，耗时=6055ms 11:57:59.701 [main] INFO
     * mytest.TestExcel - 写入sheet=sheet1，数据量600000-300000=300000，耗时=4159ms 11:58:03.827 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet2，数据量900000-600000=300000，耗时=4126ms 11:58:05.193 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet3，数据量1000000-900000=100000，耗时=1366ms 11:58:17.418 [main] INFO mytest.TestExcel -
     * 导出excel结束,总数据量=1000000，耗时=31297ms
     */
    @Test
    public void writeExcelByMulSheet() {
        String fileName = FILE_NAME;
        log.info("导出excel名称={}", fileName);
        long startTime = System.currentTimeMillis();
        // 获取数据
        List<ExcelBean> date = getDate();
        // 获取 sheet 的个数
        int sheetNum = date.size() % NUM_PER_SHEET == 0 ? date.size() / NUM_PER_SHEET : date.size() / NUM_PER_SHEET + 1;
        // 指定写入的文件
        ExcelWriter excelWriter = EasyExcel.write(fileName, ExcelBean.class).build();
        for (int i = 0; i < sheetNum; i++) {
            long l = System.currentTimeMillis();
            // 设置 sheet 的名字（sheet不能相同）
            String sheetName = "sheet" + i;
            WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName).build();
            int startNum = i * NUM_PER_SHEET;
            int endNum = i == sheetNum - 1 ? date.size() : (i + 1) * NUM_PER_SHEET;
            excelWriter.write(date.subList(startNum, endNum), writeSheet);
            log.info("写入sheet={}，数据量{}-{}={}，耗时={}ms", sheetName, endNum, startNum, endNum - startNum,
                System.currentTimeMillis() - l);
        }
        // 最好放在 finally中
        excelWriter.finish();
        log.info("导出excel结束,总数据量={}，耗时={}ms", date.size(), System.currentTimeMillis() - startTime);
        Long period = System.currentTimeMillis() - startTime;
        System.out.println("导出excel结束,数据量=" + date.size() + "，耗时=" + period + "ms");
    }

    /**
     * 方法三：同一个 Sheet，分批多次写入 当单次读取的 list 数据量过大，会产生 OOM 异常，所以需要分页读取并写入到 excel 11:55:01.590 [main] INFO mytest.TestExcel -
     * 写入数量50000-0=50000，耗时=2227ms 11:55:02.429 [main] INFO mytest.TestExcel - 写入数量100000-50000=50000，耗时=838ms
     * 11:55:03.188 [main] INFO mytest.TestExcel - 写入数量150000-100000=50000，耗时=759ms 11:55:03.951 [main] INFO
     * mytest.TestExcel - 写入数量200000-150000=50000，耗时=762ms 11:55:04.708 [main] INFO mytest.TestExcel -
     * 写入数量250000-200000=50000，耗时=757ms 11:55:05.471 [main] INFO mytest.TestExcel - 写入数量300000-250000=50000，耗时=762ms
     * 11:55:06.172 [main] INFO mytest.TestExcel - 写入数量350000-300000=50000，耗时=701ms 11:55:06.921 [main] INFO
     * mytest.TestExcel - 写入数量400000-350000=50000，耗时=749ms 11:55:07.688 [main] INFO mytest.TestExcel -
     * 写入数量450000-400000=50000，耗时=767ms 11:55:08.437 [main] INFO mytest.TestExcel - 写入数量500000-450000=50000，耗时=749ms
     * 11:55:09.141 [main] INFO mytest.TestExcel - 写入数量550000-500000=50000，耗时=704ms 11:55:09.899 [main] INFO
     * mytest.TestExcel - 写入数量600000-550000=50000，耗时=758ms 11:55:10.597 [main] INFO mytest.TestExcel -
     * 写入数量650000-600000=50000，耗时=698ms 11:55:11.353 [main] INFO mytest.TestExcel - 写入数量700000-650000=50000，耗时=756ms
     * 11:55:12.055 [main] INFO mytest.TestExcel - 写入数量750000-700000=50000，耗时=701ms 11:55:12.820 [main] INFO
     * mytest.TestExcel - 写入数量800000-750000=50000，耗时=765ms 11:55:13.576 [main] INFO mytest.TestExcel -
     * 写入数量850000-800000=50000，耗时=756ms 11:55:14.287 [main] INFO mytest.TestExcel - 写入数量900000-850000=50000，耗时=711ms
     * 11:55:15.055 [main] INFO mytest.TestExcel - 写入数量950000-900000=50000，耗时=768ms 11:55:15.773 [main] INFO
     * mytest.TestExcel - 写入数量1000000-950000=50000，耗时=718ms 11:55:28.016 [main] INFO mytest.TestExcel -
     * 导出excel结束,总数据量=1000000，耗时=31738ms
     * <p>
     * Process finished with exit code 0
     */
    @Test
    public void writeExcelByMulWrite() {
        String fileName = FILE_NAME;
        log.info("导出excel名称={}", fileName);
        long startTime = System.currentTimeMillis();
        // 获取数据
        List<ExcelBean> date = getDate();
        ExcelWriter excelWrite = EasyExcel.write(fileName, ExcelBean.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("testSheet").build();
        // 计算需要写入的次数
        int times = date.size() % NUM_BY_TIMES == 0 ? date.size() / NUM_BY_TIMES : date.size() / NUM_BY_TIMES + 1;
        for (int i = 0; i < times; i++) {
            long l = System.currentTimeMillis();
            int startNum = i * NUM_BY_TIMES;
            int endNum = i == times - 1 ? date.size() : (i + 1) * NUM_BY_TIMES;
            excelWrite.write(date.subList(startNum, endNum), writeSheet);
            log.info("写入数量{}-{}={}，耗时={}ms", endNum, startNum, endNum - startNum, System.currentTimeMillis() - l);
        }
        // 需要放入 finally 中
        if (excelWrite != null) {
            excelWrite.finish();
        }
        log.info("导出excel结束,总数据量={}，耗时={}ms", date.size(), System.currentTimeMillis() - startTime);
        Long period = System.currentTimeMillis() - startTime;
        System.out.println("导出excel结束,数据量=" + date.size() + "，耗时=" + period + "ms");
    }

    /**
     * 方案四：写入多个 sheet，并且每个 sheet 写入多次数据（结合方案二、三） 数据量大，导致一个 sheet 存储不下；同时单次读入的数据量太大。可以采用这个方法 12:02:18.751 [main] INFO
     * mytest.TestExcel - 写入sheet=sheet0，数据量=50000-0=50000，耗时=1558 12:02:19.542 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet0，数据量=100000-50000=50000，耗时=791 12:02:20.282 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet0，数据量=150000-100000=50000，耗时=740 12:02:21.037 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet0，数据量=200000-150000=50000，耗时=755 12:02:21.781 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet0，数据量=250000-200000=50000，耗时=744 12:02:22.524 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet0，数据量=300000-250000=50000，耗时=742 12:02:23.201 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet1，数据量=350000-300000=50000，耗时=677 12:02:23.852 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet1，数据量=400000-350000=50000，耗时=651 12:02:24.451 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet1，数据量=450000-400000=50000，耗时=599 12:02:25.100 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet1，数据量=500000-450000=50000，耗时=649 12:02:25.753 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet1，数据量=550000-500000=50000，耗时=653 12:02:26.350 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet1，数据量=600000-550000=50000，耗时=597 12:02:26.995 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet2，数据量=650000-600000=50000，耗时=645 12:02:27.588 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet2，数据量=700000-650000=50000，耗时=593 12:02:28.244 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet2，数据量=750000-700000=50000，耗时=656 12:02:28.893 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet2，数据量=800000-750000=50000，耗时=648 12:02:29.506 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet2，数据量=850000-800000=50000，耗时=613 12:02:30.163 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet2，数据量=900000-850000=50000，耗时=657 12:02:30.760 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet3，数据量=950000-900000=50000，耗时=597 12:02:31.419 [main] INFO mytest.TestExcel -
     * 写入sheet=sheet3，数据量=1000000-950000=50000，耗时=659 12:02:43.235 [main] INFO mytest.TestExcel -
     * 导出excel结束,总数据量=1000000，耗时=28818ms
     * <p>
     * Process finished with exit code 0
     */
    @Test
    public void writeExcelByMulSheetAndWriteChange() {
        String fileName = FILE_NAME;
        log.info("导出excel名称={}", fileName);
        long startTime = System.currentTimeMillis();
        // 获取数据
        List<ExcelBean> date = getDate();
        // 获取 sheet 的个数
        int sheetNum = date.size() % NUM_PER_SHEET == 0 ? date.size() / NUM_PER_SHEET : date.size() / NUM_PER_SHEET + 1;
        // 获取每个sheet 写入的次数
        int writeNumPerSheet =
            NUM_PER_SHEET % NUM_BY_TIMES == 0 ? NUM_PER_SHEET / NUM_BY_TIMES : NUM_PER_SHEET / NUM_BY_TIMES + 1;
        // 最后一个 sheet 写入的数量
        int writeNumLastSheet = date.size() - (sheetNum - 1) * NUM_PER_SHEET;
        // 最后一个 sheet 写入的次数
        int writeNumPerLastSheet = writeNumLastSheet % NUM_BY_TIMES == 0 ? writeNumLastSheet / NUM_BY_TIMES
            : writeNumLastSheet / NUM_BY_TIMES + 1;
        // 指定写入的文件
        ExcelWriter excelWriter = EasyExcel.write(fileName, ExcelBean.class).build();
        for (int i = 0; i < sheetNum; i++) {
            String sheetName = "sheet" + i;
            WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName).build();
            int writeNum = i == sheetNum - 1 ? writeNumPerLastSheet : writeNumPerSheet; // 每个sheet 写入的次数
            int endEndNum = i == sheetNum - 1 ? date.size() : (i + 1) * NUM_PER_SHEET; // 每个sheet 最后一次写入的最后行数
            for (int j = 0; j < writeNum; j++) {
                long l = System.currentTimeMillis();
                int startNum = i * NUM_PER_SHEET + j * NUM_BY_TIMES;
                int endNum = j == writeNum - 1 ? endEndNum : i * NUM_PER_SHEET + (j + 1) * NUM_BY_TIMES;
                excelWriter.write(date.subList(startNum, endNum), writeSheet);
                log.info("写入sheet={}，数据量={}-{}={}，耗时={}", sheetName, endNum, startNum, endNum - startNum,
                    System.currentTimeMillis() - l);
            }
        }
        // 需要放入 finally 中
        if (excelWriter != null) {
            excelWriter.finish();
        }
        log.info("导出excel结束,总数据量={}，耗时={}ms", date.size(), System.currentTimeMillis() - startTime);
        Long period = System.currentTimeMillis() - startTime;
        System.out.println("导出excel结束,数据量=" + date.size() + "，耗时=" + period + "ms");
    }

    /**
     * 获取excel 导出的数据
     *
     * @return list 集合
     */
    public List<ExcelBean> getDate() {
        log.error("开始生成数据");
        Date date = new Date();
        long startTime = System.currentTimeMillis();
        List<ExcelBean> list = Lists.newArrayList();
        for (int i = 0; i < 1000000; i++) {
            ExcelBean bean = new ExcelBean();
            bean.setId(UUID.randomUUID().toString()).
                setName("隔壁老樊" + i).
                setAddress("北京市朝阳区酒仙桥" + i + "路").
                setAge(i).
                setNumber(i + 10000).
                setHigh(1.234 * i).
                setDistance(1.234 * i).
                setStartTime(date).
                setEndTime(date);
            list.add(bean);
        }
        log.error("数据生成结束，数据量={}，耗时={}ms", list.size(), System.currentTimeMillis() - startTime);
        return list;
    }

}
