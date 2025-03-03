package ru.ditchsound.catalog.dto.Instrument;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.ditchsound.catalog.dto.Studio.StudioDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class InstrumentDto {

    @Schema (description = "тип инструмента")
    private String instrumentType;

    private StudioDto studioDto;
}
