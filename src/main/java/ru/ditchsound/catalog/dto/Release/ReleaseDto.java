package ru.ditchsound.catalog.dto.Release;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.ditchsound.catalog.dto.Drums.DrumsDto;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.enums.ReleaseStatus;
import ru.ditchsound.catalog.enums.WorkDescription;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class ReleaseDto {

    @Schema(description = "имя исполнителя")
    private String bandName;

    @Schema(description = "жанр релиза")
    private GenreEnum genre;

    @Schema(description = "статус работы")
    private ReleaseStatus releaseStatus;

    @Schema(description = "какую работу выполняет инженер")
    private WorkDescription[] workDescription;

    @Schema(description = "барабаны")
    private List<DrumsDto> drumsDto;

//    @Schema(description = "гитары")
//    private List<GuitarDto> guitarDto;
//
//    @Schema(description = "вокалы")
//    private List<VocalDto> vocalDto;
//
//    @Schema(description = "инструменты")
//    private List<InstrumentDto> instrumentDto;
}
