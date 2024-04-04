package ru.ditchsound.catalog.converters;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.GenreDto;
import ru.ditchsound.catalog.dto.PriceDto;
import ru.ditchsound.catalog.dto.ReleaseDto;
import ru.ditchsound.catalog.model.Release;

@Component
public class ReleaseConverter {

        public ReleaseDto toReleaseDto (Release release) {
            ReleaseDto releaseDto = new ReleaseDto();
            PriceDto priceDto = new PriceDto();
            GenreDto genreDto = new GenreDto();
            releaseDto.setBandName(release.getBandName());
            releaseDto.setGenreDto
                    (genreDto.setGenreName(genreDto.getGenreName()));
            releaseDto.setPriceDto(priceDto.setTotalAmount(priceDto.getTotalAmount()));
            releaseDto.setReleaseStatus(release.getReleaseStatus());
            releaseDto.setWorkDescription(release.getWorkDescription());
            return releaseDto;

        }

}
