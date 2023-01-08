package com.coderdream.autogenvedio.util;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PhantomjsUtil {


    public static void main(String[] args) throws Exception {
//        String url = "https://www.baidu.com";
        String imgName = "baidu";
        String type = "";
        String resultId = "";

        List<String> urlList = new ArrayList<>(Arrays.asList("https://apps.apple.com/cn/app/id1592844577",
                "https://apps.apple.com/cn/app/id1578843767",
                "https://apps.apple.com/cn/app/id1536585848",
                "https://apps.apple.com/cn/app/id425893570",
                "https://apps.apple.com/cn/app/id1510078277",
                "https://apps.apple.com/cn/app/id1255192598",
                "https://apps.apple.com/cn/app/id598710611",
                "https://apps.apple.com/cn/app/id6443737201"));

        String monthStr = new SimpleDateFormat("yyyyMM").format(new Date());
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String path = "D:" + File.separator + "12_iOS_Android" + File.separator + monthStr + File.separator + dateStr + File.separator + "snapshot";
        File pathDir = new File(path);
        if (!pathDir.exists()) {
            pathDir.mkdirs();
        }

        File file;
        int i = 0;
        for (String url : urlList) {
            i++;
            String appId = StringUtils.parseAppId(url);
            imgName = path + File.separator + i + "_" + appId + ".jpg";
            file = new File(imgName);
//            saveImage(url, imgName);

            try {
                captureImg(url, "1920px*1080px", imgName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    public static void saveImage(String url, String path) {
        if (!url.startsWith("http://")) {
            url = "http://" + url;
        }
//        String phantomDir = "D:/phantomjs";
        String phantomDir = Thread.currentThread().getContextClassLoader().getResource("static/phantomjs").getPath();
        StringBuffer buffer = new StringBuffer();
        buffer.append(phantomDir + "/bin/phantomjs.exe ");
        buffer.append(" --ignore-ssl-errors=yes ")
                .append(phantomDir + "/examples/rasterize.js' ") // .append("'" + phantomDir + "/examples/rasterize.js' '")
                .append(url + "' ")
                .append(path);

        try {
            Process process = Runtime.getRuntime().exec(buffer.toString());
            InputStream eis = process.getErrorStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = eis.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
            eis.close();

            InputStream is = process.getInputStream();
            buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String captureImg(String url, String size, String fileName) throws IOException {
        String img = "";

//        String phantomDir = Thread.currentThread().getContextClassLoader().getResource("static/phantomjs").getPath();
//        StringBuffer buffer = new StringBuffer();
//        buffer.append(phantomDir + "/bin/phantomjs.exe ");
//        buffer.append(" --ignore-ssl-errors=yes ")
//                .append(phantomDir + "/examples/rasterize.js' ") // .append("'" + phantomDir + "/examples/rasterize.js' '")
//                .append(url + "' ")
//                .append(path);

        String phantomDir = Thread.currentThread().getContextClassLoader().getResource("static/phantomjs").getPath();
        String os = System.getProperty("os.name").toLowerCase();
//        if (os.contains("windows")) {
//            plugin = resourceUtil.getFilePath("files/sysplugins/phantomjs.exe");
//        }
        String plugin = phantomDir + "/bin/phantomjs.exe";
        String js = phantomDir + "/examples/rasterize.js";

//        File file = new File(this.pdfPath);
//        if (!file.exists()) {
//            file.mkdirs();
//        }

//        img = this.pdfPath + DateUtils.format(new Date(), "yyyyMMddHHmmss") + System.nanoTime() + ".png";

        img = fileName;

        File pluginFile = new File(plugin);
        if (!pluginFile.canExecute()) {
            pluginFile.setExecutable(true);
        }

        File jsFile = new File(js);

        if (!exec(plugin, jsFile.getAbsolutePath(), url, img, size)) {
            return null;
        }

        return img;
    }

    public static boolean exec(String... args) {

        System.out.println(Arrays.toString(args).replaceAll(","," "));

        Process process = null;

        StringBuilder msg = new StringBuilder();

        try {
            process = Runtime.getRuntime().exec(args);
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            msg.append("error");
        }
        System.out.println("####");
        return !msg.toString().endsWith("error");
    }
}
