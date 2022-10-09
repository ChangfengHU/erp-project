package com.juzhen.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author huchangfeng
 * @since 2021-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父菜单ID，一级菜单为0
     */
//    @TableField(value = "parent_id")
    private Long parentId;

    private String name;

    private String url;

    private String perms;

    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    private Integer type;

    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 父菜单ID，一级菜单为0
     */
    @TableField(exist = false)
    private String parentName;


    /**
     * 子节点
     */
    @TableField(exist = false)
    private List<SysMenu> childList;


    /**
     * 子节点
     */
    @TableField(exist = false)
    private List<Long> menuIdList;
    /**
     * 子节点
     */
    @TableField(exist = false)
    private Boolean open;
}
