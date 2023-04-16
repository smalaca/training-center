package com.smalaca.trainingcenter.opentrainingcatalogue.adapters.clock.local;

import com.smalaca.libraries.annotation.architecture.portandadapters.SecondaryAdapter;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Clock;

import java.time.LocalDate;

@SecondaryAdapter
class LocalClock implements Clock {
    @Override
    public LocalDate nowDate() {
        return LocalDate.now();
    }
}
