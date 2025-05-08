package ru.ditchsound.catalog.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ditchsound.catalog.enums.WorkDescription;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto {

    @Schema(description = "id заявки")
    private Long id;

    @Schema(description = "имя заявки, уникальное", example = "Заявка №1 - сведение EP")
    private String requestName;

    @Schema(description = "имя группы", example = "Viking")
    private String bandName;

    @Schema(description = "количество треков", example = "5")
    private int countOfTrack;

    @Schema(description = "какие работы должен выполнить инженер")
    private WorkDescription[] workDescription;

    @Schema(description = "ссылка на исходники", example = "www.dropbox.com")
    private String multitrackLink;

    @Schema(description = "дата сдачи релиза")
    private LocalDate deadline;

    @Schema(description = "email группы", example = "igorvesenniy@gmail.com")
    private String bandEmail;
}
