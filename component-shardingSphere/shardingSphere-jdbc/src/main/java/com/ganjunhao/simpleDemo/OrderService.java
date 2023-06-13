package com.ganjunhao.simpleDemo;

import java.sql.SQLException;

/**
 * @author ganjunhao
 * @date 2023/6/12 17:48
 */
public interface OrderService {
    /**
     * 初始化表结构
     *
     * @throws SQLException SQL exception
     */
    void initEnvironment() throws SQLException;

    /**
     * 执行成功
     *
     * @throws SQLException SQL exception
     */
    void processSuccess() throws SQLException;
}
