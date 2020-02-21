package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration  //TODO-02
public class DataSourceConfig {

    // TODO-03
    @Bean(name = "batchDataSource")
    public DataSource batchDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:13306/ad_batch?serverTimezone=UTC");
        dataSource.setUsername("dev_user");
        dataSource.setPassword("devuser0122##@");
        return dataSource;
    }

}
