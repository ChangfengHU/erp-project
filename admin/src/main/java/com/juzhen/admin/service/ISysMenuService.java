package com.juzhen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzhen.admin.entity.SysMenu;

import java.util.List;

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
}
