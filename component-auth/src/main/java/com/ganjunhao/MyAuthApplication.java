package com.ganjunhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ganjunhao
 * @date 2023/4/3 13:34
 */
@SpringBootApplication(scanBasePackages = "com.ganjunhao")
public class MyAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAuthApplication.class, args);
        System.out.println("启动成功");
    }
}
