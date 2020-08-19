package com.prc.springbootmybatisgeneator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.prc.springbootmybatisgeneator.dao")
public class SpringbootMybatisGeneatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisGeneatorApplication.class, args);
    }

}
