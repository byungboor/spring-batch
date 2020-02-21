package com.example.demo.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DatePrintJobConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    public DatePrintJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job datePrintJob() {
        return jobBuilderFactory.get("datePrintJob")
                .start(datePrintStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step datePrintStep(@Value("#{jobParameters[date]}") String date) {
        return stepBuilderFactory.get("datePrintStep")
                .tasklet((a, b) -> {
                    log.info("Date Parameter : {}", date);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
