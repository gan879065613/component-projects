package com.ganjunhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ganjunhao
 * @date 2023/7/3 10:46
 */
@SpringBootApplication
public class PrintApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrintApplication.class, args);
        System.out.println("启动成功");
    }
}
