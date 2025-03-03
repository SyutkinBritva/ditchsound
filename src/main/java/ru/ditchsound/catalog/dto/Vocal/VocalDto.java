package ru.ditchsound.catalog.dto.Vocal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.ditchsound.catalog.dto.Studio.StudioDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class VocalDto {

    @Schema (description = "Тип вокала")
    private String vocalType;

    private StudioDto studioDto;
}
