package ru.ditchsound.catalog.mappers.instruments;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.model.Guitar;
import ru.ditchsound.catalog.model.Instrument;
import ru.ditchsound.catalog.model.OtherInstrument;
import ru.ditchsound.catalog.model.Studio;
import ru.ditchsound.catalog.model.Vocal;

import java.util.Optional;

import static ru.ditchsound.catalog.enums.InstrumentTypeEnum.DRUMS;
import static ru.ditchsound.catalog.enums.InstrumentTypeEnum.GUITAR;
import static ru.ditchsound.catalog.enums.InstrumentTypeEnum.OTHER_INSTRUMENT;
import static ru.ditchsound.catalog.enums.InstrumentTypeEnum.VOCAL;

@Component
public class InstrumentsMapper {

    public Instrument toEntity(InstrumentDto instrumentDto, Studio studio) {

        switch (instrumentDto.getType()) {

            case GUITAR:
                Guitar guitar = new Guitar();
                guitar.setInstrumentType(GUITAR);
                guitar.setInstrumentProperty(instrumentDto.getInstrumentProperty());
                guitar.setInstrumentImg(instrumentDto.getInstrumentImg());
                guitar.setInstrumentModel(instrumentDto.getInstrumentModel());
                guitar.setSignalChain(instrumentDto.getSignalChain());
                guitar.setStudio(studio);
                return guitar;

            case DRUMS:
                Drums drums = new Drums();
                drums.setInstrumentType(DRUMS);
                drums.setInstrumentModel(instrumentDto.getInstrumentModel());
                drums.setInstrumentImg(instrumentDto.getInstrumentImg());
                drums.setInstrumentProperty(instrumentDto.getInstrumentProperty());
                drums.setDrumsMics(instrumentDto.getDrumsMics());
                drums.setStudio(studio);
                return drums;

            case VOCAL:
                Vocal vocal = new Vocal();
                vocal.setInstrumentType(VOCAL);
                vocal.setInstrumentModel(instrumentDto.getInstrumentModel());
                vocal.setInstrumentProperty(instrumentDto.getInstrumentProperty());
                vocal.setInstrumentImg(instrumentDto.getInstrumentImg());
                vocal.setVocalMic(instrumentDto.getVocalMic());
                vocal.setVocalTechnique(instrumentDto.getVocalTechnique());
                vocal.setStudio(studio);
                return vocal;

            case OTHER_INSTRUMENT:
                OtherInstrument otherInstrument = new OtherInstrument();
                otherInstrument.setInstrumentType(OTHER_INSTRUMENT);
                otherInstrument.setInstrumentImg(instrumentDto.getInstrumentImg());
                otherInstrument.setInstrumentModel(instrumentDto.getInstrumentModel());
                otherInstrument.setInstrumentProperty(instrumentDto.getInstrumentProperty());
                otherInstrument.setPreset(instrumentDto.getPreset());
                otherInstrument.setStudio(studio);
                return otherInstrument;

            default:
                throw new IllegalArgumentException("Unknown instrument type: " + instrumentDto.getType());
        }
    }

    public <T extends Instrument> InstrumentDto toDto(Optional<T> optionalInstrument) {
        Instrument instrument = optionalInstrument.orElseThrow(() ->
                new IllegalArgumentException("Instrument not found"));

        InstrumentDto instrumentDto = new InstrumentDto();
        instrumentDto.setType(instrument.getInstrumentType());
        instrumentDto.setInstrumentImg(instrument.getInstrumentImg());
        instrumentDto.setInstrumentModel(instrument.getInstrumentModel());
        instrumentDto.setInstrumentProperty(instrument.getInstrumentProperty());

        switch (instrument.getInstrumentType()) {
            case GUITAR:
                Guitar guitar = (Guitar) instrument;
                instrumentDto.setSignalChain(guitar.getSignalChain());
                break;

            case DRUMS:
                Drums drums = (Drums) instrument;
                instrumentDto.setDrumsMics(drums.getDrumsMics());
                break;

            case VOCAL:
                Vocal vocal = (Vocal) instrument;
                instrumentDto.setVocalMic(vocal.getVocalMic());
                instrumentDto.setVocalTechnique(vocal.getVocalTechnique());
                break;

            case OTHER_INSTRUMENT:
                OtherInstrument otherInstrument = (OtherInstrument) instrument;
                instrumentDto.setPreset(otherInstrument.getPreset());
                break;

            default:
                throw new IllegalArgumentException("Unknown instrument type: " + instrument.getInstrumentType());
        }

        return instrumentDto;
    }

    public InstrumentDto toDto(Instrument instrument) {
        return toDto(Optional.of(instrument));
    }
}
