package com.coderdream.freeapps.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: JobInfo
 * @Description: JobInfo实体类
 * @author: DaPeng
 * @date: 2021年01月04日 下午4:33:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_lg_job_info")
public class JobInfo {

	@TableField(value = "position_name")
	private String positionName;

	@TableField(value = "work_year")
	private String workYear;

	@TableField(value = "salary")
	private String salary;

	@TableField(value = "address")
	private String address;

	@TableField(value = "district")
	private String district;

	@TableField(value = "create_time")
	private String createTime;

	@TableField(value = "company_name")
	private String companyName;

	@TableField(value = "description")
	private String description;

}
