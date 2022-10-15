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
    @Bean
    public com.alibaba.dubbo.config.ConsumerConfig consumerConfig() {
        com.alibaba.dubbo.config.ConsumerConfig consumerConfig = new com.alibaba.dubbo.config.ConsumerConfig();
        consumerConfig.setCheck(false);
        consumerConfig.setTimeout(40000);
        return consumerConfig;
    }

}