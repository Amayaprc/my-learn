package com.prc.springbootsecuritydynamic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.prc.springbootsecuritydynamic.dao")
public class SpringbootSecurityDynamicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurityDynamicApplication.class, args);
    }

}
