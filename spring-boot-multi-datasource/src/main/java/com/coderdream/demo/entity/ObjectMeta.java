package com.coderdream.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 1064 - You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version
 * for the right syntax to use near 'LONG NOT NULL AUTO_INCREMENT PRIMARY KEY' at line 2
 */
@Data
@ToString
public class ObjectMeta {
    private String stcd;
    private String objectType;
    private String objectName;
    private Integer objectNameIndex; // 出现第几次
    private String objectNameNew; // 杨家湾(一)
    private String remark;
}
