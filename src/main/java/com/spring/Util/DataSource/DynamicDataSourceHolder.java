package com.spring.Util.DataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arabira on 17-9-11.
 * 数据源信息上下文,用以保存当前正在使用的数据源信息
 */
public class DynamicDataSourceHolder {
    private static ThreadLocal<String> threadContext  = new ThreadLocal<String>();

    public static void setDataSource(String dataSource) {
        threadContext.set(dataSource);
    }

    public static String getDataSource() {
        String dataSourceConfig = threadContext.get();
        return dataSourceConfig;
    }

    public static void clearAllSource() {
        threadContext.remove();
    }

}
