package com.example.demo.domain.converter;

import com.example.demo.Asserts;
import com.example.demo.domain.CampaignType;

import javax.persistence.AttributeConverter;

public class CampaignTypeConverter implements AttributeConverter<CampaignType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CampaignType campaignType) {

        Asserts.assertNotNull(campaignType, "campaignStatus is null");

        return campaignType.getValue();
    }

    @Override
    public CampaignType convertToEntityAttribute(Integer integer) {
        return CampaignType.fromValue(integer);
    }
}
