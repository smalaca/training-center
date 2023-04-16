package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingId;
import org.assertj.core.api.Assertions;

public class OfferAssertion {
    private final Offer actual;

    private OfferAssertion(Offer actual) {
        this.actual = actual;
    }

    public static OfferAssertion assertThat(Offer actual) {
        return new OfferAssertion(actual);
    }

    public OfferAssertion hasParticipantId(ParticipantId expected) {
        Assertions.assertThat(actual).extracting("participantId").isEqualTo(expected);
        return this;
    }

    public OfferAssertion hasTrainingId(TrainingId expected) {
        Assertions.assertThat(actual).extracting("trainingId").isEqualTo(expected);
        return this;
    }

    public OfferAssertion hasPrice(Price expected) {
        Assertions.assertThat(actual).extracting("price").isEqualTo(expected);
        return this;
    }

    public OfferAssertion hasValidOfferNumberFor(String date, ParticipantId participantId) {
        Assertions.assertThat(actual).extracting("offerNumber")
                .isInstanceOf(OfferNumber.class)
                .satisfies(offerNumber -> {
                    OfferNumber actual = ((OfferNumber) offerNumber);
                    Assertions.assertThat(actual.number())
                            .matches("OFFER-" + date + "-" + participantId.id() + "-[0-9]{9}");
                });

        return this;
    }
}
