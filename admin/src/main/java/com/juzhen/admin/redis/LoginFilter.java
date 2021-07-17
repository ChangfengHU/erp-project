package com.juzhen.admin.redis;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.juzhen.admin.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
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

    private static Cache<String, SysUser> cache =
            CacheBuilder.newBuilder().maximumSize(10000)
            .expireAfterWrite(3, TimeUnit.MINUTES).build();


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("登录拦截------");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String token = request.getParameter("token");
        if(StringUtils.isBlank(token)) {
            Cookie[] cookies = request.getCookies();
            if(cookies!=null) {
                for(Cookie c : cookies) {
                    if(c.getName().equals("token")) {
                        token = c.getValue();
                    }
                }
            }
        }

        SysUser userDTO = null;
        if(StringUtils.isNotEmpty(token)) {
            userDTO = cache.getIfPresent(token);
            if(userDTO==null) {
//                userDTO = requestUserInfo(token);
                if(userDTO!=null) {
                    cache.put(token, userDTO);
                }
            }
        }

        if(userDTO==null) {
            response.sendRedirect("http://www.mooc.com/user/login");
            return;
        }

//        login(request, response, userDTO);

        filterChain.doFilter(request, response);
    }

    protected abstract String userEdgeServiceAddr();

    protected abstract void login(HttpServletRequest request, HttpServletResponse response, SysUser userDTO);



    public void destroy() {

    }
}
