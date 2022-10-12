package com.juzhen.api.sale.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 25131 on 2022-10-11.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class CustomerRpcDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;

    private String phone;

    private String projectIds;

    private Integer sellerId;

    private Integer shopId;

    private String source;

    private Date weddingDay;

    /**
     * 礼服师
     */
    private String dresser;

    /**
     * 预算
     */
    private BigDecimal budget;

    /**
     * 婚纱推荐
     */
    private String dress;

    /**
     * 西服推荐
     */
    private String suit;

    /**
     * 珠宝推荐
     */
    private String jewel;

    /**
     * 四大推荐
     */
    private String sida;

    private String remark;

    private Integer createUserId;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private String projectInfo;

    /**
     * 0 全部  1 已进店 2 未预约 3 已预约
     */
    private Integer bookStatus;

    private Date inshopTime;

}
