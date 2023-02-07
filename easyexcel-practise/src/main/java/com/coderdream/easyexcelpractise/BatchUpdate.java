package com.coderdream.easyexcelpractise;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.var;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Objects;
import java.util.function.Predicate;

import static java.util.stream.Collectors.joining;

/**
 *
 * @Description
 * @Datetime 2022/4/25 15:19
 * @Modified By
 */
public class BatchUpdate extends AbstractMethod {
    /**
     * 字段筛选条件
     */
    @Setter
    @Accessors (chain = true)
    private Predicate<TableFieldInfo> predicate;

    @Override
    public MappedStatement injectMappedStatement (Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        KeyGenerator keyGenerator = NoKeyGenerator.INSTANCE;
        SqlMethodEnum sqlMethod = SqlMethodEnum.BATCH_INSERT;
        var columnScript = tableInfo.getFieldList().stream().map(s -> s.getColumn()).filter(Objects :: nonNull).collect(joining(DOT_NEWLINE));
        String valuesScript = tableInfo.getFieldList().stream().map(s -> s.getInsertSqlProperty("list.")).filter(Objects :: nonNull).collect(joining(NEWLINE));
        var valuesLength =   valuesScript.length()- 1;
        if(valuesScript.lastIndexOf(COMMA)==valuesLength){
            valuesScript= valuesScript.substring(0,valuesLength);
        }
        String keyProperty = null;
        String keyColumn = null;
        // 表包含主键处理逻辑,如果不包含主键当普通字段处理
        if (StringUtils.isNotBlank(tableInfo.getKeyProperty())) {
            if (tableInfo.getIdType() == IdType.AUTO) {
                /* 自增主键 */
                keyGenerator = Jdbc3KeyGenerator.INSTANCE;
                keyProperty = tableInfo.getKeyProperty();
                keyColumn = tableInfo.getKeyColumn();
            } else {
                if (null != tableInfo.getKeySequence()) {
                    keyGenerator = TableInfoHelper.genKeyGenerator(sqlMethod.getMethod(), tableInfo, builderAssistant);
                    keyProperty = tableInfo.getKeyProperty();
                    keyColumn = tableInfo.getKeyColumn();
                }
            }
        }
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), columnScript, valuesScript);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, SqlMethodEnum.BATCH_INSERT.getMethod(), sqlSource, keyGenerator, keyProperty, keyColumn);
    }
}
