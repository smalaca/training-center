package com.smalaca.trainingcenter.opentrainingcatalogue.domain.offer;

import com.smalaca.trainingcenter.opentrainingcatalogue.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.price.Price;
import com.smalaca.trainingcenter.opentrainingcatalogue.domain.training.TrainingId;

record CreateOffer(TrainingId trainingId, ParticipantId participantId, Price price, String discountCode) { }
