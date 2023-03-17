package com.ganjunhao.feign.fallback;

import com.ganjunhao.constant.R;
import com.ganjunhao.feign.HystrixConsumerFeignClient;
import com.ganjunhao.feign.HystrixConsumerFeignClientV2;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ganjunhao
 * @date 2023/3/16 11:20
 */
@Component
public class HystrixConsumerFeignFallbackFactory implements FallbackFactory<HystrixConsumerFeignClientV2> {

    @Override
    public HystrixConsumerFeignClientV2 create(Throwable throwable) {
        return new HystrixConsumerFeignClientV2()
        {
            @Override
            public R<Void> test() {
                return R.fail("C语言中文网提醒您：服务端系统繁忙，请稍后再试！（客户端 deptInfo_Timeout 专属的回退方法触发）");
            }
        };
    }
}
