package ru.ditchsound.catalog.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ditchsound.catalog.dto.Release.ReleaseUpdateDto;
import ru.ditchsound.catalog.dto.Release.ReleaseDto;
import ru.ditchsound.catalog.enums.WorkDescription;
import ru.ditchsound.catalog.mappers.release.ReleaseMapper;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.repository.ReleaseRepository;
import ru.ditchsound.catalog.service.ReleaseService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

;

@SpringBootTest
class ReleaseServiceTest {

    @MockBean
    private ReleaseRepository releaseRepository;

    @Autowired
    private ReleaseService releaseService;

    @Autowired
    private ReleaseMapper releaseMapper;

    @Test
    void testCreateRelease() {

        //тестовая сущность которая пришла сверху, от контроллера
        final ReleaseUpdateDto wlvs = ReleaseUpdateDto.builder()
                .bandName("WLVS")
                .workDescription(new WorkDescription[]{ WorkDescription.EDITING, WorkDescription.MIXING })
                .countOfTrack(1)
                .build();

        //смоделированная ентити, которую мы ожидаем после сохранения в базе, с id = 1
        final Release entityFromBD = releaseMapper.toEntity(wlvs);
        entityFromBD.setId(1L);

        //мокаем запрос в базу, реальную тестовую базу будем подключать в докере потом (testcontainer)
        when(releaseRepository.save(any())).thenReturn(entityFromBD);

        //запускаем основной тестируемый метод
        ReleaseDto result = releaseService.createRelease(wlvs);

        //проверяем, что MockBean репозитория был вызван в рамках нашего теста
        verify(releaseRepository).save(any(Release.class));

        //основные проверки полей
        assertThat(result).isNotNull();
        assertThat(result.getBandName()).isEqualTo(wlvs.getBandName());
        assertThat(result.getWorkDescription()).isEqualTo(wlvs.getWorkDescription());
        assertThat(result.getReleaseStatus()).isEqualTo(wlvs.getReleaseStatus());
        assertThat(result.getGenre()).isEqualTo(wlvs.getGenre());

    }
}