package ru.ditchsound.catalog.exception;

public class ReleaseNotFoundException extends RuntimeException {

    public ReleaseNotFoundException(Long id) {
        super(String.format("Релиз с id %s не найден", id));
    }

    public ReleaseNotFoundException(String bandName, String releaseName) {
        super(String.format("Релиз группы %s с названием %s не найден", bandName, releaseName));
    }
}
