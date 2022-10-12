package com.juzhen.sale.edge.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MyConfigurtion {
    /**
     * 消费者配置不主动监督zookeeper服务
     *
     * @return
     */
//    @Bean
    public ApplicationConfig consumerConfig() {
        ApplicationConfig consumerConfig = new ApplicationConfig();
        log.info("消费者配置.....");
        return consumerConfig;
    }

}