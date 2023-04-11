package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.libraries.annotation.domaindrivendesign.ValueObject;
import lombok.EqualsAndHashCode;

@ValueObject
@EqualsAndHashCode
public class TrainingProgrammeCode {
    private final String code;

    public TrainingProgrammeCode(String code) {
        this.code = code;
    }
}
