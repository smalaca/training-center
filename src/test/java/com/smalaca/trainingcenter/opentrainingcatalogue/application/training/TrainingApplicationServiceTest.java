package com.smalaca.trainingcenter.opentrainingcatalogue.application.training;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferRepository;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.DiscountService;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.GivenTrainingFactory;
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
    private static final UUID PARTICIPANT_ID = UUID.randomUUID();
    private static final UUID TRAINING_ID = UUID.randomUUID();
    private static final UUID OFFER_ID = UUID.randomUUID();
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
                .withTrainingId(TRAINING_ID)
                .withPrice(BigDecimal.valueOf(123.45))
                .existing();

        service.chooseTraining(command());

        assertThat(thenOfferCreated())
                .hasParticipantId(PARTICIPANT_ID)
                .hasTrainingId(TRAINING_ID)
                .hasPrice(Price.of(BigDecimal.valueOf(123.45)));
    }

    @Test
    void shouldHaveTheSamePriceAsTrainingWhenNoDiscountCodeSent() {
        given.training()
                .withTrainingId(TRAINING_ID)
                .withPrice(BigDecimal.valueOf(67.89))
                .existing();

        service.chooseTraining(command());

        assertThat(thenOfferCreated())
                .hasPrice(Price.of(BigDecimal.valueOf(67.89)));
    }

    @Test
    void shouldCreateOfferWithDiscount() {
        String discountCode = faker.lorem().word();
        Price regular = Price.of(BigDecimal.valueOf(34.89));
        Price withDiscount = Price.of(BigDecimal.valueOf(20.00));
        given(discountService.totalPriceFor(regular, discountCode)).willReturn(withDiscount);
        given.training()
                .withTrainingId(TRAINING_ID)
                .withPrice(BigDecimal.valueOf(34.89))
                .existing();
        ChooseTrainingCommand command = new ChooseTrainingCommand(TRAINING_ID, PARTICIPANT_ID, discountCode);

        service.chooseTraining(command);

        assertThat(thenOfferCreated())
                .hasPrice(withDiscount);
    }

    private Offer thenOfferCreated() {
        ArgumentCaptor<Offer> captor = ArgumentCaptor.forClass(Offer.class);
        then(offerRepository).should().save(captor.capture());
        return captor.getValue();
    }

    private ChooseTrainingCommand command() {
        return new ChooseTrainingCommand(TRAINING_ID, PARTICIPANT_ID, NO_DISCOUNT_CODE);
    }
}