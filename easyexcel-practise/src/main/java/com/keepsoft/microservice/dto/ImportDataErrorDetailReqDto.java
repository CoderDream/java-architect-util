package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author 导入数据错误明细
 * @Date 2022/10/8 20:28
 */
@ApiModel("导入数据错误明细")
@Data
@EqualsAndHashCode(callSuper = false)
public class ImportDataErrorDetailReqDto implements Serializable {
    private static final long serialVersionUID = -1242493306307174690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 任务id
     */
    @ApiModelProperty("任务id")
    private String taskId;

    /**
     * 文件类型，如：对象结构出错列表、对象数据-数值类数据出错列表、对象数据-曲线类数据出错列表、对象数据-时序数据出错列表、对象数据-预报时序数据出错列表
     */
    @ApiModelProperty("文件类型")
    private String fileType;

    /**
     * 数据类型，如：对象实例、属性类型、值数据等等，与SheetName相同
     */
    @ApiModelProperty("数据类型")
    private String dataType;


    /**
     * 错误详情
     */
    @ApiModelProperty("错误详情")
    private String errorInfo;

}
