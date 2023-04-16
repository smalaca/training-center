package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class OfferNumberTest {
    private final Clock clock = mock(Clock.class);
    private final OfferNumberFactory factory = new OfferNumberFactory(clock);

    @TestFactory
    Stream<DynamicTest> shouldCreateOfferNumberWhenDayAndMonthAreTwoDigitNumbers() {
        return IntStream.range(0, 10).mapToObj(index -> {
            LocalDate date = givenNowFor(twoDigitDay(), twoDigitMonth());
            ParticipantId participantId = participantId();

            return dynamicTest(testName(date, participantId), () -> {
                OfferNumber actual = factory.createFor(participantId);

                assertThat(actual.number())
                        .matches("OFFER-" + date.getYear() + date.getMonthValue() + date.getDayOfMonth() + "-" + participantId.id() + "-[0-9]{9}");
            });
        });
    }

    @TestFactory
    Stream<DynamicTest> shouldCreateOfferNumberWhenMonthIsOneDigitNumber() {
        return IntStream.range(0, 10).mapToObj(index -> {
            LocalDate date = givenNowFor(twoDigitDay(), oneDigit());
            ParticipantId participantId = participantId();

            return dynamicTest(testName(date, participantId), () -> {
                OfferNumber actual = factory.createFor(participantId);

                assertThat(actual.number())
                        .matches("OFFER-" + date.getYear() + "0" + date.getMonthValue() + date.getDayOfMonth() + "-" + participantId.id() + "-[0-9]{9}");
            });
        });
    }

    @TestFactory
    Stream<DynamicTest> shouldCreateOfferNumberWhenDayIsOneDigitNumber() {
        return IntStream.range(0, 10).mapToObj(index -> {
            LocalDate date = givenNowFor(oneDigit(), twoDigitMonth());
            ParticipantId participantId = participantId();

            return dynamicTest(testName(date, participantId), () -> {
                OfferNumber actual = factory.createFor(participantId);

                assertThat(actual.number())
                        .matches("OFFER-" + date.getYear() + date.getMonthValue() + "0" + date.getDayOfMonth() + "-" + participantId.id() + "-[0-9]{9}");
            });
        });
    }

    @TestFactory
    Stream<DynamicTest> shouldCreateOfferNumberWhenMonthAndDayAreOneDigitNumbers() {
        return IntStream.range(0, 10).mapToObj(index -> {
            LocalDate date = givenNowFor(oneDigit(), oneDigit());
            ParticipantId participantId = participantId();

            return dynamicTest(testName(date, participantId), () -> {
                OfferNumber actual = factory.createFor(participantId);

                assertThat(actual.number())
                        .matches("OFFER-" + date.getYear() + "0" + date.getMonthValue() + "0" + date.getDayOfMonth() + "-" + participantId.id() + "-[0-9]{9}");
            });
        });
    }

    private ParticipantId participantId() {
        return ParticipantId.of(UUID.randomUUID());
    }

    private LocalDate givenNowFor(int day, int month) {
        int year = RandomUtils.nextInt(1900, 2100);
        LocalDate randomDate = LocalDate.of(year, month, day);
        given(clock.nowDate()).willReturn(randomDate);

        return randomDate;
    }

    private int oneDigit() {
        return RandomUtils.nextInt(1, 10);
    }

    private int twoDigitDay() {
        return RandomUtils.nextInt(10, 28);
    }

    private int twoDigitMonth() {
        return RandomUtils.nextInt(10, 12);
    }

    private String testName(LocalDate date, ParticipantId participantId) {
        return "For date: " + date + " and customer id: " + participantId.id();
    }
}