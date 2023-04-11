package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import net.datafaker.Faker;

public class GivenTrainingFactory {
    private static final Faker FAKER = new Faker();
    private final TrainingRepository trainingRepository;

    public GivenTrainingFactory(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public GivenTraining training(TrainingRepository trainingRepository) {
        return GivenTraining.create(trainingRepository, FAKER);
    }
}
