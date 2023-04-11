package com.smalaca.trainingcenter.opentrainingcatalogue.application.training;

import com.smalaca.libraries.annotation.architecture.portandadapters.PrimaryAdapter;
import com.smalaca.libraries.annotation.cqrs.Command;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferRepository;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.Training;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingRepository;

import java.util.UUID;

@PrimaryAdapter
public class TrainingApplicationService {
    private final TrainingRepository trainingRepository;
    private final OfferRepository offerRepository;

    public TrainingApplicationService(TrainingRepository trainingRepository, OfferRepository offerRepository) {
        this.trainingRepository = trainingRepository;
        this.offerRepository = offerRepository;
    }

    @Command
    public UUID chooseTraining(ChooseTrainingCommand command) {
        Training training = trainingRepository.findBy(command.trainingId());

        Offer offer = training.choose();

        return offerRepository.save(offer);
    }
}
