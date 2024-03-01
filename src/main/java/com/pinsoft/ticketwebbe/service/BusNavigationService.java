package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.BusNavigationRequest;
import com.pinsoft.ticketwebbe.entity.Bus;
import com.pinsoft.ticketwebbe.entity.BusNavigation;
import com.pinsoft.ticketwebbe.repository.BusNavigationRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BusNavigationService extends AbstractBaseService <BusNavigation, Long> {

    @Autowired
    @Getter
    private BusNavigationRepository busNavigationRepository;

    @Autowired
    private BusService busService;

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
        Bus bus = busService.get(busNavigationRequest.getBusId());
        busNavigation.setBus(bus);
        return super.save(busNavigation);
    }
}
