package com.example.demo.domain;

import com.example.demo.Asserts;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CampaignType {

    EMAIL(1);

    private static final Map<Integer, CampaignType> valueMap;

    static {
        valueMap = Arrays.stream(CampaignType.values())
                .collect(Collectors.toMap(CampaignType::getValue, Function.identity()));
    }

    private Integer value;

    CampaignType(Integer value) {
        this.value = value;
    }

    public static CampaignType fromValue(Integer value) {
        Asserts.assertNotNull(value, "value is null");

        return valueMap.get(value);
    }

    public Integer getValue() {
        return this.value;
    }
}
