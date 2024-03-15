package com.pinsoft.ticketwebbe.controller;

import com.pinsoft.ticketwebbe.dto.BusNavStationRequest;
import com.pinsoft.ticketwebbe.entity.BusNavStation;
import com.pinsoft.ticketwebbe.service.BusNavStationService;
import com.pinsoft.ticketwebbe.service.BusNavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class BusNavStationController {
    @Autowired
    BusNavStationService busNavStationService;

    @GetMapping("/busNavStation")
    public Collection<BusNavStation> get(){
        return busNavStationService.listAll();
    }

    @PostMapping("/busNavStation")
    public BusNavStation save(@RequestBody BusNavStationRequest busNavStationRequest){
        return busNavStationService.save(busNavStationRequest);
    }
    @DeleteMapping("/busNavStation/{id}")
    public void delete(@RequestParam Long id){
        busNavStationService.delete(id);
    }
}
