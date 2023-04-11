package com.smalaca.trainingcenter.opentrainingcatalogue.application.training;

import java.util.UUID;

record ChooseTrainingCommand(UUID participantId, UUID trainingId) {
}
