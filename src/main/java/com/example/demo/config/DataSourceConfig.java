package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DataSourceConfig {

    // TODO-01-05. TX Manager
    @Primary
    @Bean(name = "batchDataSource")
    public DataSource batchDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:13306/ad_batch?serverTimezone=UTC");
        dataSource.setUsername("dev_user");
        dataSource.setPassword("devuser0122##@");
        return dataSource;
    }


    @Bean(name = "campaignDataSource")
    public DataSource campaignDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:13306/ad_batch_backup?serverTimezone=UTC");
        dataSource.setUsername("dev_user_backup");
        dataSource.setPassword("1q2w3e4r");
        return dataSource;
    }
}
