package com.example.demo.mail;

import com.example.demo.domain.CampaignItemEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
// TODO-02 fill out two generics in diamond operator, first generic type is input and second is output
public class CreateMailsProcessor implements ItemProcessor<>, StepExecutionListener {

    private Long campaignId;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // TODO-01 how to set campaignId? please refer to beforeStep method in CreateMailReader..
        campaignId = ?;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public CampaignItemEntity process(String item) throws Exception {

        Long id = System.currentTimeMillis();
        id = id * 1000000;
        id += ThreadLocalRandom.current().nextInt(0, 1000000);

        return CampaignItemEntity.of(id, campaignId, item, "trace::" + id);
    }
}
