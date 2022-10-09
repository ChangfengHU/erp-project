package com.juzhen.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juzhen.user.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色与菜单对应关系 Mapper 接口
 * </p>
 *
 * @author huchangfeng
 * @since 2021-07-21
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

}
