package com.example.demo.dummy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@StepScope
@Component
public class DummyReader implements ItemReader<String>, StepExecutionListener {

    private LinkedList<String> queue;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        String tokens = stepExecution.getExecutionContext().getString("Tokens");
        log.info("original : {}", tokens);

        this.queue = new LinkedList<>(List.of(tokens.split(",")));
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public String read() throws Exception {

        String chr = queue.poll();
        log.info("chr : {}", chr);

        TimeUnit.SECONDS.sleep(1);

        return chr;
    }
}
