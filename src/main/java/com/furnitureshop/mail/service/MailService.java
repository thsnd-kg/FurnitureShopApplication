package com.furnitureshop.mail.service;

public interface MailService {
    void sendVerifyMail(String email, String token);
    void sendVerifyResetPassword(String email, String token);
}
