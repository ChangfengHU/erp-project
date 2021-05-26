package com.juzhen.common.result;

import com.juzhen.common.response.IMoocJSONResult;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 18:33.
 * @Modified By:
 */
@Data
@ToString
public class ErpResult<T>  implements Serializable {
    private static final long serialVersionUID = -4257035661825775910L;
    private  boolean success= true;
    private int code;
    private String msg;
    //数据总数
    private T  data;


    public ErpResult(Object value) {
        this.code = 200;
        this.msg = "OK";
        this.data = (T) value;
    }
    public ErpResult(int i, String msg, Object value) {
        this.code = 500;
        this.msg = msg;
        this.data = (T) value;
    }
    public static ErpResult errorMsg(String msg) {
        return new ErpResult(500, msg, null);
    }

    public static ErpResult ok(Object data) {
        return new ErpResult(data);
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}