package com.example.demo.mail;

import com.example.demo.domain.CampaignEntity;
import com.example.demo.domain.CampaignType;
import com.example.demo.repository.CampaignRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InitCampaignTasklet implements Tasklet {

    private CampaignRepository campaignRepository;

    public InitCampaignTasklet(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        Long id = System.currentTimeMillis();
        campaignRepository.save(CampaignEntity.of(id, "campaign" + LocalDate.now(), CampaignType.EMAIL));

        ExecutionContext executionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
//        ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
//        ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();

        executionContext.putLong("campaignId", id);

        return RepeatStatus.FINISHED;
    }
}
