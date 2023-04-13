package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.architecture.portandadapters.SecondaryPort;
import com.smalaca.libraries.annotation.domaindrivendesign.DomainRepository;

@DomainRepository
@SecondaryPort
public interface OfferRepository {
    OfferId save(Offer offer);
}
