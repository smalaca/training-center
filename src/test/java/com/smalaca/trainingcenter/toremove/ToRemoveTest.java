package com.smalaca.trainingcenter.toremove;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToRemoveTest {
    @Test
    void shouldReturnTrue() {
        ToRemove toRemove = new ToRemove(true);

        boolean actual = toRemove.toRemove();

        assertThat(actual).isTrue();
    }

    @Test
    void shouldReturnFalse() {
        ToRemove toRemove = new ToRemove(false);

        boolean actual = toRemove.toRemove();

        assertThat(actual).isFalse();
    }
}
