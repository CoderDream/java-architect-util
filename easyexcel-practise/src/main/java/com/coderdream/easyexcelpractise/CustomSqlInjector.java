package com.coderdream.easyexcelpractise;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import lombok.var;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;

/**
 *
 * @Description
 * @Datetime 2022/4/25 15:17
 * @Modified By
 */
@Configurable
public class CustomSqlInjector extends DefaultSqlInjector  {

    @Override
    public List<AbstractMethod> getMethodList (Class<?> mapperClass, TableInfo tableInfo) {
        var methodList = super.getMethodList(mapperClass, tableInfo);
        methodList.add(new BatchInsert());
        methodList.add(new BatchUpdate());
        return methodList;
    }
}
