package com.smalaca.trainingcenter.opentrainingcatalogue.domain.price;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class PriceTest {
    @Test
    void shouldFulfillEqualsContract() {
        EqualsVerifier.forClass(Price.class).verify();
    }
}