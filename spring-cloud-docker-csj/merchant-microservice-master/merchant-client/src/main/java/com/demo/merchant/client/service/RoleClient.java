package com.demo.merchant.client.service;

import com.demo.merchant.object.RoleQo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("merchantapi")
public interface RoleClient {
    @RequestMapping(method = RequestMethod.GET, value = "/role/{id}")
    String findById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/role/names/{name}")
    String findByName(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/role/list")
    String findList();

    @RequestMapping(method = RequestMethod.GET, value = "/role/page",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findPage(@RequestParam("index") Integer index, @RequestParam("size") Integer size,
                    @RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.POST, value = "/role/save",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody RoleQo roleQo);

    @RequestMapping(method = RequestMethod.PUT, value = "/role/update",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestBody RoleQo roleQo);

    @RequestMapping(method = RequestMethod.DELETE, value = "/role/delete/{id}")
    String delete(@RequestParam("id") Long id);
}
