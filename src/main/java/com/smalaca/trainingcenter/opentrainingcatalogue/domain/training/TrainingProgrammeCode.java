package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.libraries.annotation.domaindrivendesign.ValueObject;
import lombok.EqualsAndHashCode;

@ValueObject
@EqualsAndHashCode
public class TrainingProgrammeCode {
    private final String code;

    private TrainingProgrammeCode(String code) {
        this.code = code;
    }

    public static TrainingProgrammeCode of(String code) {
        return new TrainingProgrammeCode(code);
    }
}
