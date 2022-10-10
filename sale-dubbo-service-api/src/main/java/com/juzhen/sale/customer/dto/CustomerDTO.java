package com.juzhen.sale.customer.dto;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by 25131 on 2022-10-11.
 */
public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;

    private String phone;

    private String projectIds;

    private Integer sellerId;

    private Integer shopId;

    private String source;

    private LocalDateTime weddingDay;

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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String projectInfo;

    /**
     * 0 全部  1 已进店 2 未预约 3 已预约
     */
    private Integer bookStatus;

    private LocalDateTime inshopTime;

}
