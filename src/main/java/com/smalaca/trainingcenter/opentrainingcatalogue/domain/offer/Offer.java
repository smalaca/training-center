package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.domaindrivendesign.AggregateRoot;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingProgrammeCode;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.UUID;

@AggregateRoot
@SuppressFBWarnings("URF_UNREAD_FIELD")
public class Offer {
    private UUID trainingId;
    private TrainingProgrammeCode trainingProgrammeCode;

    public Offer(UUID trainingId, TrainingProgrammeCode trainingProgrammeCode) {
        this.trainingId = trainingId;
        this.trainingProgrammeCode = trainingProgrammeCode;
    }
}
