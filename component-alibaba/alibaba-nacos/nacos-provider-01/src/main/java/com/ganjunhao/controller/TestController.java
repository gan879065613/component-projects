package com.ganjunhao.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganjunhao
 * @date 2023/3/27 11:30
 */
@RestController
@Slf4j
@RequestMapping("/nacos")
public class TestController {
    @Value("${server.port}")
    private String serverPort;
    @GetMapping(value = "/test")
    public String getPayment() {
        return "<h2>c语言中文网提醒您，服务访问成功！</h2>服务名：spring-cloud-alibaba-provider<br /> 端口号： " + serverPort + "<br /> 传入的参数：";
    }
}
