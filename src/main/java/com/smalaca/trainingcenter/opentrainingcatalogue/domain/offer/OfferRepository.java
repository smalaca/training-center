package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.cqrs.CommandRepository;
import com.smalaca.libraries.annotation.ddd.DomainRepository;

import java.util.UUID;

@DomainRepository
@CommandRepository
public interface OfferRepository {
    UUID save(Offer offer);
}
