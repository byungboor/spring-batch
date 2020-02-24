package com.example.demo.mail;

import com.example.demo.domain.CampaignEntity;
import com.example.demo.repository.CampaignRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateMailsTasklet implements Tasklet, StepExecutionListener {

    private CampaignRepository campaignRepository;
    private Long campaignId;

    public CreateMailsTasklet(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

        Long id = stepExecution.getJobExecution().getExecutionContext().getLong("campaignId");
        this.campaignId = id;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        CampaignEntity campaignEntity = campaignRepository.findById(campaignId).orElseThrow();
        log.info("==========================================================");
        log.info("campaign : {}", campaignEntity);
        log.info("==========================================================");
        return RepeatStatus.FINISHED;
    }
}
