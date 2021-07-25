package com.juzhen.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
    * 角色
    * </p>
*
* @author huchangfeng
* @since 2021-07-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    private String roleName;

    private String remark;

            /**
            * 创建者ID
            */
    private Long createUserId;

            /**
            * 创建时间
            */
    private LocalDateTime createTime;


}
