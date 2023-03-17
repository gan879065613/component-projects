package com.ganjunhao.feign.fallback;

import com.ganjunhao.constant.R;
import com.ganjunhao.feign.HystrixConsumerFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author ganjunhao
 * @date 2023/3/16 11:20
 */
//@Component
public class HystrixConsumerFeignFallback implements HystrixConsumerFeignClient {

    public R<Void> test(){
        return R.fail("C语言中文网提醒您：服务端系统繁忙，请稍后再试！（客户端 deptInfo_Timeout 专属的回退方法触发）");
    }

}
