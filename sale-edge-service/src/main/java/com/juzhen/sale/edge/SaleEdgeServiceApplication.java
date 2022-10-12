package com.juzhen.sale.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SaleEdgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaleEdgeServiceApplication.class, args);
    }

}
