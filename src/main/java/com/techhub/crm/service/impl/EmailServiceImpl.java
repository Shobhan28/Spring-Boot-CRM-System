package com.techhub.crm.service.impl;

import com.techhub.crm.entity.Email;
import com.techhub.crm.entity.Lead;
import com.techhub.crm.exception.LeadExist;
import com.techhub.crm.payload.EmailDto;
import com.techhub.crm.repository.ContactRepository;
import com.techhub.crm.repository.EmailRepository;
import com.techhub.crm.repository.LeadRepository;
import com.techhub.crm.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class EmailServiceImpl implements EmailService {


    private JavaMailSender javaMailSender;
    private ModelMapper modelMapper;
    private LeadRepository leadRepo;
    private EmailRepository emailRepo;
    private ContactRepository contactRepo;

    public EmailServiceImpl(JavaMailSender javaMailSender, ModelMapper modelMapper,
                            LeadRepository leadRepo, EmailRepository emailRepo,
                            ContactRepository contactRepo) {
        this.javaMailSender = javaMailSender;
        this.modelMapper = modelMapper;
        this.leadRepo = leadRepo;
        this.emailRepo = emailRepo;
        this.contactRepo = contactRepo;
    }

    @Override
    public EmailDto sendEmail(EmailDto emailDto) {
        Lead lead = leadRepo.findByEmail(emailDto.getTo()).orElseThrow(
                () -> new LeadExist("Email id not Registered- " + emailDto.getTo())
        );

        // code for sending email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailDto.getFrom());
        mailMessage.setTo(emailDto.getTo());
        mailMessage.setSubject(emailDto.getSubject());
        mailMessage.setText(emailDto.getMessage());

        javaMailSender.send(mailMessage);

        Email email = mapToEntity(emailDto);
        String emailid = UUID.randomUUID().toString();
        email.setEmailId(emailid);

        Email sentEmail = emailRepo.save(email);
        return mapToDto(sentEmail);

    }

    private Email mapToEntity(EmailDto emailDto) {
        return modelMapper.map(emailDto, Email.class);
    }

    private EmailDto mapToDto(Email email) {
        return modelMapper.map(email, EmailDto.class);
    }
}