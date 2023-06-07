package com.ganjunhao.springboot.starter.mylog.config;

import com.ganjunhao.springboot.starter.mylog.aspect.MyLogPrintAspect;
import org.springframework.context.annotation.Bean;

/**
 * @author ganjunhao
 * @date 2023/3/16 10:30
 */
public class LogAutoConfiguration {

    /**
     * 日志打印AOP切面
     */
    @Bean
    public MyLogPrintAspect myLogPrintAspect() {
        return new MyLogPrintAspect();
    }
}
