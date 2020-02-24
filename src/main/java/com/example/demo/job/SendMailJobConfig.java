package com.example.demo.job;

import com.example.demo.mail.CreateMailsTasklet;
import com.example.demo.mail.FinalizeCampaignTasklet;
import com.example.demo.mail.InitCampaignTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SendMailJobConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private CreateMailsTasklet createMailsTasklet;
    private FinalizeCampaignTasklet finalizeCampaignTasklet;
    private InitCampaignTasklet initCampaignTasklet;
    private PlatformTransactionManager campaignTransactionManager;

    public SendMailJobConfig(JobBuilderFactory jobBuilderFactory,
                             StepBuilderFactory stepBuilderFactory,
                             CreateMailsTasklet createMailsTasklet,
                             FinalizeCampaignTasklet finalizeCampaignTasklet,
                             InitCampaignTasklet initCampaignTasklet,
                             @Qualifier("campaignTransactionManager") PlatformTransactionManager campaignTransactionManager) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.createMailsTasklet = createMailsTasklet;
        this.finalizeCampaignTasklet = finalizeCampaignTasklet;
        this.initCampaignTasklet = initCampaignTasklet;
        this.campaignTransactionManager = campaignTransactionManager;
    }

    @Bean
    public Job sendMailJob() {
        return jobBuilderFactory.get("sendMailJob")
                .start(initCampaignStep())
                .next(createMailsStep())
                .next(finalizeCampaignStep())
                .build();
    }

    @Bean
    public Step initCampaignStep() {
        return stepBuilderFactory.get("initCampaignStep")
                .transactionManager(campaignTransactionManager)
                .tasklet(initCampaignTasklet)
                .build();
    }

    @Bean
    public Step createMailsStep() {
        return stepBuilderFactory.get("createMailsStep")
                .transactionManager(campaignTransactionManager)
                .listener(createMailsTasklet) // TODO-03 set listener
                .tasklet(createMailsTasklet)
                .build();
    }

    @Bean
    public Step finalizeCampaignStep() {
        return stepBuilderFactory.get("finalizeCampaignStep")
                .transactionManager(campaignTransactionManager)
                .tasklet(finalizeCampaignTasklet)
                .build();
    }
}

