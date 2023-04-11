package com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantId;

import com.smalaca.libraries.annotation.domaindrivendesign.ValueObject;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@ValueObject
@EqualsAndHashCode
public class ParticipantId {
    private final UUID value;

    private ParticipantId(UUID value) {
        this.value = value;
    }

    public static ParticipantId of(UUID participantId) {
        return new ParticipantId(participantId);
    }
}
