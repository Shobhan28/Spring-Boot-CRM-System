package com.techhub.crm.service;

import com.techhub.crm.payload.ContactDto;

public interface ContactService {

    ContactDto createContact(String leadId);
    // Based on the leadId it will get the lead,
    // copy that to contact and delete the lead.
}
