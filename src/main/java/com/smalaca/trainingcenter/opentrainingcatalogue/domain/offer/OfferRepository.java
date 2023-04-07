package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.architecture.portandadapters.SecondaryPort;
import com.smalaca.libraries.annotation.cqrs.CommandRepository;
import com.smalaca.libraries.annotation.domaindrivendesign.DomainRepository;

import java.util.UUID;

@DomainRepository
@CommandRepository
@SecondaryPort
public interface OfferRepository {
    UUID save(Offer offer);
}
