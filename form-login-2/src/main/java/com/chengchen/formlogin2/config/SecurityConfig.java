package com.chengchen.formlogin2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("chencheng")
                .password("cc123456")
                .roles("admin");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html") //登陆页面 get
                .loginProcessingUrl("/doLogin") //配置登陆接口 post
                .usernameParameter("user_name")
                .passwordParameter("pass_word")
                .successForwardUrl("/success") // 服务端跳转
                //.defaultSuccessUrl("/success") // 重定向
                //.failureForwardUrl("/fail") //  登陆失败服务端跳转
                .failureUrl("/fail") // 登陆失败 重定向
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout") // GET请求
                //.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST")) //注销登陆设为POST请求
                .logoutSuccessUrl("/login.html") //注销成功后重定向
                .deleteCookies()
                //.invalidateHttpSession(true) // 默认为true 不用配置
                //.clearAuthentication(true) // 默认为true 不用配置
                .permitAll()
                .and()
                .csrf().disable();
    }
}
