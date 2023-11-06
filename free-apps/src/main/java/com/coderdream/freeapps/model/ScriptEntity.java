package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;

/**
 * @author CoderDream
 * @TableName t_subtitle
 */
@Data
public class ScriptEntity implements Serializable {

    /**
     * 说话的人，包括主持人和嘉宾
     */
    private String talker;

    /**
     * 说话内容
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(subIndex + "\n");
//        sb.append(subtitle + "\n");
//        sb.append(timeStr + "\n\n");
//        return sb.toString();
//    }
}
