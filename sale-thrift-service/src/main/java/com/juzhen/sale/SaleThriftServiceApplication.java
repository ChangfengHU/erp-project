package com.juzhen.sale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 25131 on 2019/11/3.
 */

@ComponentScan(basePackages = {"com.juzhen"})//扫描接口
@SpringBootConfiguration
@EnableAutoConfiguration
@ServletComponentScan
public class SaleThriftServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaleThriftServiceApplication.class,args);
    }
}
