package com.prc.springbootsecuritydynamic.dao;

import com.prc.springbootsecuritydynamic.pojo.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {

    List<Menu> findAllMenu();
}
