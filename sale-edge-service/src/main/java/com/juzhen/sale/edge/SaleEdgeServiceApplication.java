package com.juzhen.sale.edge;

import com.juzhen.sale.edge.filter.SaleFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class SaleEdgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaleEdgeServiceApplication.class, args);
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean(SaleFilter saleFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(saleFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/sale/*");
        filterRegistrationBean.setUrlPatterns(urlPatterns);
        return filterRegistrationBean;
    }
}
