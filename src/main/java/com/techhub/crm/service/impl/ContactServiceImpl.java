package com.techhub.crm.service.impl;

import com.techhub.crm.entity.Contact;
import com.techhub.crm.entity.Lead;
import com.techhub.crm.exception.LeadExist;
import com.techhub.crm.payload.ContactDto;
import com.techhub.crm.repository.ContactRepository;
import com.techhub.crm.repository.LeadRepository;
import com.techhub.crm.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    private LeadRepository leadRepo;
    private ContactRepository contactRepo;
    private ModelMapper modelMapper;

    // Constructor based injection
    public ContactServiceImpl(LeadRepository leadRepo, ContactRepository contactRepo,
                              ModelMapper modelMapper) {
        this.leadRepo = leadRepo;
        this.contactRepo = contactRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ContactDto createContact(String leadId) {
        // we have a leadId given to this, contacts are not directly generated,
        // it is the lead converted to contact

        Lead lead = leadRepo.findById(leadId).orElseThrow(
                () -> new LeadExist(("Lead with this id does not exists: " + leadId))
        );

        // If the lead is found copy the lead to contact
        Contact contact = convertLeadToContact(lead);
        // Before we save the contact, set the contact Id.
        String contactId = UUID.randomUUID().toString();
        contact.setContactId(contactId);
        Contact savedContact = contactRepo.save(contact);

        // once you see the contact is saved, then you delete the lead
        // check whether the contact is saved or not!
        if (savedContact.getContactId() != null) {
            // if it is saved then id will not be null and
            // when it is not null,let us delete the Lead.
            leadRepo.deleteById(lead.getLeadId());
        }
        return mapToDto(savedContact);
    }

    ContactDto mapToDto(Contact contact) {
        return modelMapper.map(contact, ContactDto.class);
    }

    Contact convertLeadToContact(Lead lead) {
        Contact contact = modelMapper.map(lead, Contact.class);
        return contact;
    }
}
