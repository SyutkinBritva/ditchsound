package ru.ditchsound.catalog.dto.Guitar;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.ditchsound.catalog.dto.Release.ReleaseDto;
import ru.ditchsound.catalog.dto.Studio.StudioDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class GuitarDto {

    @Schema(description = "type of guitar")
    private String guitarType;

    private ReleaseDto releaseDto;
    private StudioDto studioDto;

}
