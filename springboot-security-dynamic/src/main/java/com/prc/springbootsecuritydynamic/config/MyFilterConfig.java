package com.prc.springbootsecuritydynamic.config;

import com.prc.springbootsecuritydynamic.pojo.Menu;
import com.prc.springbootsecuritydynamic.pojo.Role;
import com.prc.springbootsecuritydynamic.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class MyFilterConfig implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;

    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> menus = menuService.findAllMenu();
        for (Menu menu : menus){
            if (pathMatcher.match(menu.getPattern(),requestUrl)){
                List<Role> roleList = menu.getRoles();
                String[] roles = new String[roleList.size()];
                for (int i = 0; i < roleList.size(); i++) {
                    roles[i] = "ROLE_" + roleList.get(i).getName();
                }
                return SecurityConfig.createList(roles);
            }
        }
        return SecurityConfig.createList("ROLE_other");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
