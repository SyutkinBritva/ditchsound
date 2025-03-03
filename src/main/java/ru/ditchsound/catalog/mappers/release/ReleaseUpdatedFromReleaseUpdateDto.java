package ru.ditchsound.catalog.mappers.release;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.Release.ReleaseUpdateDto;
import ru.ditchsound.catalog.model.Release;
@Component
public class ReleaseUpdatedFromReleaseUpdateDto {
    public Release updateFromReleaseUpdateDto(ReleaseUpdateDto releaseUpdateDto, Release release){
        release.setGenre(releaseUpdateDto.getGenre());
        release.setReleaseDttm(releaseUpdateDto.getReleaseDttm());
        release.setAlbumCoverLink(releaseUpdateDto.getAlbumCoverLink());
        release.setMusicLabel(releaseUpdateDto.getMusicLabel());
        release.setSocialNetworkLink(releaseUpdateDto.getSocialNetworkLink());
        return release;
    }
}
