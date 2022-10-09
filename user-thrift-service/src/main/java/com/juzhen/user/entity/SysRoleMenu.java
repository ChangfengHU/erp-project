package com.juzhen.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 角色与菜单对应关系
    * </p>
*
* @author huchangfeng
* @since 2021-07-21
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 角色ID
            */
    private Long roleId;

            /**
            * 菜单ID
            */
    private Long menuId;


}
