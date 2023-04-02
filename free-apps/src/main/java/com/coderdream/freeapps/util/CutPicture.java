package com.coderdream.freeapps.util;

import com.coderdream.freeapps.model.AppBrief;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import org.springframework.util.CollectionUtils;

/**
 * 打开所有网页
 */
public class CutPicture {


    public static void main(String[] args) throws Exception {
//        String url = "https://www.baidu.com";
        String imgName = "baidu";
        String imgNameNew = "";
        String type = "";
        String resultId = "";

        List<String> urlList = new ArrayList<>(Arrays.asList("https://apps.apple.com/cn/app/id1592844577",
//                "https://apps.apple.com/cn/app/id1578843767",
//                "https://apps.apple.com/cn/app/id1536585848",
//                "https://apps.apple.com/cn/app/id425893570",
//                "https://apps.apple.com/cn/app/id1510078277",
//                "https://apps.apple.com/cn/app/id1255192598",
//                "https://apps.apple.com/cn/app/id598710611",
                "https://apps.apple.com/cn/app/id6443737201"));


        List<AppBrief> appBriefList = BaseUtils.genBrief();

        System.out.println("[");
        for (AppBrief appBrief:appBriefList) {
            System.out.println("\"" + appBrief.getAppId()+"\"");
        }

        System.out.println("]");
        getSnapshotTemp(appBriefList);
    }

    public static void getSnapshotTemp(List<AppBrief> appBriefList) {
        String imgName;
        String monthStr = new SimpleDateFormat("yyyyMM").format(new Date());
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String path = "D:" + File.separator + "12_iOS_Android" + File.separator + monthStr + File.separator + dateStr + File.separator + "snapshot_temp";
        File pathDir = new File(path);
        if (!pathDir.exists()) {
            pathDir.mkdirs();
        }

        AppBrief appBrief;
        File file;

        int i = 0;
        String url;
        String urlCn;
        Boolean onlyUs;
        // 循环遍历10次
        for (int j = 0; j < 10; j++) {
            if (!CollectionUtils.isEmpty(appBriefList)) {
                Iterator<AppBrief> it_b = appBriefList.iterator();
                while (it_b.hasNext()) {
                    appBrief = it_b.next();
                    url = appBrief.getUrl();
                    urlCn = appBrief.getUrlCn();
                    onlyUs = appBrief.getOnlyUs();
                    i++;
//                    String appId = StringUtils.parseAppId(url);
                    String fileName = appBrief.getFilename();// fileNameMap.get(appId);
                    imgName = path + File.separator + fileName + ".png";
                    file = new File(imgName);

                    // 如果截图存在，而且大于150K，则表示截图成功
                    if (file.exists()) {
                        if (getFileSize(file) > 120 * 1024) {
//                            BufferedImageUtils.cut4K(imgName, imgNameNew);
                            it_b.remove();
                            continue; // 跳出循环
                        }
                    }

                    try {
                        if (onlyUs) {
                            System.out.println("####url:\t" + url);
//                        driver.get(url);
                            cutImage(url, file);
                        } else {
//                        driver.get(urlCn);
                            cutImage(urlCn, file);
                            System.out.println("####urlCn:\t" + urlCn);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    if (file.exists()) {
                        if (getFileSize(file) > 150 * 1024) {
//                            BufferedImageUtils.cut4K(imgName, imgNameNew);
                            it_b.remove();
                        }
                    }
                }
            }
            i = 0;
        }
    }

    /**
     * 获取文件长度
     *
     * @param file
     */
    public static Long getFileSize(File file) {
        if (file.exists() && file.isFile()) {
            String fileName = file.getName();
            System.out.println("文件" + fileName + "的大小是：" + file.length());
            return file.length();
        }
        return 0L;
    }

    public static void cutImage(String url, File file) throws Exception {
        System.out.println("url===>" + url);
        System.out.println("pathname==>" + String.valueOf(file));

        if (StringUtils.existQuestionMark(url)) {
            url += "&platform=iphone";
        } else {
            url += "?platform=iphone";
        }

        Desktop.getDesktop().browse(new URL(url).toURI());
        Robot robot = new Robot();
        robot.delay(3000);//使程序暂停一段时间，类似于线程的sleep()方法
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        int width = (int) d.getWidth(); // 1920*21920*2; //
        int height = (int) d.getHeight(); // 1080*2;//
        System.out.println("width: " + width + ", height: " + height);
        // 最大化浏览器
        robot.keyRelease(KeyEvent.VK_F11);
        robot.delay(3000);
        robot.keyRelease(KeyEvent.VK_F5);
        robot.delay(2000);
//        Image image = robot.createScreenCapture(new Rectangle(0, 0, width, height));


        Image image = robot.createScreenCapture(new Rectangle(0, 0, width, height));
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        // 保存图片
//        ImageIO.write(bi, "jpg", new File(String.valueOf(pathname) + "\\page.jpg"));
        ImageIO.write(bi, "jpg", file);
    }
}
