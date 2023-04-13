package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;

public record ChooseTrainingCommand(ParticipantId participantId, String discountCode, DiscountService discountService) {
    Price priceFor(Price price) {
        if (discountCode != null) {
            return discountService.totalPriceFor(price, discountCode);
        }

        return price;
    }
}
