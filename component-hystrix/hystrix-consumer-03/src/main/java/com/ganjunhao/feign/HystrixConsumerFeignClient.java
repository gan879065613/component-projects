package com.ganjunhao.feign;

import com.ganjunhao.constant.R;
import com.ganjunhao.feign.fallback.HystrixConsumerFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ganjunhao
 * @date 2023/3/16 11:20
 */
// value = 服务提供者提供的服务名称，即 application.name
@FeignClient(contextId = "hystrixConsumerFeignClient",
        value = "eureka-provider-batch",
        path = HystrixConsumerFeignClient.PATH_PREFIX,
        fallback = HystrixConsumerFeignFallback.class)
public interface HystrixConsumerFeignClient {
    String PATH_PREFIX = "/provider";

    @GetMapping("/test")
    public R<Void> test();

}
