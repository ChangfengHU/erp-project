package com.juzhen.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.juzhen.admin.entity.*;
import com.juzhen.admin.mapper.SysRoleMapper;
import com.juzhen.admin.service.ISysMenuService;
import com.juzhen.admin.service.ISysRoleMenuService;
import com.juzhen.admin.service.ISysRoleService;
import com.juzhen.admin.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    ISysMenuService sysMenuService;
    @Autowired
    ISysRoleMenuService sysRoleMenuService;
    @Autowired
    ISysUserRoleService sysUserRoleService;

    @Override
    public boolean toSave(Long userId, String roleName, String remark, List<Long> menuIdList, Long roleId) {

        List<Long> memuIds = new ArrayList<>();
        if (userId == 1) {
            List<SysMenu> list = sysMenuService.list();
            memuIds = Optional.ofNullable(list).orElse(Lists.newArrayList()).stream().map(SysMenu::getId).collect(Collectors.toList());
        } else {
            QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
            QueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper = new QueryWrapper<>();
            sysUserRoleQueryWrapper.eq("user_id", userId);
            List<SysUserRole> list2 = sysUserRoleService.list(sysUserRoleQueryWrapper);
            List<Long> roleIds = Optional.ofNullable(list2).orElse(Lists.newArrayList()).stream().map(sysUserRole -> sysUserRole.getRoleId()).collect(Collectors.toList());
            sysRoleMenuQueryWrapper.in("role_id", roleIds);
            List<SysRoleMenu> list1 = sysRoleMenuService.list(sysRoleMenuQueryWrapper);
            memuIds = Optional.ofNullable(list1).orElse(Lists.newArrayList()).stream().map(sysRoleMenu -> sysRoleMenu.getMenuId()).distinct().collect(Collectors.toList());

        }
        for (Long aLong : menuIdList) {
            memuIds.remove(aLong);
        }

        if (roleId == null || roleId <= 0) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleName(roleName);
            sysRole.setCreateUserId(userId);
            sysRole.setCreateTime(new Date());
            sysRole.setRoleName(roleName);
            boolean save = this.save(sysRole);

            for (Long memuId : memuIds) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(memuId);
                sysRoleMenu.setRoleId(sysRole.getRoleId());
                sysRoleMenuService.save(sysRoleMenu);

            }
        } else {
            SysRole sysRole = new SysRole();
            sysRole.setRoleName(roleName);
            sysRole.setCreateUserId(userId);
            sysRole.setCreateTime(new Date());
            sysRole.setRoleName(roleName);
            sysRole.setRoleId(roleId);
            this.updateById(sysRole);
            QueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper = new QueryWrapper<>();
            sysRoleMenuQueryWrapper.eq("role_id", roleId);
            sysRoleMenuService.remove(sysRoleMenuQueryWrapper);
            for (Long memuId : memuIds) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(memuId);
                sysRoleMenu.setRoleId(sysRole.getRoleId());
                sysRoleMenuService.save(sysRoleMenu);
            }
        }

        return true;
    }

    @Override
    public SysRole info(Long id) {
        SysRole sysRole = this.baseMapper.selectById(id);
        QueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper = new QueryWrapper<>();
        sysRoleMenuQueryWrapper.eq("role_id", id);
        List<SysRoleMenu> list = sysRoleMenuService.list(sysRoleMenuQueryWrapper);
        List<Long> menuIdList = Optional.ofNullable(list).orElse(Lists.newArrayList()).stream().map(SysRoleMenu::getMenuId).distinct().collect(Collectors.toList());
        sysRole.setMenuIdList(menuIdList);
        return sysRole;
    }

    @Override
    public void delete(List<Long> roleIds, SysUser sysUser) {
        if (sysUser.getId() == 1) {
            delete(roleIds, roleIds);

        } else {
            QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
            sysRoleQueryWrapper.eq("create_user_id", sysUser.getId());
            sysRoleQueryWrapper.in("role_id", roleIds);
            List<SysRole> sysRoles = this.baseMapper.selectList(sysRoleQueryWrapper);
            List<Long> roleIdList = Optional.ofNullable(sysRoles).orElse(Lists.newArrayList()).stream().map(SysRole::getRoleId).collect(Collectors.toList());
            delete(roleIds, roleIdList);
        }
    }

    private void delete(List<Long> roleIds, List<Long> roleIdList) {
        this.baseMapper.deleteBatchIds(roleIds);
        QueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper = new QueryWrapper<>();
        sysRoleMenuQueryWrapper.in("role_id", roleIdList);
        sysRoleMenuService.remove(sysRoleMenuQueryWrapper);
    }
}
