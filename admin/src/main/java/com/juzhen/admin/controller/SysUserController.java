//
//package com.juzhen.admin.controller;
//
//
//import com.alibaba.dubbo.common.utils.StringUtils;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.juzhen.admin.entity.SysUser;
////import com.juzhen.admin.redis.RedisClient;
//import com.juzhen.admin.result.ErpResult;
//import com.juzhen.admin.service.ISysUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Random;
//
///**
// * <p>
// * 系统用户 前端控制器
// * </p>
// *
// * @author huchangfeng
// * @since 2021-05-26
// */
//@RestController
//@RequestMapping("hy-admin/sys")
//public class SysUserController {
//
//    @Autowired
//    ISysUserService sysUserService;
//
////    @Autowired
////    private RedisClient redisClient;
//
//    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
//    public ErpResult login(@RequestBody SysUser user) throws Exception {
//        System.out.println("快进来11" + user.getUsername() + "::" + user.getPassword());
//        if (StringUtils.isBlank(user.getUsername())
//                || StringUtils.isBlank(user.getPassword())) {
//            return ErpResult.errorMsg("账号或者密码为空");
//        }
//
//        boolean usernameIsExist = sysUserService.queryUsernameIsExist(user.getUsername());
//        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
//        wrapper.eq("username", "username");
//
//        SysUser one = sysUserService.getOne(wrapper, true);
//
//        if (one == null) {
//            ErpResult.errorMsg("用户不存在");
//        }
//        if (!StringUtils.isEquals(user.getPassword(), one.getPassword())) {
//            ErpResult.errorMsg("用户名或密码错误");
//        }
//        if (one.getStatus() != 1) {
//            ErpResult.errorMsg("该用户禁止登陆");
//        }
//        String token = genToken();
//
////        3. 缓存用户
////        redisClient.set(token, one, 3600);
//        HashMap<String, Object> stringStringHashMap = new HashMap<>();
//        stringStringHashMap.put("token", token);
//        stringStringHashMap.put("expire", 43200);
//        return ErpResult.ok(stringStringHashMap);
//    }
//
//    private String genToken() {
//        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz", 32);
//    }
//
//    private String randomCode(String s, int size) {
//        StringBuilder result = new StringBuilder(size);
//
//        Random random = new Random();
//        for (int i = 0; i < size; i++) {
//            int loc = random.nextInt(s.length());
//            result.append(s.charAt(loc));
//        }
//        return result.toString();
//    }
//}
