package com.api.common.mybatis;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

public class NameSpace extends PluginAdapter {
    @Override
    public void initialized(IntrospectedTable introspectedTable) {

        introspectedTable.setMyBatis3FallbackSqlMapNamespace(introspectedTable.getBaseRecordType().replaceAll(".model",".dao")+"Mapper");

        super.initialized(introspectedTable);
    }

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

}
