package com.juzhen.user.redis;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.juzhen.user.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 2017/10/31.
 */
@Slf4j
@Component
public abstract class LoginFilter implements Filter {
//    @Autowired
//    private RedisUtils redisUtils;

    private static Cache<String, SysUser> cache =
            CacheBuilder.newBuilder().maximumSize(10000)
            .expireAfterWrite(3, TimeUnit.MINUTES).build();
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("登录拦截------init");
    }
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String requestURI = request.getRequestURI();
        log.info("登录拦截------doFilter"+requestURI);
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String token = getString(request);
        log.info("登录拦截------token"+token);
        SysUser userDTO = new SysUser();
        if(StringUtils.isNotEmpty(token)) {
            userDTO = cache.getIfPresent(token);
            if(userDTO==null) {
                userDTO = requestUserInfo(token);
                if(userDTO!=null) {
                    cache.put(token, userDTO);
                }
            }
        }

//        userDTO = (SysUser)redisTool.get(token);
        System.out.println("userDTO:"+userDTO);

//        if(!StringUtils.isEquals("/hy-admin/sys/login",requestURI) && userDTO==null) {
//            log.warn("未登录,登录拦截----");
//            System.out.println("url"+requestURI);
//            response.sendRedirect("http://www.mooc.com/user/login");
//            return;
//        }
//        login(request, response, userDTO);
        request.setAttribute("sysUser", userDTO);
        request.setAttribute("token", token);
        filterChain.doFilter(request, response);
    }

    private String getString(HttpServletRequest request) {
        String token = request.getParameter("token");
        log.info("登录拦截------token"+token);
        if(StringUtils.isEmpty(token)) {
            String header = request.getHeader("token");
            token = header;
//            Cookie[] cookies = request.getCookies();
//            if(cookies!=null) {
//                for(Cookie c : cookies) {
//                    if(c.getName().equals("token")) {
//                        token = c.getValue();
//                    }
//                }
//            }
        }
        return token;
    }

    private SysUser requestUserInfo(String token) {
//        SysUser o = (SysUser)redisUtils.get(token);
//        return o;
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        return sysUser;
    }

//    protected abstract String userEdgeServiceAddr();

    protected abstract void login(HttpServletRequest request, HttpServletResponse response, SysUser userDTO);



    public void destroy() {

    }

    private class StringUtil {
    }
}
