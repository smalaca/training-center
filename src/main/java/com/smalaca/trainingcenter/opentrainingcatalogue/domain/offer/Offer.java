package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.domaindrivendesign.AggregateRoot;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingId;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@AggregateRoot
@SuppressFBWarnings("URF_UNREAD_FIELD")
public final class Offer {
    private TrainingId trainingId;
    private ParticipantId participantId;
    private Price price;
    private OfferNumber offerNumber;

    Offer(OfferFactory.OfferData data) {
        trainingId = data.getTrainingId();
        participantId = data.getParticipantId();
        price = data.getPrice();
        offerNumber = data.getOfferNumber();
    }
}
