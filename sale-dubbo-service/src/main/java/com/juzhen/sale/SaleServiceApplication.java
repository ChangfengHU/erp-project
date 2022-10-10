package com.juzhen.sale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Michael on 2017/10/28.
 */
//@ComponentScan(basePackages = {"com.juzhen"})//扫描接口
//@SpringBootConfiguration
//@EnableAutoConfiguration
@SpringBootApplication
public class SaleServiceApplication {

    public static void main(String args[]) throws InterruptedException {
        SpringApplication.run(SaleServiceApplication.class, args);
        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
