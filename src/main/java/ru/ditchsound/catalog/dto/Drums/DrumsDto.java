package ru.ditchsound.catalog.dto.Drums;

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
public class DrumsDto {

    @Schema(description = "тип барабанов")
    private String drumsType;
    @Schema(description = "модель барабанов")
    private String drumsModel;

    private StudioDto studioDto;
}
