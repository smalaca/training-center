package com.smalaca.trainingcenter.opentrainingcatalogue.adapters.clock.local;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Clock;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LocalClockTest {
    private final Clock clock = new LocalClock();

    @Test
    void shouldCreateNowDate() {
        LocalDate now = LocalDate.now();

        LocalDate actual = clock.nowDate();

        assertThat(actual).isEqualTo(now);
    }
}