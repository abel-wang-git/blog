package com.api.base.config;


import com.api.base.model.Role;

/**
 * 项目常量
 */
public final class ProjectConstant {
    private static final String BASE_PACKAGE = "com.api";//项目基础包名称，根据自己公司的项目修改

    static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";//Model所在包
    static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";//Mapper所在包
    static final String MAPPER_INTERFACE_REFERENCE = "com.api.core.Mapper";//Mapper插件基础接口的完全限定名
    public static final String ROLE_ADMIN="admin";
    static final Role[] initRole={new Role("管理员",ROLE_ADMIN)};
}
