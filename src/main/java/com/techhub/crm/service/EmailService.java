package com.techhub.crm.service;

import com.techhub.crm.payload.EmailDto;

public interface EmailService {

    public EmailDto sendEmail (EmailDto emailDto);
}
