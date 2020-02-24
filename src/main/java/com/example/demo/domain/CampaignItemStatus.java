package com.example.demo.domain;


import com.example.demo.Asserts;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CampaignItemStatus {

    CREATED(1),                 // CampaignItem is created
    SUCCESS(2),
    FAILED(3);

    private static final Map<Integer, CampaignItemStatus> valueMap;

    static {
        valueMap = Arrays.stream(CampaignItemStatus.values())
                .collect(Collectors.toMap(CampaignItemStatus::getValue, Function.identity()));
    }

    private Integer value;

    CampaignItemStatus(Integer value) {
        this.value = value;
    }

    public static CampaignItemStatus fromValue(Integer value) {
        Asserts.assertNotNull(value, "value must not be null");

        return valueMap.get(value);
    }

    public Integer getValue() {
        return value;
    }


}
