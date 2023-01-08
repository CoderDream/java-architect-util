package com.demo.goods.client.service;


import com.demo.goods.object.GoodsQo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("goodsapi")
public interface GoodsClient {
    @RequestMapping(method = RequestMethod.GET, value = "/goods/{id}")
    String findById(@RequestParam("id") Long id);


    @RequestMapping(method = RequestMethod.GET, value = "/goods",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findPage(@RequestParam("index") Integer index, @RequestParam("size") Integer size,
                    @RequestParam("merchenatid") Long merchantid, @RequestParam("name") String name,
                    @RequestParam("sortsid") Long sortsid, @RequestParam("subsid") Long subsid,
                    @RequestParam("created") String created);

    @RequestMapping(method = RequestMethod.POST, value = "/goods",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody GoodsQo goodsQo);

    @RequestMapping(method = RequestMethod.PUT, value = "/goods",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestBody GoodsQo goodsQo);

    @RequestMapping(method = RequestMethod.DELETE, value = "/goods/{id}")
    String delete(@RequestParam("id") Long id);
}
