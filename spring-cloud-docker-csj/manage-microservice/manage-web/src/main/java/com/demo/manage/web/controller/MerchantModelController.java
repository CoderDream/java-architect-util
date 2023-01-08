package com.demo.manage.web.controller;

import com.demo.merchant.client.service.KindFuture;
import com.demo.merchant.client.service.KindRestService;
import com.demo.merchant.client.service.ModelFuture;
import com.demo.merchant.client.service.ModelRestService;
import com.demo.merchant.client.util.TreeMapConvert;
import com.demo.merchant.object.KindQo;
import com.demo.merchant.object.ModelQo;
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
@RequestMapping("/merchantmodel")
public class MerchantModelController {
    private static Logger logger = LoggerFactory.getLogger(MerchantModelController.class);

    @Autowired
    private KindFuture kindFuture;
    @Autowired
    private KindRestService kindRestService;

    @Autowired
    private ModelFuture modelFuture;
    @Autowired
    private ModelRestService modelRestService;


    @RequestMapping("/index")
    public String index(ModelMap model, Principal user) throws Exception{
        model.addAttribute("user", user);
        return "merchantmodel/index";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public CompletableFuture<Page<ModelQo>> getList(ModelQo modelQo) {
        return modelFuture.findPage(modelQo.getPage(), modelQo.getSize(), modelQo.getName()).thenApply( json -> {
            Gson gson = TreeMapConvert.getGson();
            TreeMap<String,Object> page = gson.fromJson(json, new TypeToken< TreeMap<String,Object>>(){}.getType());

            Pageable pageable = new PageRequest(modelQo.getPage(), modelQo.getSize(), null);
            List<ModelQo> list = new ArrayList<>();

            if(page.get("content") != null)
                list = gson.fromJson(page.get("content").toString(), new TypeToken<List<ModelQo>>(){}.getType());
            String count = page.get("totalelements").toString();

            return new PageImpl(list, pageable, new Long(count));
        });
    }

    @RequestMapping(value="/{id}")
    public CompletableFuture<String> show(ModelMap model, @PathVariable Long id) {
        return  modelFuture.findById(id).thenApply(json -> {
            model.addAttribute("model", new Gson().fromJson(json, ModelQo.class));
            return "merchantmodel/show";
        });
    }

    @RequestMapping("/new")
    public CompletableFuture<String> create(ModelMap model, HttpServletRequest request){
        return  kindFuture.findList().thenApply(json -> {
            List<KindQo> kindQos = new Gson().fromJson(json, new TypeToken<List<KindQo>>() {}.getType());
            //缓存模块列表为save方法使用
            request.getSession().setAttribute("kinds", kindQos);
            model.addAttribute("kinds", kindQos);
            return "merchantmodel/new";
        });
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public CompletableFuture<String> save(ModelQo modelQo, HttpServletRequest request) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            //通过模块ID指定关联对象
            String kid = request.getParameter("kid");
            //获取模块列表
            List<KindQo> kindQos = (List<KindQo>) request.getSession().getAttribute("kinds");
            for (KindQo kindQo : kindQos) {
                if (kindQo.getId().compareTo(new Long(kid)) == 0) {
                    modelQo.setKind(kindQo);
                    break;
                }
            }

            String sid = modelRestService.create(modelQo);
            logger.info("新增->ID=" + sid);
            return sid;
        });
    }

    @RequestMapping("/edit/{id}")
    public CompletableFuture<String> edit(@PathVariable Long id, ModelMap model, HttpServletRequest request) {
        return CompletableFuture.supplyAsync(() -> modelRestService.findById(id))
                .thenCompose(json -> CompletableFuture.supplyAsync(() -> {
                    ModelQo modelQo = new Gson().fromJson(json, ModelQo.class);

                    String kinks = kindRestService.findList();
                    List<KindQo> kindQoList = new Gson().fromJson(kinks, new TypeToken<List<KindQo>>() {}.getType());

                    //缓存模块列表为update方法使用
                    request.getSession().setAttribute("kinds", kindQoList);

                    model.addAttribute("kinds", kindQoList);
                    model.addAttribute("model", modelQo);

                    return "merchantmodel/edit";
                }));
    }


    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public CompletableFuture<String> update(ModelQo modelQo, HttpServletRequest request) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            //通过模块ID指定关联对象
            String kid = request.getParameter("kid");
            //获取模块列表
            List<KindQo> kindQos = (List<KindQo>) request.getSession().getAttribute("kinds");
            for (KindQo kindQo : kindQos) {
                if (kindQo.getId().compareTo(new Long(kid)) == 0) {
                    modelQo.setKind(kindQo);
                    break;
                }
            }

            String sid = modelRestService.update(modelQo);
            logger.info("修改->ID=" + sid);
            return sid;
        });
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CompletableFuture<String> delete(@PathVariable Long id) throws Exception{
        return modelFuture.delete(id).thenApply( sid -> {
            logger.info("删除->ID="+sid);
            return sid;
        });
    }

}
