package ru.ditchsound.catalog.exception;

public class RoleNotFoundException extends NotFoundException {

    public RoleNotFoundException(String name) {
        super(String.format("Роль с данным именем не найдена %s", name));
    }

    public RoleNotFoundException(Long id) {
        super(String.format("Роль с данным ID - %s не найдена", id));
    }
}
