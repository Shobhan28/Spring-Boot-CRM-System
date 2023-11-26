package com.techhub.crm.service.impl;

import com.techhub.crm.entity.Lead;
import com.techhub.crm.exception.LeadExist;
import com.techhub.crm.payload.LeadDto;
import com.techhub.crm.repository.LeadRepository;
import com.techhub.crm.service.LeadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LeadServiceImpl implements LeadService {

    @Autowired
    private LeadRepository leadRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LeadDto createLead(LeadDto leadDto) {

        boolean emailExists = leadRepo.existsByEmail(leadDto.getEmail());
        boolean mobileExists = leadRepo.existsByMobile(leadDto.getMobile());

        if (emailExists) {
            throw new LeadExist("Email ID " + leadDto.getEmail() + " already exists.");
        }
        if (mobileExists) {
            throw new LeadExist("Mobile no." + leadDto.getMobile() + "already exists.");
        }

        Lead lead = mapToEntity(leadDto);
        // setting up automatic random id generate
        String leadid = UUID.randomUUID().toString();
        lead.setLeadId(leadid);

        Lead savedLead = leadRepo.save(lead);
        LeadDto dto = mapToDto(savedLead);
        return dto;
    }

    @Override
    public void deleteLeadById(String leadId) {
        Lead lead = leadRepo.findById(leadId).orElseThrow(
                ()-> new LeadExist("Lead with the id " + leadId + " is not present")
        );
        leadRepo.deleteById(leadId);
    }

    @Override
    public List<LeadDto> getAllLeads() {
        List<Lead> allLeads = leadRepo.findAll();
        List<LeadDto> allLeadDto = allLeads.stream().map(leads -> mapToDto(leads)).collect(Collectors.toList());
        return allLeadDto;
    }

    Lead mapToEntity(LeadDto leadDto) {
        Lead lead = modelMapper.map(leadDto, Lead.class);

        return lead;
    }

    LeadDto mapToDto(Lead lead) {
        LeadDto leadDto = modelMapper.map(lead, LeadDto.class);
        return leadDto;
    }
}
