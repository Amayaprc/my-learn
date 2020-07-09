package com.prc.springbootcanal;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCanalClient
public class SpringbootCanalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCanalApplication.class, args);
    }

}
