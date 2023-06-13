package com.ganjunhao.simpleDemo;

import java.sql.SQLException;

/**
 * @author ganjunhao
 * @date 2023/6/12 17:43
 */
public interface OrderRepository {

    void createTableIfNotExists() throws SQLException;

    Long insert(final Order order) throws SQLException;

}
