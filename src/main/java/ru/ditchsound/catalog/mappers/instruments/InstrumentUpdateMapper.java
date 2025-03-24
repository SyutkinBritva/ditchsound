package ru.ditchsound.catalog.mappers.instruments;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.model.Guitar;
import ru.ditchsound.catalog.model.Instrument;
import ru.ditchsound.catalog.model.OtherInstrument;
import ru.ditchsound.catalog.model.Vocal;

@Component
public class InstrumentUpdateMapper {

    public Instrument updateInstrumentFields(Instrument instrument, InstrumentDto instrumentDto) {
        instrument.setInstrumentModel(instrumentDto.getInstrumentModel());
        instrument.setInstrumentImg(instrumentDto.getInstrumentImg());
        instrument.setInstrumentProperty(instrumentDto.getInstrumentProperty());

        switch (instrument.getInstrumentType()) {
            case GUITAR:
                if (instrument instanceof Guitar) {
                    ((Guitar) instrument).setSignalChain(instrumentDto.getSignalChain());
                }
                break;

            case DRUMS:
                if (instrument instanceof Drums) {
                    ((Drums) instrument).setDrumsMics(instrumentDto.getDrumsMics());
                }
                break;

            case VOCAL:
                if (instrument instanceof Vocal) {
                    ((Vocal) instrument).setVocalMic(instrumentDto.getVocalMic());
                    ((Vocal) instrument).setVocalTechnique(instrumentDto.getVocalTechnique());
                }
                break;

            case OTHER_INSTRUMENT:
                if (instrument instanceof OtherInstrument) {
                    ((OtherInstrument) instrument).setPreset(instrumentDto.getPreset());
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown instrument type: " + instrument.getInstrumentType());
        }
        return instrument;
    }

}
