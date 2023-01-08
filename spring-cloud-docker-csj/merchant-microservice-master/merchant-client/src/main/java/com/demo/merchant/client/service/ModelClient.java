package com.demo.merchant.client.service;

import com.demo.merchant.object.ModelQo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("merchantapi")
public interface ModelClient {
    @RequestMapping(method = RequestMethod.GET, value = "/model/{id}")
    String findById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/model/names/{name}")
    String findByName(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/model/list")
    String findList();

    @RequestMapping(method = RequestMethod.GET, value = "/model/page",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findPage(@RequestParam("index") Integer index, @RequestParam("size") Integer size,
                    @RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.POST, value = "/model/save",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody ModelQo modelQo);

    @RequestMapping(method = RequestMethod.PUT, value = "/model/update",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestBody ModelQo modelQo);

    @RequestMapping(method = RequestMethod.DELETE, value = "/model/delete/{id}")
    String delete(@RequestParam("id") Long id);
}
