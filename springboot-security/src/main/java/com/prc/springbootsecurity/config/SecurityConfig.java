package com.prc.springbootsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("123456").roles("admin")
                .and()
                .withUser("Amaya").password("123").roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //拥有admin角色才能访问的路径规则
                .antMatchers("/admin/**").hasRole("admin")
                //拥有admin或user任意一种角色才能访问的路径规则
                .antMatchers("/user/**").hasAnyRole("admin","user")
                //其他路径请求认证后即可访问
                .anyRequest().authenticated()
                .and()
                //处理登录接口的url
                .formLogin()
                .loginProcessingUrl("/doLogin")
                //配置登陆页面
                .loginPage("/login")
                //配置登录的参数名
                .usernameParameter("userName")
                .passwordParameter("passWord")
                //前后端不分离,登录成功跳转的路径
                //.successForwardUrl()
                //前后端分离
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<String,Object> map = new HashMap<>();
                        map.put("code",200);
                        map.put("message",auth.getPrincipal());
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                //登录失败的处理
                //.failureForwardUrl()
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<String,Object> map = new HashMap<>();
                        map.put("code",401);

                        if (e instanceof LockedException){
                            map.put("message","账户被锁定,登陆失败!");
                        }else if (e instanceof BadCredentialsException){
                            map.put("message","用户名或密码输入错误,登陆失败!");
                        }else if (e instanceof DisabledException){
                            map.put("message","账户被禁用,登陆失败!");
                        }else if (e instanceof AccountExpiredException){
                            map.put("message","账户过期,登陆失败!");
                        }else if (e instanceof CredentialsExpiredException){
                            map.put("message","密码过期,登陆失败!");
                        }else {
                            map.put("message","登陆失败!");
                        }

                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                //跟登录相关的接口可以直接访问
                .permitAll()
                .and()
                //注销登录配置
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<String,Object> map = new HashMap<>();
                        map.put("code",200);
                        map.put("message","注销登陆成功!");
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                .and()
                //关闭csrf攻击
                .csrf().disable();
    }
}
