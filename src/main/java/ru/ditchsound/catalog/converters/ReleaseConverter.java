package ru.ditchsound.catalog.converters;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.PriceDto;
import ru.ditchsound.catalog.dto.ReleaseDto;
import ru.ditchsound.catalog.dto.createDTO.ReleaseCreateDto;
import ru.ditchsound.catalog.model.Price;
import ru.ditchsound.catalog.model.Release;

@Component
public class ReleaseConverter {

        public ReleaseDto toReleaseDto (Release release) {
            ReleaseDto releaseDto = new ReleaseDto();
            PriceDto priceDto = new PriceDto();
            releaseDto.setBandName(release.getBandName());
            releaseDto.setPriceDto(priceDto.setTotalAmount(priceDto.getTotalAmount()));
            releaseDto.setReleaseStatus(release.getReleaseStatus());
            releaseDto.setWorkDescription(release.getWorkDescription());
            return releaseDto;

        }

        public Release toEntity (ReleaseCreateDto releaseDto){
            Release release = new Release();
            Price price = new Price();
            release.setBandName(releaseDto.getBandName());
            release.setWorkDescription(releaseDto.getWorkDescription());
            release.setCountOfTrack(releaseDto.getCountOfTrack());
            release.setReleaseStatus(releaseDto.getReleaseStatus());
            release.setEndOfWork(releaseDto.getEndOfWork());
            release.setGenre(releaseDto.getGenre());

            if (releaseDto.getPriceDto() != null) {
                price.setTotalAmount(releaseDto
                        .getPriceDto()
                        .getTotalAmount());
                release.setPrice(price);
            } else {
                throw new IllegalArgumentException("Price must not be null");
            }

            return release;

        }

}
