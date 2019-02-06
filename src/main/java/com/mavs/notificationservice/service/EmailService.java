package com.mavs.notificationservice.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
