package com.prc.springbootjpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.prc.springbootjpa.dao.mapper2",
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBean2",
        transactionManagerRef = "platformTransactionManager2")
public class JpaConfigTwo {

    //数据源
    @Resource(name = "ds2")
    DataSource ds2;

    //jpa配置
    @Autowired
    JpaProperties jpaProperties;

    @Autowired
    HibernateProperties properties;

    @Bean
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean2(EntityManagerFactoryBuilder builder){
        return builder.dataSource(ds2)
                //.properties(jpaProperties.getProperties())
                .properties(properties.determineHibernateProperties(jpaProperties.getProperties(), new
                        HibernateSettings()))
                //持久化单元
                .persistenceUnit("pu2")
                //扫描的包路径
                .packages("com.prc.springbootjpa.pojo")
                //创建
                .build();
    }

    //事务
    @Bean
    PlatformTransactionManager platformTransactionManager2(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean2(builder).getObject());
    }
}
