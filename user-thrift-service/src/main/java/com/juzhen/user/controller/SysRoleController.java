package com.juzhen.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.juzhen.common.result.ErpResult;
import com.juzhen.user.entity.SysRole;
import com.juzhen.user.entity.SysUser;
import com.juzhen.user.service.ISysRoleMenuService;
import com.juzhen.user.service.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author huchangfeng
 * @since 2021-07-20
 */
@Controller
@RequestMapping("/hy-admin/sys/role")
@Slf4j
public class SysRoleController {


    @Autowired
    ISysRoleMenuService iSysRoleMenuService;

    @Autowired
    ISysRoleService sysRoleService;

    /**
     * 新增角色 （post）
     *
     * @param sysUser
     * @return
     */
    @ResponseBody
    @GetMapping("save")
    public ErpResult saveRole(@RequestAttribute SysUser sysUser,
                              @RequestParam("roleName") String roleName,
                              @RequestParam("remark") String remark,
                              @RequestParam("roleId") Long roleId,
                              @RequestParam("menuIdList") List<Long> menuIdList

    ) {
        log.info("保存角色 roleName={},remark={},menuIdList={}", roleName, remark, menuIdList);
//        checkParamForSave(sysMenu);
        boolean save = sysRoleService.toSave(sysUser.getId(), roleName, remark, menuIdList, roleId);
        return ErpResult.ok(save);
    }

    @ResponseBody
    @GetMapping("update")
    public ErpResult updateRole(@RequestAttribute SysUser sysUser,
                                @RequestParam("roleName") String roleName,
                                @RequestParam("remark") String remark,
                                @RequestParam("roleId") Long roleId,
                                @RequestParam("menuIdList") List<Long> menuIdList

    ) {
        log.info("保存角色 roleName={},remark={},menuIdList={}", roleName, remark, menuIdList);
//        checkParamForSave(sysMenu);
        boolean save = sysRoleService.toSave(sysUser.getId(), roleName, remark, menuIdList, roleId);
        return ErpResult.ok(save);
    }

    /**
     * 角色列表 （post）
     *
     * @param sysUser
     * @return
     */
    @ResponseBody
    @GetMapping("list")
    public ErpResult listRole(@RequestAttribute SysUser sysUser,
                              SysRole sysRole

    ) {
        log.info("角色列表 sysRole={}", sysRole);
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();

        if (sysRole.getRoleName() != null) {
            sysRoleQueryWrapper.like("role_name", sysRole.getRoleName());
        }

        Page<SysRole> sysRolePage = new Page<>(sysRole.getPage(), sysRole.getLimit());
        IPage<SysRole> page = sysRoleService.page(sysRolePage, sysRoleQueryWrapper);

        log.info("总页数 pages={}", page.getPages());
        log.info("总页数 tolte={}", page.getTotal());
        return ErpResult.ok(page);
    }
    @ResponseBody
    @GetMapping("info/{id}")
    public ErpResult info(@PathVariable Long id) {
        log.info("info id ={}",id);
        SysRole info = sysRoleService.info(id);
        log.info("info result ={}",info);
        return ErpResult.ok(info);
    }

    @ResponseBody
    @GetMapping("delete")
    public ErpResult info(List<Long> roleIds,@RequestAttribute SysUser sysUser) {
        log.info("info roleIds ={}",roleIds);
        sysRoleService.delete(roleIds,sysUser);
        return ErpResult.ok(true);
    }

}
