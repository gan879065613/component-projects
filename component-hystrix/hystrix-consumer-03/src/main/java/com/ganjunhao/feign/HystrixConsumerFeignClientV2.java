package com.ganjunhao.feign;

import com.ganjunhao.constant.R;
import com.ganjunhao.feign.fallback.HystrixConsumerFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ganjunhao
 * @date 2023/3/16 11:20
 */
// value = 服务提供者提供的服务名称，即 application.name
@FeignClient(contextId = "hystrixConsumerFeignClient",
        value = "eureka-provider-batch",
        path = HystrixConsumerFeignClientV2.PATH_PREFIX,
        fallbackFactory = HystrixConsumerFeignFallbackFactory.class)
public interface HystrixConsumerFeignClientV2 {
    String PATH_PREFIX = "/provider";

    @GetMapping("/test")
    public R<Void> test();

}
