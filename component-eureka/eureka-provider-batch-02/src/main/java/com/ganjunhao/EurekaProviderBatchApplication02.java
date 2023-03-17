package com.ganjunhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ganjunhao
 * @date 2023/2/24 18:21
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class EurekaProviderBatchApplication02 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderBatchApplication02.class, args);
        System.out.println("=========Provider-Batch-02 服务启动成功========");
    }
}
