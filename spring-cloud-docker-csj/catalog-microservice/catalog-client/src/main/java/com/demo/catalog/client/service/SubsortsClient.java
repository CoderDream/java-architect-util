package com.demo.catalog.client.service;


import com.demo.catalog.object.SubsortsQo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("catalogapi")
public interface SubsortsClient {
    @RequestMapping(method = RequestMethod.GET, value = "/subsorts/{id}")
    String findById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/subsorts/names/{name}")
    String findByName(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/subsorts/findAll")
    String findAll();

    @RequestMapping(method = RequestMethod.GET, value = "/subsorts",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll(@RequestParam("index") Integer index, @RequestParam("size") Integer size,
                   @RequestParam("name") String name);


    @RequestMapping(method = RequestMethod.POST, value = "/subsorts",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody SubsortsQo subsortsQo);

    @RequestMapping(method = RequestMethod.PUT, value = "/subsorts",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestBody SubsortsQo subsortsQo);

    @RequestMapping(method = RequestMethod.DELETE, value = "/subsorts/{id}")
    String delete(@RequestParam("id") Long id);

}
