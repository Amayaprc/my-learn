package com.prc.springbootflowablenew;

import com.prc.springbootflowablenew.config.AppDispatcherServletConfiguration;
import com.prc.springbootflowablenew.config.ApplicationConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Import({
        ApplicationConfiguration.class,
        AppDispatcherServletConfiguration.class
})
@MapperScan("com.prc.springbootflowablenew.dao")
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.prc"})
public class SpringbootFlowableNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFlowableNewApplication.class, args);
    }

}
