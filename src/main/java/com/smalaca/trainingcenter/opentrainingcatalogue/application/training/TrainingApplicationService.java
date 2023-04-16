package com.smalaca.trainingcenter.opentrainingcatalogue.application.training;

import com.smalaca.libraries.annotation.architecture.portandadapters.PrimaryPort;
import com.smalaca.libraries.annotation.cqrs.Command;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Clock;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.DiscountService;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferFactory;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferRepository;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.ChooseTrainingCommand;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.Training;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingRepository;

import java.util.UUID;

@PrimaryPort
public final class TrainingApplicationService {
    private final TrainingRepository trainingRepository;
    private final OfferRepository offerRepository;
    private final OfferFactory offerFactory;

    private TrainingApplicationService(
            TrainingRepository trainingRepository, OfferRepository offerRepository, OfferFactory offerFactory) {
        this.trainingRepository = trainingRepository;
        this.offerRepository = offerRepository;
        this.offerFactory = offerFactory;
    }

    static TrainingApplicationService create(
            TrainingRepository trainingRepository, OfferRepository offerRepository, DiscountService discountService, Clock clock) {
        OfferFactory offerFactory = OfferFactory.create(discountService, clock);
        return new TrainingApplicationService(trainingRepository, offerRepository, offerFactory);
    }

    @Command
    public UUID chooseTraining(ChooseTrainingApplicationCommand command) {
        TrainingId trainingId = TrainingId.of(command.trainingId());
        Training training = trainingRepository.findBy(trainingId);
        ChooseTrainingCommand chooseTrainingCommand = asCommand(command);

        Offer offer = training.choose(chooseTrainingCommand);

        return offerRepository.save(offer).id();
    }

    private ChooseTrainingCommand asCommand(ChooseTrainingApplicationCommand command) {
        return new ChooseTrainingCommand(
                ParticipantId.of(command.participantId()), command.discountCode(), offerFactory);
    }
}
