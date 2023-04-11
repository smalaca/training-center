package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.domaindrivendesign.AggregateRoot;
import com.smalaca.libraries.annotation.domaindrivendesign.DomainFactory;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantId.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingProgrammeCode;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@AggregateRoot
@SuppressFBWarnings("URF_UNREAD_FIELD")
public final class Offer {
    private ParticipantId participantId;
    private TrainingId trainingId;
    private TrainingProgrammeCode trainingProgrammeCode;

    private Offer(OfferBuilder builder) {
        this.participantId = builder.participantId;
        this.trainingId = builder.trainingId;
        this.trainingProgrammeCode = builder.trainingProgrammeCode;
    }

    public static Offer.OfferBuilder builder() {
        return new OfferBuilder();
    }

    @DomainFactory
    public static class OfferBuilder {
        private ParticipantId participantId;
        private TrainingId trainingId;
        private TrainingProgrammeCode trainingProgrammeCode;

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

        public OfferBuilder with(TrainingProgrammeCode trainingProgrammeCode) {
            this.trainingProgrammeCode = trainingProgrammeCode;
            return this;
        }
    }
}
