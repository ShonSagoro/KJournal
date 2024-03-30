package com.kiweri.kjournal.entities.enums;

import lombok.Getter;

@Getter
public enum SubscriptionType {
    FREE("Free"),
    PREMIUM("Premium"),
    ENTERPRISE("Enterprise");

    private final String value;

    SubscriptionType(String value) {
        this.value = value;
    }
}
