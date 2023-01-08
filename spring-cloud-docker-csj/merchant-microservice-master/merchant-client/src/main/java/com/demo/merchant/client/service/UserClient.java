package com.demo.merchant.client.service;

import com.demo.merchant.object.UserQo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("merchantapi")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    String findById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/user/names/{name}")
    String findByName(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/user/list")
    String findList();

    @RequestMapping(method = RequestMethod.GET, value = "/user/page",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findPage(@RequestParam("index") Integer index, @RequestParam("size") Integer size,
                    @RequestParam("name") String name, @RequestParam("merchantId") Long merchantId);

    @RequestMapping(method = RequestMethod.POST, value = "/user/save",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody UserQo userQo);

    @RequestMapping(method = RequestMethod.PUT, value = "/user/update",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestBody UserQo userQo);

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/delete/{id}")
    String delete(@RequestParam("id") Long id);
}
