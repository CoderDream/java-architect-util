package com.coderdream.freeapps.util.boss;

import lombok.Data;
import lombok.ToString;
import java.util.List;

@Data
@ToString
public class WorkInfo {

    String jobName;//职位名称
    String jobArea;//工作地区
    String jobPubTime;//发布时间
    String education;//学历
    String jobLimit;//限制（工作n年经验、在校/应届）
    List<String> tags;//标签
    String detailUrl;//职位详细详细url
    WorkDetail workDetail;//岗位要求详细信息
    Company company;//公司信息

}


