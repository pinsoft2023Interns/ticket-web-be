package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.entity.Station;
import com.pinsoft.ticketwebbe.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class StationService extends AbstractBaseService<Station,Long> {
    @Autowired
    StationRepository stationRepository;

    @Override
    protected JpaRepository<Station, Long> getRepository() {
        return stationRepository;
    }
}
