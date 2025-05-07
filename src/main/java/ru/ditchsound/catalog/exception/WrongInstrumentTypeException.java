package ru.ditchsound.catalog.exception;

public class WrongInstrumentTypeException extends RuntimeException {
    public WrongInstrumentTypeException(String message) {
        super(message);
    }
}
