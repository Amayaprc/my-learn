package com.prc.springbootsecuritydb.security;

import com.prc.springbootsecuritydb.pojo.Role;
import com.prc.springbootsecuritydb.pojo.User;
import com.prc.springbootsecuritydb.service.RoleService;
import com.prc.springbootsecuritydb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyUserDetails implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByName(username);
        if (null == user || "".equals(user)){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Role> roles = roleService.findRoleById(user.getId());
        user.setRoles(roles);
        return user;
    }
}
