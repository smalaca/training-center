package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;

public interface DiscountService {
    Price totalPriceFor(Price price, String discountCode);
}
