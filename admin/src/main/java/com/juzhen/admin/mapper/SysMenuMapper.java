package com.juzhen.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juzhen.admin.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author huchangfeng
 * @since 2021-05-19
 */

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> menuListForAdmin(@Param("menuIds") List<Long> menuIds);
}
