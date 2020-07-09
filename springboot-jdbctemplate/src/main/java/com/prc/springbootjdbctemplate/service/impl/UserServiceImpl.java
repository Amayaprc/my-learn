package com.prc.springbootjdbctemplate.service.impl;

import com.prc.springbootjdbctemplate.pojo.User;
import com.prc.springbootjdbctemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource(name = "jdbcTemplate1")
    JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("jdbcTemplate2")
    JdbcTemplate jdbcTemplate2;

    @Override
    public Integer addUser(User user) {
        Integer result = jdbcTemplate2.update("insert into user (user_name,sex,address) values (?,?,?)",user.getUserName(),user.getSex(),user.getAddress());
        return result;
    }

    @Override
    public Integer updateUserName(String id, String userName) {
        Integer result = jdbcTemplate2.update("update user set user_name = ? where id = ?",userName,id);
        return result;
    }

    @Override
    public Integer deleteUser(String id) {
        Integer result = jdbcTemplate2.update("delete from user where id = ?",id);
        return result;
    }

    @Override
    public List<User> selectUser(String id) {
        return jdbcTemplate2.query("select * from user where id = ?",new BeanPropertyRowMapper<>(User.class),id);
    }

    @Override
    public List<User> selectUserAll1() {
        return jdbcTemplate1.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setSex(resultSet.getInt("sex"));
                user.setAddress(resultSet.getString("address"));
                return user;
            }
        });
    }

    @Override
    public List<User> selectUserAll2() {
        return jdbcTemplate2.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setSex(resultSet.getInt("sex"));
                user.setAddress(resultSet.getString("address"));
                return user;
            }
        });
    }
}
