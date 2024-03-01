package com.pinsoft.ticketwebbe.controller;

import com.pinsoft.ticketwebbe.dto.BusNavigationRequest;
import com.pinsoft.ticketwebbe.entity.Bus;
import com.pinsoft.ticketwebbe.entity.BusNavigation;
import com.pinsoft.ticketwebbe.service.BusNavigationService;
import com.pinsoft.ticketwebbe.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
public class BusNavigationController {
    @Autowired
    BusNavigationService busNavigationService;

    @Autowired
    BusService busService;

    @GetMapping("/busnavigation")
    public Collection<BusNavigation> get(){
        return busNavigationService.listAll();
    }

    @GetMapping("/busnavigation/{id}")
    public BusNavigation get(@PathVariable Long id){
        return busNavigationService.get(id);
    }

    @DeleteMapping("/busnavigation/{id}")
    public void delete(@PathVariable Long id){
        busNavigationService.delete(id);
    }


    @PostMapping("/busnavigation")
    public BusNavigation add(@RequestBody BusNavigationRequest busNavigationRequest) {

        return busNavigationService.save(busNavigationRequest);
    }




}
