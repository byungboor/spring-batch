package com.example.demo.domain.converter;

import com.example.demo.Asserts;
import com.example.demo.domain.CampaignItemStatus;

import javax.persistence.AttributeConverter;

public class CampaignItemStatusConverter implements AttributeConverter<CampaignItemStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CampaignItemStatus campaignItemStatus) {
        Asserts.assertNotNull(campaignItemStatus, "CampaignItemStatus is null");

        return campaignItemStatus.getValue();
    }

    @Override
    public CampaignItemStatus convertToEntityAttribute(Integer integer) {
        return CampaignItemStatus.fromValue(integer);
    }
}
