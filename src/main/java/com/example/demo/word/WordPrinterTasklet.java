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
        // TODO-01 - 탈출조건 확인.
        if (line == null)
            return RepeatStatus.FINISHED;

        String upperCaseLine = line.toUpperCase();

        log.info("Line : {}", upperCaseLine);

        return RepeatStatus.CONTINUABLE;
    }

    private String readLine() {
        // TODO-02 - 탈출조건을 고려해서 한 글자씩 리턴.

    }

}
