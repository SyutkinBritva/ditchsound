package ru.ditchsound.catalog.dto.Release;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.enums.WorkDescription;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class ReleaseDto {

    @Schema(description = "имя исполнителя")
    private String bandName;

    @Schema(description = "название релиза")
    private String releaseName;

    @Schema(description = "жанр релиза")
    private GenreEnum genre;

    @Schema(description = "какую работу выполняет инженер")
    private WorkDescription[] workDescription;

}
