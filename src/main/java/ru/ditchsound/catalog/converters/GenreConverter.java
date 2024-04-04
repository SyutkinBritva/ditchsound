package ru.ditchsound.catalog.converters;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.GenreDto;
import ru.ditchsound.catalog.model.Genre;

@Component
public class GenreConverter {

    public GenreDto toGenreDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
       return genreDto.setGenreName(genre.getGenreName());
    }
}
