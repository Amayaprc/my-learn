package com.prc.springbootsecuritydynamic.service.impl;

import com.prc.springbootsecuritydynamic.dao.MenuDao;
import com.prc.springbootsecuritydynamic.pojo.Menu;
import com.prc.springbootsecuritydynamic.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    public List<Menu> findAllMenu() {
        return menuDao.findAllMenu();
    }
}
