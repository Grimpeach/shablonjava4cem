package org.example.practice15;

import org.example.practice15.service.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender mailSender;

    @Test
    void testSendEmail_Success() {
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        emailService.sendEmail("test@example.com", "Test Subject", "Test Body");

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void testSendEmail_Exception() {
        doThrow(new MailException("Mail Sending Failed") {}).when(mailSender).send(any(SimpleMailMessage.class));

        emailService.sendEmail("test@example.com", "Test Subject", "Test Body");

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
