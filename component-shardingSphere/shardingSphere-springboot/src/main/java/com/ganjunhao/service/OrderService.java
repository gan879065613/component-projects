package com.ganjunhao.service;

import java.sql.SQLException;

public interface OrderService {
    void initEnvironment() throws SQLException;

    void processSuccess() throws SQLException;
}
