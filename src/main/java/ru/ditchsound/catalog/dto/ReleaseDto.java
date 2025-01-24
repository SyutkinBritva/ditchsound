package ru.ditchsound.catalog.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.enums.ReleaseStatus;
import ru.ditchsound.catalog.enums.WorkDescription;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class ReleaseDto {

    @Schema(description = "имя исполнителя")
    private String bandName;

    @Schema(description = "жанр")
    private GenreEnum genre;

    @Schema(description = "статус работы")
    private ReleaseStatus releaseStatus;

    @Schema(description = "какую работу выполняет инженер")
    private WorkDescription[] workDescription;

    @Schema(description = "цена за релиз")
    private PriceDto priceDto;
}
