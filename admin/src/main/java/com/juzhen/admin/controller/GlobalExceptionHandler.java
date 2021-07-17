package com.juzhen.admin.controller;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.juzhen.common.exception.ExceptionUtil;
import com.juzhen.common.exception.ResultStatusCode;
import com.juzhen.common.exception.UserNotFoundException;
import com.juzhen.common.result.ErpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.zip.DataFormatException;

/**
 * 全局异常捕获处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
 
    /**
     * 请求参数不全
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public String missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        logger.error("请求参数不全:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.MissingServletRequestParameter);
    }

    /**
     * http请求的方法不正确public class MybatisPlusException extends RuntimeException {
     */
    @ExceptionHandler(MybatisPlusException.class)
    @ResponseBody
    public ErpResult MybatisPlusExceptionHandler(MybatisPlusException e) {
        logger.error("业务异常:【"+e.getMessage()+"】");
        return ErpResult.errorMsg(e.getMessage());
    }

    /**
     * 请求参数类型不正确
     */
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    public String typeMismatchExceptionHandler(TypeMismatchException e) {
        logger.error("请求参数类型不正确:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.TypeMismatchException);
    }
 
    /**
     * 数据格式不正确
     */
    @ExceptionHandler(DataFormatException.class)
    @ResponseBody
    public String dataFormatExceptionHandler(DataFormatException e) {
        logger.error("数据格式不正确:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.DataFormatException);
    }
 
    /**
     * 用户没找到
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public String userNotFoundExceptionHandler(UserNotFoundException e) {
        logger.error("用户没找到:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.UserNotExist);
    }
 
    /**
     * 非法输入
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public String illegalArgumentExceptionHandler(IllegalArgumentException e) {
        logger.error("非法输入:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.IllegalArgumentException);
    }
 
 
    @ExceptionHandler  //处理其他异常
    @ResponseBody
    public ErpResult allExceptionHandler(Exception e){
        logger.error("具体错误信息:【"+ExceptionUtil.getErrorMessage(e)+"】"); //会记录出错的代码行等具体信息
        int count = 0; //只打印15行的错误堆栈
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            logger.error(stackTraceElement.toString());
            if(count++ > 13) break;
        }
        return ExceptionUtil.result(e.getMessage());
    }
 
}

