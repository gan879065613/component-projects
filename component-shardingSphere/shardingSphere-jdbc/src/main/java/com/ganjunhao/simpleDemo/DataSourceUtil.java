package com.ganjunhao.simpleDemo;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * @author ganjunhao
 * @date 2023/6/12 17:49
 */
public class DataSourceUtil {
    private static final String HOST = "127.0.0.1";

    private static final int PORT = 63306;

    private static final String USER_NAME = "root";

    private static final String PASSWORD = "haohao1995810";

    public static DataSource createDataSource(final String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName("com.mysql.cj.jdbc.Driver");
        result.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8", HOST, PORT, dataSourceName));
        result.setUsername(USER_NAME);
        result.setPassword(PASSWORD);
        return result;
    }
}
