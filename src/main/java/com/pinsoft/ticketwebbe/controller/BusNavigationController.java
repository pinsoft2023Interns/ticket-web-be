package com.pinsoft.ticketwebbe.controller;

import com.pinsoft.ticketwebbe.entity.BusNavigation;
import com.pinsoft.ticketwebbe.service.BusNavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
public class BusNavigationController {
    @Autowired
    BusNavigationService busNavigationService;

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

    //TODO
    /*
    Change requestbody to BusNavigationDto
     */
    @PostMapping("/busnavigation")
    public BusNavigation add(@RequestBody BusNavigation busNavigation) {
        return busNavigationService.save(busNavigation);
    }




}
