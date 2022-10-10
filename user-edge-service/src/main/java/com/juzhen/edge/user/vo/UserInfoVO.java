package com.juzhen.edge.user.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author huchangfeng
 * @since 2021-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private String salt;

    private String email;

    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 类型 1 管理员  2 工厂 3销售
     */
    private Integer kind;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */

    private Date createTime;

}
