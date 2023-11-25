package com.techhub.crm.service;


import com.techhub.crm.payload.LeadDto;
import org.springframework.stereotype.Service;

@Service
public interface LeadService {

    LeadDto createLead(LeadDto leadDto);

    void deleteLeadById(String lid);
}
