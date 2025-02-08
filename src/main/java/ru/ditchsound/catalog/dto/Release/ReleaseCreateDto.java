package ru.ditchsound.catalog.dto.Release;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.ditchsound.catalog.dto.Drums.DrumsDto;
import ru.ditchsound.catalog.dto.Request.RequestDto;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.enums.ReleaseStatus;
import ru.ditchsound.catalog.enums.WorkDescription;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ReleaseCreateDto {

    @Schema(description = "имя группы")
    private String bandName;

    @Schema(description = "количество песен")
    private Integer countOfTrack;

    @Schema(description = "ссылка на исходники")
    private String multitrackLink;

    @Schema(description = "дата сдачи проекта")
    private LocalDate endOfWork;

    @Schema(description = "жанр релиза")
    private GenreEnum genre;

    @Schema(description = "какие работы выполняет инженер")
    private WorkDescription[] workDescription;

    @Schema(description = "статус работы")
    private ReleaseStatus releaseStatus;

    @Schema(description = "барабаны")
    private List<DrumsDto> drumsDto;

    @Schema(description = "заявка")
    private RequestDto requestDto;


//    @Schema(description = "гитары")
//    private List<GuitarDto> guitarDto;
//
//    @Schema(description = "вокалы")
//    private List<VocalDto> vocalDto;
//
//    @Schema(description = "инструменты")
//    private List<InstrumentDto> instrumentDto;
}
