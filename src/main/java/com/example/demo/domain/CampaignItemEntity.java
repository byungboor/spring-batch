package com.example.demo.domain;

import com.example.demo.Asserts;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "CampaignItems")
@ToString
public class CampaignItemEntity {

    @Id
    @Column(name = "campaign_item_id")
    private Long campaignItemId;

    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "trace_uuid")
    private String traceUuid;

    @Column(name = "status")
    private CampaignStatus campaignStatus;

    @Column(name = "sent_flag")
    private boolean sentFlag;

    @Column(name = "open_flag")
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
