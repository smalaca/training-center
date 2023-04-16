package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.domaindrivendesign.DomainFactory;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.format.DateTimeFormatter;

@DomainFactory
class OfferNumberFactory {
    private static final String PREFIX = "OFFER";
    private static final int LENGTH = 9;
    private static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyyMMdd");

    private final Clock clock;

    OfferNumberFactory(Clock clock) {
        this.clock = clock;
    }

    OfferNumber createFor(CustomerId customerId) {
        return new OfferNumber(PREFIX + "-" + date() + "-" + customerId.id() + "-" + randomNumeric());
    }

    private String randomNumeric() {
        return RandomStringUtils.random(LENGTH, false, true);
    }

    private String date() {
        return clock.nowDate().format(YYYY_MM_DD);
    }
}
