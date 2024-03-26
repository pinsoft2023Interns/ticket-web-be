package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.BusRequest;
import com.pinsoft.ticketwebbe.dto.BusUpdateRequest;
import com.pinsoft.ticketwebbe.entity.Bus;
import com.pinsoft.ticketwebbe.entity.Company;
import com.pinsoft.ticketwebbe.exceptions.ApiRequestException;
import com.pinsoft.ticketwebbe.repository.BusRepository;
import com.pinsoft.ticketwebbe.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BusService extends AbstractBaseService<Bus,Long>{

    private final BusRepository busRepository;

    private final CompanyService companyService;

    private final CompanyRepository companyRepository;

    @Override
    protected JpaRepository<Bus, Long> getRepository() {
        return busRepository;
    }

    public Bus save(BusRequest busRequest) {
        Bus bus = new Bus();
        bus.setPlate(busRequest.getPlate());
        bus.setDriverName(busRequest.getDriverName());
        bus.setHostName(busRequest.getHostName());
        bus.setNumberOfSeats(busRequest.getNumberOfSeats());
        bus.setBusDesign(busRequest.getBusDesign());

        if(companyRepository.findById(busRequest.getCompanyId()).isPresent()){
            Company company = companyService.get(busRequest.getCompanyId());
            bus.setCompanyId(company);

            return super.save(bus);
        }else{
            throw new ApiRequestException("Check company id again!");
        }

    }
    public Bus update(BusUpdateRequest busUpdateRequest){
        Optional<Bus> busRequest= busRepository.findById(busUpdateRequest.getId());
        if(busRequest.isPresent()){
            Bus bus = busRepository.findById(busUpdateRequest.getId()).get();
            bus.setPlate(busUpdateRequest.getPlate());
            bus.setDriverName(busUpdateRequest.getDriverName());
            bus.setHostName(busUpdateRequest.getHostName());
            bus.setNumberOfSeats(busUpdateRequest.getNumberOfSeats());
            bus.setBusDesign(busUpdateRequest.getBusDesign());

            Company company = companyService.get(busUpdateRequest.getCompanyId());
            bus.setCompanyId(company);

            return busRepository.save(bus);
        }
        else{
            throw new ApiRequestException("The given id is not exist!");
        }

    }


}
