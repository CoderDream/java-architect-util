package com.coderdream.generator;

import com.coderdream.utils.Constants;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 功能描述
 *
 * @since 2022-05-05
 */
@Slf4j
@Service
public class StringGenerator implements CodeGenerator {

    private ArrayBlockingQueue<String> queue;

    @Override
    public String generateCode() {
        return queue.poll();
    }

    public StringGenerator() {
        Integer queueSizeConfig = Constants.CHARS.toCharArray().length;
        queue = new ArrayBlockingQueue<>(queueSizeConfig);
        List<String> charList = new ArrayList<>();
        for (int i = 0; i < queueSizeConfig; i++) {
            Character character = Constants.CHARS.toCharArray()[i];
            charList.add(character.toString());
        }

        //后台线程
        Thread thread = new Thread(() -> {
            int index = 0;
            while (index < queueSizeConfig) {
                // if (this.low >= this.highMax) {
                //     log.error("已用尽号码,停止后台线程,id={},low={},high={},step={},highMax={}", id, low, high, step, highMax);
                //     return;
                // }
                // //如果低水位达到高水位,高水位上移
                // if (low >= high) {
                //     high += step;
                //     //高水位不可越过最高水位
                //     if (high >= highMax) {
                //         high = highMax;
                //     }
                //     //todo 持久化记录high
                // }
                // try {
                //     //采用阻塞方式放入元素
                //     queue.put(charList.get(index));
                // } catch (InterruptedException e) {
                //     log.info("[CacheQueueNumberGenerator] daemon thread interrupted", e);
                // }
                //低水位移动
                index++;
                //todo 可以加提前预警，比如low>highMax*0.8
            }
        });
        thread.setDaemon(true);
        thread.start();

        int index = 0;
        while (index < queueSizeConfig) {
            // if (this.low >= this.highMax) {
            //     log.error("已用尽号码,停止后台线程,id={},low={},high={},step={},highMax={}", id, low, high, step, highMax);
            //     return;
            // }
            // //如果低水位达到高水位,高水位上移
            // if (low >= high) {
            //     high += step;
            //     //高水位不可越过最高水位
            //     if (high >= highMax) {
            //         high = highMax;
            //     }
            //     //todo 持久化记录high
            // }
            try {
                //采用阻塞方式放入元素
                queue.put(charList.get(index));
            } catch (InterruptedException e) {
                log.info("[CacheQueueNumberGenerator] daemon thread interrupted", e);
            }
            //低水位移动
            index++;
            //todo 可以加提前预警，比如low>highMax*0.8
        }

        System.out.println(queue.size());
    }

}

