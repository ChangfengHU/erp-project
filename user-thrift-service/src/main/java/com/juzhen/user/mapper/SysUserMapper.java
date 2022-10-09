package com.juzhen.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juzhen.user.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends MyBaseMapper<SysUser>, BaseMapper<SysUser> {
}

