package com.juzhen.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzhen.user.entity.SysUserRole;
import com.juzhen.user.mapper.SysUserRoleMapper;
import com.juzhen.user.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author huchangfeng
 * @since 2021-07-21
 */
@Service("sysUserRoleService")

public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

}
