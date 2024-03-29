package com.juzhen.common.exception;

import com.alibaba.fastjson.JSON;
import com.juzhen.common.result.ErpResult;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 返回异常的处理
 */
public class ExceptionUtil {


    private ExceptionUtil() {
    }

    /**
     * 获取异常信息
     *
     * @param e 异常
     */
    public static String getErrorMessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
    /**
     * 异常信息-->json
     *
     */
    public static String resultOf(ResultStatusCode resultStatusCode) {
        return JSON.toJSONString(resultStatusCode);
    }  /**
     * 异常信息-->json
     *
     */
    public static ErpResult result(String msg) {
        return ErpResult.errorMsg(msg);
    }

}

