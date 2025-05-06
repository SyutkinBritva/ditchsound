package ru.ditchsound.catalog.exception;

import ru.ditchsound.catalog.enums.RequestStatus;

public class RequestNotFoundException extends NotFoundException {

    public RequestNotFoundException(Long id, RequestStatus requestStatus){

        super(String.format("Заявка с id %s не найдена в статусе %s", id, requestStatus));

    }

}
