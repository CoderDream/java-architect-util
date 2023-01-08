package com.demo.goods.web.controller;

import com.demo.catalog.client.service.SortsFuture;
import com.demo.catalog.client.service.SortsRestService;
import com.demo.catalog.object.SortsQo;
import com.demo.catalog.object.SubsortsQo;
import com.demo.goods.client.service.GoodsFuture;
import com.demo.goods.client.service.GoodsRestService;
import com.demo.goods.client.util.TreeMapConvert;
import com.demo.goods.object.GoodsQo;
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
@RequestMapping("/goods")
public class GoodsController {
    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsFuture goodsFuture;
    @Autowired
    private SortsFuture sortsFuture;
    @Autowired
    private GoodsRestService goodsService;
    @Autowired
    private SortsRestService sortsService;

    @RequestMapping(value="/index")
    public ModelAndView index(){
        return new ModelAndView("/goods/index");
    }

    @RequestMapping(value="/{id}")
    public CompletableFuture<ModelAndView> findById(@PathVariable Long id, ModelMap model) {
        return CompletableFuture.supplyAsync(() -> goodsService.findById(id))
                .thenCompose(goods -> CompletableFuture.supplyAsync(() -> {
                                    GoodsQo goodsQo = new Gson().fromJson(goods, GoodsQo.class);
                                    String sorts = sortsService.findById(goodsQo.getSortsid());
                                    SortsQo sortsQo = new Gson().fromJson(sorts, SortsQo.class);
                                    String subsname = "";
                                    for (SubsortsQo subsortsQo : sortsQo.getSubsortses()) {
                                        if (goodsQo.getSubsid().compareTo(subsortsQo.getId()) == 0) {
                                            subsname = subsortsQo.getName();
                                            break;
                                        }
                                    }
                                    model.addAttribute("goods", goodsQo);
                                    model.addAttribute("sortsname", sortsQo.getName());
                                    model.addAttribute("subsname", subsname);
                                    return new ModelAndView("goods/show");
                                }
                        )
                );

    }

    @RequestMapping(value = "/list")
    public CompletableFuture<Page<Map<String, Object>>> findAll(GoodsQo goodsQo) {
        return goodsFuture.findPage(goodsQo).thenApply( json -> {
            Gson gson = TreeMapConvert.getGson();
            TreeMap<String,Object> page = gson.fromJson(json, new TypeToken< TreeMap<String,Object>>(){}.getType());

            Pageable pageable = new PageRequest(goodsQo.getPage(), goodsQo.getSize(), null);
            List<GoodsQo> list = new ArrayList<>();

            if(page != null && page.get("content") != null)
                list = gson.fromJson(page.get("content").toString(), new TypeToken<List<GoodsQo>>(){}.getType());
            String count = page.get("totalelements").toString();

            return new PageImpl(list, pageable, new Long(count));
        });
    }

    @RequestMapping("/new")
    public CompletableFuture<ModelAndView> create(HttpServletRequest request) {
        return sortsFuture.findList().thenApply(sortses ->{
            List<SortsQo> list = new Gson().fromJson(sortses, new TypeToken<List<SortsQo>>() {}.getType());
            return new ModelAndView("goods/new", "sortses", list);
        });
    }


    @RequestMapping(value="/save", method = RequestMethod.POST)
    public CompletableFuture<String> save(GoodsQo goodsQo) {
        return goodsFuture.create(goodsQo).thenApply(sid ->{
            logger.info("新增->ID="+sid);
            return sid;
        });
    }

    @RequestMapping("/edit/{id}")
    public CompletableFuture<ModelAndView> update(@PathVariable Long id, ModelMap model) {
        return goodsFuture.findById(id).thenCompose(goods -> CompletableFuture.supplyAsync(() -> {
                            Gson gson = TreeMapConvert.getGson();
                            GoodsQo goodsQo = gson.fromJson(goods, GoodsQo.class);

                            String sortses = sortsService.findList();
                            List<SortsQo> list = new Gson().fromJson(sortses, new TypeToken<List<SortsQo>>() {}.getType());

                            List<SubsortsQo> subs = new ArrayList<SubsortsQo>();
                            for (SortsQo sorts : list) {
                                if (goodsQo.getSortsid().compareTo(sorts.getId()) == 0) {
                                    subs = sorts.getSubsortses();
                                    break;
                                }
                            }

                            model.addAttribute("goods", goodsQo);
                            model.addAttribute("sortses", list);
                            model.addAttribute("subs", subs);
                            return new ModelAndView("goods/edit");
                        }
                )
        );
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public CompletableFuture<String> update(GoodsQo goodsQo) {
        return goodsFuture.update(goodsQo).thenApply(sid -> {
            logger.info("修改->ID="+sid);
            return sid;
        });
    }

    @RequestMapping(value="/delete/{id}")
    public CompletableFuture<String> delete(@PathVariable Long id) {
        return goodsFuture.delete(id).thenApply(sid ->{
            logger.info("删除->ID="+sid);
            return sid;
        });
    }

}
