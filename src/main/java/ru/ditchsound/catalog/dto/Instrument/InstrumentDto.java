package ru.ditchsound.catalog.dto.Instrument;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.ditchsound.catalog.dto.Studio.StudioDto;
import ru.ditchsound.catalog.enums.InstrumentPropertyEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class InstrumentDto {

    @Schema(description = "тип инструмента")
    private String type;

    @Schema(description = "характеристика инструмента")
    private InstrumentPropertyEnum instrumentProperty;

    @Schema(description = "модель инструмента")
    private String instrumentModel;

    @Schema(description = "фото инструмента")
    private String instrumentImg;

    @Schema(description = "студия")
    private StudioDto studioDto;

    //drums
    @Schema(description = "комплект микрофонов")
    private String drumsMics;

    //guitars
    @Schema(description = "цепочка эффектов")
    private String signalChain;

    //vocal
    @Schema(description = "вокальная техника")
    private String vocalTechnique;

    @Schema(description = "вокальный микрофон")
    private String vocalMic;

    //otherInstrument
    @Schema(description = "предустановка")
    private String preset;
}
