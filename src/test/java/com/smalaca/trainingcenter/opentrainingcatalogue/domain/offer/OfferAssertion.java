package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingProgrammeCode;
import org.assertj.core.api.Assertions;

import java.util.UUID;

public class OfferAssertion {
    private final Offer actual;

    private OfferAssertion(Offer actual) {
        this.actual = actual;
    }

    public static OfferAssertion assertThat(Offer actual) {
        return new OfferAssertion(actual);
    }

    public OfferAssertion hasTrainingId(UUID expected) {
        Assertions.assertThat(actual).extracting("trainingId").isEqualTo(expected);
        return this;
    }

    public OfferAssertion hasTrainingProgrammeCode(TrainingProgrammeCode expected) {
        Assertions.assertThat(actual).extracting("trainingProgrammeCode").isEqualTo(expected);
        return this;
    }
}
