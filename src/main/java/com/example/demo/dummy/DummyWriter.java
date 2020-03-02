package com.example.demo.dummy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@StepScope
@Component
public class DummyWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> items) throws Exception {
        log.info("write items - {}", items);
    }
}
