package com.ganjunhao.controller;

import com.ganjunhao.constant.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * (provider)表控制层
 *
 * @author lixiaolong
 * @date 2022/01/13 16:05
 */
@RestController
@RequestMapping("/provider")
public class Provider02Controller {


    @Value("${server.port}")
    private String serverPort;

    // 服务发现
    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 测试调用
     *
     */
    @GetMapping("/test")
    public R<Void> test() {
        return R.ok("服务提供者02被调用了，端口：" + serverPort);
    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        services.forEach(System.out::println);
        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-provider-batch-02");
        instances.forEach(instance -> {
            System.out.println(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        });

        return this.discoveryClient;
    }

    @GetMapping("/serverPort")
    public String getServerPort() {
        return serverPort;
    }

    @GetMapping("/feign/timeout")
    public String getFeignTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    /**
     * 链路监控
     *
     */
    @GetMapping("/zipkin")
    public String providerZipkin() {
        return "hi,i`am providerzipkin server fall back.welcome to atguigu.hahahahahhahahah";
    }
}