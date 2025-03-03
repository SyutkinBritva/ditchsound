package ru.ditchsound.catalog.exception;

public class ReleaseNotFoundException extends RuntimeException {
    public ReleaseNotFoundException(Long id) {
        super(String.format("Релиз с id %s не найден", id));
    }
}
