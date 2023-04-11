package com.smalaca.trainingcenter.opentrainingcatalogue.application.training;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferRepository;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.GivenTraining;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingProgrammeCode;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferAssertion.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class TrainingApplicationServiceTest {
    private final TrainingRepository trainingRepository = mock(TrainingRepository.class);
    private final OfferRepository offerRepository = mock(OfferRepository.class);
    private final TrainingApplicationService service = new TrainingApplicationService(trainingRepository, offerRepository);

    @Test
    void shouldReturnOfferId() {
        ChooseTrainingCommand command = command();
        GivenTraining.givenTraining(trainingRepository)
                .withTrainingId(command.trainingId())
                .existing();
        UUID expected = UUID.randomUUID();
        given(offerRepository.save(any(Offer.class))).willReturn(expected);

        UUID actual = service.chooseTraining(command);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCreateOfferForTraining() {
        ChooseTrainingCommand command = command();
        GivenTraining.givenTraining(trainingRepository)
                .withTrainingProgrammeCode("DDD")
                .withTrainingId(command.trainingId())
                .existing();

        service.chooseTraining(command);

        assertThat(thenOfferCreated())
                .hasParticipantId(command.participantId())
                .hasTrainingId(command.trainingId())
                .hasTrainingProgrammeCode(new TrainingProgrammeCode("DDD"));
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