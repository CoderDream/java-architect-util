package com.coderdream;

import static org.junit.Assert.assertTrue;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);

        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("5");
        list1.add("6");

        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("7");
        list2.add("8");

        // 交集
        List intersection = list1.stream().filter(item -> list2.contains(item)).collect(Collectors.toList());
        System.out.println("---交集 intersection---");
        intersection.parallelStream().forEach(System.out::println);

// 差集 (list1 - list2)

        List reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(Collectors.toList());

        System.out.println("---差集 reduce1 (list1 - list2)---");

        reduce1.parallelStream().forEach(System.out::println);

// 差集 (list2 - list1)

        List reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(Collectors.toList());

        System.out.println("---差集 reduce2 (list2 - list1)---");

        reduce2.parallelStream().forEach(System.out::println);

// 并集

        List listAll = list1.parallelStream().collect(Collectors.toList());

        List listAll2 = list2.parallelStream().collect(Collectors.toList());

        listAll.addAll(listAll2);

        System.out.println("---并集 listAll---");

        listAll.parallelStream().forEachOrdered(System.out::println);

        // 去重并集
        //List listAllDistinct = listAll.stream().distinct().collect(Collectors.toList());

        System.out.println("---得到去重并集 listAllDistinct---");

        //listAllDistinct.parallelStream().forEachOrdered(System.out::println);

        System.out.println("---原来的List1---");

        list1.parallelStream().forEachOrdered(System.out::println);

        System.out.println("---原来的List2---");

        list2.parallelStream().forEachOrdered(System.out::println);


        // A交B
        //list1.retainAll(list2);
        // A并B
       // list1.addAll(list2);
        // A减去B
        list1.removeAll(list2);

        System.out.println(list1.toString());
    }

    private Collector toList() {

        return null;
    }

    /**
     * 依据集合元素的属性，从list1中减去list2中存在的元素，
     *
     * @param list1
     * @param list2
     * @param argumentName
     * @return
     */
    public static <T> List<T> subtractList(List<T> list1, List<T> list2, String argumentName) {
        List<T> result = new ArrayList<T>();
        if (CollectionUtils.isEmpty(list1)) {
            return result;
        }
        if (CollectionUtils.isEmpty(list2)) {
            return list1;
        }
        Map<Object, T> map = initMap(list2, argumentName);
        for (T t : list1) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(t);
            Object value = beanWrapper.getPropertyValue(argumentName);
            if (map.get(value) == null) {
                result.add(t);
            }
        }
        return result;
    }

    private static <T> Map<Object, T> initMap(List<T> list2, String argumentName) {
        Map<Object, T> resultMap = new HashMap<Object, T>(list2.size());
        for (T t : list2) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(t);
            if (beanWrapper.getPropertyValue(argumentName) == null) {
                throw new RuntimeException("argumentName is null");
            }
            resultMap.put(beanWrapper.getPropertyValue(argumentName), t);
        }
        return resultMap;
    }
}
