package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.domaindrivendesign.AggregateRoot;
import com.smalaca.libraries.annotation.domaindrivendesign.DomainFactory;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingId;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@AggregateRoot
@SuppressFBWarnings("URF_UNREAD_FIELD")
public final class Offer {
    private TrainingId trainingId;
    private ParticipantId participantId;
    private Price price;
    private OfferNumber offerNumber;

    private Offer(OfferBuilder builder) {
        this.trainingId = builder.trainingId;
        this.participantId = builder.participantId;
        this.price = builder.price;
        this.offerNumber = builder.offerNumber;
    }

    public static Offer.OfferBuilder builder() {
        return new OfferBuilder();
    }

    @DomainFactory
    public static class OfferBuilder {
        private ParticipantId participantId;
        private TrainingId trainingId;
        private Price price;
        private OfferNumber offerNumber;

        public Offer build() {
            return new Offer(this);
        }

        public OfferBuilder with(ParticipantId participantId) {
            this.participantId = participantId;
            return this;
        }

        public OfferBuilder with(TrainingId trainingId) {
            this.trainingId = trainingId;
            return this;
        }

        public OfferBuilder with(Price price) {
            this.price = price;
            return this;
        }

        OfferBuilder with(OfferNumber offerNumber) {
            this.offerNumber = offerNumber;
            return this;
        }
    }
}
