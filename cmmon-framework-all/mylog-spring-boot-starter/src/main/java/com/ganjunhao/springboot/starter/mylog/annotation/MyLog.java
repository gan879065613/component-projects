package com.ganjunhao.springboot.starter.mylog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Log 注解打印，可以标记在类或者方法上
 * 标记在类上，类下所有方法都会打印
 * 标记在方法上，仅打印标记方法
 * 如果类或者方法上都有标记，以方法上注解为准
 *
 * @author ganjunhao
 * @date 2023/3/16 10:30
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog {

    /**
     * 入参打印
     *
     * @return 打印结果中是否包含入参
     */
    boolean inPrint() default true;

    /**
     * 出参打印
     *
     * @return 打印结果中是否包含出参
     */
    boolean outPrint() default true;
}
