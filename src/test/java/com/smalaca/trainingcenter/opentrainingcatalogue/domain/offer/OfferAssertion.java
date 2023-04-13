package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingId;
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

    public OfferAssertion hasParticipantId(UUID expected) {
        Assertions.assertThat(actual).extracting("participantId").isEqualTo(ParticipantId.of(expected));
        return this;
    }

    public OfferAssertion hasTrainingId(UUID expected) {
        Assertions.assertThat(actual).extracting("trainingId").isEqualTo(TrainingId.of(expected));
        return this;
    }

    public OfferAssertion hasPrice(Price expected) {
        Assertions.assertThat(actual).extracting("price").isEqualTo(expected);
        return this;
    }
}
