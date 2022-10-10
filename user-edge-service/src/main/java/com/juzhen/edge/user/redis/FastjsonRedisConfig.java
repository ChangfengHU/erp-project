//package com.juzhen.http.user.redis;
//
//import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
//import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class FastjsonRedisConfig {
//
//    /**
//     * 自定义redisTemplate配置
//     *
//     * 使用的序列化方式
//     *
//     * @param redisConnectionFactory
//     * @return
//     */
////    @Bean
////    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
////        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
////        redisTemplate.setConnectionFactory(redisConnectionFactory);
////        //key值使用spring默认的StringRedisSerializer
////        redisTemplate.setKeySerializer(new StringRedisSerializer());
////        //value值使用fastjson的GenericFastJsonRedisSerializer
////        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
////        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
////        //以下是hash序列化的配置
////        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
////        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
////
////        return redisTemplate;
////    }
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        //使用fastjson序列化
//        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
//        // value值的序列化采用fastJsonRedisSerializer
//        template.setValueSerializer(fastJsonRedisSerializer);
//        template.setHashValueSerializer(fastJsonRedisSerializer);
//        // key的序列化采用StringRedisSerializer
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }
//
//}