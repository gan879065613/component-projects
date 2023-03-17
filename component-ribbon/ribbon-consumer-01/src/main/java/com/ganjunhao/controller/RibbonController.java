package com.ganjunhao.controller;

import com.ganjunhao.constant.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ganjunhao
 * @date 2023/3/16 10:06
 */
@RestController
@RequestMapping("/consumer")
public class RibbonController {

    // private static final String REST_URL_PROVIDER_PREFIX = "http://localhost:8001/"; 这种方式是直调用服务方的方法，根本没有用到 Spring Cloud
    // 面向微服务编程，即通过微服务的名称来获取调用地址
    private static final String SERVER_URL = "http://eureka-provider-batch"; // 使用注册到 Spring Cloud Eureka 服务注册中心中的服务，即 application.name
    @Autowired
    private RestTemplate restTemplate; //RestTemplate 是一种简单便捷的访问 restful 服务模板类，是 Spring 提供的用于访问 Rest 服务的客户端模板工具集，提供了多种便捷访问远程 HTTP 服务的方法

    @GetMapping("/test")
    public R<Void> test() {
        return restTemplate.getForObject(SERVER_URL + "/provider/test", R.class);
    }

}
