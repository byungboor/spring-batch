package com.example.demo.job;

import com.example.demo.mail.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SendMailJobConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    private InitCampaignTasklet initCampaignTasklet;
    private FinalizeCampaignTasklet finalizeCampaignTasklet;
    private CreateMailsReader createMailsReader;
    private CreateMailsProcessor createMailsProcessor;
    private CreateMailsWriter createMailsWriter;

    private PlatformTransactionManager campaignTransactionManager;

    public SendMailJobConfig(JobBuilderFactory jobBuilderFactory,
                             StepBuilderFactory stepBuilderFactory,
                             @StepScope InitCampaignTasklet initCampaignTasklet,
                             @StepScope FinalizeCampaignTasklet finalizeCampaignTasklet,
                             @StepScope CreateMailsReader createMailsReader,
                             @StepScope CreateMailsProcessor createMailsProcessor,
                             @StepScope CreateMailsWriter createMailsWriter,
                             @Qualifier("campaignTransactionManager") PlatformTransactionManager campaignTransactionManager) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.initCampaignTasklet = initCampaignTasklet;
        this.finalizeCampaignTasklet = finalizeCampaignTasklet;
        this.createMailsReader = createMailsReader;
        this.createMailsProcessor = createMailsProcessor;
        this.createMailsWriter = createMailsWriter;
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

        // TODO-04 fill out file name which is located in resources folder.
        // if you can't find out where is resources folder,
        // please find application.properties file and the folder containing that file  is resources folder
        createMailsReader.setResourcePath();

        return stepBuilderFactory.get("createMailsStep")
                .transactionManager(campaignTransactionManager)
                . //TODO-05 : set chunk() method.
                .reader(createMailsReader)
                .processor(createMailsProcessor)
                .writer(createMailsWriter)
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

