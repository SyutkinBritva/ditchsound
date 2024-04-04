package ru.ditchsound.catalog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class VocalDto {

    @Schema (description = "Тип вокала")
    private String vocalType;

    private StudioDto studioDto;
    private ReleaseDto releaseDto;
}