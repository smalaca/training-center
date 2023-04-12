package com.smalaca.trainingcenter.opentrainingcatalogue.application.training;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferRepository;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.GivenTrainingFactory;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingProgrammeCode;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingRepository;
import org.junit.jupiter.api.BeforeEach;
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
    private static final UUID PARTICIPANT_ID = UUID.randomUUID();
    private static final UUID TRAINING_ID = UUID.randomUUID();
    private static final UUID OFFER_ID = UUID.randomUUID();

    private final TrainingRepository trainingRepository = mock(TrainingRepository.class);
    private final OfferRepository offerRepository = mock(OfferRepository.class);
    private final TrainingApplicationService service = new TrainingApplicationService(trainingRepository, offerRepository);

    private GivenTrainingFactory given;

    @BeforeEach
    void init() {
        initGivenTrainingFactory();
        givenOfferId();
    }

    private void initGivenTrainingFactory() {
        given = new GivenTrainingFactory(trainingRepository);
    }

    private void givenOfferId() {
        given(offerRepository.save(any(Offer.class))).willReturn(OFFER_ID);
    }

    @Test
    void shouldReturnOfferId() {
        given.training()
                .withTrainingId(TRAINING_ID)
                .existing();

        UUID actual = service.chooseTraining(command());

        assertThat(actual).isEqualTo(OFFER_ID);
    }

    @Test
    void shouldCreateOfferForTraining() {
        given.training()
                .withTrainingProgrammeCode("DDD")
                .withTrainingId(TRAINING_ID)
                .existing();

        service.chooseTraining(command());

        assertThat(thenOfferCreated())
                .hasParticipantId(PARTICIPANT_ID)
                .hasTrainingId(TRAINING_ID)
                .hasTrainingProgrammeCode(TrainingProgrammeCode.of("DDD"));
    }

    private Offer thenOfferCreated() {
        ArgumentCaptor<Offer> captor = ArgumentCaptor.forClass(Offer.class);
        then(offerRepository).should().save(captor.capture());
        return captor.getValue();
    }

    private ChooseTrainingCommand command() {
        return new ChooseTrainingCommand(PARTICIPANT_ID, TRAINING_ID);
    }
}