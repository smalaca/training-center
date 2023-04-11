package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.libraries.annotation.architecture.portandadapters.PrimaryPort;
import com.smalaca.libraries.annotation.domaindrivendesign.AggregateRoot;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantId.ParticipantId;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@AggregateRoot
public class Training {
    @SuppressFBWarnings("UWF_UNWRITTEN_FIELD")
    private TrainingId trainingId;
    private final TrainingProgrammeCode trainingProgrammeCode;

    Training(TrainingProgrammeCode trainingProgrammeCode) {
        this.trainingProgrammeCode = trainingProgrammeCode;
    }

    @PrimaryPort
    public Offer choose(ParticipantId participantId) {
        return Offer.builder()
                .with(participantId)
                .with(trainingId)
                .with(trainingProgrammeCode)
                .build();
    }
}
