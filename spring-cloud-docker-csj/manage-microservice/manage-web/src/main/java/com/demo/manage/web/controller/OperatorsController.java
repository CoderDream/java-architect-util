package com.demo.manage.web.controller;

import com.demo.manage.domain.entity.Department;
import com.demo.manage.domain.entity.Operators;
import com.demo.manage.domain.entity.Part;
import com.demo.manage.domain.service.DepartmentService;
import com.demo.manage.domain.service.OperatorService;
import com.demo.manage.domain.service.PartService;
import com.demo.manage.object.OperatorsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/operators")
public class OperatorsController{
    private static Logger logger = LoggerFactory.getLogger(OperatorsController.class);

    @Autowired
    private OperatorService operatorService;
    @Autowired
    private PartService partService;
    @Autowired
    private DepartmentService departmentService;


    @RequestMapping("/index")
    public String index(ModelMap model, Principal user) throws Exception{
        model.addAttribute("user", user);
        return "operators/index";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Page<Operators> getList(OperatorsVo operatorsVo) {
        try {
            return operatorService.findAll(operatorsVo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value="/{id}")
    public String show(ModelMap model, @PathVariable Long id) {
        Operators operators = operatorService.findOne(id);
        model.addAttribute("operators",operators);
        return "operators/show";
    }

    @RequestMapping("/new")
    public String create(ModelMap model, Operators operators){

        List<Part> partList = partService.findAll();
        List<Department> departmentList = departmentService.findAll();

        model.addAttribute("parts", partList);
        model.addAttribute("departments", departmentList);
        model.addAttribute("operators", operators);
        return "operators/new";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public CompletableFuture<String> save(Operators operators) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
            operators.setPassword(bpe.encode(operators.getPassword()));
            logger.info("新增->ID=" + operators.getId());
            operatorService.save(operators);
            return "1";
        });
    }

    @RequestMapping(value="/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id){
        Operators operators = operatorService.findOne(id);

        List<Part> partList = partService.findAll();

        List<Department> departmentList = departmentService.findAll();

        List<Long> pids = new ArrayList<>();
        for(Part part : operators.getParts()){
            pids.add(part.getId());
        }

        model.addAttribute("operators",operators);
        model.addAttribute("parts", partList);
        model.addAttribute("pids", pids);
        model.addAttribute("departments", departmentList);
        return "operators/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public CompletableFuture<String> update(Operators operators) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            logger.info("修改->ID=" + operators.getId());
            operatorService.save(operators);
            return "1";
        });
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) throws Exception{
        operatorService.delete(id);
        logger.info("删除->ID="+id);
        return "1";
    }

}
