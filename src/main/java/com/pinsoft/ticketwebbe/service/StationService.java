package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.StationRequest;
import com.pinsoft.ticketwebbe.dto.StationUpdateRequest;
import com.pinsoft.ticketwebbe.entity.BusNavigation;
import com.pinsoft.ticketwebbe.entity.Station;
import com.pinsoft.ticketwebbe.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class StationService extends AbstractBaseService<Station,Long> {
    @Autowired
    StationRepository stationRepository;

    @Autowired
    BusNavigationService busNavigationService;

    @Override
    protected JpaRepository<Station, Long> getRepository() {
        return stationRepository;
    }

    public Station save(StationRequest stationRequest){
        Station station= new Station();
        station.setName(stationRequest.getName());
        BusNavigation busNavigation = busNavigationService.get(stationRequest.getBusNavigationId());
        station.setBusNavigations(busNavigation);
        return super.save(station);

    }

    public Station update(StationUpdateRequest stationUpdateRequest){
        Station station = stationRepository.getById(stationUpdateRequest.getId());
        station.setName(stationUpdateRequest.getName());
        BusNavigation busNavigation = busNavigationService.get(stationUpdateRequest.getId());
        station.setBusNavigations(busNavigation);
        return stationRepository.save(station);
    }
}
