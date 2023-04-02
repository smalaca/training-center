package com.smalaca.trainingcenter.toremove;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToRemoveTest {
    @Test
    void shouldReturnTrue() {
        ToRemove toRemove = new ToRemove("TEST", true);

        boolean actual = toRemove.toRemove();

        assertThat(actual).isTrue();
    }

    @Test
    void shouldReturnFalse() {
        ToRemove toRemove = new ToRemove("TEST", false);

        boolean actual = toRemove.toRemove();

        assertThat(actual).isFalse();
    }

    @Test
    void shouldReturnWhatShouldBeRemoved() {
        ToRemove toRemove = new ToRemove("TEST", false);

        String actual = toRemove.what();

        assertThat(actual).isEqualTo("TEST");
    }
}
