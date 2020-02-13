package com.example.demo.job;

import com.example.demo.word.WordPrinterTasklet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SimpleJobConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private WordPrinterTasklet wordPrinterTasklet;

    public SimpleJobConfig(JobBuilderFactory jobBuilderFactory,
                           StepBuilderFactory stepBuilderFactory,
                           WordPrinterTasklet wordPrinterTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.wordPrinterTasklet = wordPrinterTasklet;
    }

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
                .start() //TODO-04 Step 구성.
                .build();
    }

    @Bean
    public Step simpleStep() {
        return stepBuilderFactory.get("simpleStep")
                .tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
                    log.info("************************************************");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step wordPrintStep() {
        //TODO-03 wordPrintStep 구성.
    }
}
