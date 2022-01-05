package com.chengchen.customauthenticate1.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class MyAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String requestVerifyCode = req.getParameter("verifyCode");
        String verifyCode = (String) req.getSession().getAttribute("verify_code");

        if (requestVerifyCode == null || verifyCode == null || !requestVerifyCode.equals(verifyCode)) {
            throw new AuthenticationServiceException("验证码错误");
        }

        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
