package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.StationRequest;
import com.pinsoft.ticketwebbe.dto.StationUpdateRequest;
import com.pinsoft.ticketwebbe.entity.BusNavigation;
import com.pinsoft.ticketwebbe.entity.Station;
import com.pinsoft.ticketwebbe.exceptions.ApiRequestException;
import com.pinsoft.ticketwebbe.repository.BusNavigationRepository;
import com.pinsoft.ticketwebbe.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StationService extends AbstractBaseService<Station,Long> {
    @Autowired
    StationRepository stationRepository;

    @Autowired
    BusNavigationService busNavigationService;

    @Autowired
    BusNavigationRepository busNavigationRepository;

    @Override
    protected JpaRepository<Station, Long> getRepository() {
        return stationRepository;
    }

    public Station save(StationRequest stationRequest){
        Station station= new Station();
        station.setName(stationRequest.getName());
        return super.save(station);
    }

    public Station update(StationUpdateRequest stationUpdateRequest){
        Optional<Station> stationRequest = stationRepository.findById(stationUpdateRequest.getId());
        if(stationRequest.isPresent()){
            Station station = stationRepository.findById(stationUpdateRequest.getId()).get();
            station.setName(stationUpdateRequest.getName());
            return stationRepository.save(station);
        }else{
            throw new ApiRequestException("The given id is not exist!");
        }

    }
}
