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
// TODO-01 spring bean 을 설정하기 위한 애너테이션을 선언해주세요.
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
//        return jobBuilderFactory.get("wordPrintJob")
//                .start(wordPrintStep())
//                .build();
        // TODO-02 wordPrintJob 이라는 이름을 갖는 Job 을 생성합니ㅏㄷ.
        // jobBuilderFactory 를 이용해서 생성합니다.
        // 그리고 아래의 wordPrintStep() 빈을 주입합니다.
    }

    // TODO-03 스프링 빈으로 설정하려면 어떤 에너테이션을 선언할까요?
    public Step wordPrintStep() {
        return stepBuilderFactory.get("wordPrintStep")
                .tasklet() // TODO-04 wordPrinterTasklet 을 전달해주세요..
                .build();
    }

    // TODO-05 실행시 프로그램 args 로 다음 값을 설정합시다. --spring.batch.job.names=wordPrintJob version=1
    // TODO-06 실행시 프로그램 args 로 다음 값을 설정합시다. --spring.batch.job.names=SimpleJobConfig version=1
}
