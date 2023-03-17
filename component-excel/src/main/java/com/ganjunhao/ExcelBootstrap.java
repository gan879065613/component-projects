package com.ganjunhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExcelBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ExcelBootstrap.class, args);
        System.out.println("=========Excel服务启动成功========");
    }
}
