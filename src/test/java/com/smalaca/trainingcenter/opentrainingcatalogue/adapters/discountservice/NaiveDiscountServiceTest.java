package com.smalaca.trainingcenter.opentrainingcatalogue.adapters.discountservice;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.DiscountService;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class NaiveDiscountServiceTest {
    private static final Faker FAKER = new Faker();
    private final DiscountService discountService = new NaiveDiscountService();

    @Test
    void shouldReturnPrice() {
        Price actual = discountService.totalPriceFor(priceOf(123.45), discountCode());

        assertThat(actual).isEqualTo(priceOf(123.45));
    }

    private Price priceOf(double price) {
        return Price.of(BigDecimal.valueOf(price));
    }

    private String discountCode() {
        return FAKER.lorem().word();
    }
}