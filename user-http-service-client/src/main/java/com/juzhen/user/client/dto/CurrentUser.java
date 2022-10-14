//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.juzhen.user.client.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
@Data
public class CurrentUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String realName;

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
