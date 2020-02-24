package com.example.demo.domain;

import com.example.demo.Asserts;
import com.example.demo.domain.converter.CampaignStatusConverter;
import com.example.demo.domain.converter.CampaignTypeConverter;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Entity
@Table(name = "Campaigns")
@ToString
public class CampaignEntity {

    @Id
    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "name")
    private String name;

    @Column(name = "execute_on")
    private ZonedDateTime executeOn;

    @Column(name = "status")
    @Convert(converter = CampaignStatusConverter.class)
    private CampaignStatus status;

    @Column(name = "type")
    @Convert(converter = CampaignTypeConverter.class)
    private CampaignType type;

    private CampaignEntity() {

    }

    public static CampaignEntity of(Long campaignId, String name, CampaignType type) {

        Asserts.assertNotNull(campaignId, "campaignId must not be null");
        Asserts.assertNotEmpty(name, "name must not be empty");
        Asserts.assertNotNull(type, "type must not be null");

        CampaignEntity campaignEntity = new CampaignEntity();
        campaignEntity.campaignId = campaignId;
        campaignEntity.name = name;
        campaignEntity.executeOn = ZonedDateTime.now();
        campaignEntity.type = type;
        campaignEntity.status = CampaignStatus.CREATED;

        return campaignEntity;
    }

    public CampaignEntity updateStatus(CampaignStatus campaignStatus) {
        this.status = campaignStatus;
        return this;
    }
}
