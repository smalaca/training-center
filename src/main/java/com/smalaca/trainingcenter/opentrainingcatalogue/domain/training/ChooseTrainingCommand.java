package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.CreateOfferCommand;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferFactory;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;

public record ChooseTrainingCommand(ParticipantId participantId, String discountCode, OfferFactory offerFactory) {
    CreateOfferCommand asCreateOfferCommand(TrainingId trainingId, Price price) {
        return new CreateOfferCommand(trainingId, participantId, price, discountCode);
    }
}
