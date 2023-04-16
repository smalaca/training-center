package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.domaindrivendesign.DomainFactory;

@DomainFactory
class OfferFactory {
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
        return Offer.builder()
                .with(command.trainingId())
                .with(command.participantId())
                .with(discountService.totalPriceFor(command.price(), command.discountCode()))
                .with(offerNumberFactory.createFor(command.participantId()))
                .build();
    }
}
