package ru.ditchsound.catalog.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.ReleaseDto;
import ru.ditchsound.catalog.dto.createDTO.ReleaseCreateDto;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.mappers.ReleaseMapper;
import ru.ditchsound.catalog.mappers.createMappers.CreateReleaseMapper;
import ru.ditchsound.catalog.model.Price;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.repository.PriceRepository;
import ru.ditchsound.catalog.repository.ReleaseRepository;

import java.util.List;
import java.util.stream.Collectors;

///

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseRepository releaseRepository;
    private final ReleaseMapper releaseMapper;
    private final CreateReleaseMapper createReleaseMapper;
    private final PriceRepository priceRepository;


    public ReleaseServiceImpl(ReleaseRepository releaseRepository, ReleaseMapper releaseMapper, CreateReleaseMapper createReleaseMapper, PriceRepository priceRepository) {
        this.releaseRepository = releaseRepository;
        this.releaseMapper = releaseMapper;
        this.createReleaseMapper = createReleaseMapper;
        this.priceRepository = priceRepository;
    }


    @Transactional(readOnly = true)
    public ReleaseDto findById(Long id) {

        Release release = releaseRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("в базе нету релиза с переданным id %s", id)));

        return releaseMapper.toDto(release);
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releaseList = releaseRepository.findAll(pageable);
        return releaseList.stream()
                .map(releaseMapper::toDto).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findReleaseByBandName(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releaseList = releaseRepository
                .findAllByBandNameIgnoreCase(name, pageable);
        return releaseList.stream().
                map(releaseMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findByStatus(String status, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releaseList = releaseRepository
                .findAllByReleaseStatus(status, pageable);
        return releaseList.stream().
                map(releaseMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findByLabelName(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releasePage = releaseRepository.
                findAllByMusicLabel(name, pageable);
        return releasePage.stream().map(releaseMapper::toDto).
                collect(Collectors.toList());
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

    @Transactional(readOnly = true)
    public List<ReleaseDto> findByPrice(Double price, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releasePage = releaseRepository.findAllByPriceTotalAmount(price, pageable);
        return releasePage
                .stream()
                .map(releaseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReleaseDto createRelease(ReleaseCreateDto releaseCreateDto) {

        Release release = createReleaseMapper.toEntity(releaseCreateDto);
        Price price = createReleaseMapper.relesaseDtoToPrice(releaseCreateDto);

        // Установить связь между сущностями
        price.setRelease(release);
        release.setPrice(price);

        // Hibernate сам сохранит обе сущности
        Release saved = releaseRepository.save(release);

        return releaseMapper.toDto(saved);
    }
}


//TODO рабочий вариант для понимания, оставлю тут на память
//    @Transactional()
//    public Release createRelease (ReleaseCreateDto releaseCreateDto) {
//        Release unsaved = createReleaseMapper.toEntity(releaseCreateDto);
//        //из маппера сконвертировали релиз, но он без PK, потому что ещё не сохранен в БД
//        Price price = createReleaseMapper.relesaseDtoToPrice(releaseCreateDto);
//        // на 110 строке мы сохраняем релиз в БД и он возвращает сущность уже с PK
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