package com.juzhen.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzhen.admin.entity.SysRole;
import com.juzhen.admin.mapper.SysRoleMapper;
import com.juzhen.admin.service.ISysRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author huchangfeng
 * @since 2021-07-20
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

}
