package ru.ditchsound.catalog.util;


import ru.ditchsound.catalog.dto.Request.RequestDto;
import ru.ditchsound.catalog.enums.RequestStatus;
import ru.ditchsound.catalog.enums.WorkDescription;
import ru.ditchsound.catalog.model.Request;

import java.time.LocalDate;

public class DataUtils {

    public static Request getRequestTransient(){
        return Request.builder()
                .requestName("заявка номер 1")
                .workDescription(new WorkDescription[]{WorkDescription.MIXING, WorkDescription.MASTERING})
                .bandName("WLVS")
                .countOfTrack(2)
                .deadline(LocalDate.of(2025, 5, 12))
                .bandEmail("wlvs@mail.ru")
                .multitrackLink("www.dropbox.com")
                .requestStatus(RequestStatus.CREATED)
                .build();
    }

    public static Request getRequestPersistence(){
         Request request = Request.builder()
                .requestName("заявка номер 1")
                .workDescription(new WorkDescription[]{WorkDescription.MIXING, WorkDescription.MASTERING})
                .bandName("WLVS")
                .countOfTrack(2)
                .deadline(LocalDate.of(2025, 5, 12))
                .bandEmail("wlvs@mail.ru")
                .multitrackLink("www.dropbox.com")
                .requestStatus(RequestStatus.CREATED)
                .build();
         request.setId(1L);
         return request;
    }

    public static RequestDto getRequestDto(){
        return RequestDto.builder()
                .requestName("сведение 2 песен")
                .bandName("govnar")
                .bandEmail("govnar@mail.ru")
                .countOfTrack(2)
                .deadline(LocalDate.of(2025, 5, 12))
                .multitrackLink("www.dropbox.com/12^^%$e")
                .workDescription(new WorkDescription[]{WorkDescription.MIXING, WorkDescription.MASTERING})
                .build();
    }

}
