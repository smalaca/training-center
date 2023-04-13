package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import net.datafaker.Faker;

import java.lang.reflect.Field;

import static org.mockito.BDDMockito.given;

public class GivenTraining {
    private final TrainingRepository trainingRepository;
    private final Faker faker;

    private TrainingId trainingId;
    private Price price;

    GivenTraining(TrainingRepository trainingRepository, Faker faker) {
        this.trainingRepository = trainingRepository;
        this.faker = faker;
    }

    public GivenTraining withTrainingId(TrainingId trainingId) {
        this.trainingId = trainingId;
        return this;
    }

    public GivenTraining withPrice(Price price) {
        this.price = price;
        return this;
    }

    public void existing() {
        Training training = training();
        given(trainingRepository.findBy(trainingId)).willReturn(training);
    }

    private Training training() {
        TrainingProgrammeCode trainingProgrammeCode = TrainingProgrammeCode.of(faker.lorem().word());
        Training training = new Training(trainingProgrammeCode, price);

        return withTrainingId(training);
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
