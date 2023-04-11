package com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid;

import com.smalaca.libraries.annotation.domaindrivendesign.DomainFactory;
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

    @DomainFactory
    public static ParticipantId of(UUID participantId) {
        return new ParticipantId(participantId);
    }
}
