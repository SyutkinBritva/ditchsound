package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.model.Request;

public interface EmailService {

    public void sendPriceApprovalEmail(Request request);

    public void sendDeclineEmail(Request request);
}
