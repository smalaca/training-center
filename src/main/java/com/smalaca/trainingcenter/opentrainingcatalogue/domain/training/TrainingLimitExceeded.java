package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

public class TrainingLimitExceeded extends RuntimeException {
    TrainingLimitExceeded(TrainingId trainingId) {
        super("Participants limit exceed for training: " + trainingId.id());
    }
}
