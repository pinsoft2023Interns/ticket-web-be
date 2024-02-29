package com.pinsoft.ticketwebbe.controller;

import com.pinsoft.ticketwebbe.dto.CompanyRequest;
import com.pinsoft.ticketwebbe.entity.Company;
import com.pinsoft.ticketwebbe.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/company")
    public Collection<Company> get(){
        return companyService.listAll();
    }

    @GetMapping("/company/{id}")
    public Company get(@PathVariable Long id){
        return companyService.get(id);
    }

    @DeleteMapping("/company/{id}")
    public void delete(@PathVariable Long id){
        companyService.delete(id);
    }

    //TODO
    /*
    Change requestbody to CompanyDto
     */
    @PostMapping("/company")
    public Company add(@RequestBody CompanyRequest companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());

        return companyService.save(company);
    }
}
