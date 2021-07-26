package com.juzhen.admin.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzhen.admin.entity.SysMenu;
import com.juzhen.admin.entity.SysRoleMenu;
import com.juzhen.admin.entity.SysUserRole;
import com.juzhen.admin.mapper.SysMenuMapper;
import com.juzhen.admin.service.ISysMenuService;
import com.juzhen.admin.service.ISysRoleMenuService;
import com.juzhen.admin.service.ISysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author huchangfeng
 * @since 2021-05-19
 */
@Service("sysMenuService")
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    ISysRoleMenuService iSysRoleMenuService;
    @Autowired
    ISysUserRoleService iSysUserRoleService;


    @Override
    public List<SysMenu> menuListForAdmin() {
        return this.baseMapper.menuListForAdmin(null);
    }

    @Override
    public List<SysMenu> menuListForUser(Long userId) {
        List<Long> menuIdList = getMenuIdListHasAuth(userId);
        List<SysMenu> sysMenuList = getSysMenusReuslt(menuIdList);
        return sysMenuList;
    }



    @Override
    public List<SysMenu> menuNavListForAdmin() {
        List<SysMenu> sysMenuList = getSysMenusReuslt(null);
        return sysMenuList;
    }


    @Override
    public List<SysMenu> menuNavListForUser(Long userId) {
        List<Long> menuIdList = getMenuIdListHasAuth(userId);
        List<SysMenu> sysMenuList = getSysMenusReuslt(menuIdList);
        return sysMenuList;
    }




    @Override
    public Set<String> menuPermissionsForAdmin() {
        List<SysMenu> sysMenuList = this.baseMapper.selectList(null);
        Set<String> result = getPermissionsResult(sysMenuList);
        return result;
    }

    @Override
    public Set<String> menuPermissionsForUser(Long userId) {
        List<Long> menuIdList = getMenuIdListHasAuth(userId);
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.in("menu_id", menuIdList);
        List<SysMenu> sysMenuList = this.baseMapper.selectList(sysMenuQueryWrapper);
        Set<String> result = getPermissionsResult(sysMenuList);
        return result;
    }




    private Set<String> getPermissionsResult(List<SysMenu> sysMenuList) {
        List<String> collect = sysMenuList.stream().filter(sysMenu -> StringUtils.hasText(sysMenu.getPerms()))
                .map(sysMenu -> sysMenu.getPerms()).collect(Collectors.toList());
        Set<String> result = new HashSet<>();
        for (String s : collect) {
            result.addAll(Arrays.asList(s.split(",")));
        }
        result.add("sys:menu:nav");
        result.add("sys:menu:info");
        result.add("sys:menu:password");
        return result;
    }

    private List<SysMenu> getSysMenusFirst(List<Long> menuIdList) {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        if (CollectionUtils.isNotEmpty(menuIdList)) {
            sysMenuQueryWrapper.in("menu_id", menuIdList);
        }
        sysMenuQueryWrapper.eq("parent_id", 0L);
//        sysMenuQueryWrapper.eq("type", 0);
        return this.baseMapper.selectList(sysMenuQueryWrapper);
    }



    private List<SysMenu> getSysMenusReuslt(List<Long> o) {
        List<SysMenu> sysMenuList = getSysMenusFirst(o);
        log.info("sysMenuList={}" + sysMenuList);
        for (SysMenu menu : sysMenuList) {
            QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
            sysMenuQueryWrapper.eq("parent_id", menu.getId());
            sysMenuQueryWrapper.orderByAsc("order_num");
            List<SysMenu> selectList = this.baseMapper.selectList(sysMenuQueryWrapper);
            menu.setChildList(selectList);
        }
        return sysMenuList;
    }
    @Override
    public List<SysMenu> select() {
        List<SysMenu> sysMenuList = getSysMenusFirst(null);
        log.info("sysMenuList={}" , sysMenuList);
        List<SysMenu> sysMenus = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
            sysMenuQueryWrapper.eq("parent_id", sysMenu.getId());
            sysMenuQueryWrapper.orderByAsc("order_num");
            List<SysMenu> selectList = this.baseMapper.selectList(sysMenuQueryWrapper);
            sysMenus.addAll(selectList);
        }
        List<Long> collect = sysMenuList.stream().map(sysMenu -> sysMenu.getId()).collect(Collectors.toList());
        List<Long> menuIds = sysMenus.stream().map(sysMenu -> sysMenu.getId()).distinct().collect(Collectors.toList());
        menuIds.addAll(collect);
        log.info("menuIds={}", menuIds);
        List<SysMenu> sysMenuList1 = this.baseMapper.menuListForAdmin(menuIds);
        log.info("menuIds={}", sysMenuList1);
        SysMenu sysMenu = new SysMenu();
        sysMenu.setParentId(-1L);
        sysMenu.setName("一级菜单");
        sysMenu.setOpen(true);
        sysMenuList1.add(sysMenu);
        return sysMenuList1;
    }

    /**
     * 获取 用户下有权限的所有的 菜单Id
     * @param userId   $2y$12$U1lRLlwtplsJyOxrAXoBY.uHFdNifxdO8yajq524i42StZWAsrrbS
     * @return
     */
    private List<Long> getMenuIdListHasAuth(Long userId) {
        QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
        sysUserRoleQueryWrapper.eq("user_id", userId);
        List<SysUserRole> list = iSysUserRoleService.list(sysUserRoleQueryWrapper);
        List<Long> roleIdList = Optional.ofNullable(list).orElse(new ArrayList<>()).stream().map(sysUserRole -> sysUserRole.getRoleId()
        ).collect(Collectors.toList());
        QueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper = new QueryWrapper<>();
        sysRoleMenuQueryWrapper.in("role_id", roleIdList);
        List<SysRoleMenu> list1 = iSysRoleMenuService.list(sysRoleMenuQueryWrapper);
        List<Long> menuIdList = Optional.ofNullable(list1).orElse(new ArrayList<>()).stream().filter(sysRoleMenu -> sysRoleMenu.getMenuId() != null).
                map(sysRoleMenu -> sysRoleMenu.getMenuId()).distinct().collect(Collectors.toList());
        return menuIdList;
    }

}
