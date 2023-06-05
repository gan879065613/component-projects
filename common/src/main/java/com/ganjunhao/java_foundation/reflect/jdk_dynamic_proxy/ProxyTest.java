package com.ganjunhao.java_foundation.reflect.jdk_dynamic_proxy;

import java.lang.reflect.Proxy;

/**
 * @author ganjunhao
 * @date 2023/6/5 9:45
 */
public class ProxyTest {
    public static void main(String[] args) {
        // JDK 代理必须是接口并且要有实现类
        // 将 Proxy.newProxyInstance 生成的动态代理类存放到磁盘中
        // 默认生成路径 com.sun.proxy 包下
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ActorService actorService = new ActorServiceImpl();
        ActorServiceInvocationHandler handler = new ActorServiceInvocationHandler(actorService);
        // 这里的类加载器为 AppClassLoader, 不明白的可以看下类加载器相关知识
        // 使用 Proxy 创建动态代理类时需要提供类加载器、实现的接口数组、自定义 InvocationHandler 对象作为参数
        ActorService actorServiceProxy = (ActorService) Proxy.newProxyInstance(actorService.getClass().getClassLoader(),
                actorService.getClass().getInterfaces(), handler);
        // 生成的动态代理类重写了 Object 类中的三大基本方法
        // 使用静态代码块来初始化接口中方法的 Method 对象, 包含被代理类的方法以及 Object 的三个方法
        String result = actorServiceProxy.action("吹口哨");
        System.out.println("我总共表演了" + result);
        /**
         * 运行结果:
         * 我要表演：吹口哨
         * 我除了表演：吹口哨
         * 我还要表演一个特技
         * 我总共表演了吹口哨翻跟头
         */
    }

}
