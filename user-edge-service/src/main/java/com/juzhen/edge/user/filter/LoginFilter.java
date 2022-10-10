package com.juzhen.edge.user.filter;

import com.alibaba.dubbo.common.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Michael on 2017/10/31.
 */
@Slf4j
@Component
@WebFilter(urlPatterns = "/user/*",filterName = "LoginFilter")
public abstract class LoginFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("登录1313131拦截------");
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




//        login(request, response, userDTO);

        filterChain.doFilter(request, response);
    }

    protected abstract String userEdgeServiceAddr();




    public void destroy() {

    }
}
