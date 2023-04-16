package com.smalaca.trainingcenter.opentrainingcatalogue.domain.price;

import com.smalaca.libraries.annotation.domaindrivendesign.Factory;
import com.smalaca.libraries.annotation.domaindrivendesign.ValueObject;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.Optional;

@ValueObject
public final class Price {
    private final BigDecimal value;

    private Price(BigDecimal value) {
        this.value = value;
    }

    @Factory
    public static Price of(BigDecimal value) {
        return new Price(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Price price = (Price) o;

        if (hasNoPrice() && price.hasNoPrice()) {
            return true;
        }

        return hasSamePriceAs(price);
    }

    private boolean hasNoPrice() {
        return value == null;
    }

    private boolean hasSamePriceAs(Price price) {
        return hasPrice() && price.hasPrice() && value.compareTo(price.value) == 0;
    }

    private boolean hasPrice() {
        return value != null;
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(Optional.ofNullable(value).map(BigDecimal::stripTrailingZeros))
                .toHashCode();
    }
}
