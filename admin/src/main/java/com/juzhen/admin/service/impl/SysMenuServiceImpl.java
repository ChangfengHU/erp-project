package com.juzhen.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzhen.admin.entity.SysMenu;
import com.juzhen.admin.mapper.SysMenuMapper;
import com.juzhen.admin.service.ISysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author huchangfeng
 * @since 2021-05-19
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public List<SysMenu> menuListForAdmin() {
        return this.baseMapper.menuListForAdmin();
    }
}
