package com.ganjunhao.controller;

import com.ganjunhao.constant.R;
import com.ganjunhao.service.HystrixConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ganjunhao
 * @date 2023/3/17 9:16
 */
@RestController
@RequestMapping("/hystrixConsumer")
public class HystrixConsumerServiceController {

    @Resource
    private HystrixConsumerService hystrixConsumerService;

    /**
     * 测试调用
     *
     */
    @GetMapping("/test")
    public R<Void> test() {
        return hystrixConsumerService.test();
    }
}
