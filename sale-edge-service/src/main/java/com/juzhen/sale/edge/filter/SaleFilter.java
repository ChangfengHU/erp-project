package com.juzhen.sale.edge.filter;

import com.juzhen.user.client.dto.CurrentUser;
import com.juzhen.user.client.filter.LoginFilter;
import com.juzhen.user.client.session.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Michael on 2017/11/4.
 */
@Component
@Slf4j
public class SaleFilter extends LoginFilter {

    @Value("${user.edge.service.addr}")
    private String userEdgeServiceAddr;

    @Override
    protected String userEdgeServiceAddr() {
        return userEdgeServiceAddr;
    }

    @Override
    protected void login(HttpServletRequest request, HttpServletResponse response, CurrentUser currentUser) {
        log.info("login----={}",currentUser);
        SessionManager.setSession(currentUser);
        request.setAttribute("currentUser", currentUser);
    }
}
