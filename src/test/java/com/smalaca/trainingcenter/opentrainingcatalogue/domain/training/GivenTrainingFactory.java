package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

public class GivenTrainingFactory {
    private final TrainingRepository trainingRepository;

    public GivenTrainingFactory(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public GivenTraining training(TrainingRepository trainingRepository) {
        return new GivenTraining(trainingRepository);
    }
}
