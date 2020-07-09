package com.prc.springbootsecuritydb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.prc.springbootsecuritydb.dao")
public class SpringbootSecurityDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurityDbApplication.class, args);
    }

}
