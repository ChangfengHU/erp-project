package com.juzhen.admin.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzhen.admin.entity.SysUser;
import com.juzhen.admin.mapper.SysUserMapper;
import com.juzhen.admin.redis.TryCatch;
import com.juzhen.admin.service.ISysUserService;
import com.juzhen.common.utils.MD5Utils;
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
public class SysUserServiceImpl  extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

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
        Assert.isTrue(StringUtils.isEquals(md5Str, one.getPassword()),"用户名或密码错误");
        Assert.isTrue(one.getStatus() == 1,"该用户禁止登陆");
    }
}
