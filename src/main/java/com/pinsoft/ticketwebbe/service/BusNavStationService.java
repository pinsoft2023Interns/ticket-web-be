package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.BusNavStationRequest;
import com.pinsoft.ticketwebbe.entity.BusNavStation;
import com.pinsoft.ticketwebbe.entity.BusNavigation;
import com.pinsoft.ticketwebbe.entity.Station;
import com.pinsoft.ticketwebbe.exceptions.ApiRequestException;
import com.pinsoft.ticketwebbe.repository.BusNavStationRepository;
import com.pinsoft.ticketwebbe.repository.BusNavigationRepository;
import com.pinsoft.ticketwebbe.repository.StationRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusNavStationService extends AbstractBaseService<BusNavStation,Long> {

    @Getter
    private final BusNavStationRepository busNavStationRepository;

    private final StationRepository stationRepository;

    private final StationService stationService;

    private final BusNavigationRepository busNavigationRepository;

    private final BusNavigationService busNavigationService;

    @Override
    protected JpaRepository<BusNavStation, Long> getRepository(){
        return busNavStationRepository;
    }

    //TODO

    public BusNavStation save(BusNavStationRequest busNavStationRequest){
        BusNavStation busNavStation = new BusNavStation();
        busNavStation.setStationOrder(busNavStationRequest.getStationOrder());
        busNavStation.setArrivalDate(busNavStationRequest.getArrivalDate());
        busNavStation.setDepartureDate(busNavStationRequest.getDepartureDate());
        if(stationRepository.findById(busNavStationRequest.getStationId()).isPresent() &&
            busNavigationRepository.findById(busNavStationRequest.getBusNavigationId()).isPresent()
        ){
            Station station = stationService.get(busNavStationRequest.getStationId());
            busNavStation.setStation(station);
            BusNavigation busNavigation = busNavigationService.get(busNavStationRequest.getBusNavigationId());
            busNavStation.setBusNavigation(busNavigation);
            return save(busNavStation);
        }
        else {
            throw new ApiRequestException("Check BusNavigation id and station id again!");
        }
    }

}
