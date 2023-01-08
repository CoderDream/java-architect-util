package com.demo.goods.restapi.test;

import com.demo.goods.domain.config.JpaConfiguration;
import com.demo.goods.domain.entity.Picture;
import com.demo.goods.domain.service.PictureService;
import com.demo.goods.restapi.GoodsRestApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class, GoodsRestApiApplication.class})
@SpringBootTest
public class PictureTest {
    private static Logger logger = LoggerFactory.getLogger(PictureTest.class);
    @Autowired
    private PictureService pictureService;

    @Test
    public void insertData(){
        Picture picture = new Picture();
        picture.setFileName("images/demo1.png");
        picture.setPathInfo("http://localhost:8092/");
        picture.setHeight(200);
        picture.setWidth(300);
        picture.setMerchantid(1L);

        pictureService.save(picture);
        
        Assert.notNull(picture.getId(), "not insert");
    }
}
