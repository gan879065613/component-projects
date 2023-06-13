package com.ganjunhao.simpleDemo;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author ganjunhao
 * @date 2023/6/12 17:51
 */
public class TestMain {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = ShardingDatabasesAndTableConfiguration.getDataSource();
        OrderService orderService = new OrderServiceImpl(dataSource);
        orderService.initEnvironment();
        orderService.processSuccess();
    }
}
