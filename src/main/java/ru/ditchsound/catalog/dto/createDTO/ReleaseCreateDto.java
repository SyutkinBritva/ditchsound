package ru.ditchsound.catalog.dto.createDTO;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.ditchsound.catalog.dto.PriceDto;
import ru.ditchsound.catalog.model.GenreEnum;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ReleaseCreateDto {
    @Schema(description = "имя группы")
    private String bandName;

    @Schema(description = "какую работу выполняет инженер")
    private String workDescription;

    @Schema(description = "количество песен")
    private Integer countOfTrack;

    @Schema(description = "статус работы")
    @NotNull()
    private String releaseStatus;

    @Schema(description = "ссылка на мультитрек")
    private String multitrackLink;

    @Schema(description = "дата сдачи")
    private LocalDate endOfWork;

    @Schema(description = "жанр")
    private GenreEnum genre;

    @Schema(description = "общая сумма")
    private PriceDto priceDto;

}
