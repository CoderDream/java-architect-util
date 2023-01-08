package com.demo.merchant.restapi.test;

import com.demo.merchant.domain.config.JpaConfiguration;
import com.demo.merchant.domain.entity.*;
import com.demo.merchant.domain.service.*;
import com.demo.merchant.domain.util.CopyUtil;
import com.demo.merchant.object.MerchantQo;
import com.demo.merchant.object.UserQo;
import com.demo.merchant.restapi.MerchantRestApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class, MerchantRestApiApplication.class})
@SpringBootTest
public class UserTest {
    private static Logger logger = LoggerFactory.getLogger(UserTest.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private KindService kindService;
    @Autowired
    private MerchantService merchantService;

    //@Test
    public void insertData(){
        Kind kind = new Kind();
        kind.setName("商家系统");
        kind.setLink("merchantweb");
        kindService.save(kind);
        Assert.notNull(kind.getId(), "create kind error");

        Model model = new Model();
        model.setName("用户管理");
        model.setHost("/user/index");
        model.setKind(kind);
        modelService.save(model);
        Assert.notNull(model.getId(), "create model error");

        Resource resource = new Resource();
        resource.setName("用户修改");
        resource.setUrl("/user/edit/**");
        resource.setModel(model);
        resourceService.save(resource);
        Assert.notNull(resource.getId(), "create resource error");

        Role role = new Role();
        role.setName("商家管理员");
        List<Resource> resources = new ArrayList<>();
        resources.add(resource);
        role.setResources(resources);
        roleService.save(role);
        Assert.notNull(role.getId(), "create role error");

        Merchant merchant = new Merchant();
        merchant.setName("测试商家");
        merchantService.save(merchant);
        Assert.notNull(merchant.getId(), "create merchant error");

        User user = new User();
        user.setName("test");
        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
        user.setPassword(bpe.encode("test"));
        user.setEmail("user1@com.cn");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        user.setMerchant(merchant);
        userService.save(user);
        Assert.notNull(user.getId(), "create user error");
    }

    //@Test
    public void update(){
        User user = userService.findByName("user");
        Assert.notNull(user, "user not find");
        Merchant merchant = merchantService.findOne(1L);
        Assert.notNull(merchant, "merchant not find");

        user.setMerchant(merchant);
        userService.save(user);
    }

    //@Test
    public void getData(){
        User user = userService.findOne(1L);
        Assert.notNull(user, "not find");
        logger.info("===============name={},role name ={}", user.getName(), user.getRoles().get(0).getName());
    }

    //@Test
    public void delData(){
        userService.delete(2L);
        //Assert.notNull(orders, "");
    }

    @Test
    public void findAll() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2017-01-01 00:00:00");
        UserQo userQo = new UserQo();
        userQo.setCreated(date);

        Merchant merchant = merchantService.findOne(1L);
        MerchantQo merchantQo = CopyUtil.copy(merchant, MerchantQo.class);
        userQo.setMerchant(merchantQo);

        Page<User> page = userService.findAll(userQo);

        Assert.notEmpty(page.getContent(), "list is empty");
        List<User> list = page.getContent();
        for(User user : list){
            logger.info("============user name={},role name={},resource name={},model name={}",
                    user.getName(), user.getRoles().get(0).getName(),
                    user.getRoles().get(0).getResources().get(0).getName(),
                    user.getRoles().get(0).getResources().get(0).getModel().getName());
        }
    }
}
