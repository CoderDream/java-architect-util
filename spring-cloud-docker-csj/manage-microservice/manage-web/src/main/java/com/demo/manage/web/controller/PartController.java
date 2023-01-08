package com.demo.manage.web.controller;

import com.demo.manage.domain.entity.Part;
import com.demo.manage.domain.service.OperatorService;
import com.demo.manage.domain.service.PartService;
import com.demo.manage.object.PartVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/part")
public class PartController {
    private static Logger logger = LoggerFactory.getLogger(PartController.class);

    @Autowired
    private PartService partService;


    @Autowired
    private OperatorService operatorService;


    @RequestMapping("/index")
    public String index(ModelMap model, Principal user) throws Exception{
        model.addAttribute("user", user);
        return "part/index";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Page<Part> getList(PartVo partVo) {
        try {
            return partService.findAll(partVo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value="/{id}")
    public String show(ModelMap model, @PathVariable Long id) {
        Part part = partService.findOne(id);
        model.addAttribute("part",part);
        return "part/show";
    }

    @RequestMapping("/new")
    public String create(ModelMap model, Part part){
        return "part/new";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public CompletableFuture<String> save(Part part) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            logger.info("新增->ID=" + part.getId());
            partService.save(part);
            return "1";
        });
    }

    @RequestMapping(value="/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id){
        Part part = partService.findOne(id);

        model.addAttribute("part",part);
        return "part/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public CompletableFuture<String> update(Part part) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            logger.info("修改->ID=" + part.getId());
            partService.save(part);
            return "1";
        });
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) throws Exception{
        partService.delete(id);
        logger.info("删除->ID="+id);
        return "1";
    }

}
