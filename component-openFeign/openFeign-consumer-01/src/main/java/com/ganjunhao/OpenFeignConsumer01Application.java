package com.ganjunhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenFeignConsumer01Application {

    public static void main(String[] args) {
        SpringApplication.run(OpenFeignConsumer01Application.class, args);
        System.out.println("启动成功");

    }

}
