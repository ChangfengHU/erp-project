////
//
//// Source code recreated from a .class file by IntelliJ IDEA
//
//// (powered by FernFlower decompiler)
//
////
//
//
//package com.juzhen.admin.entity;
//
//
//
//import com.baomidou.mybatisplus.annotation.FieldStrategy;
//
//import com.baomidou.mybatisplus.annotation.TableField;
//
//import java.io.Serializable;
//
//import java.util.Date;
//
//
//
//
//public class BaseDO<ID extends Serializable> implements Serializable {
//
//    private static final long serialVersionUID = 5447814577338342112L;
//
//    private ID id;
//
//    private String createBy;
//
////    @TableField(
////
////        value = "create_time",
////
////        updateStrategy = FieldStrategy.DEFAULT
////
////    )
//
//    private Date createTime;
//
//    private String modifyBy;
//
//    @TableField(
//
//            value = "modify_time",
//
//            update = "now(3)"
//
//    )
//
//    private Date modifyTime;
//
//    private String extendField;
//
//
////
////    public String toString() {
////
//////        return ToStringBuilder.reflectionToString(this);
////
////    }
//
//
//    public BaseDO() {
//
//    }
//
//
//    public ID getId() {
//
//        return this.id;
//
//    }
//
//
//    public String getCreateBy() {
//
//        return this.createBy;
//
//    }
//
//
//    public Date getCreateTime() {
//
//        return this.createTime;
//
//    }
//
//
//    public String getModifyBy() {
//
//        return this.modifyBy;
//
//    }
//
//
//    public Date getModifyTime() {
//
//        return this.modifyTime;
//
//    }
//
//
//    public String getExtendField() {
//
//        return this.extendField;
//
//    }
//
//
//    public void setId(ID id) {
//
//        this.id = id;
//
//    }
//
//
//    public void setCreateBy(String createBy) {
//
//        this.createBy = createBy;
//
//    }
//
//
//    public void setCreateTime(Date createTime) {
//
//        this.createTime = createTime;
//
//    }
//
//
//    public void setModifyBy(String modifyBy) {
//
//        this.modifyBy = modifyBy;
//
//    }
//
//
//    public void setModifyTime(Date modifyTime) {
//
//        this.modifyTime = modifyTime;
//
//    }
//
//
//    public void setExtendField(String extendField) {
//
//        this.extendField = extendField;
//
//    }
//
//}
//
//
