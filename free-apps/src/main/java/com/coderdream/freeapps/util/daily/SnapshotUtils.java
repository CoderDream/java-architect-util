package com.coderdream.freeapps.util.daily;

import com.coderdream.freeapps.model.AppBrief;
import com.coderdream.freeapps.util.BaseUtils;
import com.coderdream.freeapps.util.CutPicture;
import com.coderdream.freeapps.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.util.CollectionUtils;

public class SnapshotUtils {
    public static void main(String[] args) {

       // https://apps.apple.com/us/app/lock-screen-16/id1641143233?l=zh
        //https://apps.apple.com/us/app/inotification/id1502455581?l=zh
//        String s = "https://apps.apple.com/us/app/inotification/id1502455581?l=zh";
//        System.out.println(s.lastIndexOf("/?"));

//                String s = "https://apps.apple.com/us/app/inotification/id1502455581";
//        System.out.println(s.charAt("/?"));

        List<AppBrief> appBriefList = BaseUtils.genBrief();
        snapshot(appBriefList);

//        String url = "https://mp.weixin.qq.com/s/w3DmjBd5yzgJmtyLOG5aNA";https://mp.weixin.qq.com/s/szC-CWiPKDFUR3lBk-Qcnw
//        snapshot(url);

//        List<AppBrief> snapshotFileNameList = snapshotOpenBrowser(appBriefList);
//        for (AppBrief appBrief : snapshotFileNameList) {
//            Long picSize = CutPicture.getFileSize(new File(appBrief.getSnapshotPath()));
//            System.out.println("Snapshot picSize: " + picSize);
//        }

        // 打开浏览器
//        snapshotOpenBrowser(appBriefList);
    }

    public static boolean snapshot(List<AppBrief> appBriefList) {
        com.google.common.collect.ImmutableMap immutableMap;
        boolean res = true;
//        List<AppBrief> result = new ArrayList<>();
        List<String> snapshotFileNameList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        String driverPath = Thread.currentThread().getContextClassLoader().getResource("static/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);   //设置chrome驱动程序的路径
        System.out.println(System.getProperty("webdriver.chrome.driver"));
        // ChromeOptions9hy
        ChromeOptions chromeOptions = new ChromeOptions();
        // 设置后台静默模式启动浏览器
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.setBinary("D:\\00_Green\\Chrome100_AllNew_2022.4.4\\App\\chrome.exe");
//        chromeOptions.addArguments("--window-size=3840,1916"); // 3840 1916 1920,
        chromeOptions.addArguments("--window-size=3840,1916"); // 3840 1916 1920,1050
        WebDriver driver = new ChromeDriver(chromeOptions);   //初始化一个chrome驱动实例，保存到driver中

//        List<String> urlList = GenerateAppInfo.genUrlCnList();

        // 截图文件夹
        String snapshotPath = BaseUtils.getPath() + File.separator + "snapshot";
        File snapshotDir = new File(snapshotPath);
        if (!snapshotDir.exists()) {
            snapshotDir.mkdirs();
        }

        File file;
        int i = 0;
        // 循环遍历10次
//        List<String> urlCnList = appBriefList.stream().map(AppBrief::getUrlCn).collect(Collectors.toList());
//        List<String> urlList = appBriefList.stream().map(AppBrief::getUrl).collect(Collectors.toList());
        AppBrief appBrief;
        String url;
        String urlCn;
        Boolean onlyUs;
//        urlList.addAll(urlCnList);
        for (int j = 0; j < 10; j++) {
            if (!CollectionUtils.isEmpty(appBriefList)) {
                Iterator<AppBrief> it_b = appBriefList.iterator();
                while (it_b.hasNext()) {

                    appBrief = it_b.next();
                    url = appBrief.getUrl();
                    urlCn = appBrief.getUrlCn();
                    onlyUs = appBrief.getOnlyUs();

//                    String url =  appBrief.getUrl();// appBrief.getUrlCn();//it_b.next();
                    i++;

                    //使用getScreenshotAs进行截取屏幕
//                    try {
////                        String appId = StringUtils.parseAppId(url);
////                        file = new File(snapshotPath + File.separator + String.format("%02d", i) + "_" + appId + ".png");
//
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println("当前打开页面的标题是： " + driver.getTitle());
                    file = new File(snapshotPath + File.separator + appBrief.getFilename() + ".png");
                    // 如果截图存在，而且大于100K，则表示截图成功
                    if (file.exists()) {
                        Long picSize = CutPicture.getFileSize(file);
                        System.out.println("picSize: " + picSize);
                        if (picSize > 100 * 1024) {
                            System.out.println("#### 已存在，跳过，remove");
//                            System.out.println("urlList size: " + urlList.size());
                            snapshotFileNameList.add(file.getAbsolutePath());
                            System.out.println("snapshotPath: " + file.getAbsolutePath());
//                            appBrief.setSnapshotPath(file.getAbsolutePath());
//                            result.add(appBrief);
                            it_b.remove(); // 移除当前项
                            res = true;
                            continue; // 跳出循环
                        } else {
                            res = false;
                        }
                    } else {
                        System.out.println("#### file not exist" + file.getAbsolutePath());
                    }

//                    String appId = StringUtils.parseAppId(url);
//                    String filename = appBrief.getFilename();
                    Actions action = new Actions(driver);
                    try {
//                        Thread.sleep(2000);
//                        action.keyDown(Keys.F5).sendKeys("t").keyUp(Keys.CONTROL).perform();  //ctr+t 快捷方式新打开一个标签页
//                        action.keyDown(Keys.F5).keyUp(Keys.CONTROL).perform();  //ctr+t 快捷方式新打开一个标签页
//                        driver.sendKeys(Keys.F5);
                        if(StringUtils.existQuestionMark(url)) {
                            url += "&platform=iphone";
                        } else {
                            url +=  "?platform=iphone";
                        }
                        if(StringUtils.existQuestionMark(urlCn)) {
                            urlCn += "&platform=iphone";
                        } else {
                            urlCn +=  "?platform=iphone";
                        }
                        if (onlyUs) {
                            System.out.println("####url:\t" + url);
                            driver.get(url);
                        } else {
                            driver.get(urlCn);
                            System.out.println("####urlCn:\t" + urlCn);
                        }

                        Thread.sleep(5000);  //等待页面加载完成，后续写代码注意，如果页面加载未完成，可能导致页面元素找不到
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //使用getScreenshotAs进行截取屏幕
                    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    try {
//                        String appId = StringUtils.parseAppId(url);
//                        file = new File(snapshotPath + File.separator + String.format("%02d", i) + "_" + appId + ".png");
                        file = new File(snapshotPath + File.separator + appBrief.getFilename() + ".png");
                        FileUtils.copyFile(srcFile, file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
//                    System.out.println("当前打开页面的标题是： " + driver.getTitle());

                    // 如果截图存在，而且大于100K，则表示截图成功
                    if (file.exists()) {
                        Long picSize = CutPicture.getFileSize(file);
                        System.out.println("picSize: " + picSize);
                        if (picSize > 100 * 1024) {
                            System.out.println("remove");
//                            System.out.println("urlList size: " + urlList.size());
                            snapshotFileNameList.add(file.getAbsolutePath());
                            System.out.println("snapshotPath: " + file.getAbsolutePath());
//                            appBrief.setSnapshotPath(file.getAbsolutePath());
//                            result.add(appBrief);
                            it_b.remove();
                            res = true;
                        } else {
                            res = false;
                        }
                    } else {
                        System.out.println("#### file not exist" + file.getAbsolutePath());
                    }
                }
            }
            i = 0;
        }

        //关闭并退出浏览器
        driver.quit();
        System.out.println("生成成功！");
        System.out.println("耗时: " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
        return res;
    }

    public static boolean snapshot(String url) {
        boolean res = true;
//        List<AppBrief> result = new ArrayList<>();
        List<String> snapshotFileNameList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        String driverPath = Thread.currentThread().getContextClassLoader().getResource("static/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);   //设置chrome驱动程序的路径
        System.out.println(System.getProperty("webdriver.chrome.driver"));
        // ChromeOptions9hy
        ChromeOptions chromeOptions = new ChromeOptions();
        // 设置后台静默模式启动浏览器
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox");
//        chromeOptions.addArguments("--window-size=3840,1916"); // 3840 1916 1920,
        chromeOptions.addArguments("--window-size=3840,5916"); // 3840 1916 1920,1050
        WebDriver driver = new ChromeDriver(chromeOptions);   //初始化一个chrome驱动实例，保存到driver中

//        List<String> urlList = GenerateAppInfo.genUrlCnList();

        // 截图文件夹
        String snapshotPath = BaseUtils.getPath() + File.separator + "snapshot";
        File snapshotDir = new File(snapshotPath);
        if (!snapshotDir.exists()) {
            snapshotDir.mkdirs();
        }

        File file;
        int i = 0;
        // 循环遍历10次
//        List<String> urlCnList = appBriefList.stream().map(AppBrief::getUrlCn).collect(Collectors.toList());
//        List<String> urlList = appBriefList.stream().map(AppBrief::getUrl).collect(Collectors.toList());
        AppBrief appBrief;
//        String url;
        String urlCn;
        Boolean onlyUs;
//        urlList.addAll(urlCnList);
//        for (int j = 0; j < 10; j++) {
//            if (!CollectionUtils.isEmpty(appBriefList)) {
//                Iterator<AppBrief> it_b = appBriefList.iterator();
//                while (it_b.hasNext()) {
//
//                    appBrief = it_b.next();
//                    url = appBrief.getUrl();
//                    urlCn = appBrief.getUrlCn();
//                    onlyUs = appBrief.getOnlyUs();

//                    String url =  appBrief.getUrl();// appBrief.getUrlCn();//it_b.next();
                    i++;

                    //使用getScreenshotAs进行截取屏幕
//                    try {
////                        String appId = StringUtils.parseAppId(url);
////                        file = new File(snapshotPath + File.separator + String.format("%02d", i) + "_" + appId + ".png");
//
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println("当前打开页面的标题是： " + driver.getTitle());
                    file = new File(snapshotPath + File.separator + "abc" + ".png");
                    // 如果截图存在，而且大于100K，则表示截图成功
                    if (file.exists()) {
                        Long picSize = CutPicture.getFileSize(file);
                        System.out.println("picSize: " + picSize);
                        if (picSize > 100 * 1024) {
                            System.out.println("#### 已存在，跳过，remove");
//                            System.out.println("urlList size: " + urlList.size());
                            snapshotFileNameList.add(file.getAbsolutePath());
                            System.out.println("snapshotPath: " + file.getAbsolutePath());
//                            appBrief.setSnapshotPath(file.getAbsolutePath());
//                            result.add(appBrief);
//                            it_b.remove(); // 移除当前项
                            res = true;
//                            continue; // 跳出循环
                        } else {
                            res = false;
                        }
                    } else {
                        System.out.println("#### file not exist" + file.getAbsolutePath());
                    }

//                    String appId = StringUtils.parseAppId(url);
//                    String filename = appBrief.getFilename();
                    Actions action = new Actions(driver);
                    try {
//                        Thread.sleep(2000);
//                        action.keyDown(Keys.F5).sendKeys("t").keyUp(Keys.CONTROL).perform();  //ctr+t 快捷方式新打开一个标签页
//                        action.keyDown(Keys.F5).keyUp(Keys.CONTROL).perform();  //ctr+t 快捷方式新打开一个标签页
//                        driver.sendKeys(Keys.F5);
//                        if(StringUtils.existQuestionMark(url)) {
//                            url += "&platform=iphone";
//                        } else {
//                            url +=  "?platform=iphone";
//                        }
//                        if(StringUtils.existQuestionMark(urlCn)) {
//                            urlCn += "&platform=iphone";
//                        } else {
//                            urlCn +=  "?platform=iphone";
//                        }
//                        if (onlyUs) {
                            System.out.println("####url:\t" + url);
                            driver.get(url);
//                        } else {
//                            driver.get(urlCn);
//                            System.out.println("####urlCn:\t" + urlCn);
//                        }

                        Thread.sleep(5000);  //等待页面加载完成，后续写代码注意，如果页面加载未完成，可能导致页面元素找不到
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //使用getScreenshotAs进行截取屏幕
                    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    try {
//                        String appId = StringUtils.parseAppId(url);
//                        file = new File(snapshotPath + File.separator + String.format("%02d", i) + "_" + appId + ".png");
                        file = new File(snapshotPath + File.separator + "abc" + ".png");
                        FileUtils.copyFile(srcFile, file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
//                    System.out.println("当前打开页面的标题是： " + driver.getTitle());

                    // 如果截图存在，而且大于100K，则表示截图成功
//                    if (file.exists()) {
//                        Long picSize = CutPicture.getFileSize(file);
//                        System.out.println("picSize: " + picSize);
//                        if (picSize > 100 * 1024) {
//                            System.out.println("remove");
////                            System.out.println("urlList size: " + urlList.size());
//                            snapshotFileNameList.add(file.getAbsolutePath());
//                            System.out.println("snapshotPath: " + file.getAbsolutePath());
////                            appBrief.setSnapshotPath(file.getAbsolutePath());
////                            result.add(appBrief);
//                            it_b.remove();
//                            res = true;
//                        } else {
//                            res = false;
//                        }
//                    } else {
//                        System.out.println("#### file not exist" + file.getAbsolutePath());
//                    }
//                }
//            }
            i = 0;
//        }

        //关闭并退出浏览器
        driver.quit();
        System.out.println("生成成功！");
        System.out.println("耗时: " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
        return res;
    }

    public static List<AppBrief> snapshotOpenBrowser(List<AppBrief> appBriefList) {
        List<AppBrief> result = new ArrayList<>();
        List<String> snapshotFileNameList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        String driverPath = Thread.currentThread().getContextClassLoader().getResource("static/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);   //设置chrome驱动程序的路径
        System.out.println(System.getProperty("webdriver.chrome.driver"));
        // ChromeOptions
//        ChromeOptions chromeOptions = new ChromeOptions();
//        // 设置后台静默模式启动浏览器
////        chromeOptions.addArguments("--headless");
//        chromeOptions.addArguments("--start-maximized");
//        chromeOptions.addArguments("--no-sandbox");
//        chromeOptions.addArguments("--window-size=3840,1916"); // 3840 1916 1920,1050
//        WebDriver driver = new ChromeDriver(chromeOptions);   //初始化一个chrome驱动实例，保存到driver中

        WebDriver driver = new ChromeDriver();   //初始化一个chrome驱动实例，保存到driver中
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); //隐式等待10秒
        //最大化窗口
        driver.manage().window().maximize();  //最大化窗口
//        List<String> urlList = GenerateAppInfo.genUrlCnList();

        // 截图文件夹
        String snapshotPath = BaseUtils.getPath() + File.separator + "snapshot";
        File snapshotDir = new File(snapshotPath);
        if (!snapshotDir.exists()) {
            snapshotDir.mkdirs();
        }

        File file;
        int i = 0;
        // 循环遍历10次
        AppBrief appBrief;
        for (int j = 0; j < 10; j++) {
            if (!CollectionUtils.isEmpty(appBriefList)) {
                Iterator<AppBrief> it_b = appBriefList.iterator();
                while (it_b.hasNext()) {
                    appBrief = it_b.next();
                    String url = appBrief.getUrlCn();//it_b.next();
                    i++;
//                    String appId = StringUtils.parseAppId(url);
                    String filename = appBrief.getFilename();

                    Actions action = new Actions(driver);
                    try {
                        Thread.sleep(2000);
                        action.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();  //ctr+t 快捷方式新打开一个标签页
                        driver.get(url + "?platform=iphone");

                        Thread.sleep(2000);  //等待页面加载完成，后续写代码注意，如果页面加载未完成，可能导致页面元素找不到
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //使用getScreenshotAs进行截取屏幕
                    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    try {
                        file = new File(snapshotPath + File.separator + filename + ".png");
                        FileUtils.copyFile(srcFile, file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
//                    System.out.println("当前打开页面的标题是： " + driver.getTitle());

                    // 如果截图存在，而且大于100K，则表示截图成功
                    if (file.exists()) {
                        Long picSize = CutPicture.getFileSize(file);
                        System.out.println("picSize: " + picSize);
                        if (picSize > 100 * 1024) {
                            System.out.println("remove");
//                            System.out.println("urlList size: " + urlList.size());
                            snapshotFileNameList.add(file.getAbsolutePath());
                            System.out.println("snapshotPath: " + file.getAbsolutePath());
                            appBrief.setSnapshotPath(file.getAbsolutePath());
                            result.add(appBrief);
                            it_b.remove();
                        }
                    } else {
                        System.out.println("#### file not exist" + file.getAbsolutePath());
                    }
                }
            }
            i = 0;
        }

        //关闭并退出浏览器
//        driver.quit();
        System.out.println("生成成功！");
        System.out.println("耗时: " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
        return result;
    }

    // https://mp.weixin.qq.com/s/w3DmjBd5yzgJmtyLOG5aNA
    public static void snapshotOpenBrowser(String url) {
        List<AppBrief> result = new ArrayList<>();
        List<String> snapshotFileNameList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        String driverPath = Thread.currentThread().getContextClassLoader().getResource("static/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);   //设置chrome驱动程序的路径
        System.out.println(System.getProperty("webdriver.chrome.driver"));
        // ChromeOptions
        ChromeOptions chromeOptions = new ChromeOptions();
        // 设置后台静默模式启动浏览器
//        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--window-size=3840,4916"); // 3840 1916 1920,1050
        WebDriver driver = new ChromeDriver(chromeOptions);   //初始化一个chrome驱动实例，保存到driver中

//        WebDriver driver = new ChromeDriver();   //初始化一个chrome驱动实例，保存到driver中
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); //隐式等待10秒
        //最大化窗口
        driver.manage().window().maximize();  //最大化窗口
//        List<String> urlList = GenerateAppInfo.genUrlCnList();

        // 截图文件夹
        String snapshotPath = BaseUtils.getPath() + File.separator + "snapshot";
        File snapshotDir = new File(snapshotPath);
        if (!snapshotDir.exists()) {
            snapshotDir.mkdirs();
        }
        File file;
        String filename = "temp";
        Actions action = new Actions(driver);
        try {
            Thread.sleep(2000);
            action.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();  //ctr+t 快捷方式新打开一个标签页
            driver.get(url);

            Thread.sleep(2000);  //等待页面加载完成，后续写代码注意，如果页面加载未完成，可能导致页面元素找不到
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //使用getScreenshotAs进行截取屏幕
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            file = new File(snapshotPath + File.separator + filename + ".png");
            FileUtils.copyFile(srcFile, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        File file;
//        int i = 0;
//        // 循环遍历10次
//        AppBrief appBrief;
//        for (int j = 0; j < 10; j++) {
//            if (!CollectionUtils.isEmpty(appBriefList)) {
//                Iterator<AppBrief> it_b = appBriefList.iterator();
//                while (it_b.hasNext()) {
//                    appBrief = it_b.next();
//                    String url = appBrief.getUrlCn();//it_b.next();
//                    i++;
////                    String appId = StringUtils.parseAppId(url);
//                    String filename = appBrief.getFilename();
//
//                    Actions action = new Actions(driver);
//                    try {
//                        Thread.sleep(2000);
//                        action.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();  //ctr+t 快捷方式新打开一个标签页
//                        driver.get(url + "?platform=iphone");
//
//                        Thread.sleep(2000);  //等待页面加载完成，后续写代码注意，如果页面加载未完成，可能导致页面元素找不到
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    //使用getScreenshotAs进行截取屏幕
//                    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//                    try {
//                        file = new File(snapshotPath + File.separator + filename + ".png");
//                        FileUtils.copyFile(srcFile, file);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
////                    System.out.println("当前打开页面的标题是： " + driver.getTitle());
//
//                    // 如果截图存在，而且大于100K，则表示截图成功
//                    if (file.exists()) {
//                        Long picSize = CutPicture.getFileSize(file);
//                        System.out.println("picSize: " + picSize);
//                        if (picSize > 100 * 1024) {
//                            System.out.println("remove");
////                            System.out.println("urlList size: " + urlList.size());
//                            snapshotFileNameList.add(file.getAbsolutePath());
//                            System.out.println("snapshotPath: " + file.getAbsolutePath());
//                            appBrief.setSnapshotPath(file.getAbsolutePath());
//                            result.add(appBrief);
//                            it_b.remove();
//                        }
//                    } else {
//                        System.out.println("#### file not exist" + file.getAbsolutePath());
//                    }
//                }
//            }
//            i = 0;
//        }

        //关闭并退出浏览器
//        driver.quit();
        System.out.println("生成成功！");
        System.out.println("耗时: " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
//        return result;
    }

//    public static String snapshotOpenBrowser(String url) {
//        List<String> snapshotFileNameList = new ArrayList<>();
//        long startTime = System.currentTimeMillis();
//        String driverPath = Thread.currentThread().getContextClassLoader().getResource("static/chromedriver.exe").getPath();
//        System.setProperty("webdriver.chrome.driver", driverPath);   //设置chrome驱动程序的路径
//        System.out.println(System.getProperty("webdriver.chrome.driver"));
//        // ChromeOptions9hy
//        ChromeOptions chromeOptions = new ChromeOptions();
//        // 设置后台静默模式启动浏览器
//        chromeOptions.addArguments("--headless");
//        chromeOptions.addArguments("--start-maximized");
//        chromeOptions.addArguments("--no-sandbox");
////        chromeOptions.addArguments("--window-size=3840,1916"); // 3840 1916 1920,
//        chromeOptions.addArguments("--window-size=3840,1916"); // 3840 1916 1920,1050
//        WebDriver driver = new ChromeDriver(chromeOptions);   //初始化一个chrome驱动实例，保存到driver中
//        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); //隐式等待10秒
//        //最大化窗口
//        driver.manage().window().maximize();  //最大化窗口
////        List<String> urlList = GenerateAppInfo.genUrlCnList();
//
//        // 截图文件夹
//        String snapshotPath = BaseUtils.getPath() + File.separator + "snapshot";
//        File snapshotDir = new File(snapshotPath);
//        if (!snapshotDir.exists()) {
//            snapshotDir.mkdirs();
//        }
//
//        File file;
//        int i = 0;
//        // 循环遍历10次
//        for (int j = 0; j < 10; j++) {
//                    i++;
////                    String appId = StringUtils.parseAppId(url);
//                    String filename = appBrief.getFilename();
//
//                    Actions action = new Actions(driver);
//                    try {
//                        Thread.sleep(2000);
//                        action.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();  //ctr+t 快捷方式新打开一个标签页
//                        driver.get(url + "?platform=iphone");
//
//                        Thread.sleep(2000);  //等待页面加载完成，后续写代码注意，如果页面加载未完成，可能导致页面元素找不到
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    //使用getScreenshotAs进行截取屏幕
//                    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//                    try {
//                        file = new File(snapshotPath + File.separator + filename + ".png");
//                        FileUtils.copyFile(srcFile, file);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
////                    System.out.println("当前打开页面的标题是： " + driver.getTitle());
//
//                    // 如果截图存在，而且大于100K，则表示截图成功
//                    if (file.exists()) {
//                        Long picSize = CutPicture.getFileSize(file);
//                        System.out.println("picSize: " + picSize);
//                        if (picSize > 100 * 1024) {
//                            System.out.println("remove");
////                            System.out.println("urlList size: " + urlList.size());
//                            snapshotFileNameList.add(file.getAbsolutePath());
//                            System.out.println("snapshotPath: " + file.getAbsolutePath());
//                            appBrief.setSnapshotPath(file.getAbsolutePath());
//                            result.add(appBrief);
//                            it_b.remove();
//                        }
//                    } else {
//                        System.out.println("#### file not exist" + file.getAbsolutePath());
//                    }
//            i = 0;
//        }
//
//        //关闭并退出浏览器
////        driver.quit();
//        System.out.println("生成成功！");
//        System.out.println("耗时: " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
//        return result;
//    }

    // https://mp.weixin.qq.com/s/dCc1PwS4-gZ2Ms41jFZ7ZQ
}
