package com.techhub.crm.service.impl;

import com.techhub.crm.entity.Lead;
import com.techhub.crm.helper.ExcelHelper;
import com.techhub.crm.repository.LeadRepository;
import com.techhub.crm.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private LeadRepository leadrepo;

    @Override
    public ByteArrayInputStream load() {

        List<Lead> leads = leadrepo.findAll();

        ByteArrayInputStream in = ExcelHelper.leadsToExcel(leads);
        return in;
    }
}

