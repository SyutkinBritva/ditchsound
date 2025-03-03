package ru.ditchsound.catalog.dto.Release;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.ditchsound.catalog.enums.GenreEnum;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ReleaseUpdateDto {

    @Schema(description = "жанр группы")
    private GenreEnum genre;

    @Schema(description = "ссыдка на альбомную обложку")
    private String albumCoverLink;

    @Schema(description = "ссылка на сайт группы")
    private String socialNetworkLink;

    @Schema(description = "дата выхода")
    private LocalDate releaseDttm;

    @Schema(description = "лейбл")
    private String musicLabel;

}
