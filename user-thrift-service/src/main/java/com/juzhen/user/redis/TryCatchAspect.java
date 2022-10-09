package com.juzhen.user.redis;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.juzhen.common.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author： ye.lu
 * @Date： 2020/7/8 14:44
 * @Description： TODO
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
@Order(300)
public class TryCatchAspect {

//    @Pointcut("execution(* com.juzhen.admin.controller.*(*))")
//    public void RlockAspect() {
//    }
    @Pointcut("@annotation(TryCatch)")
    private void servicePointCut() {
        System.out.println("没有看到");
    }

    @Around("servicePointCut()")
    public Object doArround(ProceedingJoinPoint proceedingJoinPoint)  {
        long startTime = System.currentTimeMillis();

        Object target = proceedingJoinPoint.getTarget();
        Object[] args = proceedingJoinPoint.getArgs();
        Signature signature = proceedingJoinPoint.getSignature();
        String methonName = target.getClass().getName() + "," + signature.getName();

        Object object = null;
        try {
            log.info("服务调用开始 接口名称={},入参={}",methonName, JSON.toJSON(args));
            object = proceedingJoinPoint.proceed();
        } catch (UserNotFoundException runtimeException) {
//            throw new UserNotFoundException(runtimeException.getMessage());
            log.warn("服务调用异常"+runtimeException.getMessage());
        } catch (MybatisPlusException runtimeException) {
            log.warn("服务调用异常"+runtimeException.getMessage());
        } catch (Exception e) {
//            throw new UserNotFoundException(e.getMessage());
            log.warn("系统调用异常"+e.getMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long endTime = System.currentTimeMillis()-startTime;
        log.info("服务调用返回 接口名称={},结果={}，耗时={}",methonName, JSON.toJSON(object),endTime);
        return object;
    }


}