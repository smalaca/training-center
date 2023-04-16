package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.libraries.annotation.architecture.portandadapters.SecondaryPort;
import com.smalaca.libraries.annotation.domaindrivendesign.Repository;

@Repository
@SecondaryPort
public interface TrainingRepository {
    Training findBy(TrainingId trainingId);
}
