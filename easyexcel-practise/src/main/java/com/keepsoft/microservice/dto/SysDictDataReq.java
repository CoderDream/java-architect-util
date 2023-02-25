package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据表 sys_dict_data
 *
 * @author keepsoft
 */
@ApiModel("对象属性响应")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDictDataReq {
    private static final long serialVersionUID = 1L;

    /** 字典编码 */
    @ApiModelProperty(name = "字典编码")
    private Integer pageNum;

    /** 字典编码 */
    @ApiModelProperty(name = "字典编码")
    private Integer pageSize;

    /** 字典编码 */
    @ApiModelProperty(name = "字典编码")
    private String dictType;

//    Integer pageNum,Integer pageSize, String dictType

}
