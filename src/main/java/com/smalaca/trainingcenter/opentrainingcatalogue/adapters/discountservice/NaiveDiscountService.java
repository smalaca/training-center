package com.smalaca.trainingcenter.opentrainingcatalogue.adapters.discountservice;

import com.smalaca.libraries.annotation.architecture.portandadapters.SecondaryAdapter;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.DiscountService;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;

@SecondaryAdapter
class NaiveDiscountService implements DiscountService {
    @Override
    public Price totalPriceFor(Price price, String discountCode) {
        return price;
    }
}
