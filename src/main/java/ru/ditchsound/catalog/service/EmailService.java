package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.model.Request;

public interface EmailService {

    void sendPriceApprovalEmail(Request request);

    void sendDeclineEmail(Request request);
}
