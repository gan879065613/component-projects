package com.ganjunhao.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ganjunhao
 * @date 2023/3/16 10:05
 */
@Configuration
public class RestTemplateConfig {

    @Bean //将 RestTemplate 注入到容器中
    @LoadBalanced //在客户端使用 RestTemplate 请求服务端时，开启负载均衡（Ribbon）
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * 配置Ribbon自带负载均衡策略
     * RoundRobinRule：	按照线性轮询策略，即按照一定的顺序依次选取服务实例
     * RandomRule：随机选取一个服务实例
     * RetryRule：	按照 RoundRobinRule（轮询）的策略来获取服务，如果获取的服务实例为 null 或已经失效，
     * 则在指定的时间之内不断地进行重试（重试时获取服务的策略还是 RoundRobinRule 中定义的策略），如果超过指定时间依然没获取到服务实例则返回 null 。
     * WeightedResponseTimeRule：WeightedResponseTimeRule 是 RoundRobinRule 的一个子类，它对 RoundRobinRule 的功能进行了扩展。
     * 根据平均响应时间，来计算所有服务实例的权重，响应时间越短的服务实例权重越高，被选中的概率越大。刚启动时，如果统计信息不足，
     * 则使用线性轮询策略，等信息足够时，再切换到 WeightedResponseTimeRule
     * BestAvailableRule：继承自 ClientConfigEnabledRoundRobinRule。先过滤点故障或失效的服务实例，然后再选择并发量最小的服务实例
     * AvailabilityFilteringRule：先过滤掉故障或失效的服务实例，然后再选择并发量较小的服务实例。
     * ZoneAvoidanceRule：默认的负载均衡策略，综合判断服务所在区域（zone）的性能和服务（server）的可用性，来选择服务实例。在没有区域的环境下，该策略与轮询（RandomRule）策略类似。
     */
 /*   @Bean
    public IRule myRule() {
        // RandomRule 为随机策略
        return new RandomRule();
    }*/
}
