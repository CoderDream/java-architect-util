package com.demo.manage.web.controller;

import com.demo.merchant.client.service.ModelFuture;
import com.demo.merchant.client.service.ModelRestService;
import com.demo.merchant.client.service.ResourceFuture;
import com.demo.merchant.client.service.ResourceRestService;
import com.demo.merchant.client.util.TreeMapConvert;
import com.demo.merchant.object.ModelQo;
import com.demo.merchant.object.ResourceQo;
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
@RequestMapping("/merchantresource")
public class MerchantResourceController{
    private static Logger logger = LoggerFactory.getLogger(MerchantResourceController.class);

    @Autowired
    private ResourceFuture resourceFuture;
    @Autowired
    private ResourceRestService resourceRestService;

    @Autowired
    private ModelFuture modelFuture;
    @Autowired
    private ModelRestService modelRestService;


    @RequestMapping("/index")
    public String index(ModelMap model, Principal user) throws Exception{
        model.addAttribute("user", user);
        return "merchantresource/index";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public CompletableFuture<Page<ResourceQo>> getList(ResourceQo resourceQo) {
        return resourceFuture.findPage(resourceQo.getPage(), resourceQo.getSize(), resourceQo.getName()).thenApply( json -> {
            Gson gson = TreeMapConvert.getGson();
            TreeMap<String,Object> page = gson.fromJson(json, new TypeToken< TreeMap<String,Object>>(){}.getType());

            Pageable pageable = new PageRequest(resourceQo.getPage(), resourceQo.getSize(), null);
            List<ResourceQo> list = new ArrayList<>();

            if(page.get("content") != null)
                list = gson.fromJson(page.get("content").toString(), new TypeToken<List<ResourceQo>>(){}.getType());
            String count = page.get("totalelements").toString();

            return new PageImpl(list, pageable, new Long(count));
        });
    }

    @RequestMapping(value="/{id}")
    public CompletableFuture<String> show(ModelMap model, @PathVariable Long id) {
        return  resourceFuture.findById(id).thenApply(json -> {
            model.addAttribute("resource", new Gson().fromJson(json, ResourceQo.class));
            return "merchantresource/show";
        });
    }

    @RequestMapping("/new")
    public CompletableFuture<String> create(ModelMap model, HttpServletRequest request){
        return  modelFuture.findList().thenApply(json -> {
            List<ModelQo> modelList = new Gson().fromJson(json, new TypeToken<List<ModelQo>>() {}.getType());
            //缓存模块列表为save方法使用
            request.getSession().setAttribute("models", modelList);
            model.addAttribute("models", modelList);
            return "merchantresource/new";
        });
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public CompletableFuture<String> save(ResourceQo resourceQo, HttpServletRequest request) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            //通过模块ID指定关联对象
            String mid = request.getParameter("mid");
            //获取模块列表
            List<ModelQo> modelQos = (List<ModelQo>) request.getSession().getAttribute("models");
            for (ModelQo modelQo : modelQos) {
                if (modelQo.getId().compareTo(new Long(mid)) == 0) {
                    resourceQo.setModel(modelQo);
                    break;
                }
            }

            String sid = resourceRestService.create(resourceQo);
            logger.info("新增->ID=" + sid);
            return sid;
        });
    }

    @RequestMapping("/edit/{id}")
    public CompletableFuture<String> edit(@PathVariable Long id, ModelMap model, HttpServletRequest request) {
        return CompletableFuture.supplyAsync(() -> resourceRestService.findById(id))
                .thenCompose(json -> CompletableFuture.supplyAsync(() -> {
                    ResourceQo resourceQo = new Gson().fromJson(json, ResourceQo.class);

                    String models = modelRestService.findList();
                    List<ModelQo> modelQoList = new Gson().fromJson(models, new TypeToken<List<ModelQo>>() {}.getType());

                    //缓存模块列表为update方法使用
                    request.getSession().setAttribute("models", modelQoList);

                    model.addAttribute("models", modelQoList);
                    model.addAttribute("resource", resourceQo);

                    return "merchantresource/edit";
                }));
    }


    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public CompletableFuture<String> update(ResourceQo resourceQo, HttpServletRequest request) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            //通过模块ID指定关联对象
            String mid = request.getParameter("mid");
            //获取模块列表
            List<ModelQo> modelQos = (List<ModelQo>) request.getSession().getAttribute("models");
            for (ModelQo modelQo : modelQos) {
                if (modelQo.getId().compareTo(new Long(mid)) == 0) {
                    resourceQo.setModel(modelQo);
                    break;
                }
            }

            String sid = resourceRestService.update(resourceQo);
            logger.info("修改->ID=" + sid);
            return sid;
        });
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CompletableFuture<String> delete(@PathVariable Long id) throws Exception{
        return resourceFuture.delete(id).thenApply( sid -> {
            logger.info("删除->ID="+sid);
            return sid;
        });
    }

}
