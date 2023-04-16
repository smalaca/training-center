package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.domaindrivendesign.Factory;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.format.DateTimeFormatter;

@Factory
class OfferNumberFactory {
    private static final String PREFIX = "OFFER";
    private static final int LENGTH = 9;
    private static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyyMMdd");

    private final Clock clock;

    OfferNumberFactory(Clock clock) {
        this.clock = clock;
    }

    OfferNumber createFor(ParticipantId participantId) {
        return new OfferNumber(PREFIX + "-" + date() + "-" + participantId.id() + "-" + randomNumeric());
    }

    private String randomNumeric() {
        return RandomStringUtils.random(LENGTH, false, true);
    }

    private String date() {
        return clock.nowDate().format(YYYY_MM_DD);
    }
}
