package com.smalaca.trainingcenter.opentrainingcatalogue.application.training;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferRepository;

import java.util.UUID;

public class TrainingApplicationService {
    private final OfferRepository offerRepository;

    public TrainingApplicationService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public UUID chooseTraining() {
        return offerRepository.save(new Offer());
    }
}
