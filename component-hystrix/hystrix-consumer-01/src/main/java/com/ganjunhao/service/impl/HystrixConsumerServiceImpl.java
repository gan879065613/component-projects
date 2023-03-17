package com.ganjunhao.service.impl;

import com.ganjunhao.constant.R;
import com.ganjunhao.service.HystrixConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author ganjunhao
 * @date 2023/3/16 22:42
 */
@Service
public class HystrixConsumerServiceImpl implements HystrixConsumerService {

    //一旦该方法失败并抛出了异常信息后，会自动调用  @HystrixCommand 注解标注的 fallbackMethod 指定的方法
    @HystrixCommand(fallbackMethod = "backMethod", commandProperties =
            //规定 5 秒钟以内就不报错，正常运行，超过 5 秒就报错，调用指定的方法
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    @Override
    public R<Void> test() {
        int outTime = 6;
        try {
            TimeUnit.SECONDS.sleep(outTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok("线程池：" + Thread.currentThread().getName() + "  deptInfo_Timeout,  耗时: " + outTime);
    }

    // 当服务出现故障后，调用该方法给出友好提示
    @Override
    public R<Void> backMethod() {
        return R.ok("C语言中文网提醒您，系统繁忙请稍后再试！" + "线程池：" + Thread.currentThread().getName() + "  deptInfo_Timeout,id:   " );
    }
}
