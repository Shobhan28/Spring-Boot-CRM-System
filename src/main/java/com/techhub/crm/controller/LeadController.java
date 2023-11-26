package com.techhub.crm.controller;

import com.techhub.crm.payload.LeadDto;
import com.techhub.crm.service.ExcelService;
import com.techhub.crm.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Objects;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;
    @Autowired
    private ExcelService excelService;

    //  http://localhost:8080/api/leads
    @PostMapping
    public ResponseEntity<LeadDto> createLead(@RequestBody LeadDto leadDto) {
        // this data will go to service layer
        LeadDto leadDto1 = leadService.createLead(leadDto);
        return new ResponseEntity<>(leadDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("/{lid}")
    public ResponseEntity<String> deleteLeadById(@PathVariable String lid) {
        leadService.deleteLeadById(lid);
        return new ResponseEntity<>("Lead is deleted", HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<LeadDto>> getAllLeads() {
        List<LeadDto> listleads = leadService.getAllLeads();
        return ResponseEntity.status(HttpStatus.OK).body(listleads);
    }

    @GetMapping("/get/{lid}")
    public ResponseEntity<LeadDto> findByLeadid(@PathVariable("lid") String lid) {
        LeadDto Dto = leadService.findByLeadid(lid);
        return ResponseEntity.status(HttpStatus.OK).body(Dto);
    }

    //  http://localhost:8080/api/leads/excelReports
    @GetMapping("/excelReports")
    public ResponseEntity<Resource> getAllLeadsExcelReports() {

        String filename = "leads.xlsx";
        InputStreamResource file = new InputStreamResource(excelService.load());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));

        return ResponseEntity.ok()
                .headers(headers)
                .body(file);

    }
}