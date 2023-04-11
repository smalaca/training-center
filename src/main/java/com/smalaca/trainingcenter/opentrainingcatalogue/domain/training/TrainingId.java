package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.libraries.annotation.domaindrivendesign.ValueObject;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@ValueObject
@EqualsAndHashCode
public final class TrainingId {
    private final UUID value;

    private TrainingId(UUID value) {
        this.value = value;
    }

    public static TrainingId of(UUID trainingId) {
        return new TrainingId(trainingId);
    }
}
