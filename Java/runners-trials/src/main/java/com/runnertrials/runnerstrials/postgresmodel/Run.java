package com.runnertrials.runnerstrials.postgresmodel;

import com.runnertrials.runnerstrials.model.Location;

public record Run(
        String name,
        String description,
        Sentiment sentiment,
        Integer distance,
        Integer timeTaken,
        Location location
) {
}
