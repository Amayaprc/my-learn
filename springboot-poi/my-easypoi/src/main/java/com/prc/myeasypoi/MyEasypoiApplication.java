package com.prc.myeasypoi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.prc.myeasypoi.dao")
public class MyEasypoiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEasypoiApplication.class, args);
	}

}
