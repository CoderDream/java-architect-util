package com.coderdream.easyexcelpractise.mybatis.plus;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.coderdream.easyexcelpractise.mybatis.plus.injector.InsertIgnoreBatchAllColumn;
import com.coderdream.easyexcelpractise.mybatis.plus.injector.UpsertBatchSomeColumn;

import java.util.List;

/**
 * @author spark
 */
public class MybatisSqlInjector extends DefaultSqlInjector {

    /**
     * 如果只需增加方法，保留MP自带方法
     * 可以super.getMethodList() 再add
     *
     * @return /
     */
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass,tableInfo);
        methodList.add(new InsertBatchSomeColumn());
        methodList.add(new InsertIgnoreBatchAllColumn());
        methodList.add(new UpsertBatchSomeColumn());
        return methodList;
    }
}