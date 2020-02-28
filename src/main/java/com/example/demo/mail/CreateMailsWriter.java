package com.example.demo.mail;

import com.example.demo.domain.CampaignItemEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CreateMailsWriter implements ItemWriter<CampaignItemEntity> {

    @Override
    public void write(List<? extends CampaignItemEntity> items) throws Exception {

        String combinedRecipients = items.stream()
                .map(CampaignItemEntity.class::cast)
                .map(CampaignItemEntity::getRecipient)
                .collect(Collectors.joining(","));

        log.info("Writer : {}", combinedRecipients);
    }
}
