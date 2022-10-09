package com.juzhen.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzhen.user.entity.SysRole;
import com.juzhen.user.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author huchangfeng
 * @since 2021-07-20
 */
public interface ISysRoleService extends IService<SysRole> {

    boolean toSave(Long id, String roleName, String remark, List<Long> menuIdList, Long roleId);

    SysRole info(Long id);

    void delete(List<Long> roleIds, SysUser sysUser);
}
