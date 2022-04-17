package com.debug.middleware.server;/**
 * Created by Administrator on 2019/3/14.
 */

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/14 22:02
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CommonTest {

    private static final Logger log= LoggerFactory.getLogger(CommonTest.class);

    @Test
    public void one(){
        List<A> listOne=new ArrayList<>();
        listOne.add(new A(1,"a",10));
        listOne.add(new A(2,"b",20));
        listOne.add(new A(3,"c",30));
        listOne.add(new A(4,"d",40));

        List<A> listTwo=new ArrayList<>();
        listTwo.add(new A(2,"b",20));
        listTwo.add(new A(4,"d",40));


        //第一种方法：
        /*listOne.retainAll(listTwo);
        log.info("结果：{} ",listOne);*/


        //第二种方法：
        List<A> result=new ArrayList<>();
        for (A a:listOne){
            Collection<A> filter=Collections2.filter(listTwo, new Predicate<A>() {
                @Override
                public boolean apply(A b) {
                    return a.getId().equals(b.getId());
                }
            });

            if (filter!=null && filter.size()>0){
                result.add(a);
            }
        }
        log.info("结果：{} ",result);

        Float.parseFloat(String.valueOf(Double.parseDouble("1.3")));
    }

    @Data
    @ToString
    class A{
        private Integer id;
        private String name;
        private Integer total;

        public A(Integer id, String name, Integer total) {
            this.id = id;
            this.name = name;
            this.total = total;
        }

    }
}
































