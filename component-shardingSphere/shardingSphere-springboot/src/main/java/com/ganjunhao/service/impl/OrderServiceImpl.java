package com.ganjunhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ganjunhao.entity.Order;
import com.ganjunhao.mapper.OrderMapper;
import com.ganjunhao.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ganjunhao
 * @date 2023/6/13 11:14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    Random random = new Random();

    @Override
    public void initEnvironment() throws SQLException {
        orderMapper.createTableIfNotExists();
    }

    @Override
    public void processSuccess() throws SQLException {
        System.out.println("-------------- Process Success Begin ---------------");
        List<Long> orderIds = insertData();
        System.out.println("-------------- Process Success Finish --------------");
    }

    private List<Long> insertData() throws SQLException {
        System.out.println("---------------------------- Insert Data ----------------------------");
        List<Long> result = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            Order order = new Order();
            order.setUserId(random.nextInt(10000));
            order.setAddressId(i);
            order.setStatus("INSERT_TEST");
            orderMapper.insert(order);
            result.add(order.getOrderId());
        }
        return result;
    }
}
