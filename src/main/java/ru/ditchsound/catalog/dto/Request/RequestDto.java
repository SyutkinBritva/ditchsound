package ru.ditchsound.catalog.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ditchsound.catalog.enums.WorkDescription;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    @Schema(description = "имя группы" , example = "Viking")
    private String bandName;

    @Schema(description = "количество треков", example = "5")
    private int countOfTrack;

    @Schema(description = "какие работы должен выполнить инженер")
    private WorkDescription[] workDescription;

    @Schema(description = "ссылка на исходники", example = "www.dropbox.com")
    private String multitrackLink;

    @Schema(description = "email группы", example = "vikingband@gmail.com")
    private String bandEmail;

}
