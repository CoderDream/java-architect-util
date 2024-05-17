package com.coderdream.freeapps.util.srt;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        map.put(1,	10);
        map.put(2,	8 );
        map.put(3,	10);
        map.put(4,	11);
        map.put(5,	7 );
        map.put(6,	6 );
        map.put(7,	5 );
        map.put(8,	7 );
        map.put(9,	10);

        for(Integer key : map.keySet()) {
            Integer value = map.get(key);
            for(int i=1; i <= value; i++) {
                String url = "https://qr.cip.com.cn/html/qrcode/xiaoma/32617/mp3/" + key + "/" + key + "." + i + ".mp3";
                System.out.println(url);
            }
        }

    }

}
