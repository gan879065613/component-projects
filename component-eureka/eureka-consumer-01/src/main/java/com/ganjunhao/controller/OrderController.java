package com.ganjunhao.controller;

import com.ganjunhao.constant.R;
import com.ganjunhao.lb.MyLoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author lixiaolong
 * @date 2020/12/19 11:31
 * @description
 */
@RestController
@RequestMapping("/consumer")
public class OrderController {

//    public static final String consumer_URL = "http://localhost:8001";

    public static final String SERVER_URL = "http://eureka-provider-batch-01";

    @Resource
    private RestTemplate restTemplate;

    // 注入自定义的负载均衡规则
    @Resource
    private MyLoadBalancer myLoadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/test")
    public R<Void> test() {
        return restTemplate.getForObject(SERVER_URL + "/provider/test", R.class);
    }

    /**
     * @author lixiaolong
     * @date 2020/12/23 10:27
     * @description 测试自定义的负载均衡规则
     */
    @GetMapping(value = "/consumer/lb")
    public String getConsumerLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-consumer-01");
        if (instances == null || instances.isEmpty()) {
            return null;
        }
        // 调用自定义的负载均衡策略
        ServiceInstance serviceInstance = myLoadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/consumer/lb", String.class);

    }

    // ====================> zipkin+sleuth
    @GetMapping("/consumer/zipkin")
    public String consumerZipkin() {
        String result = restTemplate.getForObject(SERVER_URL + "/provider/zipkin/", String.class);
        return result;
    }
}
