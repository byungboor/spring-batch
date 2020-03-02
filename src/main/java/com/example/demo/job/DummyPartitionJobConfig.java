package com.example.demo.job;

import com.example.demo.dummy.DummyPartitioner;
import com.example.demo.dummy.DummyReader;
import com.example.demo.dummy.DummyWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
@Configuration
public class DummyPartitionJobConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private DummyPartitioner dummyPartitioner;
    private DummyWriter dummyWriter;
    private DummyReader dummyReader;

    public DummyPartitionJobConfig(JobBuilderFactory jobBuilderFactory,
                                   StepBuilderFactory stepBuilderFactory,
                                   DummyPartitioner dummyPartitioner,
                                   DummyWriter dummyWriter,
                                   DummyReader dummyReader) {

        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dummyPartitioner = dummyPartitioner;
        this.dummyWriter = dummyWriter;
        this.dummyReader = dummyReader;
    }

    @Bean
    public Job dummyPartitionJob() {
        return jobBuilderFactory.get("dummyPartitionJob")
                .start(dummyPartitionStep())
                .build();
    }

    @Bean
    public Step dummyPartitionStep() {
        return stepBuilderFactory.get("dummyPartitionStep")
                .listener(dummyPartitioner)
                .partitioner("dummyStep", dummyPartitioner)
                .step(dummyStep())
                .taskExecutor(taskExecutor())
                .gridSize(2)
                .build();
    }

    @Bean
    public Step dummyStep() {
        return stepBuilderFactory.get("dummyStep")
                .<String, String>chunk(3)
                .reader(dummyReader)
                .writer(dummyWriter)
                .build();
    }


    @Bean(destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(3);
        taskExecutor.setMaxPoolSize(3);
        taskExecutor.setThreadNamePrefix("dummyWorker-");
        taskExecutor.afterPropertiesSet();
        return taskExecutor;
    }
}

