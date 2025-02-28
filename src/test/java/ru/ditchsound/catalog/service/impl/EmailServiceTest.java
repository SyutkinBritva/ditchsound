package ru.ditchsound.catalog.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import ru.ditchsound.catalog.model.Request;
import ru.ditchsound.catalog.util.DataUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    @DisplayName("тест метода отправки письма с ценой для подтверждения заявки")
    public void testSendPriceApprovalEmail(){
        //given
        Request request = DataUtils.getRequestPersistence();
        //when
        emailService.sendPriceApprovalEmail(request);
        //then
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    @DisplayName("тест метода отправки письма с отклонением заявки")
    public  void testSendDeclineEmail(){
        //given
        Request request = DataUtils.getRequestPersistence();
        //when
        emailService.sendDeclineEmail(request);
        //then
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
