package org.labs.lab4;

public class BadValidationException extends Exception {

    public BadValidationException() {
    }

    public BadValidationException(String arg0) {
        super(arg0);
    }

    @Override
    public String toString() {
        return "BadValidationException []";
    }
}
