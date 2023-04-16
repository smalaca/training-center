package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingId;
import net.datafaker.Faker;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferAssertion.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class OfferFactoryTest {
    private static final Faker FAKER = new Faker();
    private static final UUID PARTICIPANT_UUID = UUID.randomUUID();
    private static final ParticipantId PARTICIPANT_ID = ParticipantId.of(PARTICIPANT_UUID);
    private static final UUID TRAINING_UUID = UUID.randomUUID();
    private static final TrainingId TRAINING_ID = TrainingId.of(TRAINING_UUID);
    private static final Price TRAINING_PRICE = Price.of(BigDecimal.valueOf(RandomUtils.nextDouble(10, 1000)));
    private static final Price PRICE_WITH_DISCOUNT = Price.of(BigDecimal.valueOf(RandomUtils.nextDouble(10, 1000)));
    private static final String DISCOUNT_CODE = FAKER.lorem().word();

    private final DiscountService discountService = mock(DiscountService.class);
    private final Clock clock = mock(Clock.class);
    private final OfferFactory offerFactory = OfferFactory.create(discountService, clock);

    @Test
    void shouldCreateOfferForTraining() {
        given(clock.nowDate()).willReturn(LocalDate.of(2022, 4, 16));
        given(discountService.totalPriceFor(TRAINING_PRICE, DISCOUNT_CODE)).willReturn(PRICE_WITH_DISCOUNT);

        Offer actual = offerFactory.create(command());

        assertThat(actual)
                .hasParticipantId(PARTICIPANT_ID)
                .hasTrainingId(TRAINING_ID)
                .hasPrice(PRICE_WITH_DISCOUNT)
                .hasValidOfferNumberFor("20220416", PARTICIPANT_ID);
    }

    private CreateOffer command() {
        return new CreateOffer(TRAINING_ID, PARTICIPANT_ID, TRAINING_PRICE, DISCOUNT_CODE);
    }
}