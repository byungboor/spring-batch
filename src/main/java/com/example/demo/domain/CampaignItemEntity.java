package com.example.demo.domain;

import com.example.demo.Asserts;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "CampaignItems")
@ToString
public class CampaignItemEntity {

    private Long campaignItemId;
    private Long campaignId;
    private String recipient;
    private String traceUuid;
    private CampaignStatus campaignStatus;
    private boolean sentFlag;
    private boolean openFlag;

    private CampaignItemEntity() {

    }

    public static final CampaignItemEntity of(Long campaignItemId, Long campaignId, String recipient, String traceUuid) {

        Asserts.assertNotNull(campaignItemId, "campaignItemId must not be null");
        Asserts.assertNotNull(campaignId, "campaignId must not be null");
        Asserts.assertNotEmpty(recipient, "recipient must not be empty");
        Asserts.assertNotNull(traceUuid, "traceUuid must not be null");

        CampaignItemEntity entity = new CampaignItemEntity();
        entity.campaignItemId = campaignItemId;
        entity.campaignId = campaignId;
        entity.recipient = recipient;
        entity.traceUuid = traceUuid;
        entity.campaignStatus = CampaignStatus.CREATED;
        entity.sentFlag = false;
        entity.openFlag = false;

        return entity;
    }

}
