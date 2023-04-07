package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import java.lang.reflect.Field;
import java.util.UUID;

public class TrainingTestFactory {
    public Training create(UUID trainingId, TrainingProgrammeCode code) {
        return withTrainingId(new Training(code), trainingId);
    }

    private Training withTrainingId(Training training, UUID trainingId)  {
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
