package com.smalaca.trainingcenter.opentrainingcatalogue.application.training;

import java.util.UUID;

record ChooseTrainingApplicationCommand(UUID trainingId, UUID participantId, String discountCode) {
}
