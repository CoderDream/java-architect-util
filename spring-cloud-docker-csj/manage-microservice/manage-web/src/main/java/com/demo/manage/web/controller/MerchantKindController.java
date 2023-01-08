package com.demo.manage.web.controller;

import com.demo.merchant.client.service.KindFuture;
import com.demo.merchant.client.util.TreeMapConvert;
import com.demo.merchant.object.KindQo;
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
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/merchantkind")
public class MerchantKindController {
    private static Logger logger = LoggerFactory.getLogger(MerchantKindController.class);

    @Autowired
    private KindFuture kindFuture;


    @RequestMapping("/index")
    public String index(ModelMap model, Principal user) throws Exception{
        model.addAttribute("user", user);
        return "merchantkind/index";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public CompletableFuture<Page<KindQo>> getList(KindQo kindQo) {
        return kindFuture.findPage(kindQo.getPage(), kindQo.getSize(), kindQo.getName()).thenApply( json -> {
            Gson gson = TreeMapConvert.getGson();
            TreeMap<String,Object> page = gson.fromJson(json, new TypeToken< TreeMap<String,Object>>(){}.getType());

            Pageable pageable = new PageRequest(kindQo.getPage(), kindQo.getSize(), null);
            List<KindQo> list = new ArrayList<>();

            if(page.get("content") != null)
                list = gson.fromJson(page.get("content").toString(), new TypeToken<List<KindQo>>(){}.getType());
            String count = page.get("totalelements").toString();

            return new PageImpl(list, pageable, new Long(count));
        });
    }

    @RequestMapping(value="/{id}")
    public CompletableFuture<String> show(ModelMap model, @PathVariable Long id) {
        return  kindFuture.findById(id).thenApply(json -> {
            model.addAttribute("kind", new Gson().fromJson(json, KindQo.class));
            return "merchantkind/show";
        });
    }

    @RequestMapping("/new")
    public CompletableFuture<String> create(ModelMap model, HttpServletRequest request){
        return CompletableFuture.supplyAsync(() -> {
            return "merchantkind/new";
        });
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public CompletableFuture<String> save(KindQo kindQo, HttpServletRequest request) throws Exception{
        return kindFuture.create(kindQo).thenApply(sid -> {
            logger.info("新增->ID=" + sid);
            return sid;
        });
    }

    @RequestMapping("/edit/{id}")
    public CompletableFuture<String> edit(@PathVariable Long id, ModelMap model, HttpServletRequest request) {
        return kindFuture.findById(id).thenApply(json -> {
            KindQo kindQo = new Gson().fromJson(json, KindQo.class);

            model.addAttribute("kind", kindQo);

            return "merchantkind/edit";
        });
    }


    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public CompletableFuture<String> update(KindQo kindQo, HttpServletRequest request) throws Exception{
        return kindFuture.update(kindQo).thenApply(sid -> {
            logger.info("修改->ID=" + sid);
            return sid;
        });
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CompletableFuture<String> delete(@PathVariable Long id) throws Exception{
        return kindFuture.delete(id).thenApply( sid -> {
            logger.info("删除->ID="+sid);
            return sid;
        });
    }

}
