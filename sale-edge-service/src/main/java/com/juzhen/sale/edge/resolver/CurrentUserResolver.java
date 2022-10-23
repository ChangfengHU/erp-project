package com.juzhen.sale.edge.resolver;

import com.juzhen.user.client.dto.CurrentUser;
import com.juzhen.user.client.session.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 25131 on 2022-10-14.
 */
@Slf4j
@Component
public class CurrentUserResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        log.info("@@@@@@@@方法解析器---start---");
        return methodParameter.hasParameterAnnotation(Authlogin.class);
    }

    @Nullable
    @Override
    public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, @Nullable WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String token = httpServletRequest.getHeader("token");
        CurrentUser currentUser = SessionManager.userCache.getIfPresent(token);
        CurrentUser currentUser1 = SessionManager.currentUser();
        log.info("resolveArgument---currentUser1---{}",currentUser1);
//        if (currentUser == null) {
//            throw new Exception("未登录");
//        }
        return currentUser;
    }
}
