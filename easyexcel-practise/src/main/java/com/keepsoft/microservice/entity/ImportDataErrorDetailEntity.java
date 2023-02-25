package com.keepsoft.microservice.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 导入数据错误明细
 * @Date 2022/10/8 20:28
 */
@TableName(value = "import_data_error_detail", autoResultMap = true)
@Data
public class ImportDataErrorDetailEntity implements Serializable {
    private static final long serialVersionUID = -1242493306307174690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务id
     */
    @ApiModelProperty("任务id")
    @TableField(value = "task_id")
    private String taskId;

    /**
     * 文件类型，如：对象结构出错列表、对象数据-数值类数据出错列表、对象数据-曲线类数据出错列表、对象数据-时序数据出错列表、对象数据-预报时序数据出错列表
     */
    @ApiModelProperty("文件类型")
    @TableField(value = "file_type")
    private String fileType;

    /**
     * 数据类型，如：对象实例、属性类型、值数据等等，与SheetName相同
     */
    @ApiModelProperty("数据类型")
    @TableField(value = "data_type")
    private String dataType;

//    /**
//     * 错误详情
//     */
//    @ApiModelProperty("错误详情")
//    @TableField(value = "error_info", typeHandler = JacksonTypeHandler.class)
//    private String errorInfo;

    /**
     * 错误详情
     */
    @ApiModelProperty("错误详情")
    @TableField(value = "error_info")
    private JSONObject errorInfo;

//    @TableField(value = "error_info", javaType =true, jdbcType = JdbcType.OTHER, typeHandler = JsonObjectTypeHandler.class)


}
