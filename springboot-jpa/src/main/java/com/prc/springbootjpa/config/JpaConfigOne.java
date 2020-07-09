package com.prc.springbootjpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.prc.springbootjpa.dao.mapper1",
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBean1",
        transactionManagerRef = "platformTransactionManager1")
public class JpaConfigOne {

    //数据源
    @Resource(name = "ds1")
    DataSource ds1;

    //jpa配置
    @Autowired
    JpaProperties jpaProperties;

    @Autowired
    HibernateProperties properties;

    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean1(EntityManagerFactoryBuilder builder){
        return builder.dataSource(ds1)
                //.properties(jpaProperties.getProperties())
                .properties(properties.determineHibernateProperties(jpaProperties.getProperties(), new
                        HibernateSettings()))
                //持久化单元
                .persistenceUnit("pu1")
                //扫描的包路径
                .packages("com.prc.springbootjpa.pojo")
                //创建
                .build();
    }

    //事务
    @Bean
    PlatformTransactionManager platformTransactionManager1(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean1(builder).getObject());
    }
}
