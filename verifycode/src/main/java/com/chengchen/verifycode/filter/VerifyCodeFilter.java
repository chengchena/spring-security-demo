package com.chengchen.verifycode.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class VerifyCodeFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if ("POST".equalsIgnoreCase(request.getMethod()) && "/doLogin".equals(request.getServletPath())) {
            // 验证码验证
            String requestCaptcha = request.getParameter("verifyCode");
            String genCaptcha = (String) request.getSession().getAttribute("verify_code");
            if (StringUtils.isEmpty(genCaptcha)) {
                //throw new AuthenticationServiceException("验证码不能为空!");
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(new ObjectMapper().writeValueAsString("验证码未生成!"));
                out.flush();
                out.close();
            }

            if (StringUtils.isEmpty(requestCaptcha)) {
                //throw new AuthenticationServiceException("验证码不能为空!");
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(new ObjectMapper().writeValueAsString("验证码不能为空!"));
                out.flush();
                out.close();
            }
            if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
                //throw new AuthenticationServiceException("验证码错误!");
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(new ObjectMapper().writeValueAsString("验证码错误!"));
                out.flush();
                out.close();
            }
        }
        filterChain.doFilter(request, response);
    }
}

