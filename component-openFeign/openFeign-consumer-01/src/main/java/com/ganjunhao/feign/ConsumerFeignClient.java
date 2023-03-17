package com.ganjunhao.feign;

import com.ganjunhao.constant.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ganjunhao
 * @date 2023/3/16 11:20
 */
// value = 服务提供者提供的服务名称，即 application.name
@FeignClient(contextId = "consumerFeignClient", value = "eureka-provider-batch", path = ConsumerFeignClient.PATH_PREFIX)
public interface ConsumerFeignClient {
    String PATH_PREFIX = "/provider";

    @GetMapping("/test")
    public R<Void> test();
}
