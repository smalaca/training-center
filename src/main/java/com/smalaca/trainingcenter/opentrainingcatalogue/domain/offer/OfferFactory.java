package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.domaindrivendesign.DomainFactory;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingId;
import lombok.AccessLevel;
import lombok.Getter;

@DomainFactory
final class OfferFactory {
    private final DiscountService discountService;
    private final OfferNumberFactory offerNumberFactory;

    private OfferFactory(DiscountService discountService, OfferNumberFactory offerNumberFactory) {
        this.discountService = discountService;
        this.offerNumberFactory = offerNumberFactory;
    }

    static OfferFactory create(DiscountService discountService, Clock clock) {
        return new OfferFactory(discountService, new OfferNumberFactory(clock));
    }

    Offer create(CreateOffer command) {
        OfferData offerData = new OfferData();
        offerData.participantId = command.participantId();
        offerData.trainingId = command.trainingId();
        offerData.price = discountService.totalPriceFor(command.price(), command.discountCode());
        offerData.offerNumber = offerNumberFactory.createFor(command.participantId());

        return new Offer(offerData);
    }

    @Getter(AccessLevel.PACKAGE)
    class OfferData {
        private ParticipantId participantId;
        private TrainingId trainingId;
        private Price price;
        private OfferNumber offerNumber;

        private OfferData() { }
    }
}
