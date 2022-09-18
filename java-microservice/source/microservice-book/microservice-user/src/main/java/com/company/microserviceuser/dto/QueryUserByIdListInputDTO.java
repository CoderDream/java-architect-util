package com.company.microserviceuser.dto;

import lombok.Data;

import java.util.List;

/**
 * User id list.
 * @author xindaqi
 * @since 2020-11-09
 */
@Data
public class QueryUserByIdListInputDTO {

    private List<Long> idList;

    private Integer pageNum;

    private Integer pageSize;

}