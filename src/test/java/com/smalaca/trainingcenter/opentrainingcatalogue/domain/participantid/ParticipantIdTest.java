package com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class ParticipantIdTest {
    @Test
    void shouldFulfillEqualsContract() {
        EqualsVerifier.forClass(ParticipantId.class).verify();
    }
}