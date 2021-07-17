package com.juzhen.admin.controller;


import com.alibaba.dubbo.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.juzhen.admin.aop.TryCatch;
import com.juzhen.admin.entity.SysUser;
import com.juzhen.admin.redis.RedisClient;
import com.juzhen.admin.service.ISysTestService;
import com.juzhen.admin.service.ISysUserService;
import com.juzhen.common.result.ErpResult;
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
@RequestMapping("hy-admin/sys")
@Slf4j
public class SysUserController {

    @Autowired
    ISysUserService sysUserService;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    ISysTestService iSysTestService;


    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ErpResult login(SysUser user) throws Exception {
        iSysTestService.login(user);
        //校验
        verifyParam(user);
        //登录
        SysUser one =sysUserService.login(user);
        //生成token
        HashMap<String, Object> stringStringHashMap = buildToken(one);
        return ErpResult.ok(stringStringHashMap);
    }
    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    public ErpResult logout(SysUser user)  {
        //校验
        verifyParam(user);
        //登录
        SysUser one =sysUserService.login(user);
        //生成token
        HashMap<String, Object> stringStringHashMap = buildToken(one);
        return ErpResult.ok(stringStringHashMap);
    }








    private HashMap<String, Object> buildToken(SysUser one) {
        String token = genToken();
        //3. 缓存用户
        redisClient.set(token, one, 3600);
        HashMap<String, Object> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("token", token);
        stringStringHashMap.put("expire", 43200);
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
