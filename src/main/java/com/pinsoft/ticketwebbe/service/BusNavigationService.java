package com.pinsoft.ticketwebbe.service;

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


    @Override
    protected JpaRepository<BusNavigation, Long> getRepository() {
        return busNavigationRepository;
    }
}
