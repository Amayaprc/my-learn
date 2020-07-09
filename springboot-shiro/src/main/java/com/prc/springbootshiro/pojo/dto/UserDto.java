package com.prc.springbootshiro.pojo.dto;

import com.prc.springbootshiro.pojo.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDto implements Serializable {

    private User user;

    private List<String> roles;

    private List<String> permissions;

    public UserDto(User user, List<String> roles, List<String> permissions) {
        this.user = user;
        this.roles = roles;
        this.permissions = permissions;
    }
}
