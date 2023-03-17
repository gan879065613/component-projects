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
public class EurekaServerBatchApplication02 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerBatchApplication02.class, args);
        System.out.println("=========Eureka-Batch-02 服务启动成功========");
    }
}
