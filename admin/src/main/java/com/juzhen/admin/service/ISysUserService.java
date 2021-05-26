package com.juzhen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzhen.admin.entity.SysUser;

public interface ISysUserService extends IService<SysUser> {

    boolean queryUsernameIsExist(String username);
}