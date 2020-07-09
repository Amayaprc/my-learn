package com.prc.springbootsecurityjwt.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //获取请求头参数
        String header = req.getHeader("authorization");
        Jws<Claims> jws = Jwts.parser().setSigningKey("test")
                .parseClaimsJws(header.replace("Bearer", ""));
        Claims claims = jws.getBody();
        //获取用户名
        String username = claims.getSubject();
        //获取角色
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(((String) claims.get("authorities")));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
