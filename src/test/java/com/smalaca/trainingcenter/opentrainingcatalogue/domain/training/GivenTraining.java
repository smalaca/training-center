package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import net.datafaker.Faker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

public class GivenTraining {
    private final TrainingRepository trainingRepository;
    private final Faker faker;

    private final TrainingId trainingId;
    private final List<ParticipantId> participants = new ArrayList<>();
    private Price price;
    private int participantsLimit = 13;

    GivenTraining(TrainingRepository trainingRepository, TrainingId trainingId, Faker faker) {
        this.trainingRepository = trainingRepository;
        this.trainingId = trainingId;
        this.faker = faker;
    }

    public GivenTraining withPrice(Price price) {
        this.price = price;
        return this;
    }

    public GivenTraining withParticipantsLimit(int participantsLimit) {
        this.participantsLimit = participantsLimit;
        return this;
    }

    public GivenTraining withParticipant(ParticipantId participantId) {
        participants.add(participantId);
        return this;
    }

    public void existing() {
        Training training = training();
        given(trainingRepository.findBy(trainingId)).willReturn(training);
    }

    private Training training() {
        TrainingProgrammeCode trainingProgrammeCode = TrainingProgrammeCode.of(faker.lorem().word());
        Training training = new Training(trainingProgrammeCode, price, participantsLimit, participants);

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
