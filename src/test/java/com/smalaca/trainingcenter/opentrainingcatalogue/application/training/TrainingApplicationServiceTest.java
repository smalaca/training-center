package com.smalaca.trainingcenter.opentrainingcatalogue.application.training;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferRepository;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.DiscountService;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.GivenTrainingFactory;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.UUID;

import static com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferAssertion.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class TrainingApplicationServiceTest {
    private static final UUID PARTICIPANT_UUID = UUID.randomUUID();
    private static final ParticipantId PARTICIPANT_ID = ParticipantId.of(PARTICIPANT_UUID);
    private static final UUID TRAINING_UUID = UUID.randomUUID();
    private static final TrainingId TRAINING_ID = TrainingId.of(TRAINING_UUID);
    private static final UUID OFFER_UUID = UUID.randomUUID();
    private static final String NO_DISCOUNT_CODE = null;

    private final DiscountService discountService = mock(DiscountService.class);
    private final TrainingRepository trainingRepository = mock(TrainingRepository.class);
    private final OfferRepository offerRepository = mock(OfferRepository.class);
    private final TrainingApplicationService service = new TrainingApplicationService(trainingRepository, offerRepository, discountService);

    private final Faker faker = new Faker();
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
        given(offerRepository.save(any(Offer.class))).willReturn(OFFER_UUID);
    }

    @Test
    void shouldReturnOfferId() {
        given.training()
                .withTrainingId(TRAINING_ID)
                .existing();

        UUID actual = service.chooseTraining(command());

        assertThat(actual).isEqualTo(OFFER_UUID);
    }

    @Test
    void shouldCreateOfferForTraining() {
        given.training()
                .withTrainingId(TRAINING_ID)
                .withPrice(priceOf(123.45))
                .existing();

        service.chooseTraining(command());

        assertThat(createdOffer())
                .hasParticipantId(PARTICIPANT_ID)
                .hasTrainingId(TRAINING_ID)
                .hasPrice(priceOf(123.45));
    }

    @Test
    void shouldHaveTheSamePriceAsTrainingWhenNoDiscountCodeSent() {
        given.training()
                .withTrainingId(TRAINING_ID)
                .withPrice(priceOf(67.89))
                .existing();

        service.chooseTraining(commandWithDiscountCode(NO_DISCOUNT_CODE));

        assertThat(createdOffer()).hasPrice(priceOf(67.89));
    }

    @Test
    void shouldCreateOfferWithDiscount() {
        String discountCode = faker.lorem().word();
        given(discountService.totalPriceFor(priceOf(34.89), discountCode)).willReturn(priceOf(20.00));
        given.training()
                .withTrainingId(TRAINING_ID)
                .withPrice(priceOf(34.89))
                .existing();

        service.chooseTraining(commandWithDiscountCode(discountCode));

        assertThat(createdOffer()).hasPrice(priceOf(20.00));
    }

    private Price priceOf(double value) {
        return Price.of(BigDecimal.valueOf(value));
    }

    private Offer createdOffer() {
        ArgumentCaptor<Offer> captor = ArgumentCaptor.forClass(Offer.class);
        then(offerRepository).should().save(captor.capture());
        return captor.getValue();
    }

    private ChooseTrainingCommand command() {
        return commandWithDiscountCode(NO_DISCOUNT_CODE);
    }

    private ChooseTrainingCommand commandWithDiscountCode(String discountCode) {
        return new ChooseTrainingCommand(TRAINING_UUID, PARTICIPANT_UUID, discountCode);
    }
}