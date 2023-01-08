package com.demo.merchant.restapi.controller;

import com.demo.merchant.domain.entity.Merchant;
import com.demo.merchant.domain.entity.Role;
import com.demo.merchant.domain.entity.User;
import com.demo.merchant.domain.service.MerchantService;
import com.demo.merchant.domain.service.RoleService;
import com.demo.merchant.domain.service.UserService;
import com.demo.merchant.domain.util.CommonUtils;
import com.demo.merchant.domain.util.CopyUtil;
import com.demo.merchant.object.MerchantQo;
import com.demo.merchant.object.RoleQo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    private static Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/{id}")
    public CompletableFuture<String> findById(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> merchantService.findOne(id))
                .thenApply(merchant ->{
                    return new Gson().toJson(merchant);
                });
    }

    @RequestMapping("/list")
    public CompletableFuture<String> getList() {
        return CompletableFuture.supplyAsync(() -> {
            List<Merchant> merchants = merchantService.findAll();
            return new Gson().toJson(merchants);
        });
    }

    @RequestMapping(value = "/page")
    public CompletableFuture<String> findPage(Integer index, Integer size, String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                MerchantQo merchantQo = new MerchantQo();

                if(CommonUtils.isNotNull(index)){
                    merchantQo.setPage(index);
                }
                if(CommonUtils.isNotNull(size)){
                    merchantQo.setSize(size);
                }
                if(CommonUtils.isNotNull(name)){
                    merchantQo.setName(name);
                }

                Page<Merchant> merchantPage = merchantService.findAll(merchantQo);

                Map<String, Object> page = new HashMap<>();
                page.put("content", merchantPage.getContent());
                page.put("totalPages", merchantPage.getTotalPages());
                page.put("totalelements", merchantPage.getTotalElements());

                return new Gson().toJson(page);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody MerchantQo merchantQo) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            Merchant merchant = CopyUtil.copy(merchantQo, Merchant.class);

            merchantService.save(merchant);

            //新建商家增加默认用户
            RoleQo roleQo = new RoleQo();
            roleQo.setSize(1);
            List<Role> roles = roleService.findAll(roleQo).getContent();

            User user = new User();
            user.setMerchant(merchant);

            user.setName(merchantQo.getUserName());

            BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
            user.setPassword(bpe.encode(merchantQo.getPassWord()));

            user.setRoles(roles);

            userService.save(user);

            logger.info("新增->ID=" + merchant.getId());
            return "1";
        });
    }

    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public CompletableFuture<String> update(@RequestBody MerchantQo merchantQo) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            Merchant merchant = CopyUtil.copy(merchantQo, Merchant.class);

            merchantService.save(merchant);

            logger.info("修改->ID=" + merchant.getId());
            return "1";
        });
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Long id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            merchantService.delete(id);
            logger.info("删除->ID=" + id);
            return "1";
        });
    }
}
