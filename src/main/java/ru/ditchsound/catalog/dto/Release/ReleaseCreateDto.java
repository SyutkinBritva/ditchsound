package ru.ditchsound.catalog.dto.Release;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.ditchsound.catalog.dto.Price.PriceDto;
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

    @Schema(description = "ссылка на исходники")
    private String multitrackLink;

    @Schema(description = "дата сдачи проекта")
    private LocalDate endOfWork;

    @Schema(description = "жанр релиза")
    private GenreEnum genre;

    @Schema(description = "какие работы выполняет инженер")
    private WorkDescription[] workDescription;

    @Schema(description = "статус работы")
    private ReleaseStatus releaseStatus;

    @Schema(description = "стоимость")
    private PriceDto priceDto;
}
