package com.juzhen.common.exception;

public class ErpBizException extends Exception {
    public ErpBizException() {
    }

    public ErpBizException(String message) {
        super(message);
    }

    public ErpBizException(String message, Throwable cause) {
        super(message, cause);
    }
}

