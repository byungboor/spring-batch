package com.example.demo.domain.converter;


import com.example.demo.Asserts;
import com.example.demo.domain.CampaignStatus;

import javax.persistence.AttributeConverter;

public class CampaignStatusConverter implements AttributeConverter<CampaignStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CampaignStatus campaignStatus) {
        Asserts.assertNotNull(campaignStatus, "campaignStatus is null");

        return campaignStatus.getValue();
    }

    @Override
    public CampaignStatus convertToEntityAttribute(Integer integer) {
        return CampaignStatus.fromValue(integer);
    }
}
