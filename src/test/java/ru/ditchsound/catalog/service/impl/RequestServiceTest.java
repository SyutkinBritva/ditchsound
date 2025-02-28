package ru.ditchsound.catalog.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ditchsound.catalog.dto.Request.RequestApprovedDto;
import ru.ditchsound.catalog.dto.Request.RequestDto;
import ru.ditchsound.catalog.dto.Request.RequestStatusUpdateDto;
import ru.ditchsound.catalog.enums.RequestStatus;
import ru.ditchsound.catalog.mappers.RequestMapper;
import ru.ditchsound.catalog.model.Price;
import ru.ditchsound.catalog.model.Request;
import ru.ditchsound.catalog.repository.PriceRepository;
import ru.ditchsound.catalog.repository.RequestRepository;
import ru.ditchsound.catalog.service.EmailService;
import ru.ditchsound.catalog.service.PriceService;
import ru.ditchsound.catalog.service.RequestService;
import ru.ditchsound.catalog.util.DataUtils;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class RequestServiceTest {

    @MockBean
    private RequestRepository requestRepository;

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestMapper requestMapper;

    @MockBean
    private PriceRepository priceRepository;

    @Autowired
    private PriceService priceService;

    @Autowired
    private EmailService emailService;

    @Test
    @DisplayName("Тест метода создания заявки")
    void testCreateRequest() {
        //given
        final RequestDto requestDto = DataUtils.getRequestDto();

        final Request entityFromBD = requestMapper.toEntity(requestDto);
        entityFromBD.setId(1L);

        when(requestRepository.save(any())).thenReturn(entityFromBD);

        //when

        RequestDto result = requestService.createRequest(requestDto);

        //then

        verify(requestRepository).save(any(Request.class));


        assertThat(result).isNotNull();
        assertThat(result.getBandName()).isEqualTo(requestDto.getBandName());
        assertThat(result.getRequestName()).isEqualTo(requestDto.getRequestName());
        assertThat(result.getBandEmail()).isEqualTo(requestDto.getBandEmail());
        assertThat(result.getCountOfTrack()).isEqualTo(requestDto.getCountOfTrack());
        assertThat(result.getWorkDescription()).isEqualTo(requestDto.getWorkDescription());
        assertThat(result.getDeadline()).isEqualTo(requestDto.getDeadline());
        assertThat(result.getMultitrackLink()).isEqualTo(requestDto.getMultitrackLink());

    }

    @Test
    @DisplayName("Тест метода утверждения заявки")
    void testApproveRequest() {
        //given
        final Double discount = 0.1;

        final Double finalAmountWithDiscount = 450.0; // 2 песни mixing mastering + discount

        final Request requestFromDb = DataUtils.getRequestPersistence();

        when(requestRepository.findByIdAndRequestStatus(requestFromDb.getId(), requestFromDb.getRequestStatus()))
                .thenReturn(Optional.of(requestFromDb));

        when(priceRepository.save(any(Price.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        RequestApprovedDto result = requestService.approveRequest(requestFromDb.getId(), discount);

        // Then
        assertNotNull(result);
        assertEquals(requestFromDb.getId(), result.getId());
        assertEquals(RequestStatus.APPROVED, result.getRequestStatus());
        assertEquals(finalAmountWithDiscount, result.getTotalAmount());

        verify(requestRepository, times(2)).save(any(Request.class));
        verify(priceRepository, times(2)).save(any(Price.class));

    }

    @Test
    @DisplayName("Тест метода подтверждения цены заявки")
    void testConfirmPrice() {

        //given
        final Request requestFromDb = DataUtils.getRequestPersistence();

        requestFromDb.setRequestStatus(RequestStatus.APPROVED);

        when(requestRepository.findByIdAndBandEmailAndRequestStatus(requestFromDb.getId(), requestFromDb.getBandEmail(), requestFromDb.getRequestStatus()))
                .thenReturn(Optional.of(requestFromDb));

        //when
        RequestStatusUpdateDto result = requestService.confirmPrice(requestFromDb.getId(), requestFromDb.getBandEmail());

        //then
        assertNotNull(result);
        assertEquals(RequestStatus.IN_WORK, result.getRequestStatus());

        verify(requestRepository, times(1)).save(any(Request.class));

    }

    @Test
    @DisplayName("Тест метода отмены заявки")
    void testDeclineRequest() {

        //given
        final Request requestFromDb = DataUtils.getRequestPersistence();
        when(requestRepository.findByIdAndRequestStatus(requestFromDb.getId(), requestFromDb.getRequestStatus()))
                .thenReturn(Optional.of(requestFromDb));
        //when
        RequestStatusUpdateDto result = requestService.declineRequest(requestFromDb.getId());

        //then
        assertNotNull(result);
        assertEquals(RequestStatus.DECLINED, result.getRequestStatus());

        verify(requestRepository, times(1)).save(any(Request.class));
    }

    @Test
    @DisplayName("Тест метода завершения заявки")
    void testCompleteRequest(){
        //given
        final  Request requestFromDb = DataUtils.getRequestPersistence();

        requestFromDb.setRequestStatus(RequestStatus.IN_WORK);

        when(requestRepository.findByIdAndRequestStatus(requestFromDb.getId(), requestFromDb.getRequestStatus()))
                .thenReturn(Optional.of(requestFromDb));
        //when
        RequestStatusUpdateDto result = requestService.completeRequest(requestFromDb.getId());

        //then
        assertNotNull(result);
        assertEquals(RequestStatus.COMPLETED, result.getRequestStatus());

    }

    @Test
    @DisplayName("Тест метода поиска заявки по id")
    void testFindRequestById(){
        //given
        final Request request = DataUtils.getRequestPersistence();
        when(requestRepository.findById(request.getId())).thenReturn(Optional.of(request));
        //when
        RequestDto result = requestService.findRequest(request.getId());
        //then
        assertNotNull(result);
        assertEquals(1L,result.getId());
    }
}
