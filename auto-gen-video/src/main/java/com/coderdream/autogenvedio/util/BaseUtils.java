package com.coderdream.autogenvedio.util;

import com.coderdream.autogenvedio.entity.AppBrief;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseUtils {

    public static void main(String[] args) {
//        int a = 10, b = 3;
//        System.out.println(a % b);
//        List<String> urlList = new ArrayList<>(Arrays.asList("https://apps.apple.com/cn/app/id1592844577",
//                "https://apps.apple.com/cn/app/id1578843767",
//                "https://apps.apple.com/cn/app/id1536585848",
//                "https://apps.apple.com/cn/app/id425893570",
//                "https://apps.apple.com/cn/app/id1510078277",
//                "https://apps.apple.com/cn/app/id1255192598",
//                "https://apps.apple.com/cn/app/id598710611",
//                "https://apps.apple.com/cn/app/id6443737201"));
//        List<Object> objectList = new ArrayList<>();
//        objectList.addAll(urlList);
//        splitObjectList(3, objectList);


        List<AppBrief> appBriefList = BaseUtils.genBrief();
        for (AppBrief appBrief:
        appBriefList) {
            System.out.println(appBrief);
        }
//        BaseUtils.splitAppBriefList(3, appBriefList);
    }

    public static List<List<AppBrief>> splitAppBriefList(int subListSize, List<AppBrief> appBriefList) {
        List<List<AppBrief>> lists = new ArrayList<>();

        List<AppBrief> tempList = new ArrayList<>();
        int tempIndex = 0;
        for (AppBrief appBrief : appBriefList) {
            System.out.println(appBrief);
            tempList.add(appBrief);
            tempIndex++;
            if (tempIndex % subListSize == 0) {
                lists.add(tempList);
                tempList = new ArrayList<>();
            }
        }

        if (!CollectionUtils.isEmpty(tempList)) {
            lists.add(tempList);
        }
        System.out.println("####");

        return lists;
    }

    public static void splitObjectList(int subListSize, List<Object> list) {
        List<List<Object>> lists = new ArrayList<>();


        List<Object> tempList = new ArrayList<>();
        int tempIndex = 0;
        for (Object object : list) {

            if (object instanceof String) {
                String str = (String) object;
                System.out.println(str);
                tempList.add(str);
                tempIndex++;
                if (tempIndex % subListSize == 0) {
                    lists.add(tempList);
                    tempList = new ArrayList<>();
                }
            }
        }

        if (!CollectionUtils.isEmpty(tempList)) {
            lists.add(tempList);
        }
        System.out.println("####");
    }

    public static String getPath() {
        String monthStr = new SimpleDateFormat("yyyyMM").format(new Date());
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String path = "D:" + File.separator + "12_iOS_Android" + File.separator + monthStr + File.separator + dateStr;
        return path;
    }

    public static List<AppBrief> genBrief() {
        List<AppBrief> appBriefList = new ArrayList<>();
        AppBrief appBrief;
        String monthStr = new SimpleDateFormat("yyyyMM").format(new Date());
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String path = getPath();
        String txtFileName = File.separator + path + File.separator + dateStr + ".txt";

        List<String> rawStrList = TxtUtils.readTxtFileToList(txtFileName);
        Map<Integer, Integer> freeIndexMap = new LinkedHashMap<>();
        int i = 0;
        int count = 1;
        for (String rawStr : rawStrList) {
            if (-1 != rawStr.lastIndexOf("https")) {
                freeIndexMap.put(count, i);
                count++;
            }
            i++;
        }

        //  `name` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
        String name;

        //  `price` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '价格',
        Integer price;

        String osType = "";

        List<String> content;
        String url;
        String urlCn;
        String appId;
        Integer lastIndex = 0;
        Integer oldOsTypeIndex = 0;
        Integer osTypeIndex = 0;
        String tempStr;
//        String osType;
        int z = 1;
        for (Map.Entry<Integer, Integer> entry : freeIndexMap.entrySet()) {
            appBrief = new AppBrief();
            Integer freeIndex = entry.getValue();

            tempStr = rawStrList.get(lastIndex);
            if ("今日苹果iOS 限免软件".equals(tempStr) && osType.isEmpty()) {
                osType = "iOS";
//                osTypeIndex = lastIndex;
                lastIndex++;
                // continue;
            }
            if ("MAC限免软件".equals(tempStr) || "MAC限免软件\u200B".equals(tempStr)) {
                osType = "MAC";
//                osTypeIndex = lastIndex;
                lastIndex++;
                // continue;
            }
            appBrief.setOsType(osType);

            name = rawStrList.get(lastIndex);
            appBrief.setName(name.trim());
            if (name.trim().equals("玛")) {
                System.out.println("####");
            }
            System.out.println(lastIndex + "_" + freeIndex);
            lastIndex++;
            String priceStr = rawStrList.get(lastIndex);

            priceStr = priceStr.replaceAll("➱0", "");
            priceStr = priceStr.replaceAll("→0", "");
            priceStr = priceStr.replaceAll("→0", "");
            priceStr = priceStr.replaceAll("➱ 0", "");
            priceStr = priceStr.replaceAll("→ 0", "");
            priceStr = priceStr.replaceAll(" 0", "");
            priceStr = priceStr.replaceAll(" ", "");
            priceStr = priceStr.replaceAll("¥", "");
            priceStr = priceStr.replaceAll("￥", "");
            priceStr = priceStr.replaceAll("元", "");
            String cashType = "RMB";
            if(priceStr.lastIndexOf("$") != -1) {
                priceStr = priceStr.replaceAll("\\$", "");
                cashType = "USD";
            }

            try {
                if(cashType.equals("USD")) {
                    Double doublePrice = Double.parseDouble(priceStr);
                    doublePrice *= 6;
                    price = (int) Math.round(doublePrice);
                } else {
                    price = Integer.parseInt(priceStr);
                }
                System.out.println("final price: " + price);
                appBrief.setPrice(price);
            } catch (Exception e) {
                System.out.println(rawStrList.get(lastIndex));
                System.out.println(e.getMessage());
            }

            lastIndex++;
            content = new ArrayList<>();
            String fileName;
            for (int j = lastIndex; j < freeIndex; j++) {
                String raw = rawStrList.get(j); // —
//                if (-1 != raw.lastIndexOf("：") || -1 != raw.lastIndexOf("—") || -1 != raw.lastIndexOf("®") || -1 != raw.lastIndexOf("@") || -1 != raw.lastIndexOf("http")) {
//                    continue;
//                } else if (-1 != raw.lastIndexOf("！")) {
//                    content.add(raw);
//                    continue;
//                } else {
//                    content.add(raw);
//                }
                if(-1 != raw.lastIndexOf("外区限免。") || -1 != raw.lastIndexOf("美区限免。")) {
                    appBrief.setOnlyUs(true);
                }
                content.add(raw);
            }
            appBrief.setContent(content);

            url = rawStrList.get(freeIndex);
            appBrief.setUrl(url);
            appId = StringUtils.parseAppId(url);
            appBrief.setAppId(appId);
            fileName = String.format("%02d", z) + "_" + appId;
            appBrief.setFilename(fileName);
            z++;
            if(appBrief.getOnlyUs()) {
                urlCn= url;
            } else {
                urlCn = "https://apps.apple.com/cn/app/" + appId;
            }
            appBrief.setUrlCn(urlCn);

            // String.format("%02d", i)
            if (-1 != urlCn.lastIndexOf("https://apps.apple.com/")) {
                appBrief.setSnapshotPath(path + File.separator + "snapshot" + File.separator + fileName + ".png");
                appBrief.setQrUrl(path + File.separator + "qr" + File.separator + "qr_" + fileName + ".png");
                appBrief.setIconUrl(path + File.separator + "icon" + File.separator + "icon_" + fileName + ".png");
                appBrief.setDetailPath(path + File.separator + "detail" + File.separator + "detail_" + fileName + ".png");
                appBrief.setSinglePosterPath(path + File.separator + "single" + File.separator + "single_" + fileName + ".png");
                appBrief.setListAppsPath(path + File.separator + "list" + File.separator); // 只是路径
                appBrief.setPagePosterPath(path + File.separator + "page" + File.separator);  // 只是路径
                appBriefList.add(appBrief);
            }

            lastIndex = freeIndex + 1;
        }

        return appBriefList;
    }

}
