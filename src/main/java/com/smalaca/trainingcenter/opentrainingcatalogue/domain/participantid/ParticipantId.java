package com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid;

import com.smalaca.libraries.annotation.domaindrivendesign.Factory;
import com.smalaca.libraries.annotation.domaindrivendesign.ValueObject;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@ValueObject
@EqualsAndHashCode
public final class ParticipantId {
    private final UUID value;

    private ParticipantId(UUID value) {
        this.value = value;
    }

    @Factory
    public static ParticipantId of(UUID participantId) {
        return new ParticipantId(participantId);
    }

    public UUID id() {
        return value;
    }
}
