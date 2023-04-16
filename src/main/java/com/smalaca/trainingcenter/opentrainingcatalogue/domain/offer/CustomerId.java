package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.domaindrivendesign.ValueObject;

import java.util.UUID;

@ValueObject
final class CustomerId {
    private final UUID value;

    private CustomerId(UUID value) {
        this.value = value;
    }

    static CustomerId of(UUID customerId) {
        return new CustomerId(customerId);
    }

    UUID id() {
        return value;
    }
}
