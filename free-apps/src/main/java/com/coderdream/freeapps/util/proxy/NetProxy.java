package com.coderdream.freeapps.util.proxy;

import com.coderdream.freeapps.util.bbc.BbcConstants;
import com.coderdream.freeapps.util.other.CdFileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CoderDream
 */
public class NetProxy {

    public static void main(String[] args) {
        String pageUrl = "https://www.bbc.co.uk/learningenglish/english/features/6-minute-english";
        pageUrl = "https://www.bbc.co.uk/learningenglish/english/features/6-minute-english_2023/ep-231102";
        NetProxy.getPageInfo(pageUrl);


//        File pathFile = new File("D://c//c//");
//        if (!pathFile.exists()) {
//            pathFile.mkdirs();
//        }
//
//        File file = new File("D://c//c//c.txt");
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    public static String getPageInfo(String pageUrl) {
        //转化数据流变数据本
        StringBuilder sb1 = new StringBuilder();
        try {
            //设置请求访问的地址
            URL url = new URL(pageUrl);

            //设置代理 , 端口是你自己使用软件代理的本地出口,socks和http代理的端口
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7890);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http 代理
            URLConnection connection = url.openConnection(proxy);
            //此处是浏览器的请求头. 一般是为了防止对面设置的反爬
            connection.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
            connection.connect();

            //连接获取数据流
            InputStream inputStream = connection.getInputStream();

//            byte[] buffer = new byte[1024*1024*8*10*10];
            byte[] buffer = new byte[1024*1024*8*10];
            int len;
            int index  = 0;
            List<String> contentList = new ArrayList<>();
            String tempStr = "";
            while ((len = inputStream.read(buffer)) != -1) {
//                System.out.println("len: " + len);
                tempStr = new String(buffer, 0, len, "UTF-8");
                System.out.println("tempStr: \t" + tempStr);
                sb1.append(tempStr);
                contentList.add(tempStr);
                index++;
//                System.out.println("#################: " + index);
            }
            //输出文本至屏幕
//            System.out.println(sb1);
            String  name = pageUrl.substring(pageUrl.lastIndexOf("/") + 1);
            String fileNameCn = BbcConstants.ROOT_FOLDER_NAME + name + ".html";
            // 写中文翻译文本
//            CdFileUtils.writeToFile(fileNameCn, contentList);
            CdFileUtils.writeToFile(fileNameCn, Arrays.asList(sb1.toString()));

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb1.toString();
    }

}
