package com.ganjunhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //开启 OpenFeign 功能
@EnableHystrix //启用 Hystrix
public class HystrixConsumer03Application {
    public static void main(String[] args) {
        SpringApplication.run(HystrixConsumer03Application.class, args);
        System.out.println("启动成功");
    }

}
