package com.demo.catalog.restapi.test;


import com.demo.catalog.domain.config.JpaConfiguration;
import com.demo.catalog.domain.entity.Sorts;
import com.demo.catalog.domain.entity.Subsorts;
import com.demo.catalog.domain.service.SortsService;
import com.demo.catalog.domain.service.SubsortsService;
import com.demo.catalog.object.SortsQo;
import com.demo.catalog.restapi.SortsRestApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class, SortsRestApiApplication.class})
@SpringBootTest
public class SortsTest {
    private static Logger logger = LoggerFactory.getLogger(SortsTest.class);

    @Autowired
    private SortsService sortsService;
    @Autowired
    private SubsortsService subsortsService;

    @PersistenceContext
    private EntityManager em;

    //@Test
    public void insertData() {
        Sorts sorts = new Sorts();
        sorts.setName("图书");
        sorts.setOperator("editor");
        sorts.setCreated(new Date());
//        Sorts sorts = sortsService.findByName("图书");


        Subsorts subsorts = new Subsorts();
        subsorts.setName("计算机");
        subsorts.setOperator("editor");
        subsorts.setCreated(new Date());

        subsortsService.save(subsorts);
        Assert.notNull(subsorts.getId(), "insert sub error");

        sorts.addSubsorts(subsorts);
        sortsService.save(sorts);
        Assert.notNull(sorts.getId(), "not insert sorts");
    }

    @Test
    public void getPage() throws Exception{
        SortsQo sortsQo = new SortsQo();
        sortsQo.setName("");
        //sortsQo.setSize(2);
        //sortsQo.setPage(0);

        Page<Sorts> page = sortsService.findAll(sortsQo);

        Assert.notEmpty(page.getContent(), "page is empty");
        for(Sorts sorts1 : page.getContent()){
            logger.info("==== sorts ==== name:{}, operator: {}, sub nam={}",
                    sorts1.getName(), sorts1.getOperator(),sorts1.getSubsortses().iterator().next().getName()
            );
        }
    }


    //@Test
    public void getList() throws Exception{
        SortsQo sortsQo = new SortsQo();
        sortsQo.setName("");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sortsQo.setCreated(sdf.parse("2017-03-27 09:54:00"));

        sortsQo.setSize(2);
        sortsQo.setPage(0);

        Query query = em.createQuery("select t from Sorts t where t.name like ?1 and t.created >= ?2");
        query.setParameter(1, "%" + sortsQo.getName() + "%");
        query.setParameter(2, sortsQo.getCreated());

        query.setFirstResult(sortsQo.getPage() * sortsQo.getSize());
        query.setMaxResults(sortsQo.getSize());

        List<Sorts> sortses = query.getResultList();

        Assert.notEmpty(sortses, "list empty");
        for(Sorts sorts : sortses) {
            logger.info("==== sorts ==== name:{}, sub name={}", sorts.getName(), sorts.getSubsortses().iterator().next().getName());
        }
    }


    //@Test
    public void getBySubId(){
        Sorts sorts = sortsService.findBySubsortsId(1L);
        Assert.notNull(sorts.getId(), "not find");
        logger.info("======== sorts name={}, subs name={}", sorts.getName(), sorts.getSubsortses().iterator().next().getName());
    }
}
