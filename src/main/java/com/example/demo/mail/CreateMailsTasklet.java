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
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateMailsTasklet implements Tasklet, // TODO-01. Listener 중 어떤 Listener 를 사용해야 할까요? {

    private CampaignRepository campaignRepository;
    private Long campaignId;

    public CreateMailsTasklet(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

        Long id; // TODO-02 InitCampaignTasklet 에서 저장한 키값을 이용해서 id 값을 할당해주세요.
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
