package com.smalaca.trainingcenter.opentrainingcatalogue.application.training;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.Offer;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer.OfferRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TrainingApplicationServiceTest {
    private final OfferRepository offerRepository = mock(OfferRepository.class);
    private final TrainingApplicationService service = new TrainingApplicationService(offerRepository);

    @Test
    void shouldReturnOfferId() {
        UUID expected = UUID.randomUUID();
        given(offerRepository.save(any(Offer.class))).willReturn(expected);

        UUID actual = service.chooseTraining();

        assertThat(actual).isEqualTo(expected);
    }
}