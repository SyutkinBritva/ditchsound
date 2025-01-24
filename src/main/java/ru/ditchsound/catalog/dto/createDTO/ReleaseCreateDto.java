package ru.ditchsound.catalog.dto.createDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.ditchsound.catalog.dto.PriceDto;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.enums.ReleaseStatus;
import ru.ditchsound.catalog.enums.WorkDescription;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ReleaseCreateDto {

    @Schema(description = "имя группы")
    private String bandName;

    @Schema(description = "количество песен")
    private Integer countOfTrack;

    @Schema(description = "ссылка на мультитрек")
    private String multitrackLink;

    @Schema(description = "дата сдачи")
    private LocalDate endOfWork;

    @Schema(description = "жанр")
    private GenreEnum genre;

    @Schema(description = "какую работу выполняет инженер")
    private WorkDescription[] workDescription;

    @Schema(description = "статус работы")
    private ReleaseStatus releaseStatus;

    @Schema(description = "общая сумма")
    private PriceDto priceDto;
}
