package com.juzhen.edge.user.redis;

import com.alibaba.dubbo.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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
//    @Autowired
//    private RedisUtils redisUtils;

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

        log.info("默认登录------");
//        userDTO = (SysUser)redisTool.get(token);
//        if(!StringUtils.isEquals("/hy-admin/sys/login",requestURI) && userDTO==null) {
//            log.warn("未登录,登录拦截----");
//            System.out.println("url"+requestURI);
//            response.sendRedirect("http://www.mooc.com/user/login");
//            return;
//        }



//        login(request, response, userDTO);
        request.setAttribute("token", token);
        filterChain.doFilter(request, response);
    }

    private String getString(HttpServletRequest request) {
        String token = request.getParameter("token");
        log.info("登录拦截------token"+token);
        if(StringUtils.isBlank(token)) {
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


//    protected abstract String userEdgeServiceAddr();




    public void destroy() {

    }
}
