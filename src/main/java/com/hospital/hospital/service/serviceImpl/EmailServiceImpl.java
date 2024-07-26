package com.hospital.hospital.service.serviceImpl;

import com.hospital.hospital.dto.EmailDto;
import com.hospital.hospital.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
        private JavaMailSender javaMailSender;


    @Override
    public void sendEmail(EmailDto emailDto) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(emailDto.getTo());
        message.setFrom("pneupane2004@gmail.com");
        message.setSubject(emailDto.getSubject());
        message.setText(emailDto.getMessage());
        javaMailSender.send(message);
    }
}
