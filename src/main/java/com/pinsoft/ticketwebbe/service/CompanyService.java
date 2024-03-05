package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.CompanyRequest;
import com.pinsoft.ticketwebbe.dto.CompanyUpdateRequest;
import com.pinsoft.ticketwebbe.entity.Company;
import com.pinsoft.ticketwebbe.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends AbstractBaseService<Company, Long>{

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    protected JpaRepository<Company, Long> getRepository() {
        return companyRepository;
    }

    public Company save(CompanyRequest companyRequest){
        Company company = new Company();
        company.setName(companyRequest.getName());
        return super.save(company);
    }
    public Company update(CompanyUpdateRequest companyUpdateRequest){
        Company company = companyRepository.getById(companyUpdateRequest.getId());
        company.setName(companyUpdateRequest.getName());
        return companyRepository.save(company);
    }

}
