package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.libraries.annotation.architecture.portandadapters.PrimaryPort;
import com.smalaca.libraries.annotation.domaindrivendesign.AggregateRoot;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@AggregateRoot
public class Training {
    @SuppressFBWarnings("UWF_UNWRITTEN_FIELD")
    private TrainingId trainingId;
    @SuppressFBWarnings("URF_UNREAD_FIELD")
    private TrainingProgrammeCode trainingProgrammeCode;
    private Price price;

    Training(TrainingProgrammeCode trainingProgrammeCode, Price price) {
        this.trainingProgrammeCode = trainingProgrammeCode;
        this.price = price;
    }

    @PrimaryPort
    public Offer choose(ParticipantId participantId) {
        return Offer.builder()
                .with(trainingId)
                .with(participantId)
                .with(price)
                .build();
    }
}
