package com.example.demo.domain;

import com.example.demo.Asserts;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CampaignStatus {

    CREATED(0),             // started
    COMPLETED(1);           // done

    private static final Map<Integer, CampaignStatus> valueMap;

    static {
        valueMap = Arrays.stream(CampaignStatus.values())
                .collect(Collectors.toMap(CampaignStatus::getValue, Function.identity()));
    }

    private Integer value;

    CampaignStatus(Integer value) {
        this.value = value;
    }

    public static CampaignStatus fromValue(Integer value) {
        Asserts.assertNotNull(value, "value must not be null");

        return valueMap.get(value);
    }

    public Integer getValue() {
        return value;
    }

}
