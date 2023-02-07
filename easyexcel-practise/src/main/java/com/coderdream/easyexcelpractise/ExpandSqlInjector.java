package com.coderdream.easyexcelpractise;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 拓展mybatisPlus 支持批量插入
 */
@Component
public class ExpandSqlInjector extends DefaultSqlInjector {

    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
//        TableInfo tableInfo = new TableInfo();
//        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
//        methodList.add(new InsertBatchSomeColumn());
//        return methodList;
        return null;
    }
}
