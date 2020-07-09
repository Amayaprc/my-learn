package com.prc.springbootjdbctemplate.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

    @Bean
    JdbcTemplate jdbcTemplate1(@Qualifier("ds1") DataSource ds1){
        return new JdbcTemplate(ds1);
    }

    @Bean
    JdbcTemplate jdbcTemplate2(@Qualifier("ds2") DataSource ds2){
        return new JdbcTemplate(ds2);
    }
}
