package com.juzhen.sale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juzhen.sale.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends MyBaseMapper<SysUser>, BaseMapper<SysUser> {
}

