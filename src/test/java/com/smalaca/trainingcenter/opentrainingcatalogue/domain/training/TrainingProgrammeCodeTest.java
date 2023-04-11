package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class TrainingProgrammeCodeTest {
    @Test
    void shouldFulfillEqualsContract() {
        EqualsVerifier.forClass(TrainingProgrammeCode.class).verify();
    }
}