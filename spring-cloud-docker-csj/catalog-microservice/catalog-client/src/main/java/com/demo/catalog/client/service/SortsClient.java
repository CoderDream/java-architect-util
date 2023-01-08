package com.demo.catalog.client.service;


import com.demo.catalog.object.SortsQo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("catalogapi")
public interface SortsClient {
    @RequestMapping(method = RequestMethod.GET, value = "/sorts/{id}")
    String findById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/sorts/names/{name}")
    String findByName(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/sorts/findAll")
    String findList();

    @RequestMapping(method = RequestMethod.GET, value = "/sorts",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findPage(@RequestParam("index") Integer index, @RequestParam("size") Integer size,
                    @RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/sorts/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll();

    @RequestMapping(method = RequestMethod.POST, value = "/sorts",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody SortsQo sortsQo);

    @RequestMapping(method = RequestMethod.PUT, value = "/sorts",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestBody SortsQo sortsQo);

    @RequestMapping(method = RequestMethod.DELETE, value = "/sorts/{id}")
    String delete(@RequestParam("id") Long id);
}
