package com.coderdream.freeapps.util.spiderdistrict;

import static com.coderdream.freeapps.util.spiderdistrict.WormMain.urlQueue;
import static com.coderdream.freeapps.util.spiderdistrict.WormMain.wormCore;
import static com.coderdream.freeapps.util.spiderdistrict.WormMain.districtMap;

import java.io.IOException;
import java.util.Map;

class MyRunnable implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
                //把主方法中的URL队列传给核心控制类，开始该线程的爬取
                wormCore.wormCore(urlQueue, districtMap);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
