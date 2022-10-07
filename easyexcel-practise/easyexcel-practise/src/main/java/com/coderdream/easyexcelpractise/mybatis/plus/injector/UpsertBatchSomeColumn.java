package com.coderdream.easyexcelpractise.mybatis.plus.injector;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author : spark
 * @date : 2021-07-18 18:53
 */
@SuppressWarnings("serial")
@Slf4j
public class UpsertBatchSomeColumn extends AbstractMethod {

    private static final String UPDATE_DUPLICATE_KEY = " ON DUPLICATE KEY UPDATE ";


    /**
     * 默认方法名
     */
    public UpsertBatchSomeColumn() {
        super("upsertBatchSomeColumn");
    }

    /**
     * 默认方法名
     *
     * @param predicate 字段筛选条件
     */
    public UpsertBatchSomeColumn(Predicate<TableFieldInfo> predicate) {
        super("upsertBatchSomeColumn");
        this.predicate = predicate;
    }

    /**
     * @param name      方法名
     * @param predicate 字段筛选条件
     * @since 3.5.0
     */
    public UpsertBatchSomeColumn(String name, Predicate<TableFieldInfo> predicate) {
        super(name);
        this.predicate = predicate;
    }
    /**
     * 字段筛选条件
     */
    @Setter
    @Accessors(chain = true)
    private Predicate<TableFieldInfo> predicate;

    @SuppressWarnings("Duplicates")
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        KeyGenerator keyGenerator = new NoKeyGenerator();
        SqlMethod sqlMethod = SqlMethod.INSERT_ONE;
        List<TableFieldInfo> fieldList = tableInfo.getFieldList();
        String insertSqlColumn = tableInfo.getKeyInsertSqlColumn(true,false) +
                this.filterTableFieldInfo(fieldList, predicate, TableFieldInfo::getInsertSqlColumn, EMPTY);
        String columnScript = LEFT_BRACKET + insertSqlColumn.substring(0, insertSqlColumn.length() - 1) + RIGHT_BRACKET;
        String insertSqlProperty = tableInfo.getKeyInsertSqlProperty(true,ENTITY_DOT, false) +
                this.filterTableFieldInfo(fieldList, predicate, i -> i.getInsertSqlProperty(ENTITY_DOT), EMPTY);
        insertSqlProperty = LEFT_BRACKET + insertSqlProperty.substring(0, insertSqlProperty.length() - 1) + RIGHT_BRACKET;
        String valuesScript = SqlScriptUtils.convertForeach(insertSqlProperty, "list", null, ENTITY, COMMA);
        String keyProperty = null;
        String keyColumn = null;
        // 表包含主键处理逻辑,如果不包含主键当普通字段处理
        if (tableInfo.havePK()) {
            if (tableInfo.getIdType() == IdType.AUTO) {
                /* 自增主键 */
                keyGenerator = new Jdbc3KeyGenerator();
                keyProperty = tableInfo.getKeyProperty();
                keyColumn = tableInfo.getKeyColumn();
            } else {
                if (null != tableInfo.getKeySequence()) {
                    keyGenerator = TableInfoHelper.genKeyGenerator(this.methodName, tableInfo, builderAssistant);
                    keyProperty = tableInfo.getKeyProperty();
                    keyColumn = tableInfo.getKeyColumn();
                }
            }
        }
        String updateScript = UPDATE_DUPLICATE_KEY + fieldList.stream().filter(tableFieldInfo -> !"create_time".equals(tableFieldInfo.getColumn()))
                .map(tableFieldInfo -> tableFieldInfo.getColumn() + " = VALUES" + LEFT_BRACKET + tableFieldInfo.getColumn() + RIGHT_BRACKET)
                .collect(Collectors.joining(","))+"</script>";

        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), columnScript, valuesScript,updateScript);
//        sql = sql + updateScript;
        sql = sql.replace("</script>",updateScript);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, this.methodName, sqlSource, keyGenerator, keyProperty, keyColumn);
    }
}




