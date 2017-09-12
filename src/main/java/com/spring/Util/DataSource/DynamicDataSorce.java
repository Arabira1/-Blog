package com.spring.Util.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by arabira on 17-9-11.
 */
public class DynamicDataSorce extends DynamicDataSourceContext {
    //当前系统中所有数据源信息
    private Map<Object, Object> targetDataSource;
    private static ApplicationContext applicationContext;

    private Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    private void addNewDataSource(String dataId, DruidDataSource newDataSource) {
        targetDataSource.put(dataId, newDataSource);
        super.setTargetDataSources(this.targetDataSource);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DynamicDataSourceHolder.getDataSource();
        if (null == dataSource || "".equals(dataSource)) {
            return DEFAULT_DATASOURCE_KEY;
        }
        verifyAndInitDataSource(dataSource);
        return dataSource;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       this.applicationContext = applicationContext;
    }

    public void setDataSource(String dataSource) {
        if (null == dataSource || "".equals(dataSource)) {
            super.setDataSource(DEFAULT_DATASOURCE_KEY);
        }
        else {
            super.setDataSource(dataSource);
        }
    }

    public void verifyAndInitDataSource(String dataSource) {
        String checkDataSourceKey = dataSource;
        Object checkDataSource = targetDataSource.get(checkDataSourceKey);
        if (null != checkDataSource) {
            return;
        }
        else {
            try {
                throw new Exception("不存在此源，请添加");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public DruidDataSource createDataSource(String dataId, String url, String username, String password) {
        DruidDataSource nowUsing = (DruidDataSource) getBean(DEFAULT_DATASOURCE_KEY);
        DruidDataSource newDataSource = new DruidDataSource();
        newDataSource.setUrl(url);
        newDataSource.setUsername(username);
        newDataSource.setPassword(password);
        newDataSource.setInitialSize(nowUsing.getInitialSize());
        newDataSource.setMinIdle(nowUsing.getMinIdle());
        newDataSource.setMaxActive(nowUsing.getMaxActive());
        newDataSource.setMaxWait(nowUsing.getMaxWait());
        newDataSource.setTimeBetweenConnectErrorMillis(nowUsing.getTimeBetweenConnectErrorMillis());
        newDataSource.setTimeBetweenEvictionRunsMillis(nowUsing.getTimeBetweenEvictionRunsMillis());
        newDataSource.setMinEvictableIdleTimeMillis(nowUsing.getMinEvictableIdleTimeMillis());
        newDataSource.setValidationQuery(nowUsing.getValidationQuery());
        newDataSource.setMaxPoolPreparedStatementPerConnectionSize(nowUsing
                .getMaxPoolPreparedStatementPerConnectionSize());
        try {
            newDataSource.setFilters("wall");
            addNewDataSource(dataId, newDataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newDataSource;
    }

    public void deleteTheDataSource(String dataSourceKey) {
        Object checkSource = this.targetDataSource.get(dataSourceKey);
        if (null == checkSource) {
            return;
        }
        else {
            targetDataSource.remove(dataSourceKey);
            setTargetDataSources(targetDataSource);
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        this.targetDataSource = targetDataSources;
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }


}
