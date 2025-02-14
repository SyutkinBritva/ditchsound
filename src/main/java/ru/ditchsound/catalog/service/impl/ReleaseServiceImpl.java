package ru.ditchsound.catalog.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Release.ReleaseCreateDto;
import ru.ditchsound.catalog.dto.Release.ReleaseDto;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.mappers.DrumsMapper;
import ru.ditchsound.catalog.mappers.ReleaseMapper;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.model.Request;
import ru.ditchsound.catalog.repository.DrumsRepository;
import ru.ditchsound.catalog.repository.ReleaseRepository;
import ru.ditchsound.catalog.repository.RequestRepository;
import ru.ditchsound.catalog.repository.StudioRepository;
import ru.ditchsound.catalog.service.DrumsService;
import ru.ditchsound.catalog.service.ReleaseService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseRepository releaseRepository;

    private final ReleaseMapper releaseMapper;

    private final DrumsService drumsService;

    private final DrumsRepository drumsRepository;

    private final RequestRepository requestRepository;

    private final StudioRepository studioRepository;

    private final DrumsMapper drumsMapper;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository,
                              ReleaseMapper releaseMapper,
                              DrumsService drumsService, DrumsRepository drumsRepository, RequestRepository requestRepository, StudioRepository studioRepository, DrumsMapper drumsMapper) {

        this.releaseRepository = releaseRepository;
        this.releaseMapper = releaseMapper;
        this.drumsService = drumsService;
        this.drumsRepository = drumsRepository;
        this.requestRepository = requestRepository;
        this.studioRepository = studioRepository;
        this.drumsMapper = drumsMapper;
    }

    @Transactional(readOnly = true)
    public ReleaseDto findById(Long id) {

        Release release = releaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("в базе нету релиза с переданным id %s", id)));

        return releaseMapper.toDto(release);
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releaseList = releaseRepository.findAll(pageable);
        return releaseList.stream()
                .map(releaseMapper::toDto)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findReleaseByBandName(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releaseList = releaseRepository
                .findAllByBandNameIgnoreCase(name, pageable);
        return releaseList.stream().
                map(releaseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findByLabelName(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releasePage = releaseRepository.findAllByMusicLabel(name, pageable);
        return releasePage.stream()
                .map(releaseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findByGenre(GenreEnum genre, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releasePage = releaseRepository.findAllByGenre(genre, pageable);
        return releasePage
                .stream()
                .map(releaseMapper::toDto)
                .collect(Collectors.toList());
    }

//    @Transactional(readOnly = true)
//    public List<ReleaseDto> findByPrice(Double price, int page, int size) {
//
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Release> releasePage = releaseRepository.findAllByPriceTotalAmount(price, pageable);
//        return releasePage
//                .stream()
//                .map(releaseMapper::toDto)
//                .collect(Collectors.toList());
//    }

    @Transactional
    public ReleaseDto createRelease(ReleaseCreateDto releaseCreateDto) {

        Release release = releaseMapper.toEntity(releaseCreateDto);

        Request request = requestRepository
                .findByRequestName(releaseCreateDto.getRequestDto().getRequestName())
                .orElseThrow(() -> new EntityNotFoundException("Заявка не найдена"));

        List<Drums> drumsList = releaseMapper.listDrumsDTOsToEntities(releaseCreateDto.getDrumsDto());

        for(Drums drum:drumsList){
            drum.setRelease(release);
            if (drum.getStudio() != null) {
                drum.getStudio().setDrums(drum); // ✅ Устанавливаем обратную связь Studio → Drums
            }
        }

        release.setRequest(request);

        release.setDrumsList(drumsList);

        Release savedRelease = releaseRepository.save(release);

        return releaseMapper.toDto(savedRelease);

        //todo тут будет инжектится и вызываться drumServiceImpl.getOrCreateNew(...), guitarServiceImpl.getOrCreateNew()

        // Установить связь между сущностями
//        price.setRelease(release);
//        release.setPrice(price);
//        release.setTotalAmount(priceService.getTotalAmount(price));
//        release.setTotalAmountWithDiscount(priceService
//                .getTotalAmountWithDiscount(price));


    }
}

//TODO рабочий вариант для понимания, оставлю тут на память
//    @Transactional()
//    public Release createRelease (ReleaseCreateDto releaseCreateDto) {
//        Release unsaved = createReleaseMapper.toEntity(releaseCreateDto);
//        //из маппера сконвертировали релиз, но он без PK, потому что ещё не сохранен в БД
//        Price price = createReleaseMapper.relesaseDtoToPrice(releaseCreateDto);
//        // сохраняем релиз в БД и он возвращает сущность уже с PK
//        Release saved = releaseRepository.save(unsaved);
//        //после получения PK мы можем установить связь между ценой и релизом (112 строчка)
//        price.setRelease(saved);
//        // затем мы можем сохранить прайс
//        priceRepository.save(price);
//        return saved;
//    }

//TODO почему был стэковерфлоу
//Ох, кажется, мы столкнулись с каким-то зацикливанием или проблемой сериализации.
// Судя по всему, проблема возникает из-за того, что в вашем коде сущности Release и
// Price имеют взаимную связь, что может привести к бесконечному циклу при сериализации
// (например, в формате JSON). Вот что мы можем сделать, чтобы исправить это.
//
//Что происходит?
//Бесконечный цикл сериализации:
//
//У Release есть ссылка на Price, а у Price — ссылка на Release. Когда Spring
// пытается сериализовать объект Release, он загружает объект Price, а затем снова
// пытается загрузить Release из Price, и так по кругу.