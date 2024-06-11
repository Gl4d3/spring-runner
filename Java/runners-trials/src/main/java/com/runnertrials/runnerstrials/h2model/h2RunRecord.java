package com.runnertrials.runnerstrials.h2model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

//This record is used to store a single h2RunRecord object. It is used to store the data of a single run record. (A ROW)
public record h2RunRecord(
        Integer id,
        @NotEmpty
        String title,
        @Positive
        int distance,
        @Positive
        int time,
        @Positive
        int pace,
        LocalDate date,
        Location location
) {
    public h2RunRecord {
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than 0");
        }
        if (time <= 0) {
            throw new IllegalArgumentException("Time must be greater than 0");
        }
        if (pace <= 0) {
            throw new IllegalArgumentException("Pace must be greater than 0");
        }
    }
}
