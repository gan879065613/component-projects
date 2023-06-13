package com.ganjunhao.controller;

import com.ganjunhao.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @author ganjunhao
 * @date 2023/6/13 11:19
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping
    public void init() throws SQLException {
        orderService.initEnvironment();
        orderService.processSuccess();
    }
}
