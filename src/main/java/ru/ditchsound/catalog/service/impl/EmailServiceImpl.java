package ru.ditchsound.catalog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.ditchsound.catalog.model.Request;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements ru.ditchsound.catalog.service.EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendPriceApprovalEmail(Request request) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(request.getBandEmail());
        mailMessage.setFrom("igorvesenniy@yandex.ru");
        mailMessage.setSubject("Подтвердите стоимость");
        mailMessage.setText("Здравствуйте!\n\n" + "Ваша заявка " +
                request.getRequestName() + " одобрена. \n"
                + "Итоговая стоимость услуг: " + request.getTotalAmount() + " USD.\n" +
                "На данный момент заявка подтверждается в Swagger по id, перейдите по ссылке \n" +
                "http://localhost:8080/swagger-ui/index.html#/request-controller/confirmPriceUsingPUT \n" +
                "И введите этот номер - " + request.getId() + " и ваш email " + request.getBandEmail() +
                " \" С уважением, DITCHSOUND!\" ");

        javaMailSender.send(mailMessage);
    }

    @Override
    public void sendDeclineEmail(Request request) {

        SimpleMailMessage declineMessage = new SimpleMailMessage();
        declineMessage.setFrom("igorvesenniy@yandex.ru");
        declineMessage.setTo(request.getBandEmail());
        declineMessage.setSubject("Отмена заявки");
        declineMessage.setText("Здравствуйте!\n\n" + "К сожалению, ваша заявка " +
                request.getRequestName() + " не одобрена. \n" +
                "Нет возможности завершить заказ к этой дате " + request.getDeadline());

        javaMailSender.send(declineMessage);
    }
}
