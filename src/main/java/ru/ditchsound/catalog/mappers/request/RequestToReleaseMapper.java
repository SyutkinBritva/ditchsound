package ru.ditchsound.catalog.mappers.request;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.model.Request;

@Component
public class RequestToReleaseMapper {
    public Release requestToRelease (Request request){
        return Release.builder()
                .bandName(request.getBandName())
                .workDescription(request.getWorkDescription())
                .countOfTrack(request.getCountOfTrack())
                .genre(GenreEnum.UNKNOWN)
                .request(request)
                .build();

    }
}
