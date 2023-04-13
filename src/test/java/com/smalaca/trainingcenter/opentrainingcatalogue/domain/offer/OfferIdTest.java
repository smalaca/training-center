package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class OfferIdTest {
    @Test
    void shouldFulfillEqualsContract() {
        EqualsVerifier.forClass(OfferId.class).verify();
    }
}