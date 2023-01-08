package com.demo.merchant.restapi.controller;

import com.demo.merchant.domain.entity.Model;
import com.demo.merchant.domain.entity.Resource;
import com.demo.merchant.domain.entity.Role;
import com.demo.merchant.domain.service.ResourceService;
import com.demo.merchant.domain.service.RoleService;
import com.demo.merchant.domain.util.CommonUtils;
import com.demo.merchant.domain.util.CopyUtil;
import com.demo.merchant.object.ResourceQo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    private static Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/{id}")
    public CompletableFuture<String> findById(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> resourceService.findOne(id))
                .thenApply(resource ->{
                    return new Gson().toJson(resource);
                });
    }

    @RequestMapping("/list")
    public CompletableFuture<String> getList() {
        return CompletableFuture.supplyAsync(() -> {
            List<Resource> resources = resourceService.findAll();
            return new Gson().toJson(resources);
        });
    }

    @RequestMapping(value = "/page")
    public CompletableFuture<String> findPage(Integer index, Integer size, String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ResourceQo resourceQo = new ResourceQo();

                if(CommonUtils.isNotNull(index)){
                    resourceQo.setPage(index);
                }
                if(CommonUtils.isNotNull(size)){
                    resourceQo.setSize(size);
                }
                if(CommonUtils.isNotNull(name)){
                    resourceQo.setName(name);
                }

                Page<Resource> resources = resourceService.findAll(resourceQo);

                Map<String, Object> page = new HashMap<>();
                page.put("content", resources.getContent());
                page.put("totalPages", resources.getTotalPages());
                page.put("totalelements", resources.getTotalElements());

                return new Gson().toJson(page);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody ResourceQo resourceQo) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            Resource resource = CopyUtil.copy(resourceQo, Resource.class);

            resource.setModel(CopyUtil.copy(resourceQo.getModel(), Model.class));

            resourceService.save(resource);

            logger.info("新增->ID=" + resource.getId());
            return "1";
        });
    }

    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public CompletableFuture<String> update(@RequestBody ResourceQo resourceQo) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            Resource resource = CopyUtil.copy(resourceQo, Resource.class);

            resource.setModel(CopyUtil.copy(resourceQo.getModel(), Model.class));

            resourceService.save(resource);

            logger.info("修改->ID=" + resource.getId());
            return "1";
        });
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Long id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            //让具有此资源的角色脱离关系
            List<Role> roleList = roleService.findByResourceId(id);
            if(roleList != null && roleList.size() > 0){
                for(Role role : roleList){
                    for(Resource resource : role.getResources()){
                        if(resource.getId().equals(id)){
                            role.getResources().remove(resource);
                            roleService.save(role);
                            break;
                        }
                    }
                }
            }
            //安全删除资源
            resourceService.delete(id);
            logger.info("删除->ID=" + id);
            return "1";
        });
    }
}
