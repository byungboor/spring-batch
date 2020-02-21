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

    // TODO-04 declare @Primary annotation here!
    @Bean(name = "batchDataSource")
    public DataSource batchDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:13306/ad_batch?serverTimezone=UTC");
        dataSource.setUsername("dev_user");
        dataSource.setPassword("devuser0122##@");
        return dataSource;
    }


    @Bean(name = "") // TODO - 01 set spring bean name as backupDataSource
    public DataSource backupDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //TODO-02 set url  of ad_batch_backup database
        dataSource.setUrl("");
        dataSource.setUsername("dev_user_backup");
        // TODO-03 set password
        dataSource.setPassword("1q2w3e4r");
        return dataSource;
    }
}
