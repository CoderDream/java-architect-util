package com.coderdream.freeapps.util.easyexcel;

import lombok.Data;

/**
 * @author CoderDream
 */
@Data
public class ArchiveDocsDTO{

    private Integer idx;

    // 资料类型代码
    private String docType;

    private String docTypeName;

    // 姓名
    private String personName;

    // J号
    private String policeNo;

    // 身份证号
    private String idCard;

    // 编制单位名称
    private String dwName;

    // 归档状态
    private Integer status;
    private String statusName;

    // 归档人单位编码
    private String gdDwCode;
    private String gdDwCodeName;

    // 产生方式
    private Integer source;
    private String sourceName;

    private String taskId;

    // 材料名称
    private String docName;

    // 出生日期
    private String birthDay;
}
