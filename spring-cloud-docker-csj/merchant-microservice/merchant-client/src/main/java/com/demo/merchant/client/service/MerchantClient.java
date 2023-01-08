package com.demo.merchant.client.service;

import com.demo.merchant.object.MerchantQo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("merchantapi")
public interface MerchantClient {
    @RequestMapping(method = RequestMethod.GET, value = "/merchant/{id}")
    String findById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/merchant/names/{name}")
    String findByName(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/merchant/list")
    String findList();

    @RequestMapping(method = RequestMethod.GET, value = "/merchant/page",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findPage(@RequestParam("index") Integer index, @RequestParam("size") Integer size,
                    @RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.POST, value = "/merchant/save",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody MerchantQo merchantQo);

    @RequestMapping(method = RequestMethod.PUT, value = "/merchant/update",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestBody MerchantQo merchantQo);

    @RequestMapping(method = RequestMethod.DELETE, value = "/merchant/delete/{id}")
    String delete(@RequestParam("id") Long id);
}
