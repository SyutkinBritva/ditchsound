package ru.ditchsound.catalog.mappers.instruments;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.model.Guitar;
import ru.ditchsound.catalog.model.Instrument;
import ru.ditchsound.catalog.model.OtherInstrument;
import ru.ditchsound.catalog.model.Studio;
import ru.ditchsound.catalog.model.Vocal;

@Component
public class InstrumentsMapper {

    public Instrument toEntity(InstrumentDto instrumentDto, Studio studio) {

        switch (instrumentDto.getType().toUpperCase()) {

            case "GUITAR":
                Guitar guitar = new Guitar();
                guitar.setInstrumentType("GUITAR");
                guitar.setInstrumentProperty(instrumentDto.getInstrumentProperty());
                guitar.setInstrumentImg(instrumentDto.getInstrumentImg());
                guitar.setInstrumentModel(instrumentDto.getInstrumentModel());
                guitar.setSignalChain(instrumentDto.getSignalChain());
                guitar.setStudio(studio);
                return guitar;

            case "DRUMS":
                Drums drums = new Drums();
                drums.setInstrumentType("DRUMS");
                drums.setInstrumentModel(instrumentDto.getInstrumentModel());
                drums.setInstrumentImg(instrumentDto.getInstrumentImg());
                drums.setInstrumentProperty(instrumentDto.getInstrumentProperty());
                drums.setDrumsMics(instrumentDto.getDrumsMics());
                drums.setStudio(studio);
                return drums;

            case "VOCAL":
                Vocal vocal = new Vocal();
                vocal.setInstrumentType("VOCAL");
                vocal.setInstrumentModel(instrumentDto.getInstrumentModel());
                vocal.setInstrumentProperty(instrumentDto.getInstrumentProperty());
                vocal.setInstrumentImg(instrumentDto.getInstrumentImg());
                vocal.setVocalMic(instrumentDto.getVocalMic());
                vocal.setVocalTechnique(instrumentDto.getVocalTechnique());
                vocal.setStudio(studio);
                return vocal;

            case "OTHER_INSTRUMENT":
                OtherInstrument otherInstrument = new OtherInstrument();
                otherInstrument.setInstrumentType("OTHER_INSTRUMENT");
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
}
