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
public class ReleaseDto {

    @Schema(description = "имя исполнителя")
    private String bandName;
    @Schema(description = "тип работ")
    private String workDescription;
    @Schema(description = "статус релиза")
    private String releaseStatus;
}
