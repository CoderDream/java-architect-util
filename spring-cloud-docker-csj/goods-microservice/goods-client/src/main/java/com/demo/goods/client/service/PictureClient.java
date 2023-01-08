package com.demo.goods.client.service;


import com.demo.goods.object.PictureQo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("goodsapi")
public interface PictureClient {
    @RequestMapping(method = RequestMethod.GET, value = "/picture/{id}")
    String findById(@RequestParam("id") Long id);


    @RequestMapping(method = RequestMethod.GET, value = "/picture",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findPage(@RequestParam("index") Integer index, @RequestParam("size") Integer size,
                    @RequestParam("merchenatid") Long merchantid, @RequestParam("created") String created);

    @RequestMapping(method = RequestMethod.POST, value = "/picture",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody PictureQo pictureQo);

    @RequestMapping(method = RequestMethod.PUT, value = "/picture",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestBody PictureQo pictureQo);

    @RequestMapping(method = RequestMethod.DELETE, value = "/picture/{id}")
    String delete(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/picture/deleteByFileName/{fileName}")
    String deleteByFileName(@RequestParam("fileName") String fileName);
}
