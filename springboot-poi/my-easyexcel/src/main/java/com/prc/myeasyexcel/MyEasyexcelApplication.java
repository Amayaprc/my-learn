package com.prc.myeasyexcel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.prc.myeasyexcel.dao")
public class MyEasyexcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEasyexcelApplication.class, args);
	}

}
