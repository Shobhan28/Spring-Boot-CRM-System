package com.techhub.crm.controller;


import com.techhub.crm.payload.LeadDto;
import com.techhub.crm.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    //  http://localhost:8080/api/leads
    @PostMapping
    public ResponseEntity <LeadDto> createLead(@RequestBody LeadDto leadDto){
        // this data will go to service layer
    LeadDto leadDto1 = leadService.createLead(leadDto);
    return new ResponseEntity<>(leadDto1, HttpStatus.CREATED);
    }

}
