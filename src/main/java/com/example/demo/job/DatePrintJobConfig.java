package com.example.demo.job;

import com.example.demo.word.DatePrinterTasklet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DatePrintJobConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private DatePrinterTasklet datePrinterTasklet;

    public DatePrintJobConfig(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilderFactory,
                              DatePrinterTasklet datePrinterTasklet) {

        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.datePrinterTasklet = datePrinterTasklet;
    }

    @Bean
    public Job datePrintJob() {
        return jobBuilderFactory.get("datePrintJob")
                .start(datePrintStep())
                .build();
    }

    @Bean
    @JobScope
    public Step datePrintStep() {
        return stepBuilderFactory.get("datePrintStep")
                .listener(datePrinterTasklet)
                .tasklet(datePrinterTasklet)
                .build();
    }
}
