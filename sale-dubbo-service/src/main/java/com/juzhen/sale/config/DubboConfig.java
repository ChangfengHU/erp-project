package com.juzhen.sale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.dubbo.config.ConsumerConfig;
@Configuration
public class DubboConfig {
    /**
     * 消费者配置不主动监督zookeeper服务
     *
     * @return
     */
    @Bean
    public ConsumerConfig consumerConfig() {
       ConsumerConfig consumerConfig = new ConsumerConfig();
       consumerConfig.setCheck(false);
       consumerConfig.setTimeout(40000);
       return consumerConfig;
    }
}