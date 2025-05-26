package com.example.batchprocessing;

public class SkippableException extends Exception {

    public SkippableException(Exception e) {
        super("Demonstrate a skippable exception", e);
    }

    private static final long serialVersionUID = 1L;

}
