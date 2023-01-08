package com.demo.order.client.service;

import com.demo.order.object.OrderQo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("orderapi")
public interface OrderClient {
    @RequestMapping(method = RequestMethod.GET, value = "/order/{id}")
    String findById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/order",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findPage(@RequestParam("index") Integer index, @RequestParam("size") Integer size,
                    @RequestParam("userid") Long userid, @RequestParam("merchenatid") Long merchantid,
                    @RequestParam("status") Integer status, @RequestParam("created") String created);

    @RequestMapping(method = RequestMethod.POST, value = "/order",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody OrderQo orderQo);

    @RequestMapping(method = RequestMethod.PUT, value = "/order",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestBody OrderQo orderQo);

    @RequestMapping(method = RequestMethod.DELETE, value = "/order/{id}")
    String delete(@RequestParam("id") Long id);
}
