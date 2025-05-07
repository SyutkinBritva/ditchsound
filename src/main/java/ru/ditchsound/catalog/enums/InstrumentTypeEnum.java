package ru.ditchsound.catalog.enums;

import ru.ditchsound.catalog.exception.WrongInstrumentTypeException;

import java.util.Arrays;


public enum InstrumentTypeEnum {
    DRUMS("drums"),
    GUITAR("guitar"),
    VOCAL("vocal"),
    OTHER_INSTRUMENT("other_instrument");

    private final String compatName;

    InstrumentTypeEnum(String compatName) {

        this.compatName = compatName;
    }

    public static InstrumentTypeEnum parse(String value) {
        return Arrays.stream(values())
                .filter(v -> v.compatName.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new WrongInstrumentTypeException("Некорректный тип инструмента: " + value));
    }
}
