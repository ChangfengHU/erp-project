package com.juzhen.api.filter;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.juzhen.api.session.SessionManager;
import com.juzhen.api.user.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 2017/10/31.
 */
@Slf4j
public abstract class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器---start----");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String token = request.getHeader("token");
        if(token !=null && !token.equals("")) {
            Cookie[] cookies = request.getCookies();
            if(cookies!=null) {
                for(Cookie c : cookies) {
                    if(c.getName().equals("token")) {
                        token = c.getValue();
                    }
                }
            }
        }

        CurrentUser currentUser = null;
        if(token !=null && !token.equals("")) {
            log.info("获取token={}",token);
            currentUser = SessionManager.userCache.getIfPresent(token);
            if(currentUser ==null) {
                currentUser = requestUserInfo(token);
                if(currentUser !=null) {
                    SessionManager.userCache.put(token, currentUser);
                    SessionManager.setSession(currentUser);
                }
            }
        }
//        if(currentUser ==null) {
//            log.info("没有登录直接返回.....");
//            response.sendRedirect("http://127.0.0.1:8082/user/login");
//            return;
//        }
        login(request, response, currentUser);

        filterChain.doFilter(request, response);
    }

    protected abstract String userEdgeServiceAddr();

    protected abstract void login(HttpServletRequest request, HttpServletResponse response, CurrentUser currentUser);

    private CurrentUser requestUserInfo(String token) {
        String url = "http://"+userEdgeServiceAddr()+"/user/authentication";
//        String url = "http://127.0.0.1:8081/user/authentication";
        System.out.println("根据token 取用户信息 url"+url+",token:"+token);
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.addHeader("token", token);
        InputStream inputStream = null;
        try {
            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode()!= HttpStatus.SC_OK) {
                throw new RuntimeException("request user info failed! StatusLine:"+response.getStatusLine());
            }
            inputStream = response.getEntity().getContent();
            byte[] temp = new byte[1024];
            StringBuilder sb = new StringBuilder();
            int len = 0;
            while((len = inputStream.read(temp))>0) {
                sb.append(new String(temp,0,len));
            }
            log.info("获取用户信息str={}",JSON.toJSONString(sb));
            CurrentUser currentUser = JSON.parseObject(sb.toString(), CurrentUser.class);
            log.info("获取用户信息对象={}",JSON.toJSONString(currentUser));
//            UserDTO userDTO = new ObjectMapper().readValue(sb.toString(), UserDTO.class);
            return currentUser;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream!=null) {
                try{
                    inputStream.close();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void destroy() {

    }
}
