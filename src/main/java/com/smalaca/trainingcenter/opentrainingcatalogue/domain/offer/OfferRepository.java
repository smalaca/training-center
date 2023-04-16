package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.architecture.portandadapters.SecondaryPort;
import com.smalaca.libraries.annotation.domaindrivendesign.Repository;

@Repository
@SecondaryPort
public interface OfferRepository {
    OfferId save(Offer offer);
}
