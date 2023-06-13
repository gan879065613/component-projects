package com.ganjunhao;

import com.ganjunhao.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

/**
 * @author ganjunhao
 * @date 2023/6/13 11:20
 */
@SpringBootTest(classes = ShardingApplication.class)
@RunWith(SpringRunner.class)
public class ShardingTest {

    @Autowired
    OrderService orderService;

    @Test
    public void orderTest() throws SQLException {
        orderService.initEnvironment();
        orderService.processSuccess();
    }
}
