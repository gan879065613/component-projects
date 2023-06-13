package com.ganjunhao.simpleDemo;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @author ganjunhao
 * @date 2023/6/12 17:46
 */
public class OrderRepositoryImpl implements OrderRepository {

    private final DataSource dataSource;

    public OrderRepositoryImpl(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createTableIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS t_order (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, address_id BIGINT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id))";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    @Override
    public Long insert(final Order order) throws SQLException {
        String sql = "INSERT INTO t_order (user_id, address_id, status) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setLong(2, order.getAddressId());
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    order.setOrderId(resultSet.getLong(1));
                }
            }
        }
        return order.getOrderId();
    }
}
