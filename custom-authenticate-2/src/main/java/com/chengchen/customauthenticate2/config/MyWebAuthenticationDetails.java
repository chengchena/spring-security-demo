package com.chengchen.customauthenticate2.config;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class MyWebAuthenticationDetails extends WebAuthenticationDetails {
    private boolean isPassed;

    public MyWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        String requestVerifyCode = request.getParameter("verifyCode");
        String verifyCode = (String) request.getSession().getAttribute("verify_code");

        if (requestVerifyCode != null && verifyCode != null && requestVerifyCode.equals(verifyCode)) {
            isPassed = true;
        }
    }

    public boolean isPassed() {
        return isPassed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(" [");
        sb.append("RemoteIpAddress=").append(this.getRemoteAddress()).append(", ");
        sb.append("SessionId=").append(this.getSessionId()).append(",");
        sb.append("isPassed=").append(this.isPassed).append("]");
        return sb.toString();
    }
}
