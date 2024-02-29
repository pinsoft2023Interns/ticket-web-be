package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.entity.Bus;
import com.pinsoft.ticketwebbe.repository.BusRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BusService extends AbstractBaseService<Bus,Long>{

    @Autowired
    private BusRepository busRepository;


    @Override
    protected JpaRepository<Bus, Long> getRepository() {
        return busRepository;
    }
}
