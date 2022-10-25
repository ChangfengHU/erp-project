package com.juzhen.user.controller.admin;


import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.juzhen.common.result.ErpResult;
import com.juzhen.user.controller.tob.BaseController;
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
@RequestMapping("admin/user")
@Slf4j
public class SuperUserController extends BaseController<ISysUserService,SysUser> {
    @Autowired
    ISysUserService service;
    @GetMapping(value = "/{id}")
    @TryCatch
    public ErpResult get(@PathVariable("id") Integer id) {
        System.out.println("get");
        return super.userGet(id, service);
    }
    @PostMapping
    @TryCatch
    @ResponseBody
    public ErpResult post(@RequestBody SysUser dto) {
        System.out.println("post");
        return super.post(dto, service);
    }
}
