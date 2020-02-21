package com.example.demo.word;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@StepScope
@Component
public class DatePrinterTasklet implements Tasklet, StepExecutionListener {

    private String date;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        JobParameters jobParameters = stepExecution.getJobParameters();
        this.date = jobParameters.getString("date");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        if (Objects.isNull(date))
            throw new IllegalStateException("date parameter is null");

        log.info("Date Parameter : {}", date);
        return RepeatStatus.FINISHED;
    }
}
