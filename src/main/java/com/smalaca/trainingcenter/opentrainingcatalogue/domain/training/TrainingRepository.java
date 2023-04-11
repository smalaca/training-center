package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.libraries.annotation.architecture.portandadapters.SecondaryPort;
import com.smalaca.libraries.annotation.domaindrivendesign.DomainRepository;

@DomainRepository
@SecondaryPort
public interface TrainingRepository {
    Training findBy(TrainingId trainingId);
}
