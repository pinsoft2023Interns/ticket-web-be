package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.BusRequest;
import com.pinsoft.ticketwebbe.dto.BusResponse;
import com.pinsoft.ticketwebbe.entity.Bus;
import com.pinsoft.ticketwebbe.entity.Company;
import com.pinsoft.ticketwebbe.repository.BusRepository;
import com.pinsoft.ticketwebbe.repository.CompanyRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusService extends AbstractBaseService<Bus,Long>{

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private CompanyService companyService;

    @Override
    protected JpaRepository<Bus, Long> getRepository() {
        return busRepository;
    }

    public Bus save(BusRequest busRequest) {
        Bus bus = new Bus();
        bus.setPlate(busRequest.getPlate());
        bus.setDriverName(busRequest.getDriverName());
        bus.setHostName(busRequest.getHostName());
        Company company = companyService.get(busRequest.getCompanyId());
        bus.setCompanyId(company);
        return super.save(bus);
    }
}
