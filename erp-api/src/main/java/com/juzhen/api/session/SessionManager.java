package com.juzhen.api.session;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.juzhen.api.user.CurrentUser;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.TimeUnit;

/**
 * Created by 25131 on 2022-10-14.
 */
public class SessionManager {
    public static Cache<String, CurrentUser> userCache =
            CacheBuilder.newBuilder().maximumSize(10000)
                    .expireAfterWrite(3, TimeUnit.MINUTES).build();
    public static final String CURRENT_USER = "current_user";


    public static void  setSession(CurrentUser currentUser) {
        RequestContextHolder.currentRequestAttributes().setAttribute(CURRENT_USER, currentUser,0);
    }
    public static CurrentUser  currentUser() {
        return getAttr();
    }
    public static <T> T getAttr() {
        Object currentUser = RequestContextHolder.currentRequestAttributes().getAttribute(CURRENT_USER, 0);
        return currentUser==null ? null : (T) currentUser;
    }

}
