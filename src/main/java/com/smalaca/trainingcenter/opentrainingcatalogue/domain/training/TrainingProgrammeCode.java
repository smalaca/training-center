package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.libraries.annotation.domaindrivendesign.ValueObject;

@ValueObject
public class TrainingProgrammeCode {
    private final String code;

    public TrainingProgrammeCode(String code) {
        this.code = code;
    }
}
