package ru.ditchsound.catalog.dto.Price;

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
public class PriceDto {

    @Schema(description = "стоимость сведения")
    private Double mixing;

    @Schema(description = "стоимость мастеринга")
    private Double mastering;

    @Schema(description = "стоимость коррекции барабанов")
    private Double editingDrums;

    @Schema(description = "стоимость коррекции вокала")
    private Double editingVocals;

    @Schema(description = "стоимость коррекции других инструментов")
    private Double editingInstrument;

    @Schema(description = "стоимость продюсирования")
    private Double producing;

    @Schema(description = "размер скидки")
    private Double discount;

    @Schema(description = "количество песен")
    private Integer numberOfSongs;

}
