package com.juzhen.user.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 2017/10/30.
 */
@Component
public class RedisClient {

    @Autowired
    private RedisTemplate redisTemplate;
    private static FastJsonConfig fastJsonConfig = new FastJsonConfig();
    private static StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    public <T> T get(String key) {
        return (T)redisTemplate.opsForValue().get(key);
    }
    public <T> T getCache(final String key,Class <T> targetClass )  {
        byte[] execute = (byte[])redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.get(stringRedisSerializer.serialize(key));
            }
        });

        return execute == null ? null : deserialize(execute,targetClass);
    }
    public  void deleteCache(final String key )  {
         redisTemplate.delete(key);
    }

    public void putCache (String key, Object value,final  long expireTime) {
        final byte[] bkey = stringRedisSerializer.serialize(key);
        final byte[] bvalue = serialize(value);
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.setEx(bkey, expireTime, bvalue);
                return true;
            }
        });
    }

    public  byte[] serialize(Object value)  {
        if (value == null) {
            return new byte[0];
        }
        try {
            return JSON.toJSONBytes(value, fastJsonConfig.getSerializeConfig(), fastJsonConfig.getSerializeFilters(), JSON.DEFAULT_GENERATE_FEATURE, fastJsonConfig.getSerializerFeatures());
        } catch (Exception e) {
            throw new SerializationException("serialize error", e);
        }
    }
    public  <T> T  deserialize(byte[] bytes,Class<T> type) throws SerializationException {
        if (bytes != null && bytes.length != 0) {
            try {
                return JSON.parseObject(bytes,type);
            } catch (Exception e) {
                throw new SerializationException("deserialize error", e);
            }
        }
        return null;
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, int timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public void expire(String key, int timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

}
