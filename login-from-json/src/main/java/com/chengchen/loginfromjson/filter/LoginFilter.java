package com.chengchen.loginfromjson.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String verifyCode = (String) request.getSession().getAttribute("verify_code");
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            Map<String, String> loginDataMap = new HashMap<>();

            try {
                loginDataMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {

            } finally {
                String code = loginDataMap.get("verifyCode");
                checkCode(response, code, verifyCode);
            }
            String username = loginDataMap.get(getUsernameParameter());
            username = (username != null) ? username : "";
            username = username.trim();
            String password = loginDataMap.get(getPasswordParameter());
            password = (password != null) ? password : "";
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);

        } else {
            checkCode(response, request.getParameter("verifyCode"), verifyCode);
            return super.attemptAuthentication(request, response);
        }


    }

    private void checkCode(HttpServletResponse response, String code, String verifyCode) {
        if (code == null || verifyCode == null || "".equals(code) || !verifyCode.toLowerCase().equals(code.toLowerCase())) {
            //验证码不正确
            throw new AuthenticationServiceException("验证码不正确");
            /*response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString("验证码错误!"));
            out.flush();
            out.close();*/
        }
    }
}
