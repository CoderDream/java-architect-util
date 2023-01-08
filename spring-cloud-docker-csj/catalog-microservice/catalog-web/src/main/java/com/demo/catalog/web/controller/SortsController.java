package com.demo.catalog.web.controller;


import com.demo.catalog.client.service.SortsFuture;
import com.demo.catalog.client.service.SortsRestService;
import com.demo.catalog.client.service.SubsortsFuture;
import com.demo.catalog.client.service.SubsortsRestService;
import com.demo.catalog.client.util.TreeMapConvert;
import com.demo.catalog.object.SortsQo;
import com.demo.catalog.object.SubsortsQo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/sorts")
public class SortsController {
    private static Logger logger = LoggerFactory.getLogger(SortsController.class);

    @Autowired
    private SortsFuture sortsFuture;
    @Autowired
    private SubsortsFuture subsortsFuture;
    @Autowired
    private SortsRestService sortsService;
    @Autowired
    private SubsortsRestService subsortsService;

    @RequestMapping(value="/index")
    public ModelAndView index(){
        return new ModelAndView("sorts/index");
    }

    @RequestMapping(value="/{id}")
    public CompletableFuture<ModelAndView> findById(@PathVariable Long id) {
        return  sortsFuture.findById(id).thenApply(sorts ->
                new ModelAndView("sorts/show", "sorts", new Gson().fromJson(sorts, SortsQo.class)));
    }


    @RequestMapping(value="/list")
    public CompletableFuture<Page<Map<String, Object>>> findAll(SortsQo sortsQo) {
        return sortsFuture.findPage(sortsQo.getPage(), sortsQo.getSize(), sortsQo.getName()).thenApply( json -> {
            Gson gson = TreeMapConvert.getGson();
            TreeMap<String,Object> page = gson.fromJson(json, new TypeToken< TreeMap<String,Object>>(){}.getType());

            Pageable pageable = new PageRequest(sortsQo.getPage(), sortsQo.getSize(), null);
            List<SortsQo> list = new ArrayList<>();

            if(page != null && page.get("content") != null)
                list = gson.fromJson(page.get("content").toString(), new TypeToken<List<SortsQo>>(){}.getType());
            String count = page.get("totalelements").toString();

            return new PageImpl(list, pageable, new Long(count));
        });
    }


    @RequestMapping("/new")
    public CompletableFuture<ModelAndView> create(HttpServletRequest request) {
        return subsortsFuture.findAll().thenApply(subs -> {
            List<SubsortsQo> subsortses = new Gson().fromJson(subs, new TypeToken<List<SubsortsQo>>() {}.getType());
            request.getSession().setAttribute("subsortses", subsortses);
            return new ModelAndView("sorts/new", "subsortses", subsortses);
        });
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public CompletableFuture<String> save(HttpServletRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            SortsQo sortsQo = new SortsQo();
            sortsQo.setName(request.getParameter("name"));
            sortsQo.setOperator("manage");

            String[] subs = request.getParameterValues("subsortses");
            List<SubsortsQo> subsortses = (List<SubsortsQo>) request.getSession().getAttribute("subsortses");
            if(subs != null && subs.length > 0) {
                for (String sub : subs) {
                    for (SubsortsQo subsortsQo : subsortses) {
                        if (subsortsQo.getId().compareTo(new Long(sub)) == 0) {
                            sortsQo.addSubsorts(subsortsQo);
                        }
                    }
                }
            }

            String sid = sortsService.create(sortsQo);
            logger.info("新增->ID=" + sid);
            return sid;
        });
    }

    @RequestMapping("/edit/{id}")
    public CompletableFuture<ModelAndView> update(@PathVariable Long id, HttpServletRequest request, ModelMap model) {
        return sortsFuture.findById(id)
                .thenCompose(json -> CompletableFuture.supplyAsync(() -> {
                    SortsQo sorts = new Gson().fromJson(json, SortsQo.class);

                    String subs = subsortsService.findAll();
                    List<SubsortsQo> subsortses = new Gson().fromJson(subs, new TypeToken<List<SubsortsQo>>() {}.getType());
                    request.getSession().setAttribute("subsortses", subsortses);

                    List<Long> subids = new ArrayList<Long>();
                    for (SubsortsQo subsortsQo : sorts.getSubsortses()) {
                        subids.add(subsortsQo.getId());
                    }

                    model.addAttribute("subsortses", subsortses);
                    model.addAttribute("subids", subids);
                    model.addAttribute("sorts", sorts);

                    return new ModelAndView("sorts/edit");
                }));
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public CompletableFuture<String> update(SortsQo sortsQo, HttpServletRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            String[] subs = request.getParameterValues("subs");
            List<SubsortsQo> subsortses = (List<SubsortsQo>) request.getSession().getAttribute("subsortses");
            for (String sub : subs) {
                for (SubsortsQo subsortsQo : subsortses) {
                    if (subsortsQo.getId().compareTo(new Long(sub)) == 0) {
                        sortsQo.addSubsorts(subsortsQo);
                    }
                }
            }

            String sid = sortsService.update(sortsQo);
            logger.info("修改->ID=" + sid);
            return sid;
        });
    }

    @RequestMapping(value="/delete/{id}")
    public CompletableFuture<String> delete(@PathVariable Long id) {
        return sortsFuture.delete(id).thenApply( sid -> {
            logger.info("删除->ID="+sid);
            return sid;
        });
    }

}
