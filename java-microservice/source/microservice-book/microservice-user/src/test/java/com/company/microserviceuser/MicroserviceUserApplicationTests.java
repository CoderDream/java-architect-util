package com.company.microserviceuser;

// import org.junit.jupiter.api.Test;
import com.company.microserviceuser.annotation.MyAnnotation;
import com.company.microserviceuser.dto.DateOutputDTO;
import com.company.microserviceuser.utils.AnnotationUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.microserviceuser.vo.*;
import com.company.microserviceuser.dao.*;
import com.company.microserviceuser.dto.UserAndRoleOutputDTO;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author xindaqi
 * @since 2020-10-26
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroserviceUserApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(MicroserviceUserApplicationTests.class);

	@Resource
	private UserDAO userDao;

	@Test
	public void contextLoads() {
	}

	/**
	 * 查询用户及角色
	 */
	@Test
	public void queryRoleList() {
		long id = 1L;
		UserAndRoleOutputDTO userAndRoleList = userDao.queryUserAndRole(id);
		logger.info("用户角色列表：{}", userAndRoleList);
	}

	/**
	 * 测试注解
	 */
	@Test
	public void annotationTest() throws Exception {
		Class<?> clzz = AnnotationUtil.class;
		if(clzz.isAnnotationPresent(Component.class)) {
			Method[] methods = clzz.getDeclaredMethods();
			for(Method method : methods) {
				if(method.isAnnotationPresent(MyAnnotation.class)) {
					MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
					String annotationValue = myAnnotation.name();
					logger.info("注解内的值：{}", annotationValue);
					method.invoke(new AnnotationUtil());
				}
			}

		}
	}

	/**
	 * 测试数据库Date类型数据映射实体String
	 */
	@Test
	public void queryDateInDatabase() {
		List<DateOutputDTO> dateOutputDTOList = userDao.queryDateInDatabase();
		logger.info("Data:{}", dateOutputDTOList);
		Assert.assertEquals("2021-01-26", dateOutputDTOList.get(0).getDate());
	}


}
