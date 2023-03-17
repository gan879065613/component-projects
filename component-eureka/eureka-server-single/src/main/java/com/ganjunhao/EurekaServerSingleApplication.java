package com.ganjunhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ganjunhao
 * @date 2023/2/24 18:21
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerSingleApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerSingleApplication.class, args);
        System.out.println("=========Eureka-Single 服务启动成功========");
    }
}
