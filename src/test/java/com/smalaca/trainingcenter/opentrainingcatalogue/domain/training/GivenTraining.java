package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import net.datafaker.Faker;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

public class GivenTraining {
    private final TrainingRepository trainingRepository;

    private TrainingId trainingId;
    private TrainingProgrammeCode trainingProgrammeCode;
    private Price price;

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

    public GivenTraining withPrice(BigDecimal price) {
        this.price = Price.of(price);
        return this;
    }

    public void existing() {
        Training training = training();
        given(trainingRepository.findBy(trainingId)).willReturn(training);
    }

    private Training training() {
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
