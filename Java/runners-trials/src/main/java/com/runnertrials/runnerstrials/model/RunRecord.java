package com.runnertrials.runnerstrials.model;

public record RunRecord(
        String name,
        int distance,
        int time,
        int pace,
        int id,
        Location location
) {}
