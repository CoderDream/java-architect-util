package com.demo.manage.restapi.test;

import com.demo.manage.domain.config.JpaConfiguration;
import com.demo.manage.domain.entity.Department;
import com.demo.manage.domain.entity.Operators;
import com.demo.manage.domain.entity.Part;
import com.demo.manage.domain.service.DepartmentService;
import com.demo.manage.domain.service.OperatorService;
import com.demo.manage.domain.service.PartService;
import com.demo.manage.restapi.ManageRestApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class, ManageRestApiApplication.class})
@SpringBootTest
public class BbServiceTest {
    private static Logger logger = LoggerFactory.getLogger(BbServiceTest.class);
    @Autowired
    private OperatorService operatorService;
    @Autowired
    private PartService partService;
    @Autowired
    private DepartmentService departmentService;

    @Test
    public void insertData(){
        Part part = new Part();
        part.setName("admins");

        partService.save(part);

        Department department = new Department();
        department.setName("技术部");
        departmentService.save(department);

        Operators operators = new Operators();
        operators.setName("admin");
        operators.setSex(1);

        operators.setDepartment(department);

        List<Part> partList = operators.getParts();
        partList.add(part);
        operators.setParts(partList);

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        operators.setPassword(bc.encode("123456"));

        operatorService.save(operators);
        assert operators.getId() > 0 : "create error";
    }

    //@Test
    public void getData(){
        Operators operators = operatorService.findByName("admin");
        assert operators !=null : "not find";
        logger.info("=================operator name={}, part name={}, department name={}",
                operators.getName(), operators.getParts().get(0).getName(), operators.getDepartment().getName());
    }
}
