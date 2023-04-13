package com.smalaca.trainingcenter.opentrainingcatalogue.domain.training;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;

public interface DiscountService {
    Price totalPriceFor(Price price, String discountCode);
}
