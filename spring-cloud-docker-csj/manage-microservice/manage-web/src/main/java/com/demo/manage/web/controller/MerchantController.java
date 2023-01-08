package com.demo.manage.web.controller;

import com.demo.merchant.client.service.MerchantFuture;
import com.demo.merchant.client.service.UserFuture;
import com.demo.merchant.client.util.TreeMapConvert;
import com.demo.merchant.object.KindQo;
import com.demo.merchant.object.MerchantQo;
import com.demo.merchant.object.UserQo;
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
@RequestMapping("/merchant")
public class MerchantController{
    private static Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    private MerchantFuture merchantFuture;
    @Autowired
    private UserFuture userFuture;


    @RequestMapping("/index")
    public String index(ModelMap model, Principal user) throws Exception{
        model.addAttribute("user", user);
        return "merchant/index";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public CompletableFuture<Page<KindQo>> getList(MerchantQo merchantQo) {
        return merchantFuture.findPage(merchantQo.getPage(), merchantQo.getSize(), merchantQo.getName()).thenApply( json -> {
            Gson gson = TreeMapConvert.getGson();
            TreeMap<String,Object> page = gson.fromJson(json, new TypeToken< TreeMap<String,Object>>(){}.getType());

            Pageable pageable = new PageRequest(merchantQo.getPage(), merchantQo.getSize(), null);
            List<MerchantQo> list = new ArrayList<>();

            if(page.get("content") != null)
                list = gson.fromJson(page.get("content").toString(), new TypeToken<List<MerchantQo>>(){}.getType());
            String count = page.get("totalelements").toString();

            return new PageImpl(list, pageable, new Long(count));
        });
    }

    @RequestMapping(value="/{id}")
    public CompletableFuture<String> show(ModelMap model, @PathVariable Long id) {
        return  merchantFuture.findById(id).thenApply(json -> {
            model.addAttribute("merchant", new Gson().fromJson(json, MerchantQo.class));
            return "merchant/show";
        });
    }

    @RequestMapping("/new")
    public CompletableFuture<String> create(ModelMap model, HttpServletRequest request){
        return CompletableFuture.supplyAsync(() -> {
            return "merchant/new";
        });
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public CompletableFuture<String> save(MerchantQo merchantQo, HttpServletRequest request) throws Exception{
        return merchantFuture.create(merchantQo).thenApply(sid -> {
            logger.info("新增->ID=" + sid);
            return sid;
        });
    }

    @RequestMapping("/edit/{id}")
    public CompletableFuture<String> edit(@PathVariable Long id, ModelMap model, HttpServletRequest request) {
        return merchantFuture.findById(id).thenApply(json -> {
            MerchantQo merchantQo = new Gson().fromJson(json, MerchantQo.class);

            model.addAttribute("merchant", merchantQo);

            return "merchant/edit";
        });
    }


    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public CompletableFuture<String> update(MerchantQo merchantQo, HttpServletRequest request) throws Exception{
        return merchantFuture.update(merchantQo).thenApply(sid -> {
            logger.info("修改->ID=" + sid);
            return sid;
        });
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CompletableFuture<String> delete(@PathVariable Long id) throws Exception{
        return merchantFuture.delete(id).thenApply( sid -> {
            logger.info("删除->ID="+sid);
            return sid;
        });
    }

    @RequestMapping(value="/getUserName/{name}",method = RequestMethod.GET)
    @ResponseBody
    public CompletableFuture<String> getUserName(@PathVariable String name) throws Exception{
        return userFuture.findByName(name).thenApply( json -> {
            UserQo userQo = new Gson().fromJson(json, UserQo.class);
            String userName = null;
            if(userQo != null) userName = userQo.getName();
            return userName;
        });
    }

}
