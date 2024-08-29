package com.coderdream.demos.web;

import com.coderdream.demos.web.pojo.BossJobDetail;
import com.coderdream.demos.web.service.impl.BossJobDetailServiceImpl;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author CoderDream
 */
public class BossDatabasePipeline implements Pipeline {

    public static Integer integer = new Integer(0);
    public static List<BossJobDetail> bossJobDetailList = new ArrayList<>();

    @Override
    public void process(ResultItems result, Task task) {
        //设计存储过程
        BossJobDetail bossJobDetail = result.get("bossJobDetail");
        if (bossJobDetail == null) {
            return;
        }
        synchronized (BossDatabasePipeline.class) {
            bossJobDetailList.add(bossJobDetail);
            /*try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //多线程的话在这里获取一下锁
            int howManyWorkStart = 10;
            if (bossJobDetailList.size() >= howManyWorkStart) {
                try {
                    BossJobDetailServiceImpl bossJobDetailService = new BossJobDetailServiceImpl();

                    // TODO 数据库持久化
//                    for (BossJobDetail bossJobDetailTemp : bossJobDetailList) {
//                        bossJobDetailTemp.setCreateTime(new Date());
//                        bossJobDetailService.save(bossJobDetailTemp);
//                    }

                    bossJobDetailService.insertOrUpdateBatch(bossJobDetailList);

                    //清空List
                    bossJobDetailList.clear();
                    System.out.println("===============bossJobDetailSize=======================");
                    System.out.println("当前bossJobDetailList的长度为：" + bossJobDetailList.size() + " ，完成一轮爬取");
                    System.out.println("==================================================");
                    integer++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (integer > 500) {
                System.exit(0);
            }
        }
    }


}
