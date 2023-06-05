package com.ganjunhao.java_foundation.reflect.jdk_dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ganjunhao
 * @date 2023/6/5 9:41
 */
public class ActorServiceInvocationHandler implements InvocationHandler {
    private ActorService actorService;

    public ActorServiceInvocationHandler(ActorService actorService) {
        this.actorService = actorService;
    }

    /**
     * 通过生成的动态代理类, 调用真正被代理类的执行方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(actorService, args);
        System.out.println("我除了表演：" + invoke.toString());
        String extendAction = "翻跟头";
        System.out.println("我还要表演一个特技");
        return invoke.toString() + extendAction;
    }
}
