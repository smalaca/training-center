package com.smalaca.trainingcenter.opentrainingcatalogue.application.training;

import java.util.UUID;

record ChooseTrainingCommand(UUID trainingId, UUID participantId, String discountCode) {
}
