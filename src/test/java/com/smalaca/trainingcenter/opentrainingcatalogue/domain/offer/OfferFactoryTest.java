package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingId;
import net.datafaker.Faker;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
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
    private static final String NO_DISCOUNT_CODE = null;

    private final DiscountService discountService = mock(DiscountService.class);
    private final Clock clock = mock(Clock.class);
    private final OfferFactory offerFactory = OfferFactory.create(discountService, clock);

    @BeforeEach
    void init() {
        given(clock.nowDate()).willReturn(LocalDate.of(2022, 4, 16));
        given(discountService.totalPriceFor(TRAINING_PRICE, DISCOUNT_CODE)).willReturn(PRICE_WITH_DISCOUNT);
    }

    @Test
    void shouldCreateOffer() {
        Offer actual = offerFactory.create(command(DISCOUNT_CODE));

        assertThat(actual)
                .hasParticipantId(PARTICIPANT_ID)
                .hasTrainingId(TRAINING_ID)
                .hasPrice(PRICE_WITH_DISCOUNT)
                .hasValidOfferNumberFor("20220416", PARTICIPANT_ID);
    }

    @Test
    void shouldCreateOfferWithoutDiscountWhenDiscountCodeNotGiven() {
        Offer actual = offerFactory.create(command(NO_DISCOUNT_CODE));

        assertThat(actual)
                .hasParticipantId(PARTICIPANT_ID)
                .hasTrainingId(TRAINING_ID)
                .hasPrice(TRAINING_PRICE)
                .hasValidOfferNumberFor("20220416", PARTICIPANT_ID);
    }

    private CreateOfferCommand command(String discountCode) {
        return new CreateOfferCommand(TRAINING_ID, PARTICIPANT_ID, TRAINING_PRICE, discountCode);
    }
}