package com.coderdream.freeapps.controller;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 */
@Data
public class SyncTaskReqDto implements Serializable {
    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Integer taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 每次执行的天数
     */
    private Integer processPeriod;

    /**
     * 最后一次完成任务的日期
     */
    private Date lastProcessDate;

    /**
     * 更新的记录条数
     */
    private Long updateRecord;


}
