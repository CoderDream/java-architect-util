package com.demo.merchant.client.service;

import com.demo.merchant.object.ResourceQo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("merchantapi")
public interface ResourceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/resource/{id}")
    String findById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/resource/names/{name}")
    String findByName(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/resource/list")
    String findList();

    @RequestMapping(method = RequestMethod.GET, value = "/resource/page",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findPage(@RequestParam("index") Integer index, @RequestParam("size") Integer size,
                    @RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.POST, value = "/resource/save",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody ResourceQo resourceQo);

    @RequestMapping(method = RequestMethod.PUT, value = "/resource/update",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestBody ResourceQo resourceQo);

    @RequestMapping(method = RequestMethod.DELETE, value = "/resource/delete/{id}")
    String delete(@RequestParam("id") Long id);
}
