package ru.ditchsound.catalog.exception;

import ru.ditchsound.catalog.enums.InstrumentTypeEnum;

public class InstrumentNotFoundException extends RuntimeException {
    public InstrumentNotFoundException(Long id) {
        super(String.format("Инструмент с id %s не найден", id));
    }
    public InstrumentNotFoundException(InstrumentTypeEnum type, String releaseName) {
        super(String.format("Инструмент типа %s с названием релиза %s не найден", type, releaseName));
    }
}
