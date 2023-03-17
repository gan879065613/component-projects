package com.ganjunhao.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author lixiaolong
 * @date 2020/12/23 9:22
 * @description 创建自己的负载均衡接口
 */
public interface MyLoadBalancer {

    /**
     * 收集服务器总共有多少台能够提供服务的机器，并放到list里面
     *
     * @param serviceInstances
     * @return ServiceInstance
     * @author lixiaolong
     * @date 2020/12/23 9:24
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);


}
