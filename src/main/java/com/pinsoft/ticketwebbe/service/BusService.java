package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.entity.Bus;
import com.pinsoft.ticketwebbe.repository.BusRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService extends AbstractBaseService<Bus,Long>{

    @Autowired
    @Getter
    private BusRepository repository;


}
