package com.coderdream.autogenvedio.util;

import com.coderdream.autogenvedio.entity.AppBrief;
import com.coderdream.autogenvedio.entity.AppInfo;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GenerateAppInfo {

    public static void main(String[] args) throws ParseException {
        BaseUtils.genBrief();
//        genUrlCnList();
//        //  genMerGeek();
//
//
        List<String> todayContentList = genTodayContent();
        for (String todayContent : todayContentList) {
            System.out.println(todayContent);
        }
    }

    public static List<String> genUrlCnList() {
        List<String> urlCnList = new ArrayList<>();
        List<AppBrief> appBriefList = BaseUtils.genBrief();
        String urlCn = "";
        for (AppBrief entity : appBriefList) {
            urlCn = entity.getUrlCn();
            if (-1 != urlCn.lastIndexOf("https://apps.apple.com/cn/app/id")) {
                urlCnList.add(entity.getUrlCn());
                System.out.println(entity.getUrlCn());
            }
        }

        return urlCnList;
    }

    public static List<String> genTodayContent() {
        List<String> todayContentList = new ArrayList<>();
        List<AppBrief> appBriefList = BaseUtils.genBrief();

        String todayStr = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        todayContentList.add("大家好，今天是 " + todayStr + "，");
        Integer sum = 0;
        for (AppBrief entity : appBriefList) {
            sum += entity.getPrice();
        }

        todayContentList.add("给您推荐 " + appBriefList.size() + " 款正在限免的游戏或应用，");
        todayContentList.add("为您节省 " + sum + " 元！");

        int i = 0;
        for (AppBrief entity : appBriefList) {
            i++;
            todayContentList.add("第 " + i + " 款是 " + entity.getName() + "，");
            todayContentList.add("本应用原价 " + entity.getPrice() + " 元，");
            todayContentList.add("应用简介");
            List<String> content = entity.getContent();
            for (String str : content) {
                if(str.length() < 10) {

                    todayContentList.add(str);
                } else {
                    String[] arr = str.split("，");
                    todayContentList.addAll(Arrays.asList(arr));
                }
            }
//            todayContentList.addAll(entity.getContent());
        }
        todayContentList.add("感谢大家的一键三连，唯有观众老爷的支持，才能让 up 主走得更远。");
        todayContentList.add("好了，今天的视频到此结束，祝大家身体健康！");

        return todayContentList;
    }

    public static void genMerGeek() {
        List<AppInfo> appBriefList = new ArrayList<>();
        AppInfo appInfo = new AppInfo();
        String monthStr = new SimpleDateFormat("yyyyMM").format(new Date());
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String path = "D:" + File.separator + "12_iOS_Android" + File.separator + monthStr + File.separator + dateStr;
        String txtFileName = File.separator + path + File.separator + dateStr + "_Mergeek.txt";

        List<String> rawStrList = TxtUtils.readTxtFileToList(txtFileName);
        Map<Integer, Integer> freeIndexMap = new LinkedHashMap<>();
        int i = 0;
        int count = 1;
        for (String rawStr : rawStrList) {
//            int flag = rawStr.lastIndexOf("https://apps.apple.com");
//            if (flag != -1) {
//                urlList.add(rawStr);
//            }
            appInfo.setCategory(rawStr);
            if ("FREE".equals(rawStr)) {
                freeIndexMap.put(count, i);
                count++;
            }
            i++;
        }


        //  `name` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
        String name;

//  `seller` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,

        //  `price` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '价格',
        Integer price;

        //  `category` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '分类',
        String category;
        String osType;

        //  `updated` date NULL DEFAULT NULL,
        Date updated;
        Integer rateAmount;
        //  `rating_current` decimal(2, 1) NULL DEFAULT NULL,
        Double ratingCurrent;
        List<String> content;
        String freeDate;
        Integer lastIndex = 0;
        for (Map.Entry<Integer, Integer> entry : freeIndexMap.entrySet()) {
            appInfo = new AppInfo();
            Integer freeIndex = entry.getValue();
            category = rawStrList.get(lastIndex);
            if ("正在加载中...".equals(category) || "已经加载完毕啦~".equals(category)) {
                continue;
            }
            appInfo.setCategory(category);
            name = rawStrList.get(lastIndex + 1);
            appInfo.setName(name);
            System.out.println(lastIndex + "_" + freeIndex);
            try {
                ratingCurrent = Double.parseDouble(rawStrList.get(lastIndex + 2));
                appInfo.setRatingCurrent(ratingCurrent);
            } catch (Exception e) {
                System.out.println(rawStrList.get(lastIndex + 2));
                System.out.println(e.getMessage());
            }
            // · 49 个评分
            String rateAmountStr = rawStrList.get(lastIndex + 3);
            rateAmountStr = rateAmountStr.replaceAll("·", "");
            rateAmountStr = rateAmountStr.replaceAll(" ", "");
            rateAmountStr = rateAmountStr.replaceAll("个评分", "");
            content = new ArrayList<>();
            for (int j = lastIndex + 4; j < freeIndex - 2; j++) {
                String raw = rawStrList.get(j); // —
                if (-1 != raw.lastIndexOf("：") || -1 != raw.lastIndexOf("—") || -1 != raw.lastIndexOf("®") || -1 != raw.lastIndexOf("@") || -1 != raw.lastIndexOf("http")) {
                    continue;
                } else if (-1 != raw.lastIndexOf("！")) {
                    content.add(raw);
                    continue;
                } else {
                    content.add(raw);
                }
            }
            appInfo.setContent(content);
            try {
                rateAmount = Integer.parseInt(rateAmountStr);
                appInfo.setRateAmount(rateAmount);
            } catch (Exception e) {
                System.out.println(rawStrList.get(lastIndex + 3));
                System.out.println(e.getMessage());
            }
            String priceStr = rawStrList.get(freeIndex - 2);

            priceStr = priceStr.replaceAll(" ", "");
            priceStr = priceStr.replaceAll("原价", "");
            priceStr = priceStr.replaceAll("元", "");
            try {
                price = Integer.parseInt(priceStr);
                appInfo.setPrice(price);
            } catch (Exception e) {
                System.out.println(rawStrList.get(lastIndex + 3));
                System.out.println(e.getMessage());
            }

            String osTypeString = rawStrList.get(freeIndex - 1);
            String[] arr = osTypeString.split(" ");
            if (arr.length == 2) {
                osType = arr[0];
                appInfo.setOsType(osType);
                freeDate = arr[1];
                appInfo.setFreeDate(freeDate);
            } else {
                System.out.println("###osTypeString: " + osTypeString);
            }
//            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
//
//            try {
//                freeDate = arr[1]; //simpleDateFormat.parse(arr[1]);
//                appInfo.setFreeDate(freeDate);
//            } catch (Exception e) {
//                System.out.println(rawStrList.get(lastIndex + 3));
//                System.out.println(e.getMessage());
//            }

            appBriefList.add(appInfo);
            lastIndex = freeIndex + 1;
        }


        for (AppInfo entity : appBriefList) {
            String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            if (entity.getFreeDate().equals(todayStr)) {
                System.out.println(entity.getName() + " : " + entity.getPrice());
            }
        }
    }
}
