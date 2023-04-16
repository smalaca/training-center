package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.architecture.portandadapters.SecondaryPort;

import java.time.LocalDate;

@SecondaryPort
public interface Clock {
    LocalDate nowDate();
}
