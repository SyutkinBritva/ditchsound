package ru.ditchsound.catalog.exception;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String userName) {
        super(String.format("Пользователь с именем %s не найден", userName));
    }
    public UserNotFoundException(Long id) {
        super(String.format("Пользователь с ID - %s не найден", id));
    }

}
