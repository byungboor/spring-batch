package com.example.demo.word;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WordPrinterTasklet implements Tasklet {

    private int offset = 0;
    private List<String> lines = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n");

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        String line = this.readLine();
        if (line == null)
            return RepeatStatus.FINISHED;

        String upperCaseLine = line.toUpperCase();

        log.info("Line : {}", upperCaseLine);

        return RepeatStatus.CONTINUABLE;
    }

    private String readLine() {

        if (offset > lines.size() - 1)
            return null;

        return lines.get(offset++);
    }

}
