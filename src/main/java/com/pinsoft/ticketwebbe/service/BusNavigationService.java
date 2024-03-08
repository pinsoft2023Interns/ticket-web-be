package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.BusNavigationRequest;
import com.pinsoft.ticketwebbe.dto.BusNavigationUpdateRequest;
import com.pinsoft.ticketwebbe.entity.Bus;
import com.pinsoft.ticketwebbe.entity.BusNavigation;
import com.pinsoft.ticketwebbe.exceptions.ApiRequestException;
import com.pinsoft.ticketwebbe.repository.BusNavigationRepository;
import com.pinsoft.ticketwebbe.repository.BusRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusNavigationService extends AbstractBaseService <BusNavigation, Long> {

    @Autowired
    @Getter
    private BusNavigationRepository busNavigationRepository;

    @Autowired
    private BusService busService;

    @Autowired
    private BusRepository busRepository;

    @Override
    protected JpaRepository<BusNavigation, Long> getRepository() {
        return busNavigationRepository;
    }

    public BusNavigation save(BusNavigationRequest busNavigationRequest){
        BusNavigation busNavigation = new BusNavigation();
        busNavigation.setDepartureDate(busNavigationRequest.getDepartureDate());
        busNavigation.setDeparturePlace(busNavigationRequest.getDeparturePlace());
        busNavigation.setArrivalPlace(busNavigationRequest.getArrivalPlace());
        busNavigation.setTravelTime(busNavigationRequest.getTravelTime());

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
            busNavigation.setDepartureDate(busNavigationUpdateRequest.getDepartureDate());
            busNavigation.setDeparturePlace(busNavigationUpdateRequest.getDeparturePlace());
            busNavigation.setArrivalPlace(busNavigationUpdateRequest.getArrivalPlace());
            busNavigation.setTravelTime(busNavigationUpdateRequest.getTravelTime());

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
