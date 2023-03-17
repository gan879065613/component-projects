package com.ganjunhao.controller;

import com.ganjunhao.constant.R;
import com.ganjunhao.feign.HystrixConsumerFeignClient;
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
    private HystrixConsumerFeignClient hystrixConsumerFeignClient;

    /**
     * 测试调用
     *
     */
    @GetMapping("/test")
    public R<Void> test() {
        return hystrixConsumerFeignClient.test();
    }

    // backMethod方法的 专用 fallback 方法
    public R<Void> backMethod() {
        log.info("backMethod出错，服务已被降级！");
        return R.fail("C语言中文网提醒您：服务端系统繁忙，请稍后再试！（客户端 deptInfo_Timeout 专属的回退方法触发）");
    }
}
