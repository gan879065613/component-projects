package com.ganjunhao.java_foundation.reflect.jdk_dynamic_proxy;

/**
 * @author ganjunhao
 * @date 2023/6/5 9:39
 */
public class ActorServiceImpl implements ActorService{
    @Override
    public String action(Object obj) {
        System.out.println("我要表演：" + obj.toString());
        return obj.toString();
    }
}
