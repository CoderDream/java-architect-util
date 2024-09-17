package com.coderdream.middleware.server;/**
 * Created by Administrator on 2019/3/23.
 */

import com.coderdream.middleware.server.utils.RedPacketUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/23 16:57
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedPacketTest {

    private static final Logger log = LoggerFactory.getLogger(RedisTest2.class);

    /**
     * 二倍均值法自测
     *
     * @throws Exception
     */
    @Test
    public void one() throws Exception {
        // 总金额单位为分，在这里假设总金额为 1000 分，即 10 元
        Integer amount = 1000;
        // 总人数，即红包总个数，在这里假设为10个
        Integer total = 10;
        // 得到随机金额列表
        List<Integer> list = RedPacketUtil.divideRedPackage(amount, total);
        log.info("总金额={}分，总个数={}个", amount, total);

        // 用于统计生成的随机金额之和是否等于总金额
        int sum = 0;
        // 遍历输出每个随机金额
        for (Integer i : list) {
            log.info("随机金额为：{}分，即 {}元", i, new BigDecimal(i.toString()).divide(new BigDecimal(100)));
            sum += i;
        }
        log.info("所有随机金额叠加之和={}分", sum);
    }

}


























