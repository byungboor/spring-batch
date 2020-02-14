package com.example.demo.job;

import com.example.demo.word.WordPrinterTasklet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class WordPrintJobConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private WordPrinterTasklet wordPrinterTasklet;

    public WordPrintJobConfig(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilderFactory,
                              WordPrinterTasklet wordPrinterTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.wordPrinterTasklet = wordPrinterTasklet;
    }

    @Bean
    public Job wordPrintJob() {
        return jobBuilderFactory.get("wordPrintJob")
                .start(wordPrintStep())
                .build();
    }

    @Bean
    public Step wordPrintStep() {
        return stepBuilderFactory.get("wordPrintStep")
                .tasklet(wordPrinterTasklet)
                .build();
    }
}
