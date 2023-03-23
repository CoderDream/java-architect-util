package com.coderdream.freeapps.util;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;

/**
 * 简单封装orika, 实现深度的BeanOfClasssA &lt;-&gt;BeanOfClassB复制
 * (代替Apache Common BeanUtils进行类复制 提高性能)
 *
 * @author chenyong
 * @since 2018-11-01
 */
public class BeanMapper {
    /**
     * orika 提供的门面类
     */
    private static MapperFacade mapper;
    private static MapperFactory mapperFactory;

    static {
        mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        mapperFactory.getConverterFactory().registerConverter(new BigDecimal2IntConverter());
        mapperFactory.getConverterFactory().registerConverter(new Date2StringConverter());
        //mapperFactory.getConverterFactory().registerConverter(new LocalDateTimeLongConverter());
       // mapperFactory.getConverterFactory().registerConverter(new LocalDateLongConverter());
        //mapperFactory.getConverterFactory().registerConverter(new LocalTimeLongConverter());
        mapper = mapperFactory.getMapperFacade();
    }

    /**
     * 将源对象 转换成目标对象类型的新对象
     *
     * @param <S>              源对象类型
     * @param <D>              目标对象类型
     * @param source           源对象
     * @param destinationClass 目标对象类型
     * @return 目标对象类型的一个新对象
     */
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    /**
     * 极致性能的复制出新类型对象到ArrayList.
     * <p>
     * 预先通过BeanMapper.getType() 静态获取并缓存Type类型，在此处传入
     *
     * @param <S>             源对象类型
     * @param <D>             目标对象类型
     * @param sourceList      源对象列表
     * @param sourceType      源对象类型
     * @param destinationType 目标类型
     * @return 目标对象对象列表
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Type<S> sourceType, Type<D> destinationType) {
        return mapper.mapAsList(sourceList, sourceType, destinationType);
    }

    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class source,Class destination, Map<String,String> fieldMap) {
        ClassMapBuilder<S,D> classMapBuilder =  mapperFactory.classMap(source,destination);
        Iterator<String> iterator = fieldMap.keySet().iterator();
        String key = null;
        while(iterator.hasNext()){
            key = iterator.next();
            classMapBuilder.field(key,fieldMap.get(key));
        }
        classMapBuilder.byDefault()//剩余的字段映射
                .register();

        return mapperFactory.getMapperFacade().mapAsList(sourceList, getType(source), getType(destination));
    }

    /**
     * @param sourceList  源对象列表
     * @param source      源对象类型
     * @param destination 目标对象类型
     * @param <S>         源对象类型
     * @param <D>         目标对象类型
     * @return
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class source, Class destination) {
        return mapList(sourceList, getType(source), getType(destination));
    }

    /**
     * 简单复制出新对象列表到数组
     * 通过source.getComponentType() 获得源Class
     * destinationType
     *
     * @param <S>              源对象类型
     * @param <D>              目标对象类型
     * @param destination      目标对象数组
     * @param source           源对象数组
     * @param destinationClass 目标类型
     * @return 目标对象对象数组
     */
    public static <S, D> D[] mapArray(final D[] destination, final S[] source, final Class<D> destinationClass) {
        return mapper.mapAsArray(destination, source, destinationClass);
    }

    /**
     * 极致性能的复制出新类型对象到数组
     * <p>
     * 预先通过BeanMapper.getType() 静态获取并缓存Type类型，在此处传入
     *
     * @param <S>             源对象类型
     * @param <D>             目标对象类型
     * @param destination     目标对象数组
     * @param source          源对象数组
     * @param sourceType      源对象类型
     * @param destinationType 源对象类型
     * @return 目标对象数组
     */
    public static <S, D> D[] mapArray(D[] destination, S[] source, Type<S> sourceType, Type<D> destinationType) {
        return mapper.mapAsArray(destination, source, sourceType, destinationType);
    }

    /**
     * 预先获取orika转换所需要的Type，避免每次转换.
     *
     * @param <E>     对象类型
     * @param rawType 要转换的类型
     * @return 转换后的类型
     */
    public static <E> Type<E> getType(final Class<E> rawType) {
        return TypeFactory.valueOf(rawType);
    }

    /**
     * 把源对象中的属性拷贝到目标对象，源对象中的null属性不拷贝到目标对象。
     *
     * @param sourceObject      源对象
     * @param destinationObject 目标对象
     * @param <S>               源对象
     * @param <D>               目标对象
     */
    public static <S, D> void map(S sourceObject, D destinationObject) {
        mapper.map(sourceObject, destinationObject);
    }

    /**
     * BigDecimal与Integer互转转换器
     */
    static class BigDecimal2IntConverter extends BidirectionalConverter<BigDecimal, Integer> {
        @Override
        public Integer convertTo(BigDecimal bigDecimal, Type<Integer> type, MappingContext mappingContext) {
            return bigDecimal.intValue();
        }

        @Override
        public BigDecimal convertFrom(Integer integer, Type<BigDecimal> type, MappingContext mappingContext) {
            return new BigDecimal(integer);
        }
    }

    static class Date2StringConverter extends BidirectionalConverter<Date, String> {
        @Override
        public String convertTo(Date date, Type<String> type, MappingContext mappingContext) {
            return DateUtil.format(date, "yyyy-MM-dd HH:mm:dd");
        }

        @Override
        public Date convertFrom(String s, Type<Date> type, MappingContext mappingContext) {
            return DateUtil.parseDate(s);
        }
    }

    static class LocalDateTimeLongConverter extends BidirectionalConverter<LocalDateTime, Long> {
        @Override
        public Long convertTo(LocalDateTime date, Type<Long> type, MappingContext mappingContext) {
            return date.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        }

        @Override
        public LocalDateTime convertFrom(Long num, Type<LocalDateTime> type, MappingContext mappingContext) {
            return LocalDateTime.ofEpochSecond(num/1000,0, ZoneOffset.ofHours(8));
        }
    }

    static class LocalDateLongConverter extends BidirectionalConverter<LocalDate, Long> {
        @Override
        public Long convertTo(LocalDate date, Type<Long> type, MappingContext mappingContext) {
            return date.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();

        }

        @Override
        public LocalDate convertFrom(Long num, Type<LocalDate> type, MappingContext mappingContext) {
            return Instant.ofEpochMilli(num).atZone(ZoneId.systemDefault()).toLocalDate();
        }
    }

    static class LocalTimeLongConverter extends BidirectionalConverter<LocalTime, Long> {
        @Override
        public Long convertTo(LocalTime date, Type<Long> type, MappingContext mappingContext) {
            return LocalDate.now().atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli() + date.getHour() * 60 * 60 * 1000l + date.getMinute() * 60 * 1000l + date.getSecond() * 1000L;

        }

        @Override
        public LocalTime convertFrom(Long num, Type<LocalTime> type, MappingContext mappingContext) {
            return LocalTime.ofSecondOfDay(num / 1000);
        }
    }






    /**
     * 将一个对象转换为另一个对象，可以解决类中嵌套原则
     * @param source
     * @param destinationClass
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S, D> D mapToBean(S source, Class<D> destinationClass){
        return JSON.parseObject(JSON.toJSONString(source),destinationClass);
    }

}
