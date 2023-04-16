package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.libraries.annotation.domaindrivendesign.Factory;
import com.smalaca.libraries.annotation.domaindrivendesign.ValueObject;
import lombok.EqualsAndHashCode;

@ValueObject
@EqualsAndHashCode
public final class TrainingProgrammeCode {
    private final String code;

    private TrainingProgrammeCode(String code) {
        this.code = code;
    }

    @Factory
    public static TrainingProgrammeCode of(String code) {
        return new TrainingProgrammeCode(code);
    }
}
