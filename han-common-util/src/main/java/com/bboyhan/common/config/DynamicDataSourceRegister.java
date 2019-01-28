package com.bboyhan.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/24 18:36
 */
@Slf4j
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {


    //指定默认数据源(springboot2.0默认数据源是hikari,如何想使用其他数据源可以自己配置)
    private static final String DATASOURCE_TYPE_DEFAULT = "com.alibaba.druid.pool.DruidDataSource";
    //默认数据源
    private DataSource defaultDataSource;
    //用户自定义数据源
    private Map<String, DataSource> slaveDataSources = new HashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        initDefaultDataSource(environment);
        initMutilDataSources(environment);
    }

    private void initDefaultDataSource(Environment env) {
        // 读取主数据源
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("driver", env.getProperty("spring.datasource.default.driver"));
        dsMap.put("url", env.getProperty("spring.datasource.default.url"));
        dsMap.put("username", env.getProperty("spring.datasource.default.username"));
        dsMap.put("password", env.getProperty("spring.datasource.default.password"));
        dsMap.put("type", env.getProperty("spring.datasource.default.type"));
        defaultDataSource = buildDataSource(dsMap);
    }


    private void initMutilDataSources(Environment env) {
        // 读取配置文件获取更多数据源
        String dsPrefixs = env.getProperty("spring.datasource.names");
        for (String dsPrefix : dsPrefixs.split(",")) {
            // 多个数据源
            Map<String, Object> dsMap = new HashMap<>();
            dsMap.put("driver", env.getProperty("spring.datasource." + dsPrefix + ".driver"));
            dsMap.put("url", env.getProperty("spring.datasource." + dsPrefix + ".url"));
            dsMap.put("username", env.getProperty("spring.datasource." + dsPrefix + ".username"));
            dsMap.put("password", env.getProperty("spring.datasource." + dsPrefix + ".password"));
            DataSource ds = buildDataSource(dsMap);
            slaveDataSources.put(dsPrefix, ds);
        }
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //添加默认数据源
        targetDataSources.put("dataSource", this.defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        //添加其他数据源
        targetDataSources.putAll(slaveDataSources);
        DynamicDataSourceContextHolder.dataSourceIds.addAll(slaveDataSources.keySet());

        //创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        //注册 - BeanDefinitionRegistry
        beanDefinitionRegistry.registerBeanDefinition("dataSource", beanDefinition);

        log.info("Dynamic DataSource Registry");
    }

    public DataSource buildDataSource(Map<String, Object> dataSourceMap) {
        try {
            Object type = dataSourceMap.get("type");
            if (type == null) {
                type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
            }

            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
            String driverClassName = dataSourceMap.get("driver").toString();
            String url = dataSourceMap.get("url").toString();
            String username = dataSourceMap.get("username").toString();
            String password = dataSourceMap.get("password").toString();
            // 自定义DataSource配置
            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
