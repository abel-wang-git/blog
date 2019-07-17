package com.api.common.mybatis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Configuration
@ComponentScan(value = "com", excludeFilters = { @ComponentScan.Filter(Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, value = { RootConfiguration.class }) })
public class RootConfiguration extends SpringBootServletInitializer {
    @Autowired
    private MapperRefresh mapperRefresh;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.registerShutdownHook(false);
        return application.sources(RootConfiguration.class);
    }

    @PostConstruct
    public void postConstruct(){
        mapperRefresh.run();
    }

}
