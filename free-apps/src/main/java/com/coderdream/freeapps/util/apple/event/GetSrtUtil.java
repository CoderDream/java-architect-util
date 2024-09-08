package com.coderdream.freeapps.util.apple.event;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.coderdream.freeapps.model.SubtitleBaseEntity;
import com.coderdream.freeapps.util.callapi.BossJobInfo;
import com.coderdream.freeapps.util.callapi.HttpUtil;
import com.coderdream.freeapps.util.other.CdFileUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetSrtUtil {

  //  private final static String SRT_PRIO = "https://events-delivery.apple.com/2403kaqfcpzjjnpkkkkmbtyqacnyrknu/vod_main_BHKucdGHJBgbQdLdPafCRNCACvstwWNC/cc/";

    private final static String SRT_PRIO =  "https://events-delivery.apple.com/1505clvgxdwlbjrjhxtjdgcdxaiabvuf/vod_main_BveVQvhWftXzpUakjHjEUkbmUYLbRdcV/cc/";
    private final static String END = ".webvtt";
    private final static String CN_PRIO = "zh/zh_";
    private final static String EN_PRIO = "en/en_";

    private final static String CN_END = "https://events-delivery.apple.com/1008yunfyueoofvvgxpjmmvcblgnjsel/vod_main_bTVAkbqTcJuRovcmMRYungakLuiQyTWT/cc/zh/zh_0.webvtt";

    private final static String EN_END = "https://events-delivery.apple.com/1008yunfyueoofvvgxpjmmvcblgnjsel/vod_main_bTVAkbqTcJuRovcmMRYungakLuiQyTWT/cc/en/en_0";

    // zh/zh_0.webvtt
    public static void main(String[] args) {

//        Integer SIZE = 379;
        Integer SIZE = 1025;

        String urlBase = "https://events-delivery.apple.com/1505clvgxdwlbjrjhxtjdgcdxaiabvuf/vod_main_BveVQvhWftXzpUakjHjEUkbmUYLbRdcV/";


        List<String> urlCnList = new ArrayList<>();
        List<String> urlEnList = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            System.out.println(SRT_PRIO + CN_PRIO + i + END);
            urlCnList.add(SRT_PRIO + CN_PRIO + i + END);
        }

        for (int i = 0; i < SIZE; i++) {
            System.out.println(SRT_PRIO + EN_PRIO + i + END);
            urlEnList.add(SRT_PRIO + EN_PRIO + i + END);
        }
//        m1();
        String FILE_PRIFIX = "Apple Event — May 7.";

        m3(urlEnList, FILE_PRIFIX + "eng.srt");
        m3(urlCnList, FILE_PRIFIX + "chn.srt");
    }

    /**
     *
     * @param urlList
     * @param fileName
     */
    public static void m3(List<String> urlList, String fileName) {
        List<String> result = new ArrayList<>();
        int index = 1;
        List<SubtitleBaseEntity> subtitleBaseEntityList = m2(urlList);
        for (SubtitleBaseEntity subtitleBaseEntity : subtitleBaseEntityList) {
            result.add(""+ index);
            result.add(subtitleBaseEntity.getTimeStr());
            result.add(subtitleBaseEntity.getSubtitle());
            result.add("");
            index++;
        }


        // 写中文翻译文本
        CdFileUtils.writeToFile(fileName, result);
//        return result;
    }


    /**
     *
     * @param urlList
     * @return
     */
    public static List<SubtitleBaseEntity> m2(List<String> urlList) {
        List<SubtitleBaseEntity> result = new ArrayList<>();
        for (String url : urlList) {
            List<SubtitleBaseEntity> strings = m1(url);
            result.addAll(strings);
        }

        return result;
    }


    /**
     *
     * @param urlList
     * @return
     */
    public static String srt2Txt(List<String> urlList) {
        List<SubtitleBaseEntity> result = new ArrayList<>();
        for (String url : urlList) {
            List<SubtitleBaseEntity> strings = m1(url);
            result.addAll(strings);
        }

        return null;

//        return result;
    }

    /**
     *
     * @param url
     * @return
     */

    public static List<SubtitleBaseEntity> m1(String url) {
        Map<String, Object> requestParam = new LinkedHashMap<>();

//        String token = "";

        Map<String, String> requestHead = new HashMap<>();
        requestHead.put("Accept", "application/json, text/javascript, */*; q=0.01");
        requestHead.put("Accept-Encoding", "gzip, deflate, br");
        requestHead.put("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        requestHead.put("Referer",
            "https://www.zhipin.com/web/geek/job?query=java%E9%AB%98%E7%BA%A7&city=101200100&areaBusiness=420111%3A908");
        requestHead.put("Sec-Ch-Ua", "\"Google Chrome\";v=\"113\", \"Chromium\";v=\"113\", \"Not-A.Brand\";v=\"24\"");
        requestHead.put("Cookie",
            "lastCity=101200100; wd_guid=95a92633-74e8-477e-bba4-956a27994955; historyState=state; _bl_uid=77lz5fjt8pUaXy5j5p1LlF3ptbt2; wt2=DwMimGfLSGmiOdxoeUkaMJuHCFhpwvyiogspq1bwwtlQbOZjYGnS8hVF5mfvcSJKiAgQjlv5aOvqZS4RIOl-UbA~~; wbg=0; __g=-; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1685444753,1685495555,1685581140,1685669412; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1685670718; __c=1685669412; __l=l=%2Fwww.zhipin.com%2Fweb%2Fgeek%2Fjob%3Fquery%3DJava%26city%3D101200100%26areaBusiness%3D420111%253A908&r=&g=&s=3&friend_source=0&s=3&friend_source=0; __a=99514548.1678799963.1685581140.1685669412.53.5.9.53; geek_zp_token=V1Q9ogE-P601dgXdNhyhsfLi2z7zjSxA~~; __zp_stoken__=40daeGnw4NCMqLTcNT3JwdlFsS1kLXTtmK2ZhOyAZZFQgKVJAEi1Na1dTbiZPU3F%2BD2RWBhlfN2dkUXpAMg93QmBQfysBHy0UHwRbDwFfSiVUAxJ9Ij8CFiUmTWNadBg1TV1kfRxWXQVyYXQ%3D");

        requestHead.put("Sec-Ch-Ua-Mobile", "?0");
//        requestHead.put("Sec-Ch-Ua-Mobile", "?0");
//        requestHead.put("Sec-Ch-Ua-Mobile", "?0");
//        requestHead.put("Sec-Ch-Ua-Mobile", "?0");

//        String url = "https://events-delivery.apple.com/2403kaqfcpzjjnpkkkkmbtyqacnyrknu/vod_main_BHKucdGHJBgbQdLdPafCRNCACvstwWNC/cc/en/en_373.webvtt";

        SubtitleBaseEntity subtitleBaseEntity = null;

        List<SubtitleBaseEntity> subtitleBaseEntityList = new ArrayList<>();
//        BossJobInfo bossJobInfo =
        List<String> stringList = HttpUtil.getText(requestParam, requestHead, url,
            5);

        if (CollectionUtil.isNotEmpty(stringList)) {
            int size = stringList.size();
            if (size >= 6) {
//                System.out.println("OK");

                String timeStr = "";

                /**
                 * 第一字幕内容
                 */
                String subtitle = "";

                /**
                 * 第二字幕内容
                 */
                String subtitleSecond = "";
                for (int i = 3; i < size-2; i++) {
                    subtitleBaseEntity = new SubtitleBaseEntity();

                    timeStr = stringList.get(i);
                    int alignIndex = timeStr.lastIndexOf("align");
                    timeStr = timeStr.substring(0, alignIndex - 1);
                    subtitleBaseEntity.setTimeStr(timeStr.trim());
                    subtitle = stringList.get(i + 1);

                    if(i+2 < size) {
                        subtitleSecond = stringList.get(i + 2);
                    } else {
                        System.out.println("###### ERROR");
                    }
                    if (StrUtil.isNotEmpty(subtitleSecond)) {
                        subtitle = subtitle + subtitleSecond;
                        i += 3;
                    } else {
                        i += 2;
                    }

                    subtitleBaseEntity.setSubtitle(subtitle);
                    subtitleBaseEntityList.add(subtitleBaseEntity);
                }
            }
        }

//        System.out.println("###");

        return subtitleBaseEntityList;
    }

}
