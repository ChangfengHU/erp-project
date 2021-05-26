//package com.juzhen.common.result;
//
//import lombok.Data;
//import lombok.ToString;
//
//import java.io.Serializable;
//
///**
// * @Author: mrt.
// * @Description:
// * @Date:Created in 2018/1/24 18:33.
// * @Modified By:
// */
//@Data
//@ToString
//public class HeditResult<T>  implements Serializable {
//    private static final long serialVersionUID = -4257035661825775910L;
//    protected  boolean success= true;
//    protected int code;
//    protected String msg;
//    //数据总数
//    private T  data;
//
//
//    public HeditResult(Object value) {
//        this.code = 200;
//        this.msg = "OK";
//        this.data = (T) value;
//    }
//
//
//
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public boolean isErrCode() {
//        return errCode;
//    }
//
//    public void setErrCode(boolean errCode) {
//        this.errCode = errCode;
//    }
//
//    public boolean isErrMessage() {
//        return errMessage;
//    }
//
//    public void setErrMessage(boolean errMessage) {
//        this.errMessage = errMessage;
//    }
//
//    public T getValue() {
//        return value;
//    }
//
//    public void setValue(T value) {
//        this.value = value;
//    }
//}