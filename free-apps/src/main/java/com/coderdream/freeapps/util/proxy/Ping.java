package com.coderdream.freeapps.util.proxy;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.coderdream.freeapps.service.impl.AppIconServiceImpl;
import com.coderdream.freeapps.util.other.CdFileUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tgg
 */
public class Ping {

    public static void main(String[] args) {

        m1();

    }


    public static void m1() {
//
        Set<String> ipSet = new HashSet<>();

        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data";
        String filePath = folderPath + File.separator + "ping.txt";
        List<String> pingList = FileUtil.readLines(filePath, "UTF-8");
        for (String temp : pingList) {
            if (StrUtil.isNotEmpty(temp) && -1 != temp.lastIndexOf("(")) {
                if (!temp.contains("DNS地区")) {
                    int idx = temp.lastIndexOf("(");
                    temp = temp.substring(0, idx);
                    if (-1 != temp.lastIndexOf("\t")) {
                        temp = temp.substring(temp.lastIndexOf("\t") + 1);
                    }
                    ipSet.add(temp);
                    System.out.println(temp);
                }
            }
        }
        for (String ipAddress : ipSet) {
            List<String> strings = pingForList(ipAddress, 10, 3000);
            for (String str : strings) {
                System.out.println(str);
            }
            System.out.println("##############################");
        }

    }

    public static void m2() {
        List<String> ips = Arrays.asList("185.199.110.154",
            "140.82.113.22",
            "185.199.110.133",
            "185.199.110.153",
            "185.199.110.133",
            "185.199.109.133",
            "151.101.193.194",
            "192.30.255.112",
            "185.199.111.153",
            "192.30.255.113",
            "192.30.255.116",
            "185.199.109.133",
            "185.199.109.133",
            "185.199.111.133",
            "185.199.108.133",
            "185.199.111.133",
            "185.199.111.133",
            "185.199.108.133",
            "185.199.108.133",
            "185.199.111.133",
            "185.199.111.133",
            "192.30.255.120",
            "52.216.60.137",
            "52.216.34.9",
            "52.217.226.169",
            "54.231.164.129",
            "52.217.236.97",
            "185.199.109.153",
            "140.82.113.18",
            "185.199.110.133",
            "185.199.111.133",
            "185.199.108.133",
            "20.221.80.166");

        Set<String> ipSet = new HashSet<>();
        ipSet.addAll(ips);

        for (String ipAddress : ipSet) {
            List<String> strings = pingForList(ipAddress, 10, 3000);
            for (String str : strings) {
                System.out.println(str);
            }
            System.out.println("##############################");
        }

    }

    public static boolean ping(String ipAddress) {
        int timeOut = 3000;
        boolean status = false;
        try {
            status = InetAddress.getByName(ipAddress).isReachable(timeOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(status);
        return status;
    }

    /**
     * @param ipAddress
     * @param pingTimes
     * @param timeOut
     * @return
     */
    public static List<String> pingForList(String ipAddress, int pingTimes, int timeOut) {
        List<String> result = new ArrayList<>();
        BufferedReader in = null;
        Runtime r = Runtime.getRuntime();
        // 将要执行的ping命令,此命令是windows格式的命令
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;
        // Linux命令如下
        // String pingCommand = "ping" -c " + pingTimes + " -w " + timeOut + ipAddress;
        try {
            logger.debug(pingCommand);

            // 执行命令并获取输出
            Process p = r.exec(pingCommand);
            if (p == null) {
                return result;
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream(), "gbk"));
            int connectedCount = 0;
            String line;
            // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
            while ((line = in.readLine()) != null) {
//                System.out.println("line:\t" + line);
                connectedCount += getCheckResult(line);
                if (StrUtil.isNotEmpty(line)) {
                    result.add(line);
                }
            }
            // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真
//            return connectedCount == pingTimes;
        } catch (Exception e) {
            logger.error(String.valueOf(e));
//            return false;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error(String.valueOf(e));
            }
        }

        return result;
    }


    public static boolean ping(String ipAddress, int pingTimes, int timeOut) {
        BufferedReader in = null;
        Runtime r = Runtime.getRuntime();
        // 将要执行的ping命令,此命令是windows格式的命令
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;
        // Linux命令如下
        // String pingCommand = "ping" -c " + pingTimes + " -w " + timeOut + ipAddress;
        try {
            logger.debug(pingCommand);

            // 执行命令并获取输出
            Process p = r.exec(pingCommand);
            if (p == null) {
                return false;
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream(), "gbk"));
            int connectedCount = 0;
            String line;

            // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
            while ((line = in.readLine()) != null) {
                System.out.println("line:\t" + line);
                connectedCount += getCheckResult(line);
            }
            // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真
            return connectedCount == pingTimes;
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            return false;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error(String.valueOf(e));
            }
        }
    }

    //若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.
    private static int getCheckResult(String line) {  // System.out.println("控制台输出的结果为:"+line);
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            return 1;
        }
        return 0;
    }

    private static final Logger logger = LoggerFactory.getLogger(Ping.class);

//    private static final Logger log = Logger.getLogger(Ping.class);
}
