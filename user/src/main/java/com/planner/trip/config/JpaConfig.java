package com.planner.trip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = {"com.planner.trip.repository"})//다른 모듈 Repo 스캔중
@EnableTransactionManagement
public class JpaConfig {
    @Autowired
    DataSource dataSource;
//    @Bean
//    public DataSource dataSource(){
//        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//        dataSource.setDriverClass(org.mariadb.jdbc.Driver.class);
//        dataSource.setUrl("jdbc:mariadb://54.180.107.191:3305/trip");
//        dataSource.setUsername("root");
//        dataSource.setPassword("newcatering");
//        return dataSource;
//    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){//엔티티 생성
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true); // 서버실행시 DDL 실행

        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(vendorAdapter);
        bean.setPackagesToScan("com.planner.trip.model");// 다른 모듈 model 스캔중

        return bean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory managerFactory){//트랜잭션
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(managerFactory);
        return transactionManager;
    }
}
