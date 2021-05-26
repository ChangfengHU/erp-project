package com.juzhen.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzhen.admin.entity.SysUser;
import com.juzhen.admin.mapper.SysUserMapper;
import com.juzhen.admin.service.ISysUserService;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public boolean queryUsernameIsExist(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
        wrapper.eq("username", "username");
        //根据条件查询一条数据，如果结果超过一条会报错
        SysUser result = this.baseMapper.selectOne(wrapper);

        return result != null ? true : false;
    }
}
