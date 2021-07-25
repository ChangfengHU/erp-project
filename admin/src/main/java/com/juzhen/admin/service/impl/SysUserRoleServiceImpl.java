package com.juzhen.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzhen.admin.entity.SysUserRole;
import com.juzhen.admin.mapper.SysUserRoleMapper;
import com.juzhen.admin.service.ISysUserRoleService;
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
