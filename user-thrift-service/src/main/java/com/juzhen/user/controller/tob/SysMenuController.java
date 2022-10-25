package com.juzhen.user.controller.tob;

import com.baomidou.mybatisplus.core.toolkit.Assert;

import com.juzhen.common.constant.MyConstants;
import com.juzhen.common.result.ErpResult;
import com.juzhen.user.entity.SysMenu;
import com.juzhen.user.entity.SysUser;
import com.juzhen.user.redis.TryCatch;
import com.juzhen.user.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
@Slf4j
public class SysMenuController   {

    @Autowired
    ISysMenuService sysMenuService;

    /**
     *  导航列表（GET）
     * @param sysUser
     * @return
     */
    @ResponseBody
    @GetMapping("nav")
    @TryCatch
    public ErpResult nav( SysUser sysUser) {
        sysUser = new SysUser();
        sysUser.setId(1L);
        log.info("获取登录信息sysUser" +sysUser);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        List<SysMenu> sysMenuList = new ArrayList<>();
        Set<String> permissions = new HashSet<>();
        if (MyConstants.ADMIN_ID == sysUser.getId()){
            sysMenuList = sysMenuService.menuNavListForAdmin();
            permissions = sysMenuService.menuPermissionsForAdmin();
        }else {
            sysMenuList = sysMenuService.menuNavListForUser(sysUser.getId());
            permissions = sysMenuService.menuPermissionsForUser(sysUser.getId());
        }

        stringObjectHashMap.put("menuList", sysMenuList);
        stringObjectHashMap.put("permissions", permissions);
        return ErpResult.ok(stringObjectHashMap);
    }

  /**
     *  菜单列表（GET）
     * @param sysUser
     * @return
     */
    @ResponseBody
    @GetMapping("list")
    public ErpResult menuListForAdmin(@RequestAttribute SysUser sysUser) {
        log.info("获取登录信息sysUser" +sysUser);
        List<SysMenu> byId = new ArrayList<>();
        if (MyConstants.ADMIN_ID == sysUser.getId()){
            byId = sysMenuService.menuListForAdmin();
        }else {
            byId = sysMenuService.menuListForUser(sysUser.getId());
        }
        return ErpResult.ok(byId);
    }
 /**
     *  菜单列表（post）
     * @param sysUser
     * @return
     */

    @ResponseBody
    @GetMapping("save")
    public ErpResult saveMenu(@RequestAttribute SysUser sysUser,@RequestBody SysMenu sysMenu) {
        log.info("获取登录信息sysUser" +sysMenu);
        checkParamForSave(sysMenu);
        boolean save = sysMenuService.save(sysMenu);
        return ErpResult.ok(save);
    }
    /**
     *  菜单列表（post）
     * @param sysUser
     * @return
     */

    @ResponseBody
    @GetMapping("update")
    public ErpResult updateMenu(@RequestAttribute SysUser sysUser,@RequestBody SysMenu sysMenu) {
        log.info("获取登录信息sysUser" +sysMenu);
        checkParamForSave(sysMenu);
        boolean save = sysMenuService.updateById(sysMenu);
        return ErpResult.ok(save);
    }

    /**
     *  菜单列表（post）
     * @param id
     * @return
     */

    @ResponseBody
    @GetMapping("info/{id}")
    public ErpResult info(@PathVariable Long id) {
        log.info("info id ={}",id);
        SysMenu byId = sysMenuService.getById(id);
        log.info("info result ={}",byId);
        return ErpResult.ok(byId);
    }
   /**
     *  删除惨淡（post）
     * @param id
     * @return
     */

    @ResponseBody
    @GetMapping("delete/{id}")
    public ErpResult delete(@PathVariable Long id) {
        log.info("info id ={}",id);
        Boolean result = sysMenuService.delete(id);
        log.info("info result ={}",result);
        return ErpResult.ok(result);
    }

    /**
     *  菜单列表（post）
     * @return
     */

    @ResponseBody
    @GetMapping("select")
    public ErpResult select() {
        List<SysMenu> select = sysMenuService.select();
        log.info("info result ={}",select);
        return ErpResult.ok(select);
    }

    private void checkParamForSave(SysMenu sysMenu) {
        Assert.notNull(sysMenu,"菜单为空");
        Assert.notNull(sysMenu.getId(),"id is null");
        Assert.notNull(sysMenu.getParentId(),"parent is null");
        Assert.notNull(sysMenu.getName(),"name is null");
        Assert.notNull(sysMenu.getUrl(),"url is null");
        Assert.notNull(sysMenu.getPerms(),"perms is null");
        Assert.notNull(sysMenu.getType(),"type is null");
        Assert.notNull(sysMenu.getIcon(),"icon is null");
        Assert.notNull(sysMenu.getOrderNum(),"orderNum is null");
    }




}
