package com.ganjunhao.config.myBalanceRule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ganjunhao
 * @date 2023/3/16 10:30
 */
@Configuration
public class MyBalanceRuleConfig {
    @Bean
    public IRule myRule() {
        //自定义 Ribbon 负载均衡策略
        return new MyBalanceRule(); //自定义，随机选择某一个微服务，执行五次
    }
}
