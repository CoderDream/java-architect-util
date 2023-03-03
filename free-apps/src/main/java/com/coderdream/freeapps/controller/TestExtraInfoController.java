package com.coderdream.freeapps.controller;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
//import com.alibaba.fastjson2.TypeReference;
import com.coderdream.freeapps.model.ExtraInfo;
import com.coderdream.freeapps.model.ExtraNode;
import com.coderdream.freeapps.service.ExtraInfoService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@RestController
//@RequestMapping("/test")
public class TestExtraInfoController {

    @Resource
    private ExtraInfoService extraInfoMapper;

    @GetMapping
    public List<ExtraNode> listAll() {
        List<ExtraNode> listExtraNode = new ArrayList<>();
        ExtraNode[] strArr = new ExtraNode[0];
        String str = null;
        String str2 = null;
        List<ExtraInfo> listExtraInfo = extraInfoMapper.selectAll();
        ExtraNode extraNode = new ExtraNode();
        for (ExtraInfo extraInfo : listExtraInfo) {
            listExtraNode = extraInfo.getExtraList();
            strArr = extraInfo.getExtraArray();
            extraNode = extraInfo.getExtraObject();
            str2 = extraInfo.getExtraJson().get("name").toString();
        }

        System.out.println(extraNode.getName());
        System.out.println(str2);
        //类型转换异常：java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to com.所以要用下面方法解决
        ObjectMapper mapper = new ObjectMapper();
        List<ExtraNode> list = mapper.convertValue(listExtraNode, new TypeReference<List<ExtraNode>>() {
        });

        str = list.stream().filter(f -> f.getId() == 2).map(ExtraNode::getName).collect(Collectors.joining(""));
        System.out.println(str);

        List<ExtraNode> listExtraNodeArr = new ArrayList<>(Arrays.asList(strArr));
        System.out.println(listExtraNodeArr.stream().map(ExtraNode::getName).collect(Collectors.joining(",")));
        return listExtraNode;
    }
}
