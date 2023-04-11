package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import net.datafaker.Faker;

import java.util.UUID;

import static org.mockito.BDDMockito.given;

public class GivenTraining {
    private final Faker faker = new Faker();
    private final TrainingTestFactory trainingTestFactory = new TrainingTestFactory();
    private final TrainingRepository trainingRepository;

    private TrainingId trainingId;
    private TrainingProgrammeCode trainingProgrammeCode = TrainingProgrammeCode.of(faker.lorem().word());

    GivenTraining(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public GivenTraining withTrainingProgrammeCode(String trainingProgrammeCode) {
        this.trainingProgrammeCode = TrainingProgrammeCode.of(trainingProgrammeCode);
        return this;
    }

    public GivenTraining withTrainingId(UUID trainingId) {
        this.trainingId = TrainingId.of(trainingId);
        return this;
    }

    public void existing() {
        Training training = trainingTestFactory.create(trainingId, trainingProgrammeCode);
        given(trainingRepository.findBy(trainingId)).willReturn(training);
    }
}
