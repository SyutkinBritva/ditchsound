package ru.ditchsound.catalog.enums;

/**
 * Типы работ. При добавлении тут - обязательно добавить и в тип базы данных в SQL
 * !!!!
 */
public enum WorkDescription {

    MIXING(200.0),
    MASTERING(50.0),
    PRODUCING(500.0),
    EDITING(50.0),
    WRITING(1000.0);

    private final double price;

    WorkDescription(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
