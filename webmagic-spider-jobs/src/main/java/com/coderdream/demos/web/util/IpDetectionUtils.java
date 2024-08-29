package com.coderdream.demos.web.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CoderDream
 */
@Slf4j
public class IpDetectionUtils {

    public static void main(String[] args) {
        System.out.println(ipDetection("119.96.188.171", 30000, 10000));
    }

    /**
     * 通过socket检测ip:port是否能够通信
     *
     * @param ipAddress
     * @param port
     * @param timeout
     * @return
     */
    public static Boolean ipDetection(String ipAddress, Integer port, Integer timeout) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(InetAddress.getByName(ipAddress), port), timeout);
        } catch (IOException e) {
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    /**
     * @param ipAddress     待检测IP地址
     * @param port          待检测port
     * @param retryCount    重试次数
     * @param timeout       检测超时时间（超时应该在3钞以上）
     * @param detectionFlag 标志位 0检测IP  1检测IP:PORT
     * @return
     */
    public static Boolean retryIPDetection(String ipAddress, Integer port, Integer retryCount, Integer timeout,
        Integer detectionFlag) {
        // 当返回值是true时，说明host是可用的，false则不可。
        boolean status = false;
        Integer tryCount = 1;

        //重试机制
        while (tryCount <= retryCount && status == false) {
            if (detectionFlag.equals(0)) {
                status = ipDetection(ipAddress, port, timeout);
            } else {
                status = ipDetection(ipAddress, port, timeout);
            }
            if (status == false) {
                log.info("第[" + tryCount + "]次连接 " + ipAddress + ":" + port + " 失败！");
                tryCount++;
                continue;
            }
            log.info("连接 " + ipAddress + ":" + port + " 成功！");
            return true;
        }
        return false;
    }


}
