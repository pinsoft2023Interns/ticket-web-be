package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.BusNavigationRequest;
import com.pinsoft.ticketwebbe.dto.BusNavigationUpdateRequest;
import com.pinsoft.ticketwebbe.entity.Bus;
import com.pinsoft.ticketwebbe.entity.BusNavigation;
import com.pinsoft.ticketwebbe.exceptions.ApiRequestException;
import com.pinsoft.ticketwebbe.repository.BusNavigationRepository;
import com.pinsoft.ticketwebbe.repository.BusRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusNavigationService extends AbstractBaseService <BusNavigation, Long> {


    @Getter
    private final BusNavigationRepository busNavigationRepository;

    private final BusService busService;

    private final BusRepository busRepository;

    @Override
    protected JpaRepository<BusNavigation, Long> getRepository() {
        return busNavigationRepository;
    }

    public BusNavigation save(BusNavigationRequest busNavigationRequest){
        BusNavigation busNavigation = new BusNavigation();
        if(busRepository.findById(busNavigationRequest.getBusId()).isPresent()){
            Bus bus = busService.get(busNavigationRequest.getBusId());
            busNavigation.setBus(bus);

            return super.save(busNavigation);
        }
        else{
            throw new ApiRequestException("Check Bus id again!");
        }

    }
    public BusNavigation update(BusNavigationUpdateRequest busNavigationUpdateRequest){
        Optional<BusNavigation> busNavigationRequest = busNavigationRepository.findById(busNavigationUpdateRequest.getId());

        if(busNavigationRequest.isPresent()){
            BusNavigation busNavigation = busNavigationRepository.findById(busNavigationUpdateRequest.getId()).get();
            if(busRepository.findById(busNavigationUpdateRequest.getBusId()).isPresent()){
                Bus bus= busService.get(busNavigationUpdateRequest.getBusId());
                busNavigation.setBus(bus);
                return busNavigationRepository.save(busNavigation);
            }
            else{
                throw new ApiRequestException("The bus id is not valid!");
            }
        }
        else{
            throw new ApiRequestException("The given id is not exist!");
        }

    }
}
