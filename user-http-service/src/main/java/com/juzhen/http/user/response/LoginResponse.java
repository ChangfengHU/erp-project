package com.juzhen.http.user.response;

import com.juzhen.http.user.vo.UserInfoVO;
import groovy.transform.ToString;
import lombok.Data;

/**
 * Created by Michael on 2017/10/30.
 */
@Data
@ToString
public class LoginResponse extends Response {

    private String token;

    private UserInfoVO userInfoVO;

    public LoginResponse(String token, UserInfoVO userInfoVO) {
        this.token = token;
        this.userInfoVO = userInfoVO;
    }
    public LoginResponse(String token) {
        this.token = token;
    }

}
