package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.libraries.annotation.domaindrivendesign.AggregateRoot;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.CreateOfferCommand;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferFactory;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@AggregateRoot
public class Training {
    @SuppressFBWarnings("UWF_UNWRITTEN_FIELD")
    private TrainingId trainingId;
    @SuppressFBWarnings("URF_UNREAD_FIELD")
    private TrainingProgrammeCode trainingProgrammeCode;
    private Price price;

    Training(TrainingProgrammeCode trainingProgrammeCode, Price price) {
        this.trainingProgrammeCode = trainingProgrammeCode;
        this.price = price;
    }

    public Offer choose(ChooseTrainingCommand command) {
        CreateOfferCommand createOffer =  command.asCreateOfferCommand(trainingId, price);
        OfferFactory offerFactory = command.offerFactory();

        return offerFactory.create(createOffer);
    }
}
