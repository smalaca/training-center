package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.domaindrivendesign.DomainFactory;
import com.smalaca.libraries.annotation.domaindrivendesign.ValueObject;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@ValueObject
@EqualsAndHashCode
public final class OfferId {
    private final UUID value;

    private OfferId(UUID value) {
        this.value = value;
    }

    @DomainFactory
    public static OfferId of(UUID offerId) {
        return new OfferId(offerId);
    }

    public UUID id() {
        return value;
    }
}
