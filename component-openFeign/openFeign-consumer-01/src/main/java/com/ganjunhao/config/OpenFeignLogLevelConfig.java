package com.ganjunhao.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ganjunhao
 * @date 2023/3/16 11:52
 */

@Configuration
public class OpenFeignLogLevelConfig {

    /**
     * OpenFeign 日志增强
     * 配置 OpenFeign 记录哪些内容
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        /* Logger.Level 的具体级别如下：
            NONE：不记录任何信息。
            BASIC：仅记录请求方法、URL 以及响应状态码和执行时间。
            HEADERS：除了记录 BASIC 级别的信息外，还会记录请求和响应的头信息。
            FULL：记录所有请求与响应的明细，包括头信息、请求体、元数据等等。*/
        return Logger.Level.FULL;

    }
}
