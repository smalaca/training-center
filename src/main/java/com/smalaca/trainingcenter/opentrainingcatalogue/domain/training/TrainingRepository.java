package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.libraries.annotation.architecture.portandadapters.SecondaryPort;
import com.smalaca.libraries.annotation.cqrs.CommandRepository;
import com.smalaca.libraries.annotation.domaindrivendesign.DomainRepository;

import java.util.UUID;

@DomainRepository
@CommandRepository
@SecondaryPort
public interface TrainingRepository {
    Training findBy(UUID trainingId);
}
