package com.juzhen.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 用户与角色对应关系
    * </p>
*
* @author huchangfeng
* @since 2021-07-21
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 用户ID
            */
    private Long userId;

            /**
            * 角色ID
            */
    private Long roleId;


}
