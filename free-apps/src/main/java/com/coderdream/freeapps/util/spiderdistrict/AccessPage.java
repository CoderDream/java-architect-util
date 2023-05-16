package com.coderdream.freeapps.util.spiderdistrict;

import java.util.ArrayList;
import java.util.HashMap;

public class AccessPage {
    //数据处理，把信息中的Url返回给核心，文本信息储存
    public ArrayList<String> dataAccess(HashMap<String, ArrayList<String>> stringArrayListHashMap){
        return stringArrayListHashMap.get("url");
    }
}
