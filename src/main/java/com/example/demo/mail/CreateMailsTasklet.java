package com.example.demo.mail;

import com.example.demo.domain.CampaignEntity;
import com.example.demo.repository.CampaignRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateMailsTasklet implements Tasklet {

    private CampaignRepository campaignRepository;

    public CreateMailsTasklet(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    // TODO-02-02. get campaign id from previous step
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        ExecutionContext executionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
        Long id = executionContext.getLong("campaignId");

        CampaignEntity campaignEntity = campaignRepository.findById(id).orElseThrow();
        log.info("==========================================================");
        log.info("campaign : {}", campaignEntity);
        log.info("==========================================================");
        return RepeatStatus.FINISHED;
    }
}
