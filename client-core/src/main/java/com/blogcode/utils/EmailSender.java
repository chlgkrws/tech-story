package com.blogcode.utils;

import com.blogcode.dto.MailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSender {
    private final JavaMailSender javaMailSender;

    @Async
    public void sendMail(MailDto mailDto) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(mailDto.getEmailAddress());
            mailMessage.setSubject(mailDto.getTitle());
            mailMessage.setText(mailDto.getMessage());

        javaMailSender.send(mailMessage);
    }
}
