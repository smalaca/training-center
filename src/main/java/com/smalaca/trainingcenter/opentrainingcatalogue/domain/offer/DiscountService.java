package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.libraries.annotation.architecture.portandadapters.SecondaryPort;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;

@SecondaryPort
public interface DiscountService {
    Price totalPriceFor(Price price, String discountCode);
}
