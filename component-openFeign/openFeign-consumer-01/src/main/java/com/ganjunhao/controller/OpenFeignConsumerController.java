package com.ganjunhao.controller;

import com.ganjunhao.constant.R;
import com.ganjunhao.feign.ConsumerFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ganjunhao
 * @date 2023/3/16 11:34
 */
@RestController
@RequestMapping("/openFeignConsumer")
public class OpenFeignConsumerController {

    @Resource
    private ConsumerFeignClient consumerFeignClient;

    /**
     * 测试调用
     *
     */
    @GetMapping("/test")
    public R<Void> test() {
        return consumerFeignClient.test();
    }
}
