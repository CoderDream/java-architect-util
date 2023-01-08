package com.demo.manage.web.controller;

import com.demo.merchant.client.service.ResourceFuture;
import com.demo.merchant.client.service.ResourceRestService;
import com.demo.merchant.client.service.RoleFuture;
import com.demo.merchant.client.service.RoleRestService;
import com.demo.merchant.client.util.TreeMapConvert;
import com.demo.merchant.object.ResourceQo;
import com.demo.merchant.object.RoleQo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/merchantrole")
public class MerchantRoleController {
    private static Logger logger = LoggerFactory.getLogger(MerchantRoleController.class);

    @Autowired
    private ResourceFuture resourceFuture;
    @Autowired
    private ResourceRestService resourceRestService;

    @Autowired
    private RoleFuture roleFuture;
    @Autowired
    private RoleRestService roleRestService;


    @RequestMapping("/index")
    public String index(ModelMap model, Principal user) throws Exception{
        model.addAttribute("user", user);
        return "merchantrole/index";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public CompletableFuture<Page<RoleQo>> getList(RoleQo roleQo) {
        return roleFuture.findPage(roleQo.getPage(), roleQo.getSize(), roleQo.getName()).thenApply( json -> {
            Gson gson = TreeMapConvert.getGson();
            TreeMap<String,Object> page = gson.fromJson(json, new TypeToken< TreeMap<String,Object>>(){}.getType());

            Pageable pageable = new PageRequest(roleQo.getPage(), roleQo.getSize(), null);
            List<RoleQo> list = new ArrayList<>();

            if(page.get("content") != null)
                list = gson.fromJson(page.get("content").toString(), new TypeToken<List<RoleQo>>(){}.getType());
            String count = page.get("totalelements").toString();

            return new PageImpl(list, pageable, new Long(count));
        });
    }

    @RequestMapping(value="/{id}")
    public CompletableFuture<String> show(ModelMap model, @PathVariable Long id) {
        return  roleFuture.findById(id).thenApply(json -> {
            model.addAttribute("role", new Gson().fromJson(json, RoleQo.class));
            return "merchantrole/show";
        });
    }

    @RequestMapping("/new")
    public CompletableFuture<String> create(ModelMap model, HttpServletRequest request){
        return  resourceFuture.findList().thenApply(json -> {
            List<ResourceQo> resourceQoList = new Gson().fromJson(json, new TypeToken<List<ResourceQo>>() {}.getType());
            //缓存资源列表为save方法使用
            request.getSession().setAttribute("resources", resourceQoList);
            model.addAttribute("resources", resourceQoList);
            return "merchantrole/new";
        });
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public CompletableFuture<String> save(RoleQo roleQo, HttpServletRequest request) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            //通过资源ID指定关联对象
            String[] rids = request.getParameterValues("rids");
            //获取资源列表
            List<ResourceQo> resourceQoList = (List<ResourceQo>) request.getSession().getAttribute("resources");
            List<ResourceQo> resourceQos = new ArrayList<ResourceQo>();
            for (String rid : rids) {
                for (ResourceQo resourceQo : resourceQoList) {
                    if (resourceQo.getId().compareTo(new Long(rid)) == 0) {
                        resourceQos.add(resourceQo);
                    }
                }
            }
            roleQo.setResources(resourceQos);


            String sid = roleRestService.create(roleQo);
            logger.info("新增->ID=" + sid);
            return sid;
        });
    }

    @RequestMapping("/edit/{id}")
    public CompletableFuture<String> edit(@PathVariable Long id, ModelMap model, HttpServletRequest request) {
        return CompletableFuture.supplyAsync(() -> roleRestService.findById(id))
                .thenCompose(json -> CompletableFuture.supplyAsync(() -> {
                    RoleQo roleQo = new Gson().fromJson(json, RoleQo.class);

                    String resources = resourceRestService.findList();
                    List<ResourceQo> resourceVoList = new Gson().fromJson(resources, new TypeToken<List<ResourceQo>>() {}.getType());

                    //缓存资源列表为update方法使用
                    request.getSession().setAttribute("resources", resourceVoList);

                    List<Long> rids = new ArrayList<>();
                    for(ResourceQo resource : roleQo.getResources()){
                        rids.add(resource.getId());
                    }

                    model.addAttribute("resources", resourceVoList);
                    model.addAttribute("role", roleQo);
                    model.addAttribute("rids", rids);

                    return "merchantrole/edit";
                }));
    }


    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public CompletableFuture<String> update(RoleQo roleQo, HttpServletRequest request) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            //通过资源ID指定关联对象
            String[] rids = request.getParameterValues("rids");
            //获取资源列表
            List<ResourceQo> resourceQoList = (List<ResourceQo>) request.getSession().getAttribute("resources");
            List<ResourceQo> resourceQos = new ArrayList<ResourceQo>();
            for (String rid : rids) {
                for (ResourceQo resourceVo : resourceQoList) {
                    if (resourceVo.getId().compareTo(new Long(rid)) == 0) {
                        resourceQos.add(resourceVo);
                    }
                }
            }
            roleQo.setResources(resourceQos);

            String sid = roleRestService.update(roleQo);
            logger.info("修改->ID=" + sid);
            return sid;
        });
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CompletableFuture<String> delete(@PathVariable Long id) throws Exception{
        return roleFuture.delete(id).thenApply( sid -> {
            logger.info("删除->ID="+sid);
            return sid;
        });
    }

}
