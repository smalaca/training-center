package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.libraries.annotation.domaindrivendesign.AggregateRoot;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.CreateOfferCommand;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferFactory;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.List;

@AggregateRoot
public class Training {
    @SuppressFBWarnings("UWF_UNWRITTEN_FIELD")
    private TrainingId trainingId;
    @SuppressFBWarnings("URF_UNREAD_FIELD")
    private TrainingProgrammeCode trainingProgrammeCode;
    private Price price;
    private int participantsLimit;
    private List<ParticipantId> participants;

    Training(TrainingProgrammeCode trainingProgrammeCode, Price price, int participantsLimit, List<ParticipantId> participants) {
        this.trainingProgrammeCode = trainingProgrammeCode;
        this.price = price;
        this.participantsLimit = participantsLimit;
        this.participants = participants;
    }

    public Offer choose(ChooseTrainingCommand command) {
        if (participantsLimitExceed()) {
            throw new TrainingLimitExceeded(trainingId);
        }

        CreateOfferCommand createOffer =  command.asCreateOfferCommand(trainingId, price);
        OfferFactory offerFactory = command.offerFactory();

        return offerFactory.create(createOffer);
    }

    private boolean participantsLimitExceed() {
        return participants.size() >= participantsLimit;
    }
}
