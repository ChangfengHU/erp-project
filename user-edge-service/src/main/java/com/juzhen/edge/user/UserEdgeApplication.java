package com.juzhen.edge.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootConfiguration
@ComponentScan(basePackages = {"com.juzhen.edge.user"})//扫描接口
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan
public class UserEdgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserEdgeApplication.class, args);
    }

}


