package com.juzhen.user.controller.tob;


import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.juzhen.common.result.ErpResult;
import com.juzhen.user.entity.SysUser;
import com.juzhen.user.mapper.SysUserMapper;
import com.juzhen.user.redis.RedisClient;
import com.juzhen.user.redis.TryCatch;
import com.juzhen.user.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Random;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author huchangfeng
 * @since 2021-05-26
 */
@RestController
@RequestMapping("login")
@Slf4j
public class LoginController {

    @Autowired
    ISysUserService sysUserService;

    @Autowired
    private RedisClient redisClient;


    @Autowired
    SysUserMapper sysUserMapper;



    /*
     * restful post /demo/handle
     */
    @RequestMapping(value = "/user", method = {RequestMethod.POST})
    public ErpResult userPost(SysUser sysUser) {
        sysUserService.save(sysUser);
        return ErpResult.ok(true);
    }

    @TryCatch
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ErpResult login(SysUser user) throws Exception {
        log.info("登录开始={}", user);
        //校验
        verifyParam(user);
        //登录
        SysUser one =sysUserService.login(user);

        //生成token
        HashMap<String, Object> stringStringHashMap = buildToken(one);
        return ErpResult.ok(stringStringHashMap);
    }
    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    public ErpResult logout(@RequestAttribute String token)  {
        redisClient.deleteCache(token);
        return ErpResult.ok(true);
    }

    private HashMap<String, Object> buildToken(SysUser one) {
        String token = genToken();
        //3. 缓存用户
        redisClient.putCache(token, one, 3600);
        HashMap<String, Object> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("token", token);
        stringStringHashMap.put("expire", 3600);
        return stringStringHashMap;
    }



    private void verifyParam(SysUser user) {
        Assert.isTrue(StringUtils.isNotEmpty(user.getUsername())
                && StringUtils.isNotEmpty(user.getPassword()), "账号或者密码为空");
    }


    private String genToken() {
        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz", 32);
    }

    private String randomCode(String s, int size) {
        StringBuilder result = new StringBuilder(size);

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }
        return result.toString();
    }




}
