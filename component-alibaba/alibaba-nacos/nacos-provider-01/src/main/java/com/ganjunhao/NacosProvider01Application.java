package com.ganjunhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ganjunhao
 * @date 2023/3/27 10:29
 */

@SpringBootApplication
@EnableDiscoveryClient //开启服务发现功能
public class NacosProvider01Application {
    public static void main(String[] args) {
        SpringApplication.run(NacosProvider01Application.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ Nacos服务提供者启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
