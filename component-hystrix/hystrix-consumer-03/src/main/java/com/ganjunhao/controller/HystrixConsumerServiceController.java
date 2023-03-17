package com.ganjunhao.controller;

import com.ganjunhao.constant.R;
import com.ganjunhao.feign.HystrixConsumerFeignClientV2;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class HystrixConsumerServiceController {

    @Resource
    private HystrixConsumerFeignClientV2 hystrixConsumerFeignClientV2;

    /**
     * 测试调用
     *
     */
    @GetMapping("/test")
    public R<Void> test() {
        return hystrixConsumerFeignClientV2.test();
    }

}
