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
@RequestMapping("tob")
@Slf4j
public class SysUserController {


}
