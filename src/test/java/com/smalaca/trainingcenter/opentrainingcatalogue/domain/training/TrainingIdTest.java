package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class TrainingIdTest {
    @Test
    void shouldFulfillEqualsContract() {
        EqualsVerifier.forClass(TrainingId.class).verify();
    }
}