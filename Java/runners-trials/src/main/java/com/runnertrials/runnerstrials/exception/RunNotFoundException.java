package com.runnertrials.runnerstrials.exception;

public class RunNotFoundException extends RuntimeException{
    public RunNotFoundException() {
        super("Value is not Found");
    }
}
