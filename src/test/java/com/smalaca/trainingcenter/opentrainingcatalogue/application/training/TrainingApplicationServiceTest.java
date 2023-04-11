package com.smalaca.trainingcenter.opentrainingcatalogue.application.training;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferAssertion;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferRepository;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.Training;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingProgrammeCode;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingRepository;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingTestFactory;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class TrainingApplicationServiceTest {
    private final TrainingRepository trainingRepository = mock(TrainingRepository.class);
    private final OfferRepository offerRepository = mock(OfferRepository.class);
    private final TrainingApplicationService service = new TrainingApplicationService(trainingRepository, offerRepository);

    private final TrainingTestFactory trainingTestFactory = new TrainingTestFactory();

    @Test
    void shouldReturnOfferId() {
        ChooseTrainingCommand command = command();
        givenTraining(command, new TrainingProgrammeCode("TDD"));
        UUID expected = UUID.randomUUID();
        given(offerRepository.save(any(Offer.class))).willReturn(expected);

        UUID actual = service.chooseTraining(command);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCreateOfferForTraining() {
        ChooseTrainingCommand command = command();
        TrainingProgrammeCode code = new TrainingProgrammeCode("DDD");
        givenTraining(command, code);

        service.chooseTraining(command);

        OfferAssertion.assertThat(thenOfferCreated())
                .hasParticipantId(command.participantId())
                .hasTrainingId(command.trainingId())
                .hasTrainingProgrammeCode(code);
    }

    private void givenTraining(ChooseTrainingCommand command, TrainingProgrammeCode code) {
        TrainingId trainingId = TrainingId.of(command.trainingId());
        Training training = trainingTestFactory.create(trainingId, code);
        given(trainingRepository.findBy(trainingId)).willReturn(training);
    }

    private Offer thenOfferCreated() {
        ArgumentCaptor<Offer> captor = ArgumentCaptor.forClass(Offer.class);
        then(offerRepository).should().save(captor.capture());
        return captor.getValue();
    }

    private ChooseTrainingCommand command() {
        return new ChooseTrainingCommand(UUID.randomUUID(), UUID.randomUUID());
    }
}