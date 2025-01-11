package ru.ditchsound.catalog.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.ditchsound.catalog.model.GenreEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class ReleaseDto {

    @Schema(description = "имя исполнителя")
    private String bandName;
    @Schema(description = "тип работ")
    private String workDescription;
    @Schema(description = "статус релиза")
    private String releaseStatus;

    private GenreEnum genre;

    private PriceDto priceDto;

}
