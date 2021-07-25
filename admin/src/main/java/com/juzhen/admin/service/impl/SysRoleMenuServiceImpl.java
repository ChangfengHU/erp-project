package com.juzhen.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzhen.admin.entity.SysRoleMenu;
import com.juzhen.admin.mapper.SysRoleMenuMapper;
import com.juzhen.admin.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author huchangfeng
 * @since 2021-07-21
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

}
