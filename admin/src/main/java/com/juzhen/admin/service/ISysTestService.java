package com.juzhen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzhen.admin.entity.SysUser;

public interface ISysTestService {

    boolean queryUsernameIsExist(String username);
    public  SysUser login(SysUser user) ;
}