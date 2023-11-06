/**
 * Copyright 2023 json.cn
 */
package com.coderdream.freeapps.util.callapi;

import java.util.List;
import lombok.Data;
import lombok.ToString;

/**
 * Auto-generated: 2023-06-02 11:6:29
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
@ToString
public class ZpData {

    private int resCount;
    private String filterString;
    private String lid;
    private boolean hasMore;
    private List<JobInfo> jobList;
    private int totalCount;
    private String brandCard;

    private String name;
    private String seed;
    private String ts;

}
