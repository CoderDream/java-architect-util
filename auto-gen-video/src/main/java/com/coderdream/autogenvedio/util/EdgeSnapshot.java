package com.coderdream.autogenvedio.util;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdgeSnapshot {
    public static void main(String[] args) throws IOException {
        //驱动程序所在位置
        System.setProperty("webdriver.edge.driver", "D:\\03_Dev\\edgedriver_win64\\msedgedriver.exe");
        EdgeDriver driver = new EdgeDriver();
        System.out.println("跳转首页面...");
        String url = "https://mp.weixin.qq.com/s/w3DmjBd5yzgJmtyLOG5aNA";
        //第一次进入页面
        driver.get(url);

//        driver.get("https://login.dingtalk.com/oauth2/challenge.htm?redirect_uri=https%3A%2F%2Foa.dingtalk.com%2Fomp%2Flogin%2Fdingtalk_sso_call_back%3Fcontinue%3Dhttps%253A%252F%252Foa.dingtalk.com&response_type=code&client_id=dingoaltcsv4vlgoefhpec&scope=openid+corpid&org_type=management");
        System.out.println("正在登录...");

        //创建一个等待器，300秒后超时，每2秒检查一次，有结果后直接返回，这里直接返回accessToken
//        Cookie accessToken = new FluentWait<EdgeDriver>(driver)
//                .withTimeout(Duration.ofSeconds(300))
//                .pollingEvery(Duration.ofSeconds(2))
//                .ignoring(NoSuchElementException.class)
//                .until(edgeDriver -> {
//                    Cookie access_token = driver.manage().getCookieNamed("access_token");
//                    if (access_token == null) {
//                        System.out.println("正在检查正在登录中...");
//                    } else {
//                        System.out.println("登录成功！");
//                    }
//                    return access_token;
//                });

        System.out.println("开始截图");
//        driver.navigate().to(String.format("https://aflow.dingtalk.com/dingtalk/web/query/oaDesigner?from=oaAdminHomeWeb&processCode=%s&tab=process", "PROC-B9DBBF95-3130-4DAC-8FB4-C1390C3C9E54"));
        //模拟窗口调小
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", false);
        map.put("width", 300);
        map.put("height", 300);
        map.put("deviceScaleFactor", 1);
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", map);
        //等待页面元素.dingflow-design被载入
//        WebElement dingflowDesign = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(300))
//                .pollingEvery(Duration.ofSeconds(2))
//                .ignoring(NoSuchElementException.class)
//                .until(edgeDriver -> {
//                    List<WebElement> elements = driver.findElements(By.className("dingflow-design"));
//                    if (elements == null || elements.size() == 0) {
//                        System.out.println("正在跳转中...");
//                        return null;
//                    }
//                    System.out.println("跳转成功！");
//                    return elements.get(0);
//                });
        System.out.println("正在截图...");
//        Long width = (Long) driver.executeScript("return arguments[0].scrollWidth", dingflowDesign);
//        Long height = (Long) driver.executeScript("return arguments[0].scrollHeight", dingflowDesign);
//        map.clear();
//        map.put("mobile", false);
//        map.put("width", width + width / 10);
//        map.put("height", height + height / 10);
//        map.put("deviceScaleFactor", 1);
//        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", map);
//        File src = driver.getScreenshotAs(OutputType.FILE);
//        String pngfileName = "D:\\edgedriver_win64\\testImage.png";
//        FileUtils.copyFile(src, new File(pngfileName));
//        FileUtils.copyFile(captureElement(src, dingflowDesign), new File(pngfileName));
//        System.out.println("页面截图成功!");
//        System.out.println("完成，退出！！");
//        driver.quit();
    }

    /**
     * 根据元素生成图片
     *
     * @param screenshot
     * @param element
     * @return
     */
    public static File captureElement(File screenshot, WebElement element) {
        try {
            BufferedImage img = ImageIO.read(screenshot);
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();
            //获取指定元素的坐标
            Point point = element.getLocation();
            //从元素左上角坐标开始，按照元素的高宽对img进行裁剪为符合需要的图片
            BufferedImage dest = img.getSubimage(point.getX(), point.getY(), width, height);
            ImageIO.write(dest, "png", screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshot;
    }
}
