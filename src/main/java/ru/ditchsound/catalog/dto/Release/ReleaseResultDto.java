package ru.ditchsound.catalog.dto.Release;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.enums.WorkDescription;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReleaseResultDto {

    @Schema(description = "имя исполнителя")
    private String bandName;

    @Schema(description = "название релиза")
    private String releaseName;

    @Schema(description = "жанр группы")
    private GenreEnum genre;

    @Schema(description = "ссыдка на альбомную обложку")
    private String albumCoverLink;

    @Schema(description = "работы, которые были выполнены")
    private WorkDescription[] workDescription;

    @Schema(description = "ссылка на сайт группы")
    private String socialNetworkLink;

    @Schema(description = "дата выхода")
    private LocalDate releaseDttm;

    @Schema(description = "лейбл")
    private String musicLabel;

}
