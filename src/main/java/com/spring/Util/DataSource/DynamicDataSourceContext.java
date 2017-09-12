package com.spring.Util.DataSource;

import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * Created by arabira on 17-9-11.
 * 继承AbstractRoutingDataSource的类可以动态配置多数据源
 * 实现ApplicationContextAware的类可以获取到spring配置文件中的所有注入bean对象
 *
 */
public abstract class DynamicDataSourceContext extends AbstractRoutingDataSource implements ApplicationContextAware {

    //默认数据源id
    protected static final String DEFAULT_DATASOURCE_KEY = "dataSource";

    public void setDataSource(String dataSource) {
        DynamicDataSourceHolder.setDataSource(dataSource);
    }
}
