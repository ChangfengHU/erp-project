package com.juzhen.admin.controller;


import com.juzhen.admin.entity.SysMenu;
import com.juzhen.admin.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author huchangfeng
 * @since 2021-05-19
 */
@Controller
@RequestMapping("hy-admin/sys/menu")
public class SysMenuController {

    @Autowired
    ISysMenuService sysMenuService;

    @ResponseBody
    @GetMapping("list")
    public List<SysMenu> menuListForAdmin() {
        List<SysMenu> byId = sysMenuService.menuListForAdmin();
        System.out.println(byId);
        return byId;
    }

}
