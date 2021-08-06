package com.juzhen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzhen.admin.entity.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author huchangfeng
 * @since 2021-05-19
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<SysMenu> menuListForAdmin();
    List<SysMenu> menuListForUser(Long userId);



    List<SysMenu> menuNavListForAdmin();
    List<SysMenu> menuNavListForUser(Long userId);

    Set<String> menuPermissionsForAdmin();
    Set<String> menuPermissionsForUser(Long userId);

    List<SysMenu> select();

    Boolean delete(Long id);
}
