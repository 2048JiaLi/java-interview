package com.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/15 9:09
 */
public class DruidUtils {
    private static DruidDataSource dataSource;

    static {
        Properties properties = new Properties();
        try {
            properties.load(DruidUtils.class.getClassLoader().getResourceAsStream("database.properties"));
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DruidDataSource getDataSource() {
        return dataSource;
    }
}
