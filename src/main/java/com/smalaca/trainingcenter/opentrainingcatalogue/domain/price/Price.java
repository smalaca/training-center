package com.smalaca.trainingcenter.opentrainingcatalogue.domain.price;

import com.smalaca.libraries.annotation.domaindrivendesign.DomainFactory;
import com.smalaca.libraries.annotation.domaindrivendesign.ValueObject;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;

@ValueObject
public final class Price {
    private final BigDecimal value;

    private Price(BigDecimal value) {
        this.value = value;
    }

    @DomainFactory
    public static Price of(BigDecimal value) {
        return new Price(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        return
                (value == null && price.value == null) ||
                (value != null && price.value != null && value.compareTo(price.value) == 0);
    }

    @Override
    public int hashCode() {
        BigDecimal object = value == null ? null : value.stripTrailingZeros();
        return new HashCodeBuilder(17, 37)
                .append(object)
                .toHashCode();
    }
}
