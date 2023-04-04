package ru.ditchsound.catalog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DrumsDto {

    @Schema(description = "тип барабанов")
    private String drumsType;
    @Schema(description = "модель барабанов")
    private String drumsModel;

   // private ReleaseDto releaseDto;
}
