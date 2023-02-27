package com.coderdream.mybatisplusdemo.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.coderdream.mybatisplusdemo.data.ExtraNode;
import com.coderdream.mybatisplusdemo.handler.ObjectAndJsonHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName(value = "extra_info", autoResultMap = true)
public class ExtraInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private ExtraNode extraObject;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONObject extraJson;

    @TableField(typeHandler = ObjectAndJsonHandler.class)
    private List<ExtraNode> extraList;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private ExtraNode[] extraArray;
}
