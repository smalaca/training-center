package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.libraries.annotation.architecture.portandadapters.PrimaryPort;
import com.smalaca.libraries.annotation.domaindrivendesign.AggregateRoot;
import com.smalaca.libraries.annotation.domaindrivendesign.DomainFactory;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.UUID;

@AggregateRoot
public class Training {
    @SuppressFBWarnings("UWF_UNWRITTEN_FIELD")
    private UUID trainingId;
    private TrainingProgrammeCode trainingProgrammeCode;

    Training(TrainingProgrammeCode trainingProgrammeCode) {
        this.trainingProgrammeCode = trainingProgrammeCode;
    }

    @PrimaryPort
    @DomainFactory
    public Offer choose() {
        return new Offer(trainingId, trainingProgrammeCode);
    }
}
