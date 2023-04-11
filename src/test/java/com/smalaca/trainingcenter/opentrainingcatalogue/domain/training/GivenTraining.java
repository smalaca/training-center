package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import net.datafaker.Faker;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

public class GivenTraining {
    private final TrainingRepository trainingRepository;

    private TrainingId trainingId;
    private TrainingProgrammeCode trainingProgrammeCode;

    private GivenTraining(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    static GivenTraining create(TrainingRepository trainingRepository, Faker faker) {
        GivenTraining givenTraining = new GivenTraining(trainingRepository);
        givenTraining.trainingProgrammeCode = TrainingProgrammeCode.of(faker.lorem().word());

        return givenTraining;
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
        Training training = training();
        given(trainingRepository.findBy(trainingId)).willReturn(training);
    }

    private Training training() {
        return withTrainingId(new Training(trainingProgrammeCode));
    }

    private Training withTrainingId(Training training)  {
        try {
            Field trainingIdField = training.getClass().getDeclaredField("trainingId");
            trainingIdField.setAccessible(true);
            trainingIdField.set(training, trainingId);

            return training;
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }
}
