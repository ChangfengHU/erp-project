package com.juzhen.sale.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzhen.api.sale.user.UserRpcDTO;
import com.juzhen.api.sale.user.UserRpcService;
import com.juzhen.common.utils.MD5Utils;
import com.juzhen.sale.entity.SysUser;
import com.juzhen.sale.mapper.SysUserMapper;
import com.juzhen.sale.redis.RedisClient;
import com.juzhen.sale.redis.TryCatch;
import com.juzhen.sale.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author huchangfeng
 * @since 2021-05-26
 */
@Service("sysUserService")
@Slf4j
public class SysUserServiceImpl  extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService, UserRpcService.Iface{
    @Autowired
    private RedisClient redisClient;
    @Override
    public boolean queryUsernameIsExist(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
        wrapper.eq("username", "username");
        //根据条件查询一条数据，如果结果超过一条会报错
        SysUser result = this.baseMapper.selectOne(wrapper);
        return result != null ? true : false;
    }
    @Override
    @TryCatch
    public SysUser login(SysUser user)  {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
        wrapper.eq("username", user.getUsername());
        SysUser one = getOne(wrapper, true);
        handleAfterLogin(user, one);
        return one;
    }

    private void handleAfterLogin(SysUser user, SysUser one)  {
        Assert.notNull(one,"用户不存在");
        String md5Str = null;
        try {
            md5Str = MD5Utils.getMD5Str(user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.isTrue(md5Str.equals(one.getPassword()),"用户名或密码错误");
        Assert.isTrue(one.getStatus() == 1,"该用户禁止登陆");
    }



    @Override
    public UserRpcDTO getUserById(long id) throws TException {
        return null;
    }

    @Override
    public UserRpcDTO getUserByName(String username) throws TException {
        log.info("接收到请求={}",username);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
        wrapper.eq("username", username);
        //根据条件查询一条数据，如果结果超过一条会报错
        SysUser sysUser = this.baseMapper.selectOne(wrapper);
        log.info("查询={}", JSON.toJSONString(sysUser));
        redisClient.putCache(sysUser.getId()+"",sysUser,3600);
        UserRpcDTO userInfo = new UserRpcDTO();
        BeanUtils.copyProperties(sysUser,userInfo);
        log.info("返回={}", JSON.toJSONString(userInfo));
        return userInfo;
    }

    @Override
    public void insertUser(UserRpcDTO userInfo) throws TException {

    }

    @Override
    public void updateUser(UserRpcDTO userInfo) throws TException {

    }

    @Override
    public void deleteUser(long id) throws TException {

    }

}
