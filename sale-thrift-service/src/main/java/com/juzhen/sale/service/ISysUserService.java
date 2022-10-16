package com.juzhen.sale.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.juzhen.sale.entity.SysUser;

public interface ISysUserService extends IService<SysUser> {

    boolean queryUsernameIsExist(String username);
    SysUser login(SysUser user) ;
}