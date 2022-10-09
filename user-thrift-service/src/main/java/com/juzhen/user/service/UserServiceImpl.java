package com.juzhen.user.service;

import com.juzhen.user.api.UserInfo;
import com.juzhen.user.api.UserService;
import com.juzhen.user.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 25131 on 2019/11/2.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService.Iface {

    @Autowired
    private RedisClient redisClient;

    @Override
    public UserInfo getUserById(int id) throws TException {
        return null;
    }

    @Override
    public UserInfo getTeacherById(int id) throws TException {
        return null;
    }

    @Override
    public UserInfo getUserByName(String username) throws TException {
        log.info("请求收到={}",username);
        Integer hcf = (Integer)redisClient.get("hcf");
        log.info("redisClient={}",hcf);
        return new UserInfo();
    }

    @Override
    public void regiserUser(UserInfo userInfo) throws TException {

    }
}
